package CreationShip.demo.dao;

import java.util.List;

public interface IDAO <T>{

        List<T> getAll();
        T getById(Long id);
        T saveOrUpdate(T t);
        T remove(T t);
}
