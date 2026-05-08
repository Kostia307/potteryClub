package com.example.potteryclub.api;

import com.example.potteryclub.model.AvailableWorkplaceDto;
import com.example.potteryclub.service.WorkplaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Tag(name = "Workplaces", description = "Workplace API")
@RestController
public class WorkplaceController {

    private final WorkplaceService service;

    public WorkplaceController(WorkplaceService workplaceService) {
        this.service = workplaceService;
    }

    @GetMapping("/workplaces/available")
    public ResponseEntity<List<AvailableWorkplaceDto>> getAvailable(
            @RequestParam LocalDate date,
            @RequestParam String timeSlot
    ) {
        return ResponseEntity.ok(service.getAvailable(date, timeSlot));
    }
}