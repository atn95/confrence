package main.server.repository;

import main.server.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    @Query(value = "SELECT * FROM account WHERE account.email =:email", nativeQuery = true)
    Account findByEmail(@Param("email") String email);

    @Transactional
    @Query(value = "SELECT * FROM account WHERE account.email ILIKE %:term%", nativeQuery = true)
    List<Account> searchEmail(@Param("term") String term);

}
