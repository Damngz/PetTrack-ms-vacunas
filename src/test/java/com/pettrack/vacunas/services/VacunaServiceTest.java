package com.pettrack.vacunas.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pettrack.vacunas.models.Vacuna;
import com.pettrack.vacunas.repositories.VacunaRepository;

@ExtendWith(MockitoExtension.class)
class VacunaServiceTest {

    @Mock
    private VacunaRepository repo;

    @InjectMocks
    private VacunaService service;

    private Vacuna vacuna1, vacuna2;
    private Date fechaAplicacion;

    @BeforeEach
    void setUp() {
        fechaAplicacion = new Date();
        vacuna1 = new Vacuna();
        vacuna1.setId(1L);
        vacuna1.setNombre("Rabia");
        vacuna1.setFechaAplicacion(fechaAplicacion);
        vacuna1.setDosis("1ra dosis");
        vacuna1.setObservaciones("Sin reacciones");
        vacuna1.setIdMascota(1L);
        vacuna1.setIdUsuario(100L);

        vacuna2 = new Vacuna();
        vacuna2.setId(2L);
        vacuna2.setNombre("Moquillo");
        vacuna2.setFechaAplicacion(fechaAplicacion);
        vacuna2.setDosis("Refuerzo");
        vacuna2.setObservaciones("Aplicada en clínica");
        vacuna2.setIdMascota(2L);
        vacuna2.setIdUsuario(101L);
    }

    @Test
    void testFindAll_ReturnsAllVacunas() {
        when(repo.findAll()).thenReturn(Arrays.asList(vacuna1, vacuna2));

        List<Vacuna> result = service.findAll();

        assertEquals(2, result.size());
        assertEquals("Rabia", result.get(0).getNombre());
        assertEquals("Refuerzo", result.get(1).getDosis());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testFindById_WhenExists_ReturnsVacuna() {
        when(repo.findById(1L)).thenReturn(Optional.of(vacuna1));

        Vacuna result = service.findById(1L);

        assertNotNull(result);
        assertEquals("Rabia", result.getNombre());
        assertEquals(fechaAplicacion, result.getFechaAplicacion());
        verify(repo, times(1)).findById(1L);
    }

    @Test
    void testFindById_WhenNotExists_ReturnsNull() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        Vacuna result = service.findById(99L);

        assertNull(result);
        verify(repo, times(1)).findById(99L);
    }

    @Test
    void testSave_ReturnsSavedVacuna() {
        Vacuna nuevaVacuna = new Vacuna();
        nuevaVacuna.setNombre("Leptospirosis");
        nuevaVacuna.setIdMascota(3L);

        when(repo.save(any(Vacuna.class))).thenReturn(vacuna1);

        Vacuna saved = service.save(nuevaVacuna);

        assertEquals(1L, saved.getId());
        assertEquals("Rabia", saved.getNombre());
        verify(repo, times(1)).save(nuevaVacuna);
    }

    @Test
    void testDeleteById_ExecutesSuccessfully() {
        doNothing().when(repo).deleteById(1L);

        service.deleteById(1L);

        verify(repo, times(1)).deleteById(1L);
    }

    @Test
    void testFindByMascotaId_ReturnsMascotasVacunas() {
        when(repo.findByIdMascota(1L)).thenReturn(Arrays.asList(vacuna1));

        List<Vacuna> result = service.findByMascotaId(1L);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getIdMascota());
        assertEquals("Rabia", result.get(0).getNombre());
        verify(repo, times(1)).findByIdMascota(1L);
    }
}