package vl.example.accountsrest.service;

import vl.example.accountsrest.dto.AccountDTO;
import vl.example.accountsrest.entity.Coin;

import java.util.List;

public interface AccountService {

    AccountDTO findOne(Integer accountId);
    List<AccountDTO> findAll();
    AccountDTO create(AccountDTO accountDTO);
    AccountDTO update(AccountDTO accountDTO, Integer accountId);
    void delete(Integer accountId);
    boolean checkByNumber(String accountNumber, Integer accountId);
    void updateByCoin(Coin coin);
}
