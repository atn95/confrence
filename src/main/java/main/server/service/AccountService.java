package main.server.service;

import main.server.model.entity.Account;
import main.server.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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

    public Account getAccount(String email) {
        try {
            return accountRepository.findByEmail(email);
        } catch (Exception e) {
            throw e;
        }
    }

    public UserDetails loadUserInfo(String email) throws Exception{
        Account account = accountRepository.findByEmail(email);
        if(account != null && account.getEmail().equals(email)) {
            Set<SimpleGrantedAuthority> authorities = new HashSet<>(1);
            authorities.add(new SimpleGrantedAuthority("user"));
            return new User(account.getEmail(), account.getPassword(), authorities);
        } else {
            throw new Exception("User Not Found");
        }

    }
}
