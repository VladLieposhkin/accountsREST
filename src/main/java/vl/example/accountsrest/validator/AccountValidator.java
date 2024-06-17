package vl.example.accountsrest.validator;

import vl.example.accountsrest.dto.AccountDTO;
import vl.example.accountsrest.exception.CustomBadRequestException;
import vl.example.accountsrest.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountValidator implements Validator {

    private final AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(AccountDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        AccountDTO accountDTO = (AccountDTO) target;

        if (accountDTO.getClient().getId() == null)
            errors.rejectValue("client", "", "Field Client should have a value");

        if (accountDTO.getCoin().getId() == null)
            errors.rejectValue("coin", "", "Field Coin should have a value");

//        if (accountService.checkByNumber(accountDTO.getNumber(), accountDTO.getId()))
//            errors.rejectValue("number", "", "Account with same Number already present");

        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldErrors().stream()
                    .map(e -> String.join(" - ", e.getField(), e.getDefaultMessage()))
                    .collect(Collectors.joining("\n"));

            throw new CustomBadRequestException(errorMessage);
        }
    }
}
