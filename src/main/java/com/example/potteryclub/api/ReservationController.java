package com.example.potteryclub.api;

import com.example.potteryclub.service.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.openapitools.api.ReservationsApi;
import org.openapitools.model.Reservation;
import org.openapitools.model.ReservationsPostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Reservations", description = "Reservations API")
@RestController
public class ReservationController implements ReservationsApi {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public ResponseEntity<List<Reservation>> reservationsGet(LocalDate date) {
        return ResponseEntity.ok(reservationService.getReservations(date));
    }

    @Override
    public ResponseEntity<Void> reservationsPost(ReservationsPostRequest request) {
        reservationService.createReservation(request);
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<Void> reservationsIdDelete(Integer id) {
        reservationService.deleteReservation(id.longValue());
        return ResponseEntity.noContent().build();
    }
}
