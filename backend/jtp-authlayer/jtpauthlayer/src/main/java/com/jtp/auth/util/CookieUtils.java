package com.jtp.auth.util;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.Optional;

import org.springframework.util.SerializationUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtils {

    public static Optional<Cookie> getCookie(HttpServletRequest httpServletRequest, String name) {

        Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie);
                }
            }
        }

        return Optional.empty();
    }

    public static void addCookie(HttpServletResponse httpServletResponse, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        httpServletResponse.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            String name) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    httpServletResponse.addCookie(cookie);
                }
            }
        }

    }

    public static String serialize(Object object) {
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> clazz) {
        try (ByteArrayInputStream byteIn = new ByteArrayInputStream(Base64.getUrlDecoder().decode(cookie.getValue()));
                ObjectInputStream objIn = new ObjectInputStream(byteIn)) {
            Object obj = objIn.readObject();
            if (clazz.isInstance(obj)) {
                return clazz.cast(obj);
            } else {
                throw new ClassCastException("Cannot cast object to " + clazz.getName());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize object", e);
        }
    }
    
     private static final String SECRET_KEY = "04ca023b39512e46d0c2cf4b48d5aac61d34302994c87ed4eff225dcf3b0a218739f3897051a057f9b846a69ea2927a587044164b7bae5e1306219d50b588cb1"; // The key used to sign the JWT

    public static void main(String[] args) {
        String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDIiLCJpYXQiOjE3MzM1ODg5OTUsImV4cCI6MTczNDQ1Mjk5NX0.6LaJljIvMF209-jjghTJFUxUXn14-GYfQ2bw7IYaZIDeKPCKpUtlellJUa9wiqXBCpsivxDHvV4kk61nM7c_jw"; // Paste your JWT here

        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // Use the same secret key to validate
                .build()
                .parseClaimsJws(jwt)
                .getBody();

            System.out.println("JWT is valid!");
            System.out.println("Subject: " + claims.getSubject());
        } catch (Exception e) {
            System.out.println("Invalid JWT: " + e.getMessage());
        }
    }

}
