package CreationShip.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISerivce <T>{

    List<T> getAll();
    T getById(Long id);
    T saveOrUpdate(T t);
    T remove(T t);

}
