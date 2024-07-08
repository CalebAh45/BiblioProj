package Entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.Set;

@Entity(name="T_UTILISATEUR")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String UtilCode;
    private String UtilNom;
    private String UtilPrenom;
    private String UtilAdresse;
    private String UtilEmail;
    private String UtilMotDePasse;

    // Relations avec les autres entités
    @OneToMany(mappedBy = "utilisateur")
    private Set<Emprunt> UtilisateurEmprunts;

    @OneToMany(mappedBy = "utilisateur")
    private Set<Reservation> UtilisateurReservations;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
