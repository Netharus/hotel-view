package com.netharus.hotelview.dto.response;

import lombok.Builder;

@Builder
public record HistogramCount(
        String name,
        Long count
) {
}
