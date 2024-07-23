package bg.softuni.userservice.service.impl;

import bg.softuni.userservice.models.dto.gson.FriendRequestDTO;


import bg.softuni.userservice.models.entity.FriendRequest;
import bg.softuni.userservice.models.entity.user.User;

import bg.softuni.userservice.models.enums.FriendRequestStatus;

import bg.softuni.userservice.repository.FriendRequestRepository;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.FriendRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public FriendRequestServiceImpl(FriendRequestRepository friendRequestRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

@Override
    public FriendRequestDTO sendFriendRequest(Long senderId, Long receiverId) {
        Optional<User> senderOpt = userRepository.findById(senderId);
        Optional<User> receiverOpt = userRepository.findById(receiverId);

        if (senderOpt.isPresent() && receiverOpt.isPresent()) {
            User sender = senderOpt.get();
            User receiver = receiverOpt.get();

            FriendRequest friendRequest = new FriendRequest();
            friendRequest.setSender(sender);
            friendRequest.setReceiver(receiver);
            friendRequest.setStatus(FriendRequestStatus.PENDING);

            FriendRequest savedRequest = friendRequestRepository.save(friendRequest);
            return modelMapper.map(savedRequest, FriendRequestDTO.class);
        }
        throw new IllegalArgumentException("Invalid sender or receiver ID");
    }
@Override
    public FriendRequestDTO respondToFriendRequest(Long requestId, FriendRequestStatus status) {
        Optional<FriendRequest> requestOpt = friendRequestRepository.findById(requestId);

        if (requestOpt.isPresent()) {
            FriendRequest request = requestOpt.get();
            request.setStatus(status);

            if (status == FriendRequestStatus.ACCEPTED) {
                User sender = request.getSender();
                User receiver = request.getReceiver();

                sender.getFriends().add(receiver);
                receiver.getFriends().add(sender);

                userRepository.save(sender);
                userRepository.save(receiver);
            }

            FriendRequest updatedRequest = friendRequestRepository.save(request);
            return modelMapper.map(updatedRequest, FriendRequestDTO.class);
        }
        throw new IllegalArgumentException("Invalid request ID");
    }
}
