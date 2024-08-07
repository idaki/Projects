package bg.softuni.userservice.repository;

import bg.softuni.userservice.models.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findAllByUserId(Long userId);
}
