package CreationShip.demo.dao;

import CreationShip.demo.models.Question;
import CreationShip.demo.models.user.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements IDAO<Role>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public Role getById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public Role remove(Role role) {
        return null;
    }
}
