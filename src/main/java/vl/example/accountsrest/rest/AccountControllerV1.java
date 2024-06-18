package vl.example.accountsrest.rest;

import vl.example.accountsrest.dto.AccountDTO;
import vl.example.accountsrest.service.AccountService;
import vl.example.accountsrest.validator.AccountValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/accounts")
public class AccountControllerV1 {

    private final AccountService accountService;
    private final AccountValidator accountValidator;

    @PostMapping
    public ResponseEntity<AccountDTO> create(@RequestBody @Valid AccountDTO accountDTO,
                                             BindingResult bindingResult) {
        accountValidator.validate(accountDTO, bindingResult);
        return new ResponseEntity<>(accountService.create(accountDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll() {

        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findOne(@PathVariable("id") Integer accountId) {

        return new ResponseEntity<>(accountService.findOne(accountId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> update(@PathVariable("id") Integer accountId,
                                             @RequestBody @Valid AccountDTO accountDTO,
                                             BindingResult bindingResult) {
        accountValidator.validate(accountDTO, bindingResult);
        return new ResponseEntity<>(accountService.update(accountDTO, accountId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer accountId) {

        accountService.delete(accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
