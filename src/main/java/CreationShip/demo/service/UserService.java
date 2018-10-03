package CreationShip.demo.service;

import CreationShip.demo.dao.UserDaoImpl;
import CreationShip.demo.models.user.Role;
import CreationShip.demo.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements ISerivce<User>{

    @Autowired
    private UserDaoImpl userDao;

    @Override
    @Transactional
    public List<User> getAll() {
        return null;
    }

    @Override
    @Transactional
    public User getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setRole_id(new Role(1L, "user"));
        return this.userDao.save(user);
    }

    @Override
    @Transactional
    public User remove(User user) {
        return null;
    }
}
