package com.mgl.profile.security;

import com.google.gson.Gson;
import com.mgl.profile.config.JwtSecurityConfigProperties;
import com.mgl.profile.exception.ErrorDetails;
import com.mgl.profile.exception.JwtNotFoundException;
import com.mgl.profile.model.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private JwtSecurityConfigProperties jwtSecurityConfigProperties;

    @Autowired
    private Gson gson;

    public JwtAuthenticationTokenFilter() {
        super("/**/profile/**");
    }

    @Override
    // validate token here
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse response) throws AuthenticationException, IOException, ServletException, JwtNotFoundException {

        String header = httpServletRequest.getHeader(jwtSecurityConfigProperties.getHeaderKey());

        if (header == null || !header.startsWith(jwtSecurityConfigProperties.getHeader())) {
            ErrorDetails errorDetails = new ErrorDetails(new Date(), jwtSecurityConfigProperties.getErrorMessage(), jwtSecurityConfigProperties.getErrorMessage());
            response.setStatus(UNAUTHORIZED.value());
            response.getWriter().write(gson.toJson(errorDetails));
            return null;
        } else {
            String authenticationToken = header.substring(6);
            JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
            return getAuthenticationManager().authenticate(token);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}