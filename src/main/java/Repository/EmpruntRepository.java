package Repository;


import Entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    @Query("select e from T_EMPRUNT e where e.DateEmprunt = ?1 and e.DateRetour = ?2")
    Emprunt findByDateEmpruntAndDateRetour(Date dateEmprunt, Date dateRetour);


}
