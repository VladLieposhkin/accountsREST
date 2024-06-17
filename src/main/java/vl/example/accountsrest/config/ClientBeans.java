package vl.example.accountsrest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import vl.example.accountsrest.external.ExternalClientImpl;
import vl.example.accountsrest.repository.CoinRepository;

@Configuration
public class ClientBeans {

    @Bean
    public ExternalClientImpl externalClient(@Value("${accounts.services.external.url:}") String externalBaseUrl,
                                             CoinRepository coinRepository) {
        return new ExternalClientImpl(coinRepository, RestClient.builder()
                .baseUrl(externalBaseUrl)
                .build());
    }
}
