package vl.example.accountsrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AccountsRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsRestApplication.class, args);
    }

}
