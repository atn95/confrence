package main.server.controller;

import main.server.utils.security.EncryptionUtil;
import main.server.utils.security.JwtTokenUtil;
import main.server.model.Account;
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

@RestController
@Transactional
@RequestMapping(value = ServerConstants.API_ROUTE + "/user")
public class AccountController {
    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService, JwtTokenUtil jwtTokenUtil) {
        this.accountService = accountService;
        this.jwtTokenUtil = jwtTokenUtil;
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

}
