package main.server.controller;

import main.server.model.entity.FriendRequest;
import main.server.service.FriendRequestService;
import main.server.service.WebSocketService;
import main.server.utils.security.EncryptionUtil;
import main.server.utils.security.JwtTokenUtil;
import main.server.model.entity.Account;
import main.server.service.AccountService;
import main.server.model.LoginRequest;
import main.server.model.LoginResponse;
import main.server.constants.ServerConstants;
import main.server.utils.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@Transactional
@RequestMapping(value = ServerConstants.API_ROUTE + "/user")
public class AccountController {
    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private final AccountService accountService;

    @Autowired
    private final FriendRequestService friendRequestService;

    @Autowired
    private final WebSocketService webSocketService;

    @Autowired
    public AccountController(AccountService accountService, JwtTokenUtil jwtTokenUtil, FriendRequestService friendRequestService, WebSocketService webSocketService) {
        this.accountService = accountService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.friendRequestService = friendRequestService;
        this.webSocketService = webSocketService;
    }
    @GetMapping
    public String getRoute() {
        return "account controller";
    }

    @GetMapping(value = "/account/{email}")
    public Account getUser(@PathVariable String email) {
        try {
            return accountService.getAccount(email);
        } catch (Exception e) {
            throw new ApiException(404, "No Account Found With Email");
        }
    }

    @PostMapping(value = "/register")
    public Account register(@RequestBody Account newAccount) {
        try {
            System.out.println(newAccount);
           return accountService.registerAccount(newAccount);
        } catch (Exception e) {
            throw new ApiException(400, "Error Registering Account");
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest info, HttpServletResponse res) throws Exception {
        //authenticate(info.getEmail(), info.getPassword());
        final UserDetails userDetails = accountService.loadUserInfo(info.getEmail());
        final Account user = accountService.getAccount(info.getEmail());
        if(info.getPassword().equals(user.getPassword())) {
            final String token = jwtTokenUtil.generateToken(userDetails);
            final String encryptedToken = EncryptionUtil.encrypt(token);
            //@TODO: more with cookies later for peristence(read from auth)
            Cookie cookieToken = new Cookie("Authorization", encryptedToken);
            cookieToken.setSecure(true);
            cookieToken.setHttpOnly(true);
            cookieToken.setPath("/");
            res.addCookie(cookieToken);
//            res.setHeader("Set-Cookie", "Authorization="+encryptedToken+"; Secure; HttpOnly; SameSite=None; Path=/; Max-Age=99999999;");
            LoginResponse resp = new LoginResponse(user, encryptedToken);
            return ResponseEntity.ok(resp);
        } else{
            throw new ApiException(401, "Invalid Credentials");
        }
    }

    @PostMapping(value = "/friendrequest")
    public ResponseEntity<?> addFriend(@RequestBody HashMap<String, Long> request) {
        try {
            Account requester = accountService.getAccountByPK(request.get("requester_id"));
            Account receiver = accountService.getAccountByPK(request.get("receiver_id"));
            FriendRequest req = friendRequestService.newFriendRequest(requester, receiver);
            System.out.println(req);
            return ResponseEntity.ok(request);
        }catch(Exception e) {
            throw new ApiException(400, "Bad Request");
        }
    }

}
