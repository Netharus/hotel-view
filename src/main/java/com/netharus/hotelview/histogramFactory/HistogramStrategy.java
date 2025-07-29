package com.netharus.hotelview.histogramFactory;

import java.util.Map;

public interface HistogramStrategy {
    String getKey();

    Map<String, Long> getHistogram();
}
