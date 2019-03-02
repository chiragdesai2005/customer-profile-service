package com.mgl.profile.security;

import com.mgl.profile.config.JwtSecurityConfigProperties;
import com.mgl.profile.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Component
public class JwtGenerator {

    @Autowired
    private JwtSecurityConfigProperties jwtSecurityConfigProperties;

    public String generate(JwtUser jwtUser) {
        Claims claims = Jwts.claims().setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());


        return Jwts.builder()
                .setClaims(claims)
                //.setExpiration() for sample appl. not setting this value
                .signWith(HS512, jwtSecurityConfigProperties.getSecret())
                .compact();
    }
}
