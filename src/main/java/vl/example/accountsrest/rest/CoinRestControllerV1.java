package vl.example.accountsrest.rest;

import vl.example.accountsrest.dto.CoinDTO;
import vl.example.accountsrest.service.CoinService;
import vl.example.accountsrest.validator.CoinValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/coins")
public class CoinRestControllerV1 {

    private final CoinService coinService;
    private final CoinValidator coinValidator;

    @PostMapping
    public ResponseEntity<CoinDTO> create(@RequestBody @Valid CoinDTO coinDTO,
                                          BindingResult bindingResult) {
        coinValidator.validate(coinDTO, bindingResult);
        return new ResponseEntity<>(coinService.create(coinDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CoinDTO>> findAll() {

        return new ResponseEntity<>(coinService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoinDTO> findOne(@PathVariable("id") Integer coinId) {

       return new ResponseEntity<>(coinService.findOne(coinId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoinDTO> update(@PathVariable("id") Integer coinId,
                                          @RequestBody @Valid CoinDTO coinDTO,
                                          BindingResult bindingResult) {
        coinValidator.validate(coinDTO, bindingResult);
        return new ResponseEntity<>(coinService.update(coinDTO, coinId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer coinId) {

        coinService.delete(coinId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
