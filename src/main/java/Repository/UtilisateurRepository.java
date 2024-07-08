package Repository;



import Entities.Utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Query("select u from T_UTILISATEUR u where u.UtilNom = ?1 and u.UtilPrenom = ?2 and u.UtilEmail = ?3")
    Utilisateur findByUtilNomAndUtilPrenomAndUtilEmail(String utilNom, String utilPrenom, String utilEmail);


}
