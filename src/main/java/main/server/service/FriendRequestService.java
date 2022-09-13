package main.server.service;

import main.server.model.entity.Account;
import main.server.model.entity.FriendRequest;
import main.server.repository.FriendRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;

    @Autowired
    public FriendRequestService(FriendRequestRepository friendRequestRepository) {
        this.friendRequestRepository = friendRequestRepository;
    }


    public FriendRequest newFriendRequest(Account req, Account rec) throws Exception {
        try {
            FriendRequest newReq = new FriendRequest(req, rec);
            friendRequestRepository.save(newReq);
            return newReq;
        } catch (Exception e) {
            throw new Exception("Error Saving to DB");
        }
    }
}
