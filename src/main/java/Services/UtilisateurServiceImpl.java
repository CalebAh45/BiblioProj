package Services;



import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import Entities.Utilisateur;
import Repository.UtilisateurRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public void save(Utilisateur utilisateur) throws NotFoundException {
        Utilisateur u = utilisateurRepository.findByUtilNomAndUtilPrenomAndUtilEmail(utilisateur.getUtilNom(), utilisateur.getUtilPrenom(), utilisateur.getUtilEmail());
        if(u == null){
            utilisateurRepository.save(utilisateur);
        } else {
            throw new NotFoundException("Enregistrement existant");
        }
    }


    @Override
    public Set<Utilisateur> getAll() {
        Set<Utilisateur> foundUtilisateurs = new HashSet<>();
        utilisateurRepository.findAll().forEach(foundUtilisateurs::add);
        return foundUtilisateurs;
    }

    @Override
    public List<Utilisateur> UtilisateurFiltre(Long RoleCode) {
        return null;
    }

//    @Override
//    public List<Utilisateur> UtilisateurFiltre(Long RoleCode) {
//        return utilisateurRepository.findByRoleCode(RoleCode);
//    }

    @Override
    public Utilisateur getById(Long UtilCode) throws NotFoundException {
        Optional<Utilisateur> optional = utilisateurRepository.findById(UtilCode);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException("Impossible de trouver l'utilisateur avec le code " + UtilCode);
    }

    @Override
    public void deleteById(Long UtilCode) {
        utilisateurRepository.deleteById(UtilCode);
    }
}
