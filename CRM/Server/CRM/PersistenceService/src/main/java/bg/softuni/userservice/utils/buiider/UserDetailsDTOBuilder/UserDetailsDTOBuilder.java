package bg.softuni.userservice.utils.buiider.UserDetailsDTOBuilder;

import bg.softuni.userservice.models.dto.UserDetailsDTO;

public interface UserDetailsDTOBuilder {
    UserDetailsDTOBuilder withUsername(String username);

    UserDetailsDTOBuilder withFirstName(String firstName);

    UserDetailsDTOBuilder withLastName(String lastName);

    UserDetailsDTOBuilder withEmail(String email);

    UserDetailsDTOBuilder withAvatar(String avatar);

    UserDetailsDTOBuilder withId(Long id);

    UserDetailsDTO build();
}
