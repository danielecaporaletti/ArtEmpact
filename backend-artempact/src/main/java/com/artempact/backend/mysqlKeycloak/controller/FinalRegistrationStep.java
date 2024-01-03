package com.artempact.backend.mysqlKeycloak.controller;

import com.artempact.backend.mysqlArtempact.entity.profile.Profile;
import com.artempact.backend.mysqlArtempact.entity.profile.profileBusiness.ProfileBusinessService;
import com.artempact.backend.mysqlArtempact.entity.profile.profileCreative.ProfileCreativeService;
import com.artempact.backend.mysqlArtempact.repository.profile.profileBusiness.ProfileBusinessRepository;
import com.artempact.backend.mysqlArtempact.repository.profile.profileCreative.ProfileCreativeRepository;
import com.artempact.backend.mysqlGeographic.reposity.ComuneRepository;
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
    private ProfileCreativeService profileCreativeService;
    @Autowired
    private ProfileBusinessService profileBusinessService;
    @Autowired
    private ProfileCreativeRepository profileCreativeRepository;
    @Autowired
    private ProfileBusinessRepository profileBusinessRepository;
    @Autowired
    private ComuneRepository comuneRepository;

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

        // Verificare che city e province esistano
        if (!isValidLocation(newAttributes.getCity(), newAttributes.getProvince())) {
            return new ResponseEntity<>("Invalid city or province", HttpStatus.BAD_REQUEST);
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

    private boolean isValidLocation(String city, String province) {
        return !comuneRepository.findLatLonByCittaAndProvincia(city, province).isEmpty();
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

        if (!isValidLocation(city, province)) {
            return new ResponseEntity<>("Invalid city or province", HttpStatus.BAD_REQUEST);
        }

        try {
            Profile newProfile = null;
            if ("creative".equals(newAttributes.getUserType())) {
                newProfile = profileCreativeService.createProfile(id, email, phone, name, surname, dob, city, province, stageName);
            } else if ("business".equals(newAttributes.getUserType())) {
                newProfile = profileBusinessService.createProfile(id, email, phone, name, surname, dob, city, province, stageName);
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
