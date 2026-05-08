package com.example.potteryclub.repository;

import com.example.potteryclub.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findByDate(LocalDate date);

    Optional<ReservationEntity> findByDateAndTimeSlotAndWorkplaceId(
            LocalDate date,
            String timeSlot,
            Long workplaceId
    );

    List<ReservationEntity> findByDateAndTimeSlot(LocalDate date, String timeSlot);
}
