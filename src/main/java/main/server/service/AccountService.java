package main.server.service;

import main.server.model.SearchResponseAccountDTO;
import main.server.model.entity.Account;
import main.server.model.entity.FriendRequest;
import main.server.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final FriendRequestService friendRequestService;

    @Autowired
    public AccountService(AccountRepository accountRepository, FriendRequestService friendRequestService) {
        this.accountRepository = accountRepository;
        this.friendRequestService = friendRequestService;
    }

    public Account registerAccount(Account newAccount) {
        try {
            accountRepository.save(newAccount);
            return newAccount;
        } catch (Exception e) {
            throw e;
        }
    }

    public Account getAccount(String email) {
        try {
            return accountRepository.findByEmail(email);
        } catch (Exception e) {
            throw e;
        }
    }

    public Account getAccountByPK(Long id) {
        try {
            return accountRepository.findById(id).get();
        } catch (Exception e) {
            throw e;
        }
    }

    public UserDetails loadUserInfo(String email) throws Exception {
        Account account = accountRepository.findByEmail(email);
        if (account != null && account.getEmail().equals(email)) {
            Set<SimpleGrantedAuthority> authorities = new HashSet<>(1);
            authorities.add(new SimpleGrantedAuthority("user"));
            return new User(account.getEmail(), account.getPassword(), authorities);
        } else {
            throw new Exception("User Not Found");
        }

    }

    public List<SearchResponseAccountDTO> searchByEmail(String[] terms, Long requester_id) {
        HashMap<Long, SearchResponseAccountDTO> accounts = new HashMap<>();
        Account requester = accountRepository.findById(requester_id).get();
        Set<Long> friends_id = new HashSet<>();
        requester.getFriends().forEach((relation) -> {
            friends_id.add(relation.getFriend().getId());
        });

        Set<Long> pendingRequestReceiverId = new HashSet<>();
        List<FriendRequest> pendingReq = friendRequestService.pendingRequestByUserId(requester_id);
        pendingReq.forEach((req) -> {
            pendingRequestReceiverId.add(req.getReceiver());
        });


        for (String term : terms) {
            System.out.println(term);
            List<Account> result = accountRepository.searchEmail(term);
            for (Account acc : result) {
                if (!accounts.containsKey(acc.getId()) && !friends_id.contains(acc.getId())) {
                    SearchResponseAccountDTO dto = new SearchResponseAccountDTO(
                            acc.getId(),
                            acc.getEmail(),
                            acc.getDisplayName(),
                            acc.getFirstName(), acc.getLastName(),
                            acc.getStatus(),
                            acc.getCreatedAt(),
                            acc.getUpdatedAt(),
                            pendingRequestReceiverId.contains(acc.getId()));
                    accounts.put(acc.getId(), dto);
                }
            }
        }
        System.out.println(accounts);
        return accounts.values().stream().toList();
    }
}
