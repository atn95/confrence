package main.server.account;

import main.server.constants.ServerConstants;
import main.server.utils.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ServerConstants.API_ROUTE + "/user")
public class AccountController {

    public final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
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
            throw new ApiException(400, "Account Already Exist");
        }
    }

}
