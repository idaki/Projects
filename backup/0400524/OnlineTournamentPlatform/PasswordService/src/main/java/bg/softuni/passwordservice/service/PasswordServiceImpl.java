package bg.softuni.passwordservice.service;



import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.userservice.models.entity.password.UserPassword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class PasswordServiceImpl extends CrudServiceImpl<UserPassword, Long> implements PasswordService {

    public PasswordServiceImpl(JpaRepository<UserPassword, Long> repository) {
        super(repository);
    }
}
