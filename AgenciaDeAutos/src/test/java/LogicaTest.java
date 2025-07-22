import jakarta.persistence.Table;
import org.example.dto.ReservationDTO;
import org.example.dto.getReservationDTO;
import org.example.enums.StatusEnum;
import org.example.models.Reservation;
import org.example.models.Vehicle;
import org.example.services.Logica;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.example.services.Logica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogicaTest {
    private Logica logica;
    private Session session;
    private Reservation reservation;
    private Vehicle vehicle;

    @BeforeAll
    void setUp(){
        logica = Logica.getInstance();
        session = HibernateUtil.getSession();

        vehicle = new Vehicle("123abc", "Honda", "Civic", BigDecimal.valueOf(2000), false);
        reservation = new Reservation("Tomás", vehicle, LocalDate.of(2025, 06, 06), LocalDate.of(2025, 07, 06), BigDecimal.valueOf(20000), StatusEnum.RESERVED);

        session.beginTransaction();
        session.persist(reservation);
        session.persist(vehicle);

        session.getTransaction().commit();
    }

    @AfterAll
    void tearDown(){
        if(session != null && session.isOpen()){
            session.beginTransaction();
            session.createQuery("delete from Reservation").executeUpdate();
            session.createQuery("delete from Vehicle").executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }
    @Test
    void testObtenerReservas(){
        List<ReservationDTO> reservas = logica.getReservations(new getReservationDTO("Tomás", LocalDate.of(2024, 06, 06),LocalDate.of(2026, 06, 06), StatusEnum.RESERVED, "Honda"));
        Assertions.assertNotNull(reservas);
        Assertions.assertFalse(reservas.isEmpty());
        Assertions.assertEquals(1, reservas.size());
    }
}
