package vl.example.accountsrest.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vl.example.accountsrest.entity.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT a FROM Account a " +
            "WHERE a.number = :number and (:id is NULL OR a.id <> :id)")
    Optional<Account> checkByNumberAndId(@Param("number") String accountNumber, @Param("id") Integer accountId);
}
