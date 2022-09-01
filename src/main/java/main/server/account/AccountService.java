package main.server.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account newAccount) {
        try {
            accountRepository.save(newAccount);
            return newAccount;
        }catch (Exception e) {
            throw e;
        }
    }
}
