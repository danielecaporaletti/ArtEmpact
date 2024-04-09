package com.artempact.backend.keycloak.controller;

import com.artempact.backend.artempact.entity.profile.Profile;
import com.artempact.backend.artempact.entity.profile.business.ProfileBusiness;
import com.artempact.backend.artempact.entity.profile.creative.ProfileCreative;
import com.artempact.backend.artempact.repository.repository.profile.business.ProfileBusinessRepository;
import com.artempact.backend.artempact.repository.repository.profile.creative.ProfileCreativeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FinalRegistrationStep {
    @Autowired
    private ProfileCreativeRepository profileCreativeRepository;
    @Autowired
    private ProfileBusinessRepository profileBusinessRepository;

    // Dettagli del client amministratore di Keycloak per poter usare le API amministrative
    @Value("${keycloak.server.url}")
    private String keycloakServerUrl;
    @Value("${keycloak.realm}")
    private String keycloakRealm;
    @Value("${keycloak.client.id}")
    private String keycloakClientId;
    @Value("${keycloak.client.secret}")
    private String keycloakClientSecret;


    // Questi attributi sono strettamemnte collegati al profilo di keycloak. Se vengono modificato in artempact, devono essere modificati anche in keycloak.
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class UserAttributes {
        private String birthdate;
        private String city;
        private String province;
        private String phone;
        private String userType;
        // Nome d'arte (NomeCreativo | NomeBusiness)
        private String stageName;
    }


    // In questa classe vengono:
     //- 1) Inserire in keycloak gli attributi per l'utente (city, province, phone_number, user_type)
     //- 2) Assegnare il ruolo da lui scelto
     //- 3) Creare l'utente creative o business nel mysql-artempact
     // Creative --> (id, email, phone, name, surname, dob, city, province, lat, lon, creativeName)
     // Business --> (...)

    @PostMapping("/finalRegistrationStep")
    public ResponseEntity<?> changeAttributes(JwtAuthenticationToken auth, @RequestBody UserAttributes newAttributes) {

        // Crea un client amministratore di Keycloak
        Keycloak keycloak = getKeycloakClient();

        // Bisognerebbe verificare i campi. Ma in teoria bisognerebbe usare il tema di keycloak per registrarsi e verificare i campi da li.

        // Verificare che il ruolo esista
        if (!isRoleValid(keycloak, newAttributes.getUserType())) {
            return new ResponseEntity<>("Invalid user role", HttpStatus.BAD_REQUEST);
        }

        String id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        if (profileBusinessRepository.existsById(id) || profileCreativeRepository.existsById(id)) {
            return new ResponseEntity<>("Already registered user", HttpStatus.BAD_REQUEST);
        }

        // Aggiorna gli attributi dell'utente in Keycloak e assegna il ruolo
        updateKeycloakUserAttributes(keycloak, id, auth, newAttributes);
        assignRoleToUser(keycloak, auth.getToken().getClaimAsString(StandardClaimNames.SUB), newAttributes.getUserType());

        // Crea il profilo nel database
        return createProfile(auth, newAttributes);
    }

    private Keycloak getKeycloakClient() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakServerUrl)
                .realm(keycloakRealm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(keycloakClientId)
                .clientSecret(keycloakClientSecret)
                .build();
    }

    private boolean isRoleValid(Keycloak keycloak, String userType) {
        try {
            RoleRepresentation role = keycloak.realm(keycloakRealm).roles().get(userType).toRepresentation();
            return role != null;
        } catch (Exception e) {
            return false;
        }
    }

    private void updateKeycloakUserAttributes(Keycloak keycloak, String userId, JwtAuthenticationToken auth, UserAttributes newAttributes) {
        UserResource userResource = keycloak.realm(keycloakRealm).users().get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();

        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("birthdate", Collections.singletonList(auth.getToken().getClaimAsString(StandardClaimNames.BIRTHDATE)));
        attributes.put("city", Collections.singletonList(newAttributes.getCity()));
        attributes.put("province", Collections.singletonList(newAttributes.getProvince()));
        attributes.put("phone_number", Collections.singletonList(newAttributes.getPhone()));
        attributes.put("user_type", Collections.singletonList(newAttributes.getUserType()));

        userRepresentation.setAttributes(attributes);
        userResource.update(userRepresentation);
    }

    private boolean assignRoleToUser(Keycloak keycloak, String userId, String userType) {
        if (!isRoleValid(keycloak, userType)) {
            return false;
        }

        RoleRepresentation roleRepresentation = keycloak.realm(keycloakRealm).roles().get(userType).toRepresentation();
        keycloak.realm(keycloakRealm).users().get(userId).roles().realmLevel().add(Collections.singletonList(roleRepresentation));
        return true;
    }

    private ResponseEntity<?> createProfile(JwtAuthenticationToken auth, UserAttributes newAttributes) {
        String id = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        String email = auth.getToken().getClaimAsString(StandardClaimNames.PREFERRED_USERNAME);
        String phone = newAttributes.getPhone();
        String name = auth.getToken().getClaimAsString(StandardClaimNames.GIVEN_NAME);
        String surname = auth.getToken().getClaimAsString(StandardClaimNames.FAMILY_NAME);
        LocalDate dob = LocalDate.parse(auth.getToken().getClaimAsString(StandardClaimNames.BIRTHDATE));
        String city = newAttributes.getCity();
        String province = newAttributes.getProvince();
        String stageName = newAttributes.getStageName();
        short maxDistance = 9000;

        try {
            Profile newProfile = null;
            if ("creative".equals(newAttributes.getUserType())) {
                ProfileCreative profileCreative = new ProfileCreative();
                profileCreative.setId(id);
                profileCreative.setPhone(phone);
                profileCreative.setEmail(email);
                profileCreative.setName(name);
                profileCreative.setSurname(surname);
                profileCreative.setDob(dob);
                if (profileCreative.getLocality() == null) {
                    profileCreative.setLocality(new Profile.Locality()); // Assicurati che Locality sia inizializzato
                }
                profileCreative.getLocality().setCity(city);
                profileCreative.getLocality().setProvince(province);
                profileCreative.getLocality().setLat(20.0);
                profileCreative.getLocality().setLon(20.0);
                profileCreative.setCreativeName(stageName);
                profileCreative.setMaxDistance(maxDistance);
                newProfile = profileCreative; // Assegna il profileCreative al riferimento di tipo Profile
                profileCreativeRepository.save(profileCreative);
            } else if ("business".equals(newAttributes.getUserType())) {
                ProfileBusiness profileBusiness = new ProfileBusiness();
                profileBusiness.setId(id);
                profileBusiness.setPhone(phone);
                profileBusiness.setEmail(email);
                profileBusiness.setName(name);
                profileBusiness.setSurname(surname);
                profileBusiness.setDob(dob);
                if (profileBusiness.getLocality() == null) {
                    profileBusiness.setLocality(new Profile.Locality()); // Assicurati che Locality sia inizializzato
                }
                profileBusiness.getLocality().setCity(city);
                profileBusiness.getLocality().setProvince(province);
                profileBusiness.getLocality().setLat(20.0);
                profileBusiness.getLocality().setLon(20.0);
                profileBusiness.setBusinessName(stageName);
                profileBusiness.setMaxDistance(maxDistance);
                newProfile = profileBusiness; // Assegna il profileBusiness al riferimento di tipo Profile
                profileBusinessRepository.save(profileBusiness);
            }

            if (newProfile != null) {
                return new ResponseEntity<>(newProfile, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Invalid user type", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
