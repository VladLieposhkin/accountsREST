package vl.example.accountsrest.service;

import vl.example.accountsrest.dto.AccountDTO;

import java.util.List;

public interface AccountService {

    AccountDTO findOne(Integer accountId);
    List<AccountDTO> findAll();
    AccountDTO create(AccountDTO accountDTO);
    AccountDTO update(AccountDTO accountDTO, Integer accountId);
    void delete(Integer accountId);
//    boolean checkByNumber(String number, Long id);
//    void updateByCoin(Coin coin);
}
