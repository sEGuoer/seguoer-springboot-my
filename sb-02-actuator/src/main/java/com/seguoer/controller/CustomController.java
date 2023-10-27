package com.seguoer.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomController {
    Counter counter;
    public CustomController(MeterRegistry meterRegistry) {
        counter = meterRegistry.counter("custom.viewsCount");
    }

    @GetMapping("/custom-metrics")
    String customMetrics() {
        counter.increment();
        return "counter=" + counter.count();
    }
}
