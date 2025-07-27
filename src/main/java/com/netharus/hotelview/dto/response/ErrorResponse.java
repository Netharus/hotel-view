package com.netharus.hotelview.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        String title,
        int status,
        String detail,
        String instance,
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
