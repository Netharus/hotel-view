package com.netharus.hotelview.integration;

import com.netharus.hotelview.dto.response.ErrorResponse;
import com.netharus.hotelview.dto.response.FullHotelResponseDto;
import com.netharus.hotelview.dto.response.HotelResponseDto;
import com.netharus.hotelview.repository.HotelsRepository;
import com.netharus.hotelview.service.HotelsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.netharus.hotelview.util.UnitTestUtils.getHotelCreateDto;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelsControllerIntegrationTest {

    private final String BASE_URL = "/property-view/hotels";

    @Autowired
    private HotelsRepository hotelsRepository;

    @Autowired
    private HotelsService hotelsService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private Long hotelId;

    @BeforeEach
    void setUp() {
        hotelsRepository.deleteAll();
        HotelResponseDto responseDto = hotelsService.add(getHotelCreateDto());
        hotelId = responseDto.id();
    }

    @Nested
    @DisplayName("Test get all hotels")
    class GetAllHotelsTest {
        @Test
        void whenFindAllHotels_thenReturnListOfHotelsResponseDto() {
            // Act
            List<HotelResponseDto> responseDtos = testRestTemplate.getForObject(BASE_URL, List.class);

            //Assert
            assertThat(responseDtos).isNotNull();
            assertThat(responseDtos.size()).isEqualTo(1);
        }
    }


    @Nested
    @DisplayName("Test get hotel by id")
    class GetHotelByIdTest {
        @Test
        void givenHotelId_whenFindHotelById_thenReturnFullHotelResponseDto() {
            // Act
            FullHotelResponseDto fullHotelResponseDto = testRestTemplate
                    .getForObject(BASE_URL + "/" + hotelId, FullHotelResponseDto.class);

            // Assert
            assertThat(fullHotelResponseDto).isNotNull();
            assertThat(fullHotelResponseDto.id()).isEqualTo(hotelId);
        }

        @Test
        void givenHotelId_whenFindHotelById_thenReturnNotFoundException() {
            // Act
            ResponseEntity<ErrorResponse> response = testRestTemplate
                    .getForEntity(BASE_URL + "/" + (hotelId + 1), ErrorResponse.class);

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            assertThat(response.getBody()).isNotNull();
        }
    }

    @Nested
    @DisplayName("Test search hotels")
    class searchHotels {
        @Test
        void givenSearchParams_whenSearchHotels_thenReturnFilteredHotelResponseDtoList() {
            // Arrange
            String name = "Test";
            String brand = "Brand";
            String city = "City";
            String country = "Country";
            List<String> amenities = List.of("WiFi", "Parking");

            String url = UriComponentsBuilder
                    .fromUriString(BASE_URL)
                    .queryParam("name", name)
                    .queryParam("brand", brand)
                    .queryParam("city", city)
                    .queryParam("country", country)
                    .queryParam("amenities", amenities.toArray())
                    .toUriString();

            // Act
            ResponseEntity<List> response = testRestTemplate.getForEntity(url, List.class);

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().size()).isGreaterThan(0);
        }
    }


    @Nested
    @DisplayName("Test add new hotel")
    class addNewHotel {

        @Test
        void givenHotelCreateDto_whenAddNewHotel_thenReturnHotelResponseDto() {
            // Arrange
            hotelsRepository.deleteAll();

            // Act
            ResponseEntity<HotelResponseDto> response = testRestTemplate
                    .postForEntity(BASE_URL, getHotelCreateDto(), HotelResponseDto.class);

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            assertThat(response.getBody()).isNotNull();
        }

        @Test
        void givenHotelCreateDto_whenAddNewHotel_thenReturnDuplicateKeyError() {
            // Act
            ResponseEntity<ErrorResponse> response = testRestTemplate
                    .postForEntity(BASE_URL, getHotelCreateDto(), ErrorResponse.class);

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            assertThat(response.getBody()).isNotNull();
        }
    }

    @Nested
    @DisplayName("Test add amenities to hotel")
    class addAmenitiesToHotel {
        @Test
        void givenHotelIdAndListAmenities_whenAddAmenitiesToHotel_thenReturnHotelResponseDto() {
            // Arrange
            List<String> amenities = List.of("WiFi", "Parking");

            // Act
            ResponseEntity<Void> response = testRestTemplate
                    .postForEntity(BASE_URL + "/" + hotelId + "/amenities", amenities, Void.class);

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void givenHotelIdAndListAmenities_whenAddAmenitiesToHotel_thenReturnHotelNotFoundException() {
            // Arrange
            List<String> amenities = List.of("WiFi", "Parking");

            // Act
            ResponseEntity<ErrorResponse> response = testRestTemplate
                    .postForEntity(BASE_URL + "/" + (hotelId + 1) + "/amenities", amenities, ErrorResponse.class);

            // Assert
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            assertThat(response.getBody()).isNotNull();
        }

    }
}
