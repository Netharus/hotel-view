package com.netharus.hotelview.util;

import com.netharus.hotelview.domain.Address;
import com.netharus.hotelview.domain.Amenity;
import com.netharus.hotelview.domain.ArrivalTime;
import com.netharus.hotelview.domain.Contact;
import com.netharus.hotelview.domain.Hotels;
import com.netharus.hotelview.dto.request.AddressCreateDto;
import com.netharus.hotelview.dto.request.ArrivalTimeCreateDto;
import com.netharus.hotelview.dto.request.ContactCreateDto;
import com.netharus.hotelview.dto.request.HotelCreateDto;
import com.netharus.hotelview.dto.response.AddressResponseDto;
import com.netharus.hotelview.dto.response.ArrivalTimeResponseDto;
import com.netharus.hotelview.dto.response.ContactResponseDto;
import com.netharus.hotelview.dto.response.FullHotelResponseDto;
import com.netharus.hotelview.dto.response.HotelResponseDto;

import java.util.List;
import java.util.Set;

public class UnitTestUtils {
    private UnitTestUtils() {
    }

    public static AddressResponseDto getAddressResponseDto() {
        return AddressResponseDto.builder()
                .houseNumber(1)
                .street("Street")
                .city("City")
                .country("Country")
                .postCode("221100")
                .build();
    }

    public static Address getAddress() {
        return Address.builder()
                .id(1L)
                .houseNumber(1)
                .street("Street")
                .city("City")
                .country("Country")
                .postCode("221100")
                .build();
    }

    public static Contact getContact() {
        return Contact.builder()
                .id(1L)
                .phone("+375 29 123-45-67")
                .email("test@example.com")
                .build();
    }

    public static ArrivalTime getArrivalTime() {
        return ArrivalTime.builder()
                .id(1L)
                .checkIn("14:00")
                .checkOut("12:00")
                .build();
    }

    public static Amenity getAmenity(String name) {
        return Amenity.builder()
                .id(1L)
                .name(name)
                .build();
    }

    public static Hotels getHotels() {
        return Hotels.builder()
                .id(1L)
                .name("Test Hotel")
                .description("Test Description")
                .brand("Test Brand")
                .address(getAddress())
                .contacts(getContact())
                .arrivalTime(getArrivalTime())
                .amenities(Set.of())
                .build();
    }

    public static HotelResponseDto getHotelResponseDto() {
        return HotelResponseDto.builder()
                .id(1L)
                .name("Test Hotel")
                .description("Test Description")
                .address("1 Street, City, 221100, Country")
                .phone("+375 29 123-45-67")
                .build();
    }

    public static ContactResponseDto getContactResponseDto() {
        return ContactResponseDto.builder()
                .phone("+375 29 123-45-67")
                .email("test@example.com")
                .build();
    }

    public static ArrivalTimeResponseDto getArrivalTimeResponseDto() {
        return ArrivalTimeResponseDto.builder()
                .checkIn("14:00")
                .checkOut("12:00")
                .build();
    }

    public static List<String> getAmenitiesList() {
        return List.of("Free WiFi", "Parking");
    }

    public static FullHotelResponseDto getFullHotelResponseDto() {
        return FullHotelResponseDto.builder()
                .id(1L)
                .name("Test Hotel")
                .description("Test Description")
                .brand("Test Brand")
                .address(getAddressResponseDto())
                .contacts(getContactResponseDto())
                .arrivalTime(getArrivalTimeResponseDto())
                .amenities(List.of())
                .build();
    }

    public static AddressCreateDto getAddressCreateDto() {
        return new AddressCreateDto(
                1,
                "Test Street",
                "Test City",
                "Country",
                "221100"
        );
    }

    public static ContactCreateDto getContactCreateDto() {
        return new ContactCreateDto(
                "+375 29 123-45-67",
                "test@example.com"
        );
    }

    public static ArrivalTimeCreateDto getArrivalTimeCreateDto() {
        return new ArrivalTimeCreateDto(
                "14:00",
                "12:00"
        );
    }

    public static HotelCreateDto getHotelCreateDto() {
        return new HotelCreateDto(
                "Test Hotel",
                "Test Description",
                "Test Brand",
                getAddressCreateDto(),
                getContactCreateDto(),
                getArrivalTimeCreateDto()
        );
    }
}
