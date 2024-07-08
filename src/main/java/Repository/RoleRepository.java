package Repository;

import Entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from T_ROLE r where r.RoleNom = ?1")
    Role findByRoleNom(String roleNom);
}
