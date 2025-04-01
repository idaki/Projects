package bg.softuni.userservice.utils.buiider.UserBuilder;

import bg.softuni.userservice.models.entity.user.User;

public interface UserBuilder {
    UserBuilder withUsername(String username);
    UserBuilder withEmail(String email);
    UserBuilder withProfile(String firstName, String lastName, String avatar);
    UserBuilder withRole(String  role);
    UserBuilder withPassword(String password);
    User build();
}
