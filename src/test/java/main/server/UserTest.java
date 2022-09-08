package main.server;

import main.server.model.Account;
import main.server.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DataJpaTest
public class UserTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository repo;

    @Test
    public void TestCreateUser(){
        Account newAcc = new Account();
        newAcc.setEmail("atn95@gmail.com");
        newAcc.setPassword("Something");
        Account saved = repo.save(newAcc);
        Account pull = entityManager.find(Account.class, saved.getId());
        assertThat(newAcc.getId()).isEqualTo(pull.getId());
        System.out.println(newAcc.toString());
    }
}
