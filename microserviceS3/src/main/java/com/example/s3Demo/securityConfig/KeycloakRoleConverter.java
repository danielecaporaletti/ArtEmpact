package com.example.s3Demo.securityConfig;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Object realmAccessObj = jwt.getClaims().get("realm_access");

        if (realmAccessObj instanceof Map) {
            @SuppressWarnings("unchecked") // Suppress the warning for this specific cast
            Map<String, Object> realmAccess = (Map<String, Object>) realmAccessObj;

            if (realmAccess.isEmpty()) {
                return new ArrayList<>();
            }

            Object rolesObj = realmAccess.get("roles");
            if (rolesObj instanceof List) {
                @SuppressWarnings("unchecked") // Suppress the warning for this specific cast
                List<String> roles = (List<String>) rolesObj;

                // Return the result directly without using a local variable
                return roles.stream()
                        .map(roleName -> "ROLE_" + roleName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            }
        }

        return new ArrayList<>();
    }
}

