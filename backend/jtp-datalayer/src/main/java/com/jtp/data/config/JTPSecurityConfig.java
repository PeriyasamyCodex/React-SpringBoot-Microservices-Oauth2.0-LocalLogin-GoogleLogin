package com.jtp.data.config;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class JTPSecurityConfig implements WebMvcConfigurer {

    @Autowired
    private RequestLoggingFilter requestLoggingFilter;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedOrigins("*")
                .allowedHeaders("*");

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
         .addFilterBefore(requestLoggingFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/public/**").permitAll() // Public endpoints
                .anyRequest().authenticated()         // Secure all other endpoints
            )
            .oauth2ResourceServer(oauth2 -> oauth2
            .jwt()
            .decoder(jwtDecoder()));
        return http.build();
    }


     @Bean
    public FilterRegistrationBean<RequestLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/*"); // Apply to all URLs
        return registrationBean;
    }
    //  @Bean
    // public JwtAuthenticationConverter jwtAuthenticationConverter() {
    //     JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    //     // Customize JWT authentication converter if needed
    //     return converter;
    // }

    @Bean
public JwtDecoder jwtDecoder() {
  //  return NimbusJwtDecoder.withJwkSetUri("http://localhost:8080/oauth2/jwks").build();

    // String secret = "04ca023b39512e46d0c2cf4b48d5aac61d34302994c87ed4eff225dcf3b0a218739f3897051a057f9b846a69ea2927a587044164b7bae5e1306219d50b588cb1";
    //   SecretKey key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
    //     return NimbusJwtDecoder.withSecretKey(key).build();

    return new CustomJWTDecoder();
}

    //  @Bean
    // public JwtDecoder jwtDecoder() {
    //     // Validator for local issuer
    //     OAuth2TokenValidator<Jwt> localValidator = JwtValidators.createDefaultWithIssuer("http://localhost:8080");
        
    //     // Validator for Google issuer
    //     OAuth2TokenValidator<Jwt> googleValidator = JwtValidators.createDefaultWithIssuer("https://accounts.google.com");

    //     // Combine validators
    //     OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(localValidator, googleValidator);

    //     // Use NimbusJwtDecoder to decode the JWT from either issuer
    //     NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation("http://localhost:8080");
    //     jwtDecoder.setJwtValidator(validator);

    //     return jwtDecoder;
    // }

}
