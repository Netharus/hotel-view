package com.netharus.hotelview.histogramFactory;

import com.netharus.hotelview.dto.response.HistogramCount;
import com.netharus.hotelview.repository.HotelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BrandHistogramStrategy implements HistogramStrategy {


    private final HotelsRepository hotelRepository;

    @Override
    public String getKey() {
        return "brand";
    }

    @Override
    public Map<String, Long> getHistogram() {
        return hotelRepository.countGroupedByBrand().stream()
                .collect(Collectors.toMap(HistogramCount::name, HistogramCount::count));
    }
}
