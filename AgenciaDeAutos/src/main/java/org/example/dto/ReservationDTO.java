package org.example.dto;

import jakarta.persistence.*;
import org.example.enums.StatusEnum;
import org.example.models.Reservation;
import org.example.models.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReservationDTO {
    public String clientName;

    public Vehicle vehicleId;

    public LocalDate startDate;

    public LocalDate endDate;

    public BigDecimal totalCost;

    public StatusEnum status;

    public ReservationDTO(String clientName, Vehicle vehicleId, LocalDate startDate, LocalDate endDate, BigDecimal totalCost, StatusEnum status) {
        this.clientName = clientName;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.status = status;
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

    public static ReservationDTO fromEntity(Reservation reservation){
        if (reservation == null){
            return null;
        }else {
            ReservationDTO reservationDTO = new ReservationDTO(reservation.getClientName(), reservation.getVehicleId(), reservation.getStartDate(), reservation.getEndDate(), reservation.getTotalCost(), reservation.getStatus());
            return reservationDTO;
        }
    }
}
