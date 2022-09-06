package main.server.friends;

import main.server.account.Account;
import main.server.account.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomConfig {

    @Bean
    CommandLineRunner commandLineRunner(RoomRepository roomRepository, UserRelationshipRepository userRelationshipRepository, AccountRepository accountRepository) {
        return (args ) -> {
            Account an = new Account("atn95@gmail.com", "asdf1234", "an", "an", "nguyen");
            accountRepository.save(an);
            Account atn = new Account("atn955@gmail.com", "asdf1234", "an2", "an2", "nguyen");
            accountRepository.save(atn);
            Account tran = new Account("JTran@gmail.com", "asdf1234", "an2", "an2", "nguyen");
            accountRepository.save(tran);
            Room room1 = new Room();
            roomRepository.save(room1);
            Room room2 = new Room();
            roomRepository.save(room2);
            Room room3 = new Room();
            roomRepository.save(room3);
            UserRelationship friendSelf = new UserRelationship(an,atn,room3);
            userRelationshipRepository.save(friendSelf);
            UserRelationship tr = new UserRelationship(an,tran, room2);
            userRelationshipRepository.save(tr);
        };
    }
}
