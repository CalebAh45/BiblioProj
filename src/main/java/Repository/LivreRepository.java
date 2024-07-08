package Repository;

import Entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    @Query("select l from T_LIVRE l where l.LivreTitre = ?1 and l.LivreAuteur = ?2 and l.LivreCategorie = ?3")
    Livre findByLivreTitreAndLivreAuteurAndLivreCategorie(String livreTitre, String livreAuteur, String livreCategorie);

    @Query("select l from T_LIVRE l where l.LivreAuteur = ?1")
    List<Livre> findByLivreAuteur(String auteur);
}
