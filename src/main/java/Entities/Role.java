package Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Entity(name="T_ROLE")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long RoleId;
    private String RoleNom;

    // Relations avec les autres entités
    @OneToMany(mappedBy = "role")
    private Set<Utilisateur> RoleUtilisateurs;
}
