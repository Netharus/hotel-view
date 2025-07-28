package com.netharus.hotelview.controller;

import com.netharus.hotelview.dto.response.ErrorResponse;
import com.netharus.hotelview.histogramFactory.HistogramStrategyFactory;
import com.netharus.hotelview.validator.HistogramValid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "Histogram", description = "Endpoints for retrieving hotel statistics grouped by fields")
@RestController
@RequiredArgsConstructor
@RequestMapping("/property-view/histogram")
@Validated
public class HistogramRestController {

    private final HistogramStrategyFactory strategyFactory;

    @Operation(
            summary = "Get histogram of hotel data",
            description = "Returns a map of values grouped by the specified parameter "
                    + "(brand, city, country, or amenities)",
            operationId = "getHistogram",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Histogram data retrieved successfully"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid parameter",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @GetMapping("/{param}")
    public ResponseEntity<Map<String, Long>> getHistogram(
            @Parameter(
                    description = "Parameter by which to group hotels: brand, city, country, or amenities",
                    example = "city"
            )
            @Valid @HistogramValid @PathVariable String param
    ) {
        return ResponseEntity.ok(strategyFactory.getStrategy(param.toLowerCase()).getHistogram());
    }
}
