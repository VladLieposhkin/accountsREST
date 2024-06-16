package vl.example.accountsrest.mapper;

import vl.example.accountsrest.dto.ClientDetailedDTO;
import vl.example.accountsrest.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientDetailedMapper {

    private final AccountMapper accountMapper;

//  Read Client with details
    public ClientDetailedDTO toDetailedDTO(Client entity) {

        return ClientDetailedDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .accounts(entity.getAccounts().stream()
                        .map(accountMapper::toDTO)
                        .toList()
                )
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .status(entity.getStatus())
                .build();
    }

}