package com.netharus.hotelview.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Structured error response")
public record ErrorResponse(
        @Schema(description = "Short error summary", example = "Validation Failed")
        String title,

        @Schema(description = "HTTP status code", example = "400")
        int status,

        @Schema(description = "Detailed message", example = "The request contains invalid data")
        String detail,

        @Schema(description = "Path of the failed request", example = "/property-view/histogram/invalidParam")
        String instance,

        @Schema(description = "Field-level validation errors")
        Map<String, List<String>> errors
) {
    public static ErrorResponse validationError(String path, Map<String, List<String>> fieldErrors) {
        return ErrorResponse.builder()
                .title("Validation Failed")
                .status(400)
                .detail("The request contains invalid data")
                .instance(path)
                .errors(fieldErrors)
                .build();
    }
}

