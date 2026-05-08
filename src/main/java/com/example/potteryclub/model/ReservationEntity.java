package com.example.potteryclub.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String timeSlot;
    private Integer workplaceId;
    private String supplies;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // getters & setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    public Integer getWorkplaceId() { return workplaceId; }
    public void setWorkplaceId(Integer workplaceId) { this.workplaceId = workplaceId; }

    public String getSupplies() { return supplies; }
    public void setSupplies(String supplies) { this.supplies = supplies; }

    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }
}