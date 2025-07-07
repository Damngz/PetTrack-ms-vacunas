package com.pettrack.vacunas.controllers;

import com.pettrack.vacunas.models.Vacuna;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pettrack.vacunas.models.Vacuna;
import com.pettrack.vacunas.services.VacunaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VacunaController.class)
class VacunaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacunaService vacunaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeListarTodasLasVacunas() throws Exception {
        Vacuna v1 = new Vacuna();
        v1.setId(1L);
        v1.setNombre("Rabia");

        Vacuna v2 = new Vacuna();
        v2.setId(2L);
        v2.setNombre("Parvovirus");

        when(vacunaService.findAll()).thenReturn(List.of(v1, v2));

        mockMvc.perform(get("/vacunas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Rabia"))
                .andExpect(jsonPath("$[1].nombre").value("Parvovirus"));
    }
    @Test
    void debeRetornarVacunaPorId() throws Exception {
        Vacuna vacuna = new Vacuna();
        vacuna.setId(10L);
        vacuna.setNombre("Moquillo");

        when(vacunaService.findById(10L)).thenReturn(vacuna);

        mockMvc.perform(get("/vacunas/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.nombre").value("Moquillo"));
    }

    @Test
    void debeCrearVacuna() throws Exception {
        Vacuna entrada = new Vacuna();
        entrada.setNombre("Moquillo");

        Vacuna salida = new Vacuna();
        salida.setId(5L);
        salida.setNombre("Moquillo");

        when(vacunaService.save(any(Vacuna.class))).thenReturn(salida);

        mockMvc.perform(post("/vacunas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.nombre").value("Moquillo"));
    }

    @Test
    void debeActualizarVacuna() throws Exception {
        Vacuna entrada = new Vacuna();
        entrada.setNombre("Actualizada");

        Vacuna salida = new Vacuna();
        salida.setId(9L);
        salida.setNombre("Actualizada");

        when(vacunaService.save(any(Vacuna.class))).thenReturn(salida);

        mockMvc.perform(put("/vacunas/9")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(9))
                .andExpect(jsonPath("$.nombre").value("Actualizada"));
    }

    @Test
    void debeEliminarVacuna() throws Exception {
        doNothing().when(vacunaService).deleteById(7L);

        mockMvc.perform(delete("/vacunas/7"))
                .andExpect(status().isOk());

        verify(vacunaService, times(1)).deleteById(7L);
    }

    @Test
    void debeListarVacunasPorIdMascota() throws Exception {
        Vacuna vacuna = new Vacuna();
        vacuna.setId(1L);
        vacuna.setNombre("Leptospirosis");
        vacuna.setIdMascota(100L);

        when(vacunaService.findByMascotaId(100L)).thenReturn(List.of(vacuna));

        mockMvc.perform(get("/vacunas/mascota/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].idMascota").value(100))
                .andExpect(jsonPath("$[0].nombre").value("Leptospirosis"));
    }

    
}
