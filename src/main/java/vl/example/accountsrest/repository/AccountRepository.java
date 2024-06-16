package vl.example.accountsrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vl.example.accountsrest.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
