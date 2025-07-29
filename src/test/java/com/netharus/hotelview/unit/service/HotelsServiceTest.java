package com.netharus.hotelview.unit.service;

import com.netharus.hotelview.domain.Amenity;
import com.netharus.hotelview.domain.Hotels;
import com.netharus.hotelview.dto.request.HotelCreateDto;
import com.netharus.hotelview.dto.response.FullHotelResponseDto;
import com.netharus.hotelview.dto.response.HotelResponseDto;
import com.netharus.hotelview.exceptions.HotelNotFoundException;
import com.netharus.hotelview.mapper.HotelMapper;
import com.netharus.hotelview.repository.HotelsRepository;
import com.netharus.hotelview.service.impl.HotelsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.netharus.hotelview.util.UnitTestUtils.getFullHotelResponseDto;
import static com.netharus.hotelview.util.UnitTestUtils.getHotelCreateDto;
import static com.netharus.hotelview.util.UnitTestUtils.getHotelResponseDto;
import static com.netharus.hotelview.util.UnitTestUtils.getHotels;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
public class HotelsServiceTest {

    @Spy
    private HotelMapper hotelMapper = Mappers.getMapper(HotelMapper.class);

    @Mock
    private HotelsRepository hotelsRepository;

    @InjectMocks
    private HotelsServiceImpl hotelsService;

    @Nested
    @DisplayName("Test get all hotels")
    class GetAllHotelsTest {
        @Test
        void whenFindAllHotels_thenReturnListOfHotelsResponseDto() {
            // Arrange
            List<HotelResponseDto> expectedHotelsList = List.of(getHotelResponseDto());
            List<Hotels> hotelsList = List.of(getHotels());

            when(hotelsRepository.findAll()).thenReturn(hotelsList);

            // Act
            List<HotelResponseDto> actualHotelsResponse = hotelsService.getHotelDtoList();

            // Assert
            assertEquals(expectedHotelsList, actualHotelsResponse);
            verify(hotelsRepository, times(1)).findAll();
            verify(hotelMapper, times(1)).fromHotelListToHotelDtoList(hotelsList);
        }
    }

    @Nested
    @DisplayName("Test get hotel by id")
    class GetHotelByIdTest {
        @Test
        void givenHotelId_whenFindHotelById_thenReturnFullHotelResponseDto() {
            // Arrange
            Hotels hotel = getHotels();
            FullHotelResponseDto expectedResponse = getFullHotelResponseDto();

            when(hotelsRepository.findById(hotel.getId())).thenReturn(Optional.of(hotel));

            // Act
            FullHotelResponseDto actualResponse = hotelsService.findHotelById(hotel.getId());

            // Assert
            assertEquals(expectedResponse, actualResponse);
            verify(hotelsRepository, times(1)).findById(hotel.getId());
            verify(hotelMapper, times(1)).fromHotelToFullHotelDto(hotel);
        }

        @Test
        void givenHotelId_whenFindHotelById_thenReturnHotelNotFoundException() {
            // Arrange
            Long id = 1L;

            // Act & assert
            assertThrows(HotelNotFoundException.class, () -> hotelsService.findHotelById(id));
            verify(hotelsRepository, times(1)).findById(id);
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

            List<Hotels> hotelsList = List.of(getHotels());
            List<HotelResponseDto> expectedDtoList = List.of(getHotelResponseDto());

            when(hotelsRepository.findAll(any(Specification.class))).thenReturn(hotelsList);
            // Act
            List<HotelResponseDto> actualDtoList = hotelsService.searchHotels(name, brand, city, country, amenities);

            // Assert
            assertEquals(expectedDtoList, actualDtoList);
            verify(hotelsRepository, times(1)).findAll(any(Specification.class));
            verify(hotelMapper, times(1)).fromHotelListToHotelDtoList(hotelsList);
        }
    }

    @Nested
    @DisplayName("Test add new hotel")
    class addNewHotel {
        @Test
        void givenHotelCreateDto_whenAddNewHotel_thenReturnHotelResponseDto() {
            // Arrange
            HotelCreateDto hotelCreateDto = getHotelCreateDto();
            HotelResponseDto expectedResponse = getHotelResponseDto();
            Hotels hotel = getHotels();
            when(hotelsRepository.save(any())).thenReturn(hotel);

            // Act
            HotelResponseDto actualResponse = hotelsService.add(hotelCreateDto);

            // Assert
            assertEquals(expectedResponse, actualResponse);
            verify(hotelsRepository, times(1)).save(any());
        }
    }

    @Nested
    @DisplayName("Test add amenities to hotel")
    class addAmenitiesToHotel {
        @Test
        void givenHotelIdAndListAmenities_whenAddAmenitiesToHotel_thenReturnHotelResponseDto() {
            // Arrange
            Hotels hotel = getHotels();
            List<Amenity> amenities = new ArrayList<>();

            when(hotelsRepository.findById(hotel.getId())).thenReturn(Optional.of(hotel));

            // Act
            hotelsService.addAmenitiesById(hotel.getId(), amenities);

            // Assert
            verify(hotelsRepository, times(1)).findById(hotel.getId());
            verify(hotelsRepository, times(1)).save(any());
        }

        @Test
        void givenHotelIdAndListAmenities_whenAddAmenitiesToHotel_thenReturnHotelNotFoundException() {
            // Arrange
            Long id = 1L;
            List<Amenity> amenities = new ArrayList<>();

            // Act & assert
            assertThrows(HotelNotFoundException.class, () -> hotelsService.addAmenitiesById(id, amenities));
            verify(hotelsRepository, times(1)).findById(id);
        }
    }
}
