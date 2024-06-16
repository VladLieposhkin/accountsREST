package vl.example.accountsrest.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vl.example.accountsrest.dto.AccountDTO;
import vl.example.accountsrest.entity.Account;
import vl.example.accountsrest.entity.Status;
import vl.example.accountsrest.exception.CustomNotFoundException;
import vl.example.accountsrest.mapper.AccountMapper;
import vl.example.accountsrest.repository.AccountRepository;
import vl.example.accountsrest.service.AccountService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final String NOT_FOUND = "Account not found. ID = ";
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountDTO findOne(Integer accountId) {

        return accountRepository.findById(accountId)
                .map(accountMapper::toDTO)
                .orElseThrow(() -> new CustomNotFoundException(NOT_FOUND + accountId));
    }

    @Override
    public List<AccountDTO> findAll() {

        return accountRepository.findAll().stream()
                .map(accountMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public AccountDTO create(AccountDTO accountDTO) {

        return Optional.of(accountDTO)
                .map(accountMapper::fromDTO)
                .map(accountRepository::save)
                .map(accountMapper::toDTO)
                .orElseThrow();
    }

    @Transactional
    @Override
    public AccountDTO update(AccountDTO accountDTO, Integer accountId) {

        return accountRepository.findById(accountId)
                .map(account -> accountMapper.fromDTO(accountDTO, account))
                .map(accountRepository::saveAndFlush)
                .map(accountMapper::toDTO)
                .orElseThrow(() -> new CustomNotFoundException(NOT_FOUND + accountId));
    }

    @Transactional
    @Override
    public void delete(Integer accountId) {

        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isPresent()) {
            Account entity = account.get();
            entity.setStatus(Status.DELETED);
            accountRepository.saveAndFlush(entity);
        }
        else throw new CustomNotFoundException(NOT_FOUND + accountId);
    }

//    @Override
//    public boolean checkByNumber(String number, Long id) {
//
//        return accountRepository.checkByNumberAndId(number, id).isPresent();
//    }
//
//    @Transactional
//    @Override
//    public void updateByCoin(Coin coin) {
//
//        accountRepository.updateAccountsByCoin(coin, coin.getPrice());
//    }
}
