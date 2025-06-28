package com.pettrack.vacunas.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vacuna")
public class Vacuna {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_vacuna")
  private Long id;

  @Column(name = "id_mascota")
  private Long idMascota;

  @Column(name = "id_usuario")
  private Long idUsuario;

  @Column
  private String nombre;

  @Column(name = "fecha_aplicacion")
  private Date fechaAplicacion;

  @Column
  private String dosis;

  @Column
  private String observaciones;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getIdMascota() {
    return idMascota;
  }

  public void setIdMascota(Long idMascota) {
    this.idMascota = idMascota;
  }

  public Long getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Long idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Date getFechaAplicacion() {
    return fechaAplicacion;
  }

  public void setFechaAplicacion(Date fechaAplicacion) {
    this.fechaAplicacion = fechaAplicacion;
  }

  public String getDosis() {
    return dosis;
  }

  public void setDosis(String dosis) {
    this.dosis = dosis;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }
}
