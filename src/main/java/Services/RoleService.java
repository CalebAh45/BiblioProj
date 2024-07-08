package Services;

import Entities.Role;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;
import java.util.Set;


public interface RoleService {

    public void save(Role role) throws NotFoundException;


    public Set<Role> getAll();

    public Role getById(Long RoleCode) throws NotFoundException;

    public void deleteById(Long RoleCode);
}
