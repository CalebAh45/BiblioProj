package Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Entity(name="T_EMPRUNT")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long EmpruntId;
    private Date EmpruntDateEmprunt;
    private Date EmpruntDateRetour;

    // Relations avec les autres entités
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "livre_id")
    private Livre livre;
}
