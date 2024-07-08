package Services;

import Entities.Reservation;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;
import java.util.Set;


public interface ReservationService {

    public void save(Reservation reservation) throws NotFoundException;

    public Set<Reservation> getAll();


    public Reservation getById(Long reservationId) throws NotFoundException;

    public void deleteById(Long reservationId);
}
