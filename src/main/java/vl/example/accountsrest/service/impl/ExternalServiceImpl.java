package vl.example.accountsrest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vl.example.accountsrest.repository.CoinRepository;
import vl.example.accountsrest.service.ExternalService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ExternalServiceImpl implements ExternalService {

    private final CoinRepository coinRepository;
//    private final ExternalFeignClient externalClient;
//
//    @Override
//    public List<CoinModel> getExternalData() {
//
//        String request = coinRepository.findAll().stream()
//                .map(Coin::getCode)
//                .collect(Collectors.joining(","));
//
//        return externalClient.getExternalData(request);
//    }
}
