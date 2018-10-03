package CreationShip.demo.service;

import CreationShip.demo.dao.RoleDaoImpl;
import CreationShip.demo.dao.UserDaoImpl;
import CreationShip.demo.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements ISerivce<User>{

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private RoleDaoImpl roleDao;

    @Transactional
    public Boolean validateUsernameAndPassword(String username, String password) {
        return this.userDao.getUserByUsernameAndPassword(username, password) != null;
    }

    @Transactional
    public User getUserByUsername(String username)
    {
        return this.userDao.getUserByUsername(username);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return this.userDao.getAll();
    }

    @Transactional
    public Long getIdByUsername(String username) {
        return userDao.getIdByUsername(username);
    }

    @Override
    @Transactional
    public User getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setRole_id(roleDao.getById(1L));
        return this.userDao.save(user);
    }

    @Override
    @Transactional
    public User remove(User user) {
        return null;
    }
}
