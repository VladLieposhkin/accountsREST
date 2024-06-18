package vl.example.accountsrest.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;
import vl.example.accountsrest.dto.AccountDTO;
import vl.example.accountsrest.dto.ClientDTO;
import vl.example.accountsrest.dto.CoinDTO;
import vl.example.accountsrest.service.ClientService;
import vl.example.accountsrest.service.CoinService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
public class AccountRestControllerV1IT extends AbstractRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CoinService coinService;

    @Test
    void givenAccountToCreateDTO_whenCreate_thenSuccessResponse() throws Exception {
        // given
        ClientDTO clientDTO = ClientDTO.builder()
                .name("TEST_CLIENT")
                .email("TC1@mail.com")
                .build();
        clientDTO = clientService.create(clientDTO);

        CoinDTO coinDTO = CoinDTO.builder()
                .code("100")
                .name("TEST_COIN")
                .build();
        coinDTO = coinService.create(coinDTO);

        AccountDTO accountToCreateDTO = AccountDTO.builder()
                .number("TEST_ACCOUNT")
                .client(clientDTO)
                .coin(coinDTO)
                .build();
        // when
        ResultActions result = mockMvc.perform(post("/api/v1/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(accountToCreateDTO)));
        // then
        result
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number", CoreMatchers.equalTo("TEST_ACCOUNT")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", CoreMatchers.equalTo("ACTIVE")));
    }
}
