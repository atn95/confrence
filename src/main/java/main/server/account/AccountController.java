package main.server.account;

import main.server.EncryptionUtil;
import main.server.JwtTokenUtil;
import main.server.constants.ServerConstants;
import main.server.utils.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ServerConstants.API_ROUTE + "/user")
public class AccountController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    public final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.accountService = accountService;
        this.authenticationManager = authenticationManager;
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
    public ResponseEntity<?> login(@RequestBody LoginRequest info) throws Exception {
//        authenticate(info.getEmail(), info.getPassword());
        final UserDetails userDetails = accountService.loadUserInfo(info.getEmail());
        final Account user = accountService.getAccount(info.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        LoginResponse resp = new LoginResponse(user, EncryptionUtil.encrypt(token));
        return ResponseEntity.ok(resp);
    }

}
