
package com.mycompany.fitlifegym_persistencia.entidades;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public class MembresiaComprada {
    private Long idMembresiaComprada;
    private Membresia membresia;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double precioPagado;
    private Estado estado;

    public MembresiaComprada() {}

    public MembresiaComprada(Long idMembresiaComprada, Membresia membresia, LocalDate fechaInicio, LocalDate fechaFin, Double precioPagado, Estado estado) {
        this.idMembresiaComprada = idMembresiaComprada;
        this.membresia = membresia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioPagado = precioPagado;
        this.estado = estado;
    }

    public MembresiaComprada(Membresia membresia, LocalDate fechaInicio, LocalDate fechaFin, Double precioPagado, Estado estado) {
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

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "MembresiaComprada{" + "idMembresiaComprada=" + idMembresiaComprada + ", membresia=" + membresia.getTipoMembresia() + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", precioPagado=" + precioPagado + ", estado=" + estado + '}';
    }
}
