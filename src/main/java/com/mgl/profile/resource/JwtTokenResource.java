package com.mgl.profile.resource;

import com.mgl.profile.model.JwtUser;
import com.mgl.profile.security.JwtGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/v1/token")
@Slf4j
@Api(value = "Token", description = "JWT token service", produces = "application/json", consumes = "application/json")
public class JwtTokenResource {

    private JwtGenerator jwtGenerator;

    @Autowired
    public JwtTokenResource(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    @ApiOperation(value = "Retrieve token to access profile APIs", consumes = APPLICATION_JSON_VALUE, response = String.class)
    public String generate(@Valid @RequestBody final JwtUser jwtUser) {

        return jwtGenerator.generate(jwtUser);

    }
}
