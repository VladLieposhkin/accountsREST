package vl.example.accountsrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vl.example.accountsrest.entity.Coin;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Integer> {
}
