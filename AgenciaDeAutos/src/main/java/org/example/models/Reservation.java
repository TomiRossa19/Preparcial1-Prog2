package org.example.models;

import jakarta.persistence.*;
import org.example.enums.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "client_name")
    private String clientName;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicleId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    public Reservation(){

    }

    public Reservation(String clientName, Vehicle vehicleId, LocalDate startDate, LocalDate endDate, BigDecimal totalCost, StatusEnum status) {
        this.clientName = clientName;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setVehicleId(Vehicle vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public Vehicle getVehicleId() {
        return vehicleId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public StatusEnum getStatus() {
        return status;
    }
}
