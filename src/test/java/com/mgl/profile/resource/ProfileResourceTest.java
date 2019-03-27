package com.mgl.profile.resource;

import com.google.gson.Gson;
import com.mgl.profile.exception.ErrorDetails;
import com.mgl.profile.model.JwtUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
public class ProfileResourceTest {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Gson gson;


    @Test
    /**
     *  Test case - API call without Jwt
     *  Expect unauthorized status code
     */
    public void testGetProfileWithoutJWT() {
        this.webClient.get().uri("/customer-profile/services/v1/profile/1").exchange().expectStatus().isUnauthorized();
    }

    @Test
    /**
     *  Test case - API call with Jwt
     *  Expect 404, Record not found
     */
    public void testGetProfile() {
        // 1 - get JWT token and call get API
        HttpEntity<JwtUser> request = new HttpEntity<>(new JwtUser(123L,"chirag","admin"));
        String token = restTemplate.postForObject("/v1/token", request, String.class);
        System.out.println("------------------------");
        System.out.println(token);
        System.out.println("------------------------");
        assertThat(token).isNotNull();
        // 2 - validate response and Http status code
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);
        headers.set("Content-Type", "application/json");
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange("/v1/profile/1", GET,entity,String.class);
        String body = response.getBody();
        assertThat(body).isNotNull();
        System.out.println("-------------------------");
        System.out.println(response);
        System.out.println("-------------------------");
        ErrorDetails errorDetails = gson.fromJson(body, ErrorDetails.class);
        assertThat(errorDetails).isNotNull();
        assertThat(errorDetails.getMessage()).isNotNull();
        assertThat(errorDetails.getMessage()).isEqualTo("Record not found for given id 1");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}