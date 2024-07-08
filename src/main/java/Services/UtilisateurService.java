package Services;



import Entities.Utilisateur;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;
import java.util.Set;


public interface UtilisateurService {

    public void save(Utilisateur utilisateur) throws NotFoundException ;

    public Set<Utilisateur> getAll();

    public List<Utilisateur> UtilisateurFiltre(Long RoleCode);

    public Utilisateur getById(Long UtilCode) throws NotFoundException ;

    public void deleteById(Long UtilCode);
}
