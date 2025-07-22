package org.example.dto;

import org.example.enums.StatusEnum;

import java.time.LocalDate;

public class getReservationDTO {
    public String clientName;

    public LocalDate startRangeDate;

    public LocalDate endRangeDate;

    public StatusEnum status;

    public String brand;

    public getReservationDTO(String clientName, LocalDate startRangeDate, LocalDate endRangeDate, StatusEnum status, String brand) {
        this.clientName = clientName;
        this.startRangeDate = startRangeDate;
        this.endRangeDate = endRangeDate;
        this.status = status;
        this.brand = brand;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setStartRangeDate(LocalDate startRangeDate) {
        this.startRangeDate = startRangeDate;
    }

    public void setEndRangeDate(LocalDate endRangeDate) {
        this.endRangeDate = endRangeDate;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getClientName() {
        return clientName;
    }

    public LocalDate getStartRangeDate() {
        return startRangeDate;
    }

    public LocalDate getEndRangeDate() {
        return endRangeDate;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public String getBrand() {
        return brand;
    }

}
