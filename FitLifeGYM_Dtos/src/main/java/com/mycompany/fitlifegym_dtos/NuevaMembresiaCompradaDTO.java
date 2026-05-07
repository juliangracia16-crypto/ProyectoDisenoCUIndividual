/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 *
 * @author Diego
 */
public class NuevaMembresiaCompradaDTO {
    private Long idMembresiaComprada;
    private NuevaMembresiaDTO membresia;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double precioPagado;
    private EstadoDTO estado;

    public NuevaMembresiaCompradaDTO(Long idMembresiaComprada, NuevaMembresiaDTO membresia, LocalDate fechaInicio, LocalDate fechaFin, Double precioPagado, EstadoDTO estado) {
        this.idMembresiaComprada = idMembresiaComprada;
        this.membresia = membresia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioPagado = precioPagado;
        this.estado = estado;
    }

    public NuevaMembresiaCompradaDTO(NuevaMembresiaDTO membresia, LocalDate fechaInicio, LocalDate fechaFin, Double precioPagado, EstadoDTO estado) {
        this.membresia = membresia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioPagado = precioPagado;
        this.estado = estado;
    }

    public Long getIdMembresiaComprada() {
        return idMembresiaComprada;
    }

    public void setIdMembresiaComprada(Long idMembresiaComprada) {
        this.idMembresiaComprada = idMembresiaComprada;
    }

    public NuevaMembresiaDTO getMembresia() {
        return membresia;
    }

    public void setMembresia(NuevaMembresiaDTO membresia) {
        this.membresia = membresia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getPrecioPagado() {
        return precioPagado;
    }

    public void setPrecioPagado(Double precioPagado) {
        this.precioPagado = precioPagado;
    }

    public EstadoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoDTO estado) {
        this.estado = estado;
    }
    
    
}
