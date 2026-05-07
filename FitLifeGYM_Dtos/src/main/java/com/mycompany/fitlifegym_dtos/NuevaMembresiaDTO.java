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
public class NuevaMembresiaDTO {
    private Long idMembresia;
    private TipoMembresiaDTO tipoMembresia;
    private Double precio;
    private LocalDate vigencia;

    public NuevaMembresiaDTO(Long idMembresia, TipoMembresiaDTO tipoMembresia, Double precio, LocalDate vigencia) {
        this.idMembresia = idMembresia;
        this.tipoMembresia = tipoMembresia;
        this.precio = precio;
        this.vigencia = vigencia;
    }

    public NuevaMembresiaDTO(TipoMembresiaDTO tipoMembresia, Double precio, LocalDate vigencia) {
        this.tipoMembresia = tipoMembresia;
        this.precio = precio;
        this.vigencia = vigencia;
    }

    public Long getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(Long idMembresia) {
        this.idMembresia = idMembresia;
    }

    public TipoMembresiaDTO getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(TipoMembresiaDTO tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDate getVigencia() {
        return vigencia;
    }

    public void setVigencia(LocalDate vigencia) {
        this.vigencia = vigencia;
    }
    
    
}
