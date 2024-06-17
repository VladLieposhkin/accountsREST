package vl.example.accountsrest.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vl.example.accountsrest.dto.CoinDTO;
import vl.example.accountsrest.entity.Coin;
import vl.example.accountsrest.entity.Status;
import vl.example.accountsrest.exception.CustomBadRequestException;
import vl.example.accountsrest.exception.CustomNotFoundException;
import vl.example.accountsrest.mapper.CoinMapper;
import vl.example.accountsrest.repository.CoinRepository;
import vl.example.accountsrest.service.CoinService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CoinServiceImpl implements CoinService {

    private final String NOT_FOUND = "Coin not found. ID = ";
    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;

    @Override
    public List<CoinDTO> findAll() {

        return coinRepository.findAll().stream()
                .map(coinMapper::toDTO)
                .toList();
    }

    @Override
    public CoinDTO findOne(Integer coinId) {

        return coinRepository.findById(coinId)
                .map(coinMapper::toDTO)
                .orElseThrow(() -> new CustomNotFoundException(NOT_FOUND + coinId));
    }

    @Transactional
    @Override
    public CoinDTO create(CoinDTO coinDTO) {

        return Optional.of(coinDTO)
                .map(coinMapper::fromDTO)
                .map(coinRepository::save)
                .map(coinMapper::toDTO)
                .orElseThrow(() -> new CustomBadRequestException("Can't create Coin", Collections.EMPTY_LIST));
    }

    @Transactional
    @Override
    public CoinDTO update(CoinDTO coinDTO, Integer coinId) {

        return coinRepository.findById(coinId)
                .map(entity -> coinMapper.fromDTO(coinDTO, entity))
                .map(coinRepository::saveAndFlush)
                .map(coinMapper::toDTO)
                .orElseThrow(() -> new CustomNotFoundException(NOT_FOUND + coinId));
    }

    @Transactional
    @Override
    public void delete(Integer coinId) {

        Optional<Coin> coin = coinRepository.findById(coinId);

        if (coin.isPresent()) {
            Coin entity = coin.get();
            entity.setStatus(Status.DELETED);
            coinRepository.saveAndFlush(entity);
        }
        else throw new CustomNotFoundException(NOT_FOUND + coinId);
    }

    @Override
    public boolean checkById(Integer coinId) {
        return coinRepository.existsById(coinId);
    }

    @Override
    public boolean checkByCode(String code, Integer coinId) {

        return coinRepository.checkByCodeAndId(code, coinId).isPresent();
    }

    @Override
    public boolean checkByName(String name, Integer coinId) {

        return coinRepository.checkByNameAndId(name, coinId).isPresent();
    }

    @Transactional
    @Override
    public Coin updateByCode(Coin coin) {

        Coin storedCoin = coinRepository.findByCode(coin.getCode())
                .orElseThrow(() -> new CustomNotFoundException(NOT_FOUND + coin.getId()));

        coin.setId(storedCoin.getId());
        coin.setCreatedAt(storedCoin.getCreatedAt());

        coinRepository.save(coin);
        return coin;
    }
}
