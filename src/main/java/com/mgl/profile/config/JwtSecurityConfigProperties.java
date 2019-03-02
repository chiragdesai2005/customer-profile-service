package com.mgl.profile.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("jwt.security")
@Getter
@Setter
@Component
public class JwtSecurityConfigProperties {
    private String secret;
    private String header;
    private String headerKey;
    private String errorMessage;
}
