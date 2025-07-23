package org.example.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.dto.CreateReservationDTO;
import org.example.dto.FinishReservationDTO;
import org.example.dto.ReservationDTO;
import org.example.dto.getReservationDTO;
import org.example.enums.StatusEnum;
import org.example.models.Reservation;
import org.example.models.Vehicle;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Logica {
    private static Logica instance;

    private Logica(){

    }

    public static Logica getInstance(){
        if (instance == null){
            instance = new Logica();
        }
        return instance;
    }

    public boolean createReservation(CreateReservationDTO createReservationDTO){
        try(Session session = HibernateUtil.getSession()){
            Vehicle vehicle = session.get(Vehicle.class, createReservationDTO.getVehicleID());

            if(vehicle == null){
                System.out.println("El auto no existe");
                return false;
            }else{
                long days = ChronoUnit.DAYS.between(createReservationDTO.getStartDate(), createReservationDTO.getEndDate());
                BigDecimal totalCost = vehicle.getDailyRate().multiply(BigDecimal.valueOf(days));
                Reservation reservation = new Reservation(createReservationDTO.getClientName(), createReservationDTO.getVehicleID(), createReservationDTO.getStartDate(), createReservationDTO.getEndDate(), totalCost, StatusEnum.RESERVED);
                vehicle.setAvailable(false);

                session.beginTransaction();

                session.persist(reservation);
                session.merge(vehicle);

                session.getTransaction().commit();

                return true;
            }
        }
    }

    public boolean finishReservation(FinishReservationDTO finishReservationDTO){
        try (Session session = HibernateUtil.getSession()){
            Reservation reservation = session.get(Reservation.class, finishReservationDTO.getReservationID());
            if (reservation == null){
                System.out.println("La reserva no existe");
                return false;
            }else if (reservation.getStatus() == StatusEnum.RESERVED){
                if(finishReservationDTO.getReturnDate().isAfter(reservation.getEndDate())){
                    BigDecimal total = reservation.getTotalCost();
                    BigDecimal tenPercent = (BigDecimal.valueOf(10)).multiply(reservation.getTotalCost()).divide(BigDecimal.valueOf(100));
                    long days = ChronoUnit.DAYS.between(reservation.getEndDate(), finishReservationDTO.getReturnDate());
                    BigDecimal totalExtra = tenPercent.multiply(BigDecimal.valueOf(days));
                    total = total.add(totalExtra);
                    reservation.setTotalCost(total);
                }
                reservation.setStatus(StatusEnum.COMPLETED);

                Vehicle vehicle = reservation.getVehicleId();

                vehicle.setAvailable(true);

                session.merge(reservation);
                session.merge(vehicle);

                session.getTransaction().commit();

                return true;
            }else{
                System.out.println("La reserva no está activa");
                return false;
            }

        }
    }
    public List<ReservationDTO> getReservations(getReservationDTO getReservationDTO) {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Reservation> cq = cb.createQuery(Reservation.class);
            Root<Reservation> root = cq.from(Reservation.class);

            Predicate name = cb.equal(root.get("clientName"), getReservationDTO.getClientName());
            List<Predicate> predicates = new ArrayList<>();

            if (getReservationDTO.getStartRangeDate() != null || getReservationDTO.getEndRangeDate() != null) {
                Predicate dates = cb.between(root.get("startDate"), getReservationDTO.getStartRangeDate(), getReservationDTO.getEndRangeDate());
                predicates.add(dates);
            }

            if (getReservationDTO.getStatus() != null) {
                Predicate status = cb.equal(root.get("status"), getReservationDTO.getStatus());
                predicates.add(status);
            }

            if (getReservationDTO.getBrand() != null) {
                Predicate brand = cb.equal(root.get("vehicleId").get("brand"), getReservationDTO.getBrand());
                predicates.add(brand);
            }

            cq.where(cb.and(predicates.toArray(new Predicate[0])));

            List<Reservation> reservations = session.createQuery(cq).getResultList();

            return reservations.stream()
                    .map(ReservationDTO::fromEntity)
                    .collect(Collectors.toList());

        }
    }
}
