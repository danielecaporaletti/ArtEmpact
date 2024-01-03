package com.artempact.backend.securityConfig;

import com.artempact.backend.filter.CsrfCookieFilter;
import com.artempact.backend.mysqlKeycloak.config.KeycloakRoleConverter;
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
                            config.setAllowedOrigins(Collections.singletonList("http://34.16.155.45:3000"));
                            config.setAllowedOrigins(Collections.singletonList("https://34.16.155.45:3000"));
                            config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                            config.setAllowedOrigins(Collections.singletonList("http://localhost:80"));
                            config.setAllowedMethods(Collections.singletonList("*"));
                            config.setAllowCredentials(true);
                            config.setAllowedHeaders(Collections.singletonList("*"));
                            config.setExposedHeaders(Arrays.asList("Authorization"));
                            config.setMaxAge(3600L);
                            return config;
                        })
                )
                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/percorso", "/non", "/protetto", "/da", "/un", "/token")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/percorsi", "/per", "/creativi").hasRole("creative")
                                .requestMatchers("/percorsi", "/per", "/business").hasRole("business")
                                .requestMatchers("/percorsi", "/per", "/creativi","/premium").hasAnyRole("creative","premium")
                                .requestMatchers("/percorsi", "/per", "/business","/premium").hasAnyRole("business","premium")
                                .requestMatchers("/percorso", "/con", "/protezione", "/accessClose",
                                        "/educationType",
                                        "/typeOfCreative",
                                        "/workPreference",
                                        "/typeOfBusiness",
                                        "/experienceLevel",
                                        "/professionalRelationship",
                                        "/finalRegistrationStep",
                                        "/comuni/namesByPrefix",
                                        "/profile/creative",
                                        "/profile/business",
                                        "/project/creative",
                                        "/project/creative/*",
                                        "/users/*"
                                ).authenticated()
                                .requestMatchers("/percorso", "/senza", "/protezione", "/accessOpen").permitAll()
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



