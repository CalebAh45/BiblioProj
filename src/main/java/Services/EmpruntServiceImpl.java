package Services;



import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import Entities.Emprunt;
import Repository.EmpruntRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpruntServiceImpl implements EmpruntService {

    @Autowired
    private EmpruntRepository empruntRepository;

    @Override
    public void save(Emprunt emprunt) throws NotFoundException {
        Emprunt e = empruntRepository.findByDateEmpruntAndDateRetour(emprunt.getEmpruntDateEmprunt(), emprunt.getEmpruntDateRetour());
        if(e == null){
            empruntRepository.save(emprunt);
        } else {
            throw new NotFoundException("Enregistrement existant");
        }
    }

    @Override
    public Set<Emprunt> getAll() {
        Set<Emprunt> foundEmprunts = new HashSet<>();
        empruntRepository.findAll().forEach(foundEmprunts::add);
        return foundEmprunts;
    }

    @Override
    public Emprunt getById(Long EmpruntCode) throws NotFoundException {
        Optional<Emprunt> optional = empruntRepository.findById(EmpruntCode);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException("Impossible de trouver l'emprunt avec le code " + EmpruntCode);
    }

    @Override
    public void deleteById(Long EmpruntCode) {
        empruntRepository.deleteById(EmpruntCode);
    }
}

