package vl.example.accountsrest.external;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vl.example.accountsrest.service.AccountService;
import vl.example.accountsrest.service.CoinService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExternalDataProcessingJob {

    private final ExternalClient externalClient;
    private final CoinService coinService;
    private final AccountService accountService;

    @Scheduled(fixedDelay = 60, initialDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void processExternalData() {

        log.info("RETRIEVING: Started...");

        CompletableFuture.supplyAsync(externalClient::getExternalData)
                .thenApply(list ->
                                list.stream()
                                        .map(CoinModel::toCoin)
                                        .peek(coinService::updateByCode)
                                        .peek(accountService::updateByCoin)
                                        .toList()
                                        .size()
                )
                .exceptionally(exception -> {
                    log.info("RETRIEVING: Unable to process data from remote host, {}", exception.getMessage());
                    return 0;
                })
                .thenAccept(result -> log.info("RETRIEVING: Data about {} coins processed", result))
                .join();
    }

    @Scheduled(fixedDelay = 60, initialDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void processInternalData() {

        log.info("CHECKING: Started...");

        CompletableFuture.supplyAsync(coinService::findAll)
                .thenApply(coins -> coins.stream()
                        .peek(coin -> {
                            if (Math.abs(coin.getChange7d()) > 5)
                                log.warn("CHECKING: Coin {} significantly changed. % = {}",
                                        coin.getName(), coin.getChange7d());
                        })
                        .toList()
                        .size()
                )
                .exceptionally(exception -> {
                    log.error("CHECKING: Unable to process data from local host, {}", exception.getMessage());
                    return 0;
                })
                .thenAccept(result -> log.info("CHECKING: Data about {} coins processed", result))
                .join();
    }
}
