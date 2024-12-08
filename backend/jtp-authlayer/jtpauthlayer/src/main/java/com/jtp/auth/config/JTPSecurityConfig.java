package com.jtp.auth.config;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.jtp.auth.security.CustomOauth2UserService;
import com.jtp.auth.security.RestAuthenticationEntryPoint;
import com.jtp.auth.security.TokenAuthenticationFilter;
import com.jtp.auth.security.oauth2.HttpCookieOAuth2AuthRegReqRepo;
import com.jtp.auth.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.jtp.auth.security.oauth2.OAuth2AuthenticationSuccessHandler;
import com.jtp.auth.service.UserDetailsServiceImpl;
import static 
org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console; 

@Configuration
@EnableWebSecurity
public class JTPSecurityConfig{

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    HttpCookieOAuth2AuthRegReqRepo auth2AuthRegReqRepo;


    @Autowired
    private CustomOauth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Autowired
    private HttpCookieOAuth2AuthRegReqRepo auth2AuthRegReqRepo2;
    
     @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

 

   

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        String[] allowedPaths = { "/","/.well-known/**","/error","/auth/**","/login", "/signup", "/oauth2/authorize", "/oauth2/token", "/oauth2/jwks" };

       
        httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(sessionMgmt -> sessionMgmt.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                
                .formLogin(form -> form // Enable form login and customize if needed
                        .disable() // Allow access to the login page without authentication
                )
                .headers(headers -> headers.frameOptions(frameOptions -> 
                frameOptions.sameOrigin()
                ))
                .httpBasic(httpBasic -> httpBasic.disable())
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new RestAuthenticationEntryPoint()))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
                        .permitAll()
                        .requestMatchers(allowedPaths)
                        .permitAll()
                        .anyRequest().authenticated() // Protect all other endpoints
                )
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(authEndPoint -> authEndPoint
                                .baseUri("/oauth2/authorize")
                                .authorizationRequestRepository(auth2AuthRegReqRepo2))                        
                        .redirectionEndpoint(redEndPoint -> redEndPoint
                                .baseUri("/oauth2/callback/*"))
                        .userInfoEndpoint(userInfoEndPoint -> userInfoEndPoint
                                .userService(customOAuth2UserService))
                        .successHandler(oAuth2AuthenticationSuccessHandler)
                        .failureHandler(oAuth2AuthenticationFailureHandler))
                
                        .csrf(csrf -> csrf.disable());
                        

                httpSecurity.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
               
        return httpSecurity.build();

    }

     @Bean
    public JwtDecoder jwtDecoder() {
        // Use Google's JWK Set URL for JWT validation
        return JwtDecoders.fromIssuerLocation("https://accounts.google.com");
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // Customize JWT authentication converter if needed
        return converter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000","http://localhost:9003", "http://localhost:9002")); // Allowed origins
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed methods
        configuration.setAllowedHeaders(List.of("*")); // Allow all headers
        configuration.setAllowCredentials(true); // Allow cookies for authentication

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply CORS settings to all endpoints
        return source;  
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsService userDetailsService)
            throws Exception {
        // Use the AuthenticationManagerBuilder to configure authentication
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        // Return the AuthenticationManager directly without calling build()
        return authenticationManagerBuilder.getOrBuild();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCryptPasswordEncoder for password encoding
        return new BCryptPasswordEncoder();
    }
}