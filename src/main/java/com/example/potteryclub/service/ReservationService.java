package com.example.potteryclub.service;


import com.example.potteryclub.model.ReservationEntity;
import com.example.potteryclub.repository.ReservationRepository;
import org.openapitools.model.Reservation;
import org.openapitools.model.ReservationsPostRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservations(LocalDate date) {
        return reservationRepository.findByDate(date)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public void createReservation(ReservationsPostRequest request) {

        boolean exists = reservationRepository
                .findByDateAndTimeSlotAndWorkplaceId(
                        request.getDate(),
                        request.getTimeSlot(),
                        request.getWorkplaceId().longValue()
                )
                .isPresent();

        if (exists) {
            throw new RuntimeException("Time slot already booked");
        }

        ReservationEntity entity = new ReservationEntity();
        entity.setDate(request.getDate());
        entity.setTimeSlot(request.getTimeSlot());
        entity.setWorkplaceId(request.getWorkplaceId());
        entity.setSupplies(request.getSupplies());

        reservationRepository.save(entity);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    private Reservation toDto(ReservationEntity entity) {
        Reservation dto = new Reservation();
        dto.setId(Math.toIntExact(entity.getId()));
        dto.setDate(entity.getDate());
        dto.setTimeSlot(entity.getTimeSlot());
        dto.setWorkplaceId(Math.toIntExact(entity.getWorkplaceId()));
        dto.setSupplies(entity.getSupplies());
        return dto;
    }
}
