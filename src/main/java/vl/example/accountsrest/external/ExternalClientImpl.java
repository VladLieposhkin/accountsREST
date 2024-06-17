package vl.example.accountsrest.external;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import vl.example.accountsrest.entity.Coin;
import vl.example.accountsrest.repository.CoinRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ExternalClientImpl implements ExternalClient {

    private static final ParameterizedTypeReference<List<CoinModel>>
            PRODUCTS_TYPE_REFERENCE = new ParameterizedTypeReference<>() {};

    private final CoinRepository coinRepository;

    private final RestClient restClient;

    @Override
    public List<CoinModel> getExternalData() {

        String request = coinRepository.findAll().stream()
                .map(Coin::getCode)
                .collect(Collectors.joining(","));

        return restClient
                .get()
                .uri("/?id={request}", request)
                .retrieve()
                .body(PRODUCTS_TYPE_REFERENCE);
    }
}
