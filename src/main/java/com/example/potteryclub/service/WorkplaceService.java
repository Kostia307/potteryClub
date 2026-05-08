package com.example.potteryclub.service;

import com.example.potteryclub.model.AvailableWorkplaceDto;
import com.example.potteryclub.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class WorkplaceService {

    private final ReservationRepository reservationRepository;

    public WorkplaceService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<AvailableWorkplaceDto> getAvailable(LocalDate date, String timeSlot) {

        List<Integer> allWorkplaces = List.of(1, 2, 3, 4, 5);

        List<Integer> booked = reservationRepository
                .findByDateAndTimeSlot(date, timeSlot)
                .stream()
                .map(r -> r.getWorkplaceId().intValue())
                .toList();

        return allWorkplaces.stream()
                .filter(id -> !booked.contains(id))
                .map(AvailableWorkplaceDto::new)
                .toList();
    }
}
