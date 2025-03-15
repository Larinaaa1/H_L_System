/*
package ru.hpclab.hl.module1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.hpclab.hl.module1.dto.ClientDTO;
import ru.hpclab.hl.module1.service.ClientService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    private ClientService clientService; // НЕ MockBean

    private ObjectMapper objectMapper = new ObjectMapper();

    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
        clientService = Mockito.mock(ClientService.class); // МОКИРУЕМ вручную!
        clientDTO = new ClientDTO();
       // clientDTO.setId(1L);
        clientDTO.setFullName("Иван Иванов");
        clientDTO.setDriverLicense("1234567890");
        clientDTO.setPhoneNumber("+7 (999) 123-45-67");
    }

    @Test
    void getAllClients_shouldReturnClientsList() throws Exception {
        when(clientService.getAllClients()).thenReturn(List.of(clientDTO));

        mvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].fullName").value("Иван Иванов"))
                .andExpect(jsonPath("$[0].driverLicense").value("1234567890"))
                .andExpect(jsonPath("$[0].phoneNumber").value("+7 (999) 123-45-67"));
    }

    @Test
    void getClientById_shouldReturnClient() throws Exception {
        when(clientService.getClientById(1L)).thenReturn(clientDTO);

        mvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.fullName").value("Иван Иванов"))
                .andExpect(jsonPath("$.driverLicense").value("1234567890"))
                .andExpect(jsonPath("$.phoneNumber").value("+7 (999) 123-45-67"));
    }

    @Test
    void addClient_shouldReturnCreatedClient() throws Exception {
        when(clientService.saveClient(any(ClientDTO.class))).thenReturn(clientDTO);

        mvc.perform(post("/clients")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.fullName").value("Иван Иванов"))
                .andExpect(jsonPath("$.driverLicense").value("1234567890"))
                .andExpect(jsonPath("$.phoneNumber").value("+7 (999) 123-45-67"));
    }

    @Test
    void updateClient_shouldReturnUpdatedClient() throws Exception {
        when(clientService.updateClient(anyLong(), any(ClientDTO.class))).thenReturn(clientDTO);

        mvc.perform(put("/clients/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.fullName").value("Иван Иванов"))
                .andExpect(jsonPath("$.driverLicense").value("1234567890"))
                .andExpect(jsonPath("$.phoneNumber").value("+7 (999) 123-45-67"));
    }

    @Test
    void deleteClient_shouldReturnNoContent() throws Exception {
        mvc.perform(delete("/clients/1"))
                .andExpect(status().isOk());

        Mockito.verify(clientService, Mockito.times(1)).deleteClient(1L);
    }
}
 */