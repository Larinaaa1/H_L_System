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
import ru.hpclab.hl.module1.dto.CarDTO;
import ru.hpclab.hl.module1.service.CarService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
@AutoConfigureMockMvc
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    private CarService carService; // НЕ MockBean

    private ObjectMapper objectMapper = new ObjectMapper();

    private CarDTO carDTO;

    @BeforeEach
    void setUp() {
        carService = Mockito.mock(CarService.class); // МОКИРУЕМ вручную!
        carDTO = new CarDTO();
        carDTO.setId(1L);
        carDTO.setVin("ABC123");
        carDTO.setModel("Toyota Camry");
        carDTO.setColor("Black");
        carDTO.setRentalCostPerDay(50.0);
        carDTO.setCity("Moscow");
        carDTO.setSalonName("Salon 1");
    }

    @Test
    void getAllCars_shouldReturnCarsList() throws Exception {
        when(carService.getAllCars()).thenReturn(List.of(carDTO));

        mvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].vin").value("ABC123"))
                .andExpect(jsonPath("$[0].model").value("Toyota Camry"))
                .andExpect(jsonPath("$[0].color").value("Black"))
                .andExpect(jsonPath("$[0].rentalCostPerDay").value(50.0))
                .andExpect(jsonPath("$[0].city").value("Moscow"))
                .andExpect(jsonPath("$[0].salonName").value("Salon 1"));
    }

    @Test
    void getCarById_shouldReturnCar() throws Exception {
        when(carService.getCarById(1L)).thenReturn(carDTO);

        mvc.perform(get("/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vin").value("ABC123"))
                .andExpect(jsonPath("$.model").value("Toyota Camry"))
                .andExpect(jsonPath("$.color").value("Black"))
                .andExpect(jsonPath("$.rentalCostPerDay").value(50.0))
                .andExpect(jsonPath("$.city").value("Moscow"))
                .andExpect(jsonPath("$.salonName").value("Salon 1"));
    }

    @Test
    void addCar_shouldReturnCreatedCar() throws Exception {
        when(carService.saveCar(any(CarDTO.class))).thenReturn(carDTO);

        mvc.perform(post("/cars")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(carDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vin").value("ABC123"))
                .andExpect(jsonPath("$.model").value("Toyota Camry"))
                .andExpect(jsonPath("$.color").value("Black"))
                .andExpect(jsonPath("$.rentalCostPerDay").value(50.0))
                .andExpect(jsonPath("$.city").value("Moscow"))
                .andExpect(jsonPath("$.salonName").value("Salon 1"));
    }

    @Test
    void updateCar_shouldReturnUpdatedCar() throws Exception {
        when(carService.updateCar(anyLong(), any(CarDTO.class))).thenReturn(carDTO);

        mvc.perform(put("/cars/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(carDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vin").value("ABC123"))
                .andExpect(jsonPath("$.model").value("Toyota Camry"))
                .andExpect(jsonPath("$.color").value("Black"))
                .andExpect(jsonPath("$.rentalCostPerDay").value(50.0))
                .andExpect(jsonPath("$.city").value("Moscow"))
                .andExpect(jsonPath("$.salonName").value("Salon 1"));
    }

    @Test
    void deleteCar_shouldReturnNoContent() throws Exception {
        mvc.perform(delete("/cars/1"))
                .andExpect(status().isOk());

        Mockito.verify(carService, Mockito.times(1)).deleteCar(1L);
    }

    @Test
    void getAvailableCarInfo_shouldReturnAvailableCar() throws Exception {
        when(carService.getAvailableCarInfo("Moscow", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 10)))
                .thenReturn(carDTO);

        mvc.perform(get("/cars/availability")
                        .param("city", "Moscow")
                        .param("startDate", "2023-10-01")
                        .param("endDate", "2023-10-10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vin").value("ABC123"))
                .andExpect(jsonPath("$.model").value("Toyota Camry"))
                .andExpect(jsonPath("$.color").value("Black"))
                .andExpect(jsonPath("$.rentalCostPerDay").value(50.0))
                .andExpect(jsonPath("$.city").value("Moscow"))
                .andExpect(jsonPath("$.salonName").value("Salon 1"));
    }
}
 */