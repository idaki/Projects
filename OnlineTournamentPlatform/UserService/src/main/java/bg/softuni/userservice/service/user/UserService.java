package bg.softuni.userservice.service.user;


import bg.softuni.crudservice.crud.CrudService;
import bg.softuni.userservice.models.entity.user.User;

import java.util.Optional;


public interface UserService extends CrudService<User, Long> {

    Optional<User> findByUsername(String username);


}
