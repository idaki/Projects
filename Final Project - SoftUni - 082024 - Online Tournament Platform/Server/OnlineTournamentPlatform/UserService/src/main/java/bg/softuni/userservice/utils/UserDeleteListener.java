package bg.softuni.userservice.utils;

import bg.softuni.userservice.models.entity.Token;
import bg.softuni.userservice.models.entity.password.Password;
import bg.softuni.userservice.models.entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserDeleteListener implements ApplicationListener<UserDeleteEvent> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void onApplicationEvent(UserDeleteEvent event) {
//        User user = event.getUser();
//
//        // Remove associated Password entity
//        Password password = user.getPassword();
//        if (password != null) {
//            entityManager.remove(entityManager.contains(password) ? password : entityManager.merge(password));
//        }
//
//        // Remove associated Token entities
//        Set<Token> tokens = user.getTokens();
//        if (tokens != null) {
//            for (Token token : tokens) {
//                entityManager.remove(entityManager.contains(token) ? token : entityManager.merge(token));
//            }
//        }
    }
}