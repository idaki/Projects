package bg.softuni.passwordservice.service;


import bg.softuni.crudservice.crud.CrudService;
import bg.softuni.userservice.models.entity.password.UserPassword;
import org.springframework.stereotype.Service;

@Service
public interface PasswordService extends CrudService<UserPassword, Long> {



}
