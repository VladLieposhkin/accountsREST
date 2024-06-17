package vl.example.accountsrest.validator;

import vl.example.accountsrest.dto.CoinDTO;
import vl.example.accountsrest.exception.CustomBadRequestException;
import vl.example.accountsrest.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CoinValidator implements Validator {

    private final CoinService coinService;

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(CoinDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CoinDTO coinDTO = (CoinDTO) target;

//        if (coinService.checkByCode(coinDTO.getCode(), coinDTO.getId()))
//            errors.rejectValue("code", "", "Coin with same Code already present");
//
//        if (coinService.checkByName(coinDTO.getName(), coinDTO.getId()))
//            errors.rejectValue("name", "", "Coin with same Name already present");

        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldErrors().stream()
                    .map(e -> String.join(" - ", e.getField(), e.getDefaultMessage()))
                    .collect(Collectors.joining("\n"));

            throw new CustomBadRequestException(errorMessage);
        }
    }
}
