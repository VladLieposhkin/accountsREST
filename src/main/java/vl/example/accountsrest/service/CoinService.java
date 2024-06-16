package vl.example.accountsrest.service;

import vl.example.accountsrest.dto.CoinDTO;

import java.util.List;

public interface CoinService {
    List<CoinDTO> findAll();
    CoinDTO findOne(Integer coinId);
    CoinDTO create(CoinDTO coinDTO);
    CoinDTO update(CoinDTO coinDTO, Integer coinId);
    void delete(Integer coinId);
//    boolean checkByCode(String code, Integer coinId);
//    boolean checkByName(String name, Integer coinId);
//    Coin updateByCode(Coin coin);
}
