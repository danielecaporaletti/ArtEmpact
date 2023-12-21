package com.artempact.backend.mysqlKeycloak.controller;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FinalRegistrationStep {

    /*
    Questi attributi sono strettamemnte collegati allo User. Non sono gli attributo del profilo per esempio. Ma ogni User ha i suoi attributi.
    */
    private static class UserAttributes {
        private String birthdate;
        private String city;
        private String phone;
        private String usertype;

        public UserAttributes(String birthdate, String city, String phone, String usertype) {
            this.birthdate = birthdate;
            this.city = city;
            this.phone = phone;
            this.usertype = usertype;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(String birthdate) {
            this.birthdate = birthdate;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }
    }

    /*
    In questa classe vengono:
     - 1) Inserire in keycloak gli attributi per l'utente (city, phone_number, user_type)
     - 2) Assegnare il ruolo da lui scelto
     - 3) Creare l'utente creative o business nel db-artempact con ID, LAT&LONG e ENTITYNAME (id, lat, lon, entityName)
     n.b. entityname deve essere inserito nel UserAttributes
     */


    // Dettagli del client amministratore di Keycloak.
    @Value("${keycloak.server.url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.client.id}")
    private String keycloakClientId;

    @Value("${keycloak.client.secret}")
    private String keycloakClientSecret;

    @PostMapping("/finalRegistrationStep")
    public ResponseEntity changeAttributes(JwtAuthenticationToken auth, @RequestBody UserAttributes newAttributes) {

        // Estrarre l'ID dell'utente
        final var userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);

        // Crea un client amministratore di Keycloak
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakServerUrl)
                .realm(keycloakRealm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(keycloakClientId)
                .clientSecret(keycloakClientSecret)
                .build();

        // Ottenere le informazioni dell'utente da Keycloak utilizzando l'ID utente
        UserResource userResource = keycloak.realm(keycloakRealm).users().get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();

        // ------------------------------------------------------------------------------------
        // 1) inserire in keycloak gli attributi per l'utente (city, phone_number, user_type)

        // Preparare una mappa per aggiornare gli attributi dell'utente
        Map<String, List<String>> attributes = new HashMap<>();
        // Aggiungi la data di nascita dall'attributo "birthdate" del JWT
        attributes.put("birthdate", Collections.singletonList(auth.getToken().getClaimAsString(StandardClaimNames.BIRTHDATE)));
        // Aggiungi altri attributi forniti nel corpo della richiesta
        attributes.put("city", Collections.singletonList(newAttributes.getCity()));
        attributes.put("phone_number", Collections.singletonList(newAttributes.getPhone()));
        attributes.put("user_type", Collections.singletonList(newAttributes.getUsertype()));

        // Imposta gli attributi aggiornati nell'oggetto UserRepresentation
        userRepresentation.setAttributes(attributes);

        // Aggiorna l'utente in Keycloak con i nuovi attributi
        userResource.update(userRepresentation);

        // Stampa di conferma dell'aggiornamento degli attributi
        System.out.println("Updated attributes: " + userRepresentation.getAttributes().toString());

        // ------------------------------------------------------------------------------------
        // 2) Assegnare il ruolo da lui scelto

        // Trova il ruolo specificato
        RoleRepresentation roleRepresentation = keycloak.realm(keycloakRealm).roles().get(newAttributes.getUsertype()).toRepresentation();

        // Aggiungi il ruolo all'utente
        keycloak.realm(keycloakRealm).users().get(userId).roles().realmLevel().add(Arrays.asList(roleRepresentation));

        // Stampa di conferma dell'aggiornamento del ruolo
        System.out.println("Role '" + newAttributes.getUsertype() + "' add with success");

        // ------------------------------------------------------------------------------------
        // 3) Creare l'utente creative o business nel db-artempact con ID, LAT&LONG e ENTITYNAME (id, lat, lon, entityName)


        return new ResponseEntity(HttpStatus.OK);
    }
}
