package org.example.dto;

import java.time.LocalDate;

public class FinishReservationDTO {
    public Long reservationID;
    public LocalDate returnDate;

    public FinishReservationDTO(Long reservationID, LocalDate returnDate) {
        this.reservationID = reservationID;
        this.returnDate = returnDate;
    }

    public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Long getReservationID() {
        return reservationID;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

}
