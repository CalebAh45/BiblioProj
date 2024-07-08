package Repository;

import Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from T_RESERVATION r where r.ReservationDate = ?1 ")
    Reservation findByReservationDate(Date reservationDate);


}
