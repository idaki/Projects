package bg.softuni.userservice.service.crud;

import java.util.List;
import java.util.Optional;

public interface CrudService <T,ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    void deleteById(ID id);

    T update(ID id, T entity);
}
