package com.example.potteryclub.api;

import com.example.potteryclub.aspects.StatsDto;
import com.example.potteryclub.aspects.StatsHolder;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Tag(name = "Statistics", description = "AOP-collected stats")
@RestController
public class StatsController {

    private final StatsHolder statsHolder;

    public StatsController(StatsHolder statsHolder) {
        this.statsHolder = statsHolder;
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsDto> getStats() {
        Map<String, AtomicInteger> calls = statsHolder.getEndpointCalls();

        double total = calls.values().stream().mapToInt(AtomicInteger::get).sum();

        Map<String, Double> percentages = calls.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> total == 0 ? 0.0 : Math.round((e.getValue().get() / total) * 1000.0) / 10.0
                ));

        StatsDto dto = new StatsDto();
        dto.setEndpointUsagePercent(percentages);
        dto.setLongestPostTitle(statsHolder.getLongestPostTitle());
        dto.setLongestPostContentLength(statsHolder.getLongestPostLength());
        dto.setTotalPostsServed(statsHolder.getTotalPostsServed());

        return ResponseEntity.ok(dto);
    }
}
