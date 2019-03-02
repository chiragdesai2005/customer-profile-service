package com.mgl.profile.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
public class ProfileResourceTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    /**
     *  Test case - API call without Jwt
     *  Expect unauthorized status code
     */
    public void testGetProfileWithoutJWT() {
        this.webClient.get().uri("/customer-profile/services/v1/profile/1").exchange().expectStatus().isUnauthorized();
    }

    @Test
    public void testGetProfile() {
        // 1 - get JWT token and call get API
        // 2 - validate response and Http status code
    }
}