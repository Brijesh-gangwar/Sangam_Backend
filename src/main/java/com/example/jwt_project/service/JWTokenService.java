package com.example.jwt_project.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.jsonwebtoken.io.Decoders.BASE64;

@Service
public class JWTokenService {
    private String secretkey= "";

    public  JWTokenService(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");

         SecretKey sk = keyGenerator.generateKey();
         secretkey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generatetoken(String username) {

        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder().claims().add(claims).subject(username).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() +60*60*30)).and().signWith(getkey()).compact();


    }

    private SecretKey getkey() {

        byte[] keybytes = BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keybytes);
    }


    public String extractusername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean validatetoken(String token, UserDetails userDetails) {
        final String username = extractusername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getkey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }



}

