package CreationShip.demo.dao;

import CreationShip.demo.models.Message;

import CreationShip.demo.models.Question;
import CreationShip.demo.models.user.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MessageDaoImpl implements IDAO<Message> {

    private static final Logger log = Logger.getLogger(MessageDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public MessageDaoImpl() {
    }


    public Message getById(long id) {
            return  (Message) entityManager.find(Message.class, id);
      }

    public List<Message> getByQuestion(Question question) {
        return  (List<Message>) entityManager.createQuery("SELECT p FROM Message p WHERE p.question_id = :id")
                    .setParameter("id", question)
                    .getResultList();
      }

    public List<Message> getByQuestion(Long question_id) {


        Question question = new Question();
        question.setId(question_id);

        return (List<Message>) entityManager.createQuery("SELECT p FROM Message p WHERE p.question_id = :question_id")
                    .setParameter("question_id", question).getResultList();

    }

    public List<Message> getMessagesByUserId(Long user_id) {

        User user = new User();
        user.setId(user_id);

        return (List<Message>) entityManager.createQuery("SELECT p FROM Message p WHERE p.user_id = :user_id")
                .setParameter("user_id", user).getResultList();

    }

    public List<Message> getByQuestion(Long question_id, Long lastId) {

        Question question = new Question();
        question.setId(question_id);


        return  (List<Message>) entityManager.createQuery("SELECT p FROM Message p WHERE p.question_id = :id and p.id > :LastId")
                    .setParameter("id", question)
                    .setParameter("LastId", lastId)
                    .getResultList();
    }

    public List<Message> getAll() {
        return  (List<Message>) entityManager.createQuery("SELECT p FROM Message p").getResultList();
    }

    @Override
    public Message getById(Long id) {
        return  (Message) entityManager.find(Message.class, id);
    }


    @Override
    public Message remove(Message message) {
        return null;
    }

    public List<Message> getLastMessage(Long id) {

        return (List<Message>) entityManager.createQuery("SELECT p FROM Message p WHERE p.id > :id")
                    .setParameter("id", id).getResultList();
    }


    public List<Message> getByMessage(String message) {

        return (List<Message>) entityManager.createQuery("SELECT p FROM Recrord p WHERE p.message = :message")
                    .setParameter("message", message)
                    .getResultList();
     }

    @Override
    public Message save(Message message) {

            return entityManager.merge(message);
     }

}

