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

    public User getUserByUsernameAndPassword(String username, String password)
    {
        return ((User) this.entityManager
                .createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
                .setParameter("username", username)
                .setParameter("password", password)
                .setMaxResults(1)
                .getResultList()
                .get(0));
    }

    @Override
    public List<User> getAll() {
        return this.entityManager
                .createQuery("SELECT u FROM User u")
                .getResultList();
    }

    public Long getIdByUsername(String username)
    {
        return ((User) this.entityManager
                .createQuery("SELECT u FROM User u WHERE u.username= :username")
                .setParameter("username", username)
                .setMaxResults(1)
                .getResultList()
                .get(0))
                .getId();
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
