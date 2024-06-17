package vl.example.accountsrest.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vl.example.accountsrest.dto.ClientDTO;
import vl.example.accountsrest.entity.Client;
import vl.example.accountsrest.entity.Status;
import vl.example.accountsrest.exception.CustomNotFoundException;
import vl.example.accountsrest.mapper.ClientDetailedMapper;
import vl.example.accountsrest.mapper.ClientMapper;
import vl.example.accountsrest.repository.ClientRepository;
import vl.example.accountsrest.service.ClientService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final String NOT_FOUND = "Client not found. ID = ";
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ClientDetailedMapper clientDetailedMapper;

    @Transactional
    @Override
    public ClientDTO create(ClientDTO clientDTO) {

        return Optional.of(clientDTO)
                .map(clientMapper::fromDTO)
                .map(clientRepository::save)
                .map(clientMapper::toDTO)
                .orElseThrow();
    }

    @Override
    public List<ClientDTO> findAll() {

        return clientRepository.findAll().stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    @Override
    public ClientDTO findOne(Integer clientId) {

        return clientRepository.findById(clientId)
                .map(clientMapper::toDTO)
                .orElseThrow(() -> new CustomNotFoundException(NOT_FOUND + clientId));
    }

//    @Override
//    public ClientDetailedDTO findOneWithDetails(Long clientId) {
//
//        return clientRepository.findByIdWithDetails(clientId)
//                .map(clientDetailedMapper::toDetailedDTO)
//                .orElseThrow(() -> new CustomNotFoundException(NOT_FOUND + clientId));
//    }

    @Transactional
    @Override
    public ClientDTO update(ClientDTO clientDTO, Integer clientId) {

        return clientRepository.findById(clientId)
                .map(entity -> clientMapper.fromDTO(clientDTO, entity))
                .map(clientRepository::saveAndFlush)
                .map(clientMapper::toDTO)
                .orElseThrow(() -> new CustomNotFoundException(NOT_FOUND + clientId));
    }

    @Transactional
    @Override
    public void delete(Integer clientId) {

        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isPresent()) {
            Client entity = client.get();
            entity.setStatus(Status.DELETED);
            clientRepository.saveAndFlush(entity);
        }
        else throw new CustomNotFoundException(NOT_FOUND + clientId);
    }

    @Override
    public boolean checkById(Integer clientId) {

        return clientRepository.existsById(clientId);
    }

    @Override
    public boolean checkByEmail(String email, Integer id) {

        return clientRepository.checkByEmailAndId(email, id).isPresent();
    }

    @Override
    public boolean checkByName(String name, Integer id) {

        return clientRepository.checkByNameAndId(name, id).isPresent();
    }
}
