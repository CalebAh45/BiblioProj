package Services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import Entities.Role;
import Repository.RoleRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(Role role) throws NotFoundException {
        Role r = roleRepository.findByRoleNom(role.getRoleNom());
        if(r == null){
            roleRepository.save(role);
        } else {
            throw new NotFoundException("Enregistrement existant");
        }
    }

    @Override
    public Set<Role> getAll() {
        Set<Role> foundRoles = new HashSet<>();
        roleRepository.findAll().forEach(foundRoles::add);
        return foundRoles;
    }

    @Override
    public Role getById(Long RoleCode) throws NotFoundException {
        Optional<Role> optional = roleRepository.findById(RoleCode);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException("Impossible de trouver le rôle avec le code " + RoleCode);
    }

    @Override
    public void deleteById(Long RoleCode) {
        roleRepository.deleteById(RoleCode);
    }
}

