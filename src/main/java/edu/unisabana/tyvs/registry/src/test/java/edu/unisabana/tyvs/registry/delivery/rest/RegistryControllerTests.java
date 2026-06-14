package edu.unisabana.tyvs.registry.delivery.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistryControllerTests {

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void shouldRegisterValidPerson() {
        String json =
                "{\"name\":\"Ana\",\"id\":100,\"age\":30,\"gender\":\"FEMALE\",\"alive\":true}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response =
                rest.postForEntity(
                        "/register",
                        new HttpEntity<>(json, headers),
                        String.class
                );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("VALID", response.getBody());
    }

    @Test
    public void shouldReturn400ForInvalidGender() {
        String json =
                "{\"name\":\"Laura\",\"id\":200,\"age\":20,\"gender\":\"OTHER\",\"alive\":true}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response =
                rest.postForEntity(
                        "/register",
                        new HttpEntity<>(json, headers),
                        String.class
                );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Invalid gender"));
    }

    @Test
    public void shouldReturn400ForNegativeAge() {
        String json =
                "{\"name\":\"Juan\",\"id\":300,\"age\":-5,\"gender\":\"MALE\",\"alive\":true}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response =
                rest.postForEntity(
                        "/register",
                        new HttpEntity<>(json, headers),
                        String.class
                );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}