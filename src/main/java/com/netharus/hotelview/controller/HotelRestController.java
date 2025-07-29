package com.netharus.hotelview.controller;

import com.netharus.hotelview.dto.request.HotelCreateDto;
import com.netharus.hotelview.dto.response.ErrorResponse;
import com.netharus.hotelview.dto.response.FullHotelResponseDto;
import com.netharus.hotelview.dto.response.HotelResponseDto;
import com.netharus.hotelview.service.AmenityService;
import com.netharus.hotelview.service.HotelsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
@Tag(name = "Hotels controller", description = "Endpoints for managing hotel data")
public class HotelRestController {

    private final HotelsService hotelsService;

    private final AmenityService amenityService;

    @Operation(
            summary = "Get list of hotels",
            description = "Returns short information about all hotels",
            operationId = "getHotels",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of hotels returned successfully")
            }
    )
    @GetMapping("/hotels")
    @ResponseStatus(HttpStatus.OK)
    public List<HotelResponseDto> getHotels() {
        return hotelsService.getHotelDtoList();
    }

    @Operation(
            summary = "Get full hotel info by ID",
            description = "Returns full information about a single hotel",
            operationId = "getHotelById",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Hotel found"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Hotel not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @GetMapping("/hotels/{hotelId}")
    @ResponseStatus(HttpStatus.OK)
    public FullHotelResponseDto getHotelById(
            @Parameter(description = "ID of the hotel to retrieve") @PathVariable Long hotelId
    ) {
        return hotelsService.findHotelById(hotelId);
    }

    @Operation(
            summary = "Create new hotel",
            description = "Creates a hotel with address, contacts, and arrival time",
            operationId = "createHotel",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Hotel created successfully"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @PostMapping("/hotels")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelResponseDto createHotel(
            @Parameter(description = "Hotel creation request body") @Valid @RequestBody HotelCreateDto hotelCreateDto
    ) {
        return hotelsService.add(hotelCreateDto);
    }

    @Operation(
            summary = "Search hotels",
            description = "Returns list of hotels filtered by name, brand, city, country or amenities",
            operationId = "searchHotels",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Filtered hotels returned")
            }
    )
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<HotelResponseDto> searchHotels(
            @Parameter(description = "Hotel name to search") @RequestParam(required = false) String name,
            @Parameter(description = "Brand name") @RequestParam(required = false) String brand,
            @Parameter(description = "City name") @RequestParam(required = false) String city,
            @Parameter(description = "Country name") @RequestParam(required = false) String country,
            @Parameter(description = "List of amenities") @RequestParam(required = false) List<String> amenities
    ) {
        return hotelsService.searchHotels(name, brand, city, country, amenities);
    }

    @Operation(
            summary = "Add amenities to hotel",
            description = "Adds a list of amenities to the hotel by ID. Missing amenities are saved.",
            operationId = "addAmenities",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Amenities added successfully"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Hotel not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @PostMapping("/hotels/{hotelId}/amenities")
    @ResponseStatus(HttpStatus.OK)
    public void addAmenities(
            @Parameter(description = "ID of the hotel") @PathVariable Long hotelId,
            @Parameter(description = "List of amenities to add") @RequestBody List<String> amenities
    ) {
        amenityService.saveMissingAmenities(amenities);
        hotelsService.addAmenitiesById(hotelId, amenityService.getAmenitiesByName(amenities));
    }
}