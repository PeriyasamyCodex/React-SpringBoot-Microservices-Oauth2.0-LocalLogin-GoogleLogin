package com.jtp.data.config;


import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;

public class Builder {
 private String tokenValue;
  
  private final Map<String, Object> claims = new LinkedHashMap<>();
  
  private final Map<String, Object> headers = new LinkedHashMap<>();
  
  public Builder(String tokenValue) {
    this.tokenValue = tokenValue;
  }
  
  public Builder tokenValue(String tokenValue) {
    this.tokenValue = tokenValue;
    return this;
  }
  
  public Builder claim(String name, Object value) {
    System.out.println("iat ->"+name+" - "+(value instanceof Instant));
    this.claims.put(name, value);
    return this;
  }
  
  public Builder claims(Consumer<Map<String, Object>> claimsConsumer) {
    claimsConsumer.accept(this.claims);
    return this;
  }
  
  public Builder header(String name, Object value) {
    this.headers.put(name, value);
    return this;
  }
  
  public Builder headers(Consumer<Map<String, Object>> headersConsumer) {
    headersConsumer.accept(this.headers);
    return this;
  }
  
  public Builder audience(Collection<String> audience) {
    return claim("aud", audience);
  }
  
  public Builder expiresAt(Instant expiresAt) {
    claim("exp", expiresAt);
    return this;
  }
  
  public Builder jti(String jti) {
    claim("jti", jti);
    return this;
  }
  
  public Builder issuedAt(Instant issuedAt) {
    claim("iat", issuedAt);
    return this;
  }
  
  public Builder issuer(String issuer) {
    claim("iss", issuer);
    return this;
  }
  
  public Builder notBefore(Instant notBefore) {
    claim("nbf", notBefore);
    return this;
  }
  
  public Builder subject(String subject) {
    claim("sub", subject);
    return this;
  }
  
  public Jwt build() {
    System.out.println("iat ->"+(this.claims.get("iat") instanceof Instant));
    System.out.println("exp ->"+this.claims.get("exp"));
    Instant iat = toInstant(this.claims.get("iat"));
    Instant exp = toInstant(this.claims.get("exp"));
    return new Jwt(this.tokenValue, iat, exp, this.headers, this.claims);
  }
  
  private Instant toInstant(Object timestamp) {
    if (timestamp != null)
      Assert.isInstanceOf(Instant.class, timestamp, "timestamps must be of type Instant"); 
    return (Instant)timestamp;
  }
}
