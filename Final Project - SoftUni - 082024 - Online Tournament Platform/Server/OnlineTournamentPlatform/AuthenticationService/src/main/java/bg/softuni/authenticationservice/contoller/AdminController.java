package bg.softuni.authenticationservice.contoller;



import bg.softuni.userservice.models.dto.UserDetailsDTO;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {


    private final UserService userService; // Assuming you have a UserService to handle business logic

    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }


    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build(); // Return no content status
    }

    @PostMapping("/search")
    public ResponseEntity<UserDetailsDTO> searchUser(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {

        if (username == null && firstName == null && lastName == null) {
            return ResponseEntity.badRequest().body(null); // Return 400 Bad Request if all parameters are null
        }

        UserDetailsDTO userDetails = userService.findUserByDetails(username, firstName,lastName ) ;

        if (userDetails == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDetails);
    }
}
