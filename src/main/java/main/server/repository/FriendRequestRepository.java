package main.server.repository;

import main.server.model.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    @Transactional
    @Query(value = "SELECT * FROM friend_request WHERE friend_request.requester_id=:id",nativeQuery = true)
    List<FriendRequest> getPendingFriendsReqByUserID(@Param("id") Long id);
}
