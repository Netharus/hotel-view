package com.netharus.hotelview.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HistogramControllerIntegrationTest {

    private final String BASE_URL = "/property-view/histogram";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Nested
    @DisplayName("Test histogram endpoint")
    class HistogramEndpointTests {
        @Test
        void givenHistogramParam_whenGetHistogram_thenReturnHistogramMap() {
            // Act
            ResponseEntity<Map> response = testRestTemplate.getForEntity(BASE_URL + "/brand", Map.class);

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotNull();
        }

        @Test
        void givenWrongHistogramParam_whenGetHistogram_thenReturnBadRequest() {
            // Act
            ResponseEntity<Map> response = testRestTemplate.getForEntity(BASE_URL + "/" + UUID.randomUUID(), Map.class);

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            assertThat(response.getBody()).isNotNull();
        }
    }
}
