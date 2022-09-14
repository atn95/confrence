package main.server.service;

import main.server.model.entity.Account;
import main.server.model.entity.FriendRequest;
import main.server.model.entity.Room;
import main.server.model.entity.UserRelationship;
import main.server.repository.AccountRepository;
import main.server.repository.FriendRequestRepository;
import main.server.repository.RoomRepository;
import main.server.repository.UserRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserRelationshipRepository userRelationshipRepository;

    private final RoomRepository roomRepository;

    private final AccountRepository accountRepository;

    @Autowired
    public FriendRequestService(FriendRequestRepository friendRequestRepository, UserRelationshipRepository userRelationshipRepository, RoomRepository roomRepository, AccountRepository accountRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRelationshipRepository = userRelationshipRepository;
        this.roomRepository = roomRepository;
        this.accountRepository = accountRepository;
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

    public List<FriendRequest> pendingRequestByUserId(Long id) {
        return friendRequestRepository.getPendingFriendsReqByUserID(id);
    }

    public void reject(Long id) {
        friendRequestRepository.deleteById(id);
    }

    public FriendRequest getById(Long id) {
        return friendRequestRepository.findById(id).get();
    }

    public void acceptFriendRequest(Long id) {
        FriendRequest req = getById(id);
        Account request = accountRepository.findById(req.getRequester()).get();
        Account receiver = accountRepository.findById(req.getReceiver()).get();
        Room room = new Room();
        roomRepository.save(room);
        UserRelationship reqToRec = new UserRelationship(request,receiver,room);
        UserRelationship recToReq = new UserRelationship(receiver,request,room);
        userRelationshipRepository.save(reqToRec);
        userRelationshipRepository.save(recToReq);
    }
}
