package bg.softuni.userservice.controller;

import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping
    public List<User> getAllFriends(@RequestParam Long userId) {
        return friendService.getAllFriends(userId);
    }
}
