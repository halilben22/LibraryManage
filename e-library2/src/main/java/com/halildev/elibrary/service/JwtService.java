package com.halildev.elibrary.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private final String APP_SECRET = "eyJhbGciOiJIUzI1NiJ9eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTcwNDIzNTQ3NywiaWF0IjoxNzA0MjM1NDc3fQ3_sAFHAM-ANhVQP9arHfzKABvmz2zHk_BE4P1G_Ng14";


    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();


        return createToken(claims, username);
    }


    public String createToken(Map<String, Object> claims, String username) {


        return  Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 6000 * 2))
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key generateKey() {

        byte[] key = Decoders.BASE64URL.decode(APP_SECRET);

        return Keys.hmacShaKeyFor(key);
    }


    public boolean validateToken(String token,UserDetails userDetails) {


        return userDetails.getUsername().equals(extractUsername(token))&&extractExpiration(token).after(new Date());

    }


    public Date extractExpiration(String token) {

        return Jwts.parserBuilder().
                setSigningKey(generateKey())
                .build().
                parseClaimsJws(token).
                getBody().
                getExpiration();

    }


    public String extractUsername(String token) {


        Claims claims= Jwts.parserBuilder().
                setSigningKey(generateKey())
                .build().
                parseClaimsJws(token).
                getBody();

        return claims.getSubject();
    }


    public Claims extractUserObject(String token) {


        return Jwts.parserBuilder().
                setSigningKey(generateKey())
                .build().
                parseClaimsJws(token).
                getBody();
    }


}
