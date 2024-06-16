package vl.example.accountsrest.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import vl.example.accountsrest.entity.Client;
import vl.example.accountsrest.entity.Status;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class ClientRepositoryTests {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void givenClientToCreate_whenSave_thenClientCreated() {
        //given
        Client clientToCreate = Client.builder()
                .name("TEST_CLIENT")
                .email("TC@mail.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(Status.ACTIVE)
                .build();
        //when
        Client createdClient = clientRepository.save(clientToCreate);

        //then
        assertThat(createdClient).isNotNull();
        assertThat((createdClient.getId())).isNotNull();
    }

    @Test
    public void givenClientToUpdate_whenSave_thenClientUpdated() {
        //given
        Client clientToCreate = Client.builder()
                .name("TEST_CLIENT")
                .email("TC@mail.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(Status.ACTIVE)
                .build();
        clientRepository.save(clientToCreate);

        Client clientToUpdate = clientRepository.findById(clientToCreate.getId()).orElseThrow(NoSuchElementException::new);
        String updatedName = "UPDATED_TEST_CLIENT";
        clientToUpdate.setName(updatedName);

        //when
        Client updatedClient = clientRepository.save(clientToUpdate);

        //then
        assertThat(updatedClient).isNotNull();
        assertThat(updatedClient.getName()).isEqualTo(updatedName);
    }

    @Test
    public void givenClientId_whenFindById_thenClientFound() {
        //given
        Client clientToCreate = Client.builder()
                .name("TEST_CLIENT")
                .email("TC@mail.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(Status.ACTIVE)
                .build();
        clientRepository.save(clientToCreate);
        Integer clientId = clientToCreate.getId();

        //when
        Optional<Client> foundClient = clientRepository.findById(clientId);

        //then
        assertThat(foundClient.isPresent()).isTrue();
        assertThat(foundClient.get().getId()).isEqualTo(clientId);
    }
}