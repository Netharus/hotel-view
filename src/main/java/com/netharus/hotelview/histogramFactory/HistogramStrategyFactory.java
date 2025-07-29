package com.netharus.hotelview.histogramFactory;

import com.netharus.hotelview.exceptions.ErrorMessages;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class HistogramStrategyFactory {

    private final Map<String, HistogramStrategy> strategies;

    public HistogramStrategyFactory(List<HistogramStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(HistogramStrategy::getKey, s -> s));
    }

    public HistogramStrategy getStrategy(String param) {
        HistogramStrategy strategy = strategies.get(param.toLowerCase());
        if (strategy == null) {
            throw new IllegalArgumentException(ErrorMessages.ILLEGAL_PARAM + param);
        }
        return strategy;
    }
}
