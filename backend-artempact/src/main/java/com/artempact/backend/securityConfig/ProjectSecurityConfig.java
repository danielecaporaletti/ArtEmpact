package com.artempact.backend.securityConfig;

import com.artempact.backend.filter.CsrfCookieFilter;
import com.artempact.backend.keycloak.config.KeycloakRoleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/finalRegistrationStep","/percorso", "/non", "/protetto", "/da", "/un", "/token", "/accessOpen")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(authorize ->
                        authorize
                                // Percorsi per CREATIVI
                                .requestMatchers(
                                        ""
                                ).hasRole("creative")
                                // Percorsi per BUSINESS
                                .requestMatchers(
                                        ""
                                ).hasRole("business")
                                .requestMatchers("/percorsi", "/per", "/creativi","/premium").hasAnyRole("creative","premium")
                                .requestMatchers("/percorsi", "/per", "/business","/premium").hasAnyRole("business","premium")
                                // percorsi con protezione
                                .requestMatchers("/accessClose",
                                        "/profile/creative",
                                        "/profile/creative/projectCreative",
                                        "/profile/creative/creativeCityTarget",

                                        "/profile/business",
                                        "/profile/business/businessCityTarget",

                                        "/educationType",
                                        "/typeOfCreative",
                                        "/workPreference",
                                        "/typeOfBusiness",
                                        "/experienceLevel",
                                        "/professionalRelationship",

                                        "/profile/business/businessSeeksCreative",

                                        "/profile/creative/creativeSeeksBusiness",
                                        "/profile/creative/creativeSeeksBusiness/creativeSeeksBusinessLocation",

                                        "/profile/creative/creativeSeeksCollaboration",
                                        "/profile/creative/creativeSeeksCollaboration/creativeSeeksCollaborationLocation",

                                        "/match/nextCardCreative",
                                        "/match/nextCardBusiness",
                                        "/match/nextCard",
                                        "/match/resultCompatibility"

                                ).authenticated()
                                // percorsi senza protezione
                                .requestMatchers("/backendVersion","/finalRegistrationStep").permitAll()
                )
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer
                                .jwt(jwt ->
                                        jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)
                                )
                );

        return http.build();
    }
}




