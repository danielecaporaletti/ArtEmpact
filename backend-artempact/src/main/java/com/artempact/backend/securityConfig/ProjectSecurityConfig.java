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
                .cors(cors ->
                        cors.configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.applyPermitDefaultValues();
                            config.setAllowedOrigins(Arrays.asList("*")); // Permette tutte le origini
                            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH")); // Specifica i metodi permessi
                            config.setAllowCredentials(true);
                            config.setAllowedHeaders(Arrays.asList("*")); // Permette tutti gli headers
                            config.setExposedHeaders(Arrays.asList("Authorization"));
                            config.setMaxAge(3600L); // Imposta il max age
                            return config;
                        })
                )
                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/percorso", "/non", "/protetto", "/da", "/un", "/token", "/accessOpen")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(authorize ->
                        authorize
                                // Percorsi per CREATIVI
                                .requestMatchers(
                                        "/profile/creative",
                                        "/profile/creative/projectCreative",
                                        "/profile/creative/creativeCityTarget"
                                ).hasRole("creative")
                                // Percorsi per BUSINESS
                                .requestMatchers(
                                        "/profile/business",
                                        "/profile/business/businessCityTarget"
                                ).hasRole("business")
                                .requestMatchers("/percorsi", "/per", "/creativi","/premium").hasAnyRole("creative","premium")
                                .requestMatchers("/percorsi", "/per", "/business","/premium").hasAnyRole("business","premium")
                                // percorsi con protezione
                                .requestMatchers("/accessClose",
                                        "/educationType",
                                        "/typeOfCreative",
                                        "/workPreference",
                                        "/typeOfBusiness",
                                        "/experienceLevel",
                                        "/professionalRelationship",
                                        "/finalRegistrationStep",

                                        "/profile/business/businessSeeksCreative",

                                        "/profile/creative/creativeSeeksBusiness",
                                        "/profile/creative/creativeSeeksBusiness/creativeSeeksBusinessLocation",

                                        "/profile/creative/creativeSeeksCollaboration",
                                        "/profile/creative/creativeSeeksCollaboration/creativeSeeksCollaborationLocation"

                                ).authenticated()
                                // percorsi senza protezione
                                .requestMatchers("/backendVersion").permitAll()
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




