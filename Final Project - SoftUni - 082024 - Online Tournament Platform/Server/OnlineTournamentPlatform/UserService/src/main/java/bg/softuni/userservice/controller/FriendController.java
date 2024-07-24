package bg.softuni.userservice.controller;


import bg.softuni.userservice.models.dto.FriendDTO;
import bg.softuni.userservice.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/friends")
    public ResponseEntity<List<FriendDTO>> getAllFriends(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7); // Remove "Bearer " prefix
        if (jwt.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return bad request if JWT is empty
        }


        return ResponseEntity.ok(friendService.getAllFriends(jwt));
    }
}
