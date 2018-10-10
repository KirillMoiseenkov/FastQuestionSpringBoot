package CreationShip.demo.service;

import CreationShip.demo.dao.MessageDaoImpl;
import CreationShip.demo.dao.QuestionDaoImpl;
import CreationShip.demo.models.Message;
import CreationShip.demo.models.Question;
import CreationShip.demo.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MessageService implements ISerivce<Message>{

    @Autowired
    MessageDaoImpl messageDao;

    @Autowired
    QuestionDaoImpl questionDao;

    @Autowired
    UserService userService;



    public MessageService(){}

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAll() {
       return messageDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Message getById(Long id) {
        return messageDao.getById(id);
    }

    @Transactional(readOnly = true)
    public List<Message> getMessagesByMyQuestionIds(Set<Long> ids) {

        List<Message> result = new ArrayList<>();

        for(Long id : ids)
        {
            result.addAll(messageDao.getByQuestion(this.questionDao.getById(id)));
        }

        return result;
    }

    @Override
    @Transactional
    public Message save(Message message) {
       return messageDao.save(message);
    }

    @Override
    @Transactional
    public Message remove(Message message) {
        return messageDao.remove(message);
    }

    @Transactional(readOnly = true)
    public List<Message> getByQuestion(Long id){
        return messageDao.getByQuestion(id);
    }

    @Transactional(readOnly = true)
    public List<Message> getMessagesByUser(User user){

        user.setId(userService.getIdByUsername(user.getUsername()));

        return messageDao.getMessagesByUserId(user.getId());
    }

    @Transactional(readOnly = true)
    public List<Message> getByQuestion(Long id, Long lastId){
        return messageDao.getByQuestion(id,lastId);
    }


}
