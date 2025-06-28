package com.pettrack.vacunas.repositories;

import com.pettrack.vacunas.models.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacunaRepository extends JpaRepository<Vacuna, Long> {
  List<Vacuna> findByIdMascota(Long idMascota);
}
