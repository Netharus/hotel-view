package com.netharus.hotelview.histogramFactory;

import com.netharus.hotelview.dto.response.HistogramCount;
import com.netharus.hotelview.repository.HotelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AmenitiesHistogramStrategy implements HistogramStrategy {

    private final HotelsRepository hotelsRepository;

    @Override
    public String getKey() {
        return "amenities";
    }

    @Override
    public Map<String, Long> getHistogram() {
        return hotelsRepository.countGroupedByAmenity().stream()
                .collect(Collectors.toMap(HistogramCount::name, HistogramCount::count));
    }
}
