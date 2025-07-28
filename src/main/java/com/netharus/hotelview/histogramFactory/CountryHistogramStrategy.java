package com.netharus.hotelview.histogramFactory;

import com.netharus.hotelview.dto.response.HistogramCount;
import com.netharus.hotelview.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CountryHistogramStrategy implements HistogramStrategy {

    private final AddressRepository addressRepository;

    @Override
    public String getKey() {
        return "country";
    }

    @Override
    public Map<String, Long> getHistogram() {
        return addressRepository.countGroupedByCountry().stream()
                .collect(Collectors.toMap(HistogramCount::name, HistogramCount::count));
    }
}
