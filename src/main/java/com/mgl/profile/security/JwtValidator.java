package com.mgl.profile.security;

import com.mgl.profile.config.JwtSecurityConfigProperties;
import com.mgl.profile.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtValidator {

    @Autowired
    private JwtSecurityConfigProperties jwtSecurityConfigProperties;

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(jwtSecurityConfigProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            log.error("Error encountered during validate JWT {}",e);
        }

        return jwtUser;
    }
}
