package bg.softuni.userservice.utils.buiider.UserDetailsDTOBuilder;

import bg.softuni.userservice.models.dto.UserDetailsDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsDTOBuilderImpl implements UserDetailsDTOBuilder {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
    private Long id;


    @Override
    public UserDetailsDTOBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public UserDetailsDTOBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Override
    public UserDetailsDTOBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public UserDetailsDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UserDetailsDTOBuilder withAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    @Override
    public UserDetailsDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public UserDetailsDTO build() {
        UserDetailsDTO userDetails = new UserDetailsDTO();
        userDetails.setUsername(username);
        userDetails.setFirstName(firstName);
        userDetails.setLastName(lastName);
        userDetails.setEmail(email);
        userDetails.setAvatar(avatar);
        userDetails.setId(id);
        return userDetails;
    }
}
