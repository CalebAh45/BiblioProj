package Services;


import java.util.List;
import java.util.Set;

import Entities.Livre;
import org.apache.ibatis.javassist.NotFoundException;


public interface LivreService {

    public void save(Livre livre) throws NotFoundException;

    public Set<Livre> getAll();

    public List<Livre> LivreFiltre(String Auteur);

    public Livre getById(Long LivreCode) throws NotFoundException;

    public void deleteById(Long LivreCode);
}
