package vl.example.accountsrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vl.example.accountsrest.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
