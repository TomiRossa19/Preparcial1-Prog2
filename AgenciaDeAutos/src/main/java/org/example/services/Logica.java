package org.example.services;

import org.example.CreateReservationDTO;
import org.example.enums.StatusEnum;
import org.example.models.Reservation;
import org.example.models.Vehicle;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.Period;

public class Logica {
    private static Logica instance;

    private Logica(){

    }

    public Logica getInstance(){
        if (instance == null){
            instance = new Logica();
        }
        return instance;
    }

    public static boolean createReservation(CreateReservationDTO createReservationDTO){
        try(Session session = HibernateUtil.getSession()){

            Vehicle vehicle = session.get(Vehicle.class, createReservationDTO.getVehicleID());

            if(vehicle == null){
                System.out.println("El auto no existe");
                return false;
            }else{
                Integer days = Period.between(createReservationDTO.getStartDate(), createReservationDTO.getEndDate()).getDays();
                BigDecimal totalCost = vehicle.getDailyRate().multiply(BigDecimal.valueOf(days));
                Reservation reservation = new Reservation(createReservationDTO.getClientName(), createReservationDTO.getVehicleID(), createReservationDTO.getStartDate(), createReservationDTO.getEndDate(), totalCost, StatusEnum.RESERVED);
                vehicle.setAvailable(false);

                session.persist(reservation);
                session.merge(vehicle);

                session.getTransaction().commit();
            }
        }
    }

}
