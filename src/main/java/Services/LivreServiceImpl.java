package Services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import Entities.Livre;
import Repository.LivreRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivreServiceImpl implements LivreService {

    @Autowired
    private LivreRepository livreRepository;

    @Override
    public void save(Livre livre) throws NotFoundException {
        Livre l = livreRepository.findByLivreTitreAndLivreAuteurAndLivreCategorie(livre.getLivreTitre(), livre.getLivreAuteur(), livre.getLivreCategorie());
        if(l == null){
            livreRepository.save(livre);
        } else {
            throw new NotFoundException("Enregistrement existant");
        }
    }

    @Override
    public Set<Livre> getAll() {
        Set<Livre> foundLivres = new HashSet<>();
        livreRepository.findAll().forEach(foundLivres::add);
        return foundLivres;
    }

    @Override
    public List<Livre> LivreFiltre(String Auteur) {
        return livreRepository.findByLivreAuteur(Auteur);
    }

    @Override
    public Livre getById(Long LivreCode) throws NotFoundException {
        Optional<Livre> optional = livreRepository.findById(LivreCode);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException("Impossible de trouver le livre avec le code " + LivreCode);
    }

    @Override
    public void deleteById(Long LivreCode) {
        livreRepository.deleteById(LivreCode);
    }
}

