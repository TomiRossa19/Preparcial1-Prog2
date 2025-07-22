package org.example.dto;

import org.example.models.Vehicle;

import java.time.LocalDate;

public class CreateReservationDTO {
    public String clientName;
    public Vehicle vehicleID;
    public LocalDate startDate;
    public LocalDate endDate;

    public CreateReservationDTO(String clientName, Vehicle vehicleID, LocalDate startDate, LocalDate endDate) {
        this.clientName = clientName;
        this.vehicleID = vehicleID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setVehicleID(Vehicle vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getClientName() {
        return clientName;
    }

    public Vehicle getVehicleID() {
        return vehicleID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}

