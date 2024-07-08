package Entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Entity(name="T_LIVRE")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long LivreId;
    private String LivreTitre;
    private String LivreAuteur;
    private String LivreCategorie;
    private String LivreStatut;

    // Relations avec les autres entités
    @OneToMany(mappedBy = "livre")
    private Set<Emprunt> LivreEmprunts;

    @OneToMany(mappedBy = "livre")
    private Set<Reservation> LivreReservations;
}
