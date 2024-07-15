package bg.softuni.userservice.repository;

import bg.softuni.userservice.models.entity.user.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> {
}
