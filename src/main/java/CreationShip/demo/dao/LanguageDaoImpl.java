package CreationShip.demo.dao;

import CreationShip.demo.models.Language;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LanguageDaoImpl implements IDAO<Language>{

    @PersistenceContext
    EntityManager entityManager;

    public LanguageDaoImpl(){}


    @Override
    public List<Language> getAll() {
        return entityManager.createQuery("SELECT p FROM Language p").getResultList();
    }

    @Override
    public Language getById(Long id) {
        return null;
    }

    @Override
    public Language saveOrUpdate(Language language) {
        return entityManager.merge(language);
    }

    @Override
    public Language remove(Language language) {
        return null;
    }
}
