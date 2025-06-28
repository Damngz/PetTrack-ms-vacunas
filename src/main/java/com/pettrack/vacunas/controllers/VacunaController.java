package com.pettrack.vacunas.controllers;

import com.pettrack.vacunas.models.Vacuna;
import com.pettrack.vacunas.services.VacunaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vacunas")
public class VacunaController {
  private final VacunaService service;

  public VacunaController(VacunaService service) {
    this.service = service;
  }

  @GetMapping
  public List<Vacuna> getAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Vacuna getById(@PathVariable Long id) {
    return service.findById(id);
  }

  @PostMapping
  public Vacuna create(@RequestBody Vacuna vacuna) {
    return service.save(vacuna);
  }

  @PutMapping("/{id}")
  public Vacuna update(@PathVariable Long id, @RequestBody Vacuna vacuna) {
    vacuna.setId(id);
    return service.save(vacuna);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    service.deleteById(id);
  }

  @GetMapping("/mascota/{idMascota}")
  public List<Vacuna> getByMascota(@PathVariable Long idMascota) {
    return service.findByMascotaId(idMascota);
  }
}
