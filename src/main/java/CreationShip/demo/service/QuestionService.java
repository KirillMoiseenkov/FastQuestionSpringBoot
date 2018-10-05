package CreationShip.demo.service;

import CreationShip.demo.dao.QuestionDaoImpl;
import CreationShip.demo.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionService implements ISerivce<Question> {

    @Autowired
    QuestionDaoImpl questionDao;


    public QuestionService() {
    }

    @Override
    @Transactional(readOnly = true)
    public List<Question> getAll() {
        return questionDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Question getById(Long id) {
        return questionDao.getById(id);
    }

    @Override
    @Transactional
    public Question save(Question question) {
        return questionDao.save(question);
    }

    @Override
    @Transactional
    public Question remove(Question question) {
        return questionDao.remove(question);
    }

    @Transactional(readOnly = true)
    public List<Question> getRandomQuestion(int count) {
        return questionDao.getRandomQuestion(count);
    }

    @Transactional(readOnly = true)
    public List<Question> getRandomQuestionByLanguage(int count, String lang) {
        return questionDao.getRandomQuestionByLanguage(count,lang);
    }

}
