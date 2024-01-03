package com.artempact.backend.mysqlKeycloak.controller;


import java.time.LocalDate;
import java.util.Map;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetUserAttributes {

    @GetMapping("/userAttributes")
    public ResponseEntity getEndpoint(JwtAuthenticationToken auth) {
        final var preferredUsername = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        final var subject = auth.getToken().getClaimAsString(StandardClaimNames.PREFERRED_USERNAME);
        final var BIRTHDATE = auth.getToken().getClaimAsString(StandardClaimNames.BIRTHDATE);
        final var UPDATED_AT = auth.getToken().getClaimAsString(StandardClaimNames.UPDATED_AT);
        final var GENDER = auth.getToken().getClaimAsString(StandardClaimNames.GENDER);
        final var PROFILE = auth.getToken().getClaimAsString(StandardClaimNames.PROFILE);
        final var ZONEINFO = auth.getToken().getClaimAsString(StandardClaimNames.ZONEINFO);
        final var WEBSITE = auth.getToken().getClaimAsString(StandardClaimNames.WEBSITE);
        final var LOCALE = auth.getToken().getClaimAsString(StandardClaimNames.LOCALE);
        final var MIDDLE_NAME = auth.getToken().getClaimAsString(StandardClaimNames.MIDDLE_NAME);
        final var GIVEN_NAME = auth.getToken().getClaimAsString(StandardClaimNames.GIVEN_NAME);
        final var PICTURE = auth.getToken().getClaimAsString(StandardClaimNames.PICTURE);
        final var FAMILY_NAME = auth.getToken().getClaimAsString(StandardClaimNames.FAMILY_NAME);
        final var NICKNAME = auth.getToken().getClaimAsString(StandardClaimNames.NICKNAME);
        final var PHONE_NUMBER = auth.getToken().getClaimAsString(StandardClaimNames.PHONE_NUMBER);
        final var PHONE_NUMBER_VERIFIED = auth.getToken().getClaimAsString(StandardClaimNames.PHONE_NUMBER_VERIFIED);
        final var ADDRESS = auth.getToken().getClaimAsString(StandardClaimNames.ADDRESS);
        final var city = auth.getToken().getClaimAsString("city");
        final var phoneNumber = auth.getToken().getClaimAsString("phoneNumber");

        System.out.println("ID" + preferredUsername);
        System.out.println("EMAIL" + subject);
        System.out.println("BIRTHDATE" + BIRTHDATE);
        System.out.println("UPDATED_AT" + UPDATED_AT);
        System.out.println("GENDER" + GENDER);
        System.out.println("PROFILE" + PROFILE);
        System.out.println("ZONEINFO" + ZONEINFO);
        System.out.println("WEBSITE" + WEBSITE);
        System.out.println("LOCALE" + LOCALE);
        System.out.println("MIDDLE_NAME" + MIDDLE_NAME);
        System.out.println("GIVEN_NAME" + GIVEN_NAME); // Nome
        System.out.println("PICTURE" + PICTURE);
        System.out.println("FAMILY_NAME" + FAMILY_NAME);
        System.out.println("NICKNAME" + NICKNAME);
        System.out.println("PHONE_NUMBER" + PHONE_NUMBER);
        System.out.println("PHONE_NUMBER_VERIFIED" + PHONE_NUMBER_VERIFIED);
        System.out.println("city" + city);
        System.out.println("phone_number" + phoneNumber);
        System.out.println("ADDRESS: " + ADDRESS);

        return new ResponseEntity(HttpStatus.OK);
    }
    
}
