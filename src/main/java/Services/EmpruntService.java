package Services;


import Entities.Emprunt;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;
import java.util.Set;


public interface EmpruntService {

    public void save(Emprunt emprunt) throws NotFoundException;

    public Set<Emprunt> getAll();


    public Emprunt getById(Long EmpruntCode) throws NotFoundException;

    public void deleteById(Long EmpruntCode);
}

