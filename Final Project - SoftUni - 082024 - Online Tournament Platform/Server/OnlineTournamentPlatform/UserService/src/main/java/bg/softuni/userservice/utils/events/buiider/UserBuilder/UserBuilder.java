package bg.softuni.userservice.utils.events.buiider.UserBuilder;

import bg.softuni.userservice.models.entity.authorisation.Role;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.models.enums.RoleEnum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface UserBuilder {
    UserBuilder withUsername(String username);
    UserBuilder withEmail(String email);
    UserBuilder withProfile(String firstName, String lastName, String avatar);
    UserBuilder withRole(RoleEnum role);
    UserBuilder withPassword(String password, BCryptPasswordEncoder passwordEncoder);
    User build();
}
