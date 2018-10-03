package CreationShip.demo.dao;

import CreationShip.demo.models.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements IDAO<User>
{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User save(User user) {
        try {

            this.entityManager.persist(user);

        }catch (EntityExistsException e){

            e.printStackTrace();
            user = null;

        }
        return user;
    }

    @Override
    public User remove(User user) {
        return null;
    }
}
