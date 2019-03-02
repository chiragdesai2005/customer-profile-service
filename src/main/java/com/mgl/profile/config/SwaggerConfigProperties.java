package com.mgl.profile.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("swagger")
@Getter
@Setter
@Component
public class SwaggerConfigProperties {
    private String title;
    private String description;
    private String email;
    private String license;
    private String url;
    private String version;
    private String name;

    public String toString(){
        return " Title - "+ title + ", description - "+ description+ ", email-"+email+", license-"+license+ ", url-"+url;
    }
}
