//package bg.softuni.userservice.controller;
//
//import bg.softuni.userservice.models.dto.gson.FriendRequestDTO;
//
//import bg.softuni.userservice.models.enums.FriendRequestStatus;
//import bg.softuni.userservice.service.FriendRequestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/friend-requests")
//public class FriendRequestController {
//
//    private final FriendRequestService friendRequestService;
//
//    @Autowired
//    public FriendRequestController(FriendRequestService friendRequestService) {
//        this.friendRequestService = friendRequestService;
//    }
//
//    @PostMapping
//    public FriendRequestDTO sendFriendRequest(@RequestParam Long senderId, @RequestParam Long receiverId) {
//        return friendRequestService.sendFriendRequest(senderId, receiverId);
//    }
//
//    @PostMapping("/respond")
//    public FriendRequestDTO respondToFriendRequest(@RequestParam Long requestId, @RequestParam FriendRequestStatus status) {
//        return friendRequestService.respondToFriendRequest(requestId, status);
//    }
//}