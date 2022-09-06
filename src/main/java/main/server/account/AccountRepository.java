package main.server.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    @Query(value = "SELECT * FROM account WHERE account.email =:email", nativeQuery = true)
    Account findByEmail(@Param("email") String email);

}
