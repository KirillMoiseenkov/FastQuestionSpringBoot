package CreationShip.demo.service;

import CreationShip.demo.dao.MessageDaoImpl;
import CreationShip.demo.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService implements ISerivce<Message>{

    @Autowired
    MessageDaoImpl messageDao;



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
    public List<Message> getByQuestion(Long id, Long lastId){
        return messageDao.getByQuestion(id,lastId);
    }


}
