package bg.softuni.crudservice.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public abstract class  CrudServiceImpl<T, ID> implements CrudService<T, ID> {

    private final JpaRepository<T, ID> repository;

    public CrudServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return this.repository.findById(id);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public T update(ID id, T entity) {
        // Assuming the entity has an ID field
        if (repository.existsById(id)) {
            return repository.save(entity);
        }
        return null;
    }

}
