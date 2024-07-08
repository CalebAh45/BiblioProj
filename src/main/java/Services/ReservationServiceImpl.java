package Services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import Entities.Reservation;
import Repository.ReservationRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void save(Reservation reservation) throws NotFoundException {
        Reservation r = reservationRepository.findByReservationDate(
                reservation.getReservationDate());
        if (r == null) {
            reservationRepository.save(reservation);
        } else {
            throw new NotFoundException("Enregistrement existant");
        }
    }

    @Override
    public Set<Reservation> getAll() {
        Set<Reservation> foundReservations = new HashSet<>();
        reservationRepository.findAll().forEach(foundReservations::add);
        return foundReservations;
    }



    @Override
    public Reservation getById(Long reservationId) throws NotFoundException {
        Optional<Reservation> optional = reservationRepository.findById(reservationId);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException("Impossible de trouver la réservation avec le code " + reservationId);
    }

    @Override
    public void deleteById(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
