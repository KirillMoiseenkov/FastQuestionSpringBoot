package CreationShip.demo.service;

import CreationShip.demo.dao.LanguageDaoImpl;
import CreationShip.demo.models.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LanguageService implements ISerivce<Language> {

    @Autowired
    LanguageDaoImpl languageDao;


    @Override
    @Transactional(readOnly = true)
    public List<Language> getAll() {
        return languageDao.getAll();
    }

    @Override
    public Language getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public Language saveOrUpdate(Language language) {
        return languageDao.saveOrUpdate(language);
    }

    @Override
    public Language remove(Language language) {
        return null;
    }
}
