package com.netharus.hotelview.controller;

import com.netharus.hotelview.histogramFactory.HistogramStrategyFactory;
import com.netharus.hotelview.validator.HistogramValid;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/property-view/histogram")
@Validated
public class HistogramRestController {

    private final HistogramStrategyFactory strategyFactory;

    @GetMapping("/{param}")
    public ResponseEntity<Map<String, Long>> getHistogram(@Valid @HistogramValid @PathVariable String param) {
        return ResponseEntity.ok(strategyFactory.getStrategy(param.toLowerCase()).getHistogram());
    }

}
