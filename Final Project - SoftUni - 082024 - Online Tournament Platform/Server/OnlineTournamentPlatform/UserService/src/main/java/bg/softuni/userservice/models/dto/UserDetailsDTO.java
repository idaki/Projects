package bg.softuni.userservice.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import bg.softuni.userservice.models.entity.user.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsDTO {
 private Long id;
 private String username;
 @JsonProperty("first_name")
 private String firstName;
 @JsonProperty("last_name")
 private String lastName;
 private String email;
 private String avatar;

 // Constructor to initialize the fields using User object
 public UserDetailsDTO(User user) {
  this.id = user.getId();
  this.username = user.getUsername();
  this.email = user.getEmail();
  if (user.getUserProfile() != null) {
   this.firstName = user.getUserProfile().getFirstName();
   this.lastName = user.getUserProfile().getLastName();
   this.avatar = user.getUserProfile().getAvatar();
  }
 }
}
