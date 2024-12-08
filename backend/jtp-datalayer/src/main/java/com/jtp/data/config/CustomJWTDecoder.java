package com.jtp.data.config;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.util.Date;

public class CustomJWTDecoder implements JwtDecoder{

    @Override
    public Jwt decode(String token) throws JwtException {
        try {

            
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey("04ca023b39512e46d0c2cf4b48d5aac61d34302994c87ed4eff225dcf3b0a218739f3897051a057f9b846a69ea2927a587044164b7bae5e1306219d50b588cb1")  // Validate using the same secret key used for signing
                    .build()
                    .parseClaimsJws(token)    // Parse and validate the JWT
                    .getBody();               // Extract the claims
                    System.out.println("***********Custom Decoding......."+claims.getIssuedAt());
                    System.out.println("***********Custom Decoding......."+claims.getExpiration());
                    // Create a Consumer to handle claims
                    Object iatClaim = claims.get("iat");
                    System.out.println("***********iatClaim......."+iatClaim);
                    Instant iat = null;
        
                    if (iatClaim instanceof Integer) {
                        // If 'iat' is a Long (epoch seconds), convert it to Instant
                        iat = Instant.ofEpochSecond((Integer) iatClaim);
                    } else if (iatClaim instanceof Date) {
                        // If 'iat' is a Date object, convert it to Instant
                        iat = ((Date) iatClaim).toInstant();
                    }
        
                    if (iat == null) {
                        throw new JwtException("Invalid 'iat' claim in JWT token.");
                    }
        
                    // Example of extracting other claims
                    String subject = claims.getSubject();
                    Instant expiration = claims.getExpiration().toInstant();
        
                    // Check if the token has expired
                    if (expiration.isBefore(Instant.now())) {
                        throw new JwtException("Token is expired");
                    }
        
                    // Return a custom Jwt object with the claims
                    return Jwt.withTokenValue(token)
                    .header("alg", "HS512") 
                            .subject(subject)
                            .issuedAt(iat)
                            .expiresAt(expiration)
                            .build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JWT token", e);  // Handle token parsing exceptions
        }
    }

}
