package CreationShip.demo.dao;

import CreationShip.demo.models.Language;
import CreationShip.demo.models.Question;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QuestionDaoImpl implements IDAO<Question> {

    private static final Logger log = Logger.getLogger(QuestionDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public QuestionDaoImpl() {
    }


    public List<Question> getAll() {

            return entityManager.createQuery("SELECT p FROM Question p").getResultList();
    }

    @Override
    public Question getById(Long id) {
           return entityManager.find(Question.class, id);
    }


    public Question create(Question question) {
           return  (Question) entityManager.merge(question);
    }

    public List<Question> getRandomQuestion(int count) {
            return  (List<Question>) entityManager.createQuery("SELECT p FROM Question p order by rand()")
                    .setMaxResults(count)
                    .getResultList();
        }

    public List<Question> getRandomQuestionByLanguage(int count, String lang) {

            return entityManager.createQuery("SELECT p FROM Question p WHERE p.language_id.language_name = :language_name ORDER BY rand()")
                .setParameter("language_name",lang).setMaxResults(count).getResultList();
    }


    public List<Question> getByQuestion(String question) {
            return  (List<Question>) entityManager.createQuery("SELECT p FROM Question p WHERE p.question = :question")
                    .setParameter("question", question)
                    .getResultList();
        }


    @Override
    public Question saveOrUpdate(Question question) {
            return entityManager.merge(question);
    }

    @Override
    public Question remove(Question question) {
        return null;
    }

    public Long saveOrUpdateId(Question question) {
            return entityManager.merge(question).getId();
    }
}
