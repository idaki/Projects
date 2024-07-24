package bg.softuni.userservice.controller;


import bg.softuni.userservice.models.dto.FriendDTO;
import bg.softuni.userservice.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/friends")
    public List<FriendDTO> getAllFriends(@RequestBody String jwt) {
        String test = jwt;
        List<FriendDTO> friends = friendService.getAllFriends(jwt);
        System.out.println();
        return friends;
    }
}
