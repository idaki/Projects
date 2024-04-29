package bg.softuni.userservice.service.user;


import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.userservice.models.entity.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends CrudServiceImpl<User, Long> implements UserService {


    public UserServiceImpl(JpaRepository<User, Long> repository) {
        super(repository);
    }
}