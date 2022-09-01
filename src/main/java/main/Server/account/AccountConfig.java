package main.Server.account;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {
    @Bean
    CommandLineRunner commandLineRunner(AccountRepository repository) {
        return (args) -> {
            Account an = new Account("atn95@gmail.com", "asdf1234", "an", "an", "nguyen", (short) 0);
            repository.save(an);
        };
    }

}
