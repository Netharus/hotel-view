package com.netharus.hotelview.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum AllowedParametrisesHistogram {

    BRAND("brand"),
    CITY("city"),
    COUNTRY("country"),
    AMENITIES("amenities");

    private final String paramName;

    public static Set<String> getParameterNames() {
        return Arrays.stream(values())
                .map(AllowedParametrisesHistogram::getParamName)
                .collect(Collectors.toSet());
    }
}
