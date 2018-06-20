package CreationShip.demo.service;

import CreationShip.demo.dao.QuestionDaoImpl;
import CreationShip.demo.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionService implements ISerivce<Question>{

    @Autowired
    QuestionDaoImpl questionDao;


    public QuestionService(){}


    @Transactional(readOnly = true)
    public List<Question> getAll() {
        return questionDao.getAll();
    }

    @Transactional(readOnly = true)
    public Question getById(Long id) {
        return questionDao.getById(id);
    }

    @Transactional
    public Question saveOrUpdate(Question question) {
        return questionDao.saveOrUpdate(question);
    }

    @Transactional
    public Question remove(Question question) {
        return questionDao.remove(question);
    }

    @Transactional(readOnly = true)
    public List<Question> getRandomQuestion(int count){
        return questionDao.getRandomQuestion(count);
    }
}
