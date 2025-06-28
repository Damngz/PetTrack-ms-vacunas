package com.pettrack.vacunas.services;

import com.pettrack.vacunas.models.Vacuna;
import com.pettrack.vacunas.repositories.VacunaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacunaService {
  private final VacunaRepository repo;

  public VacunaService(VacunaRepository repo) {
    this.repo = repo;
  }

  public List<Vacuna> findAll() {
    return repo.findAll();
  }

  public Vacuna findById(Long id) {
    return repo.findById(id).orElse(null);
  }

  public Vacuna save(Vacuna vacuna) {
    return repo.save(vacuna);
  }

  public void deleteById(Long id) {
    repo.deleteById(id);
  }

  public List<Vacuna> findByMascotaId(Long idMascota) {
    return repo.findByIdMascota(idMascota);
  }
}
