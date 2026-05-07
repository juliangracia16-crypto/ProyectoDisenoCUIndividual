
package com.mycompany.fitlifegym_persistencia.entidades;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public class Membresia {
    private Long idMembresia;
    private TipoMembresia tipoMembresia;
    private Double precio;
    private LocalDate vigencia;

    public Membresia() {
    }

    public Membresia(Long idMembresia, TipoMembresia tipoMembresia, Double precio, LocalDate vigencia) {
        this.idMembresia = idMembresia;
        this.tipoMembresia = tipoMembresia;
        this.precio = precio;
        this.vigencia = vigencia;
    }

    public Membresia(TipoMembresia tipoMembresia, Double precio, LocalDate vigencia) {
        this.tipoMembresia = tipoMembresia;
        this.precio = precio;
        this.vigencia = vigencia;
    }

    public Membresia(TipoMembresia tipoMembresia, Double precio) {
        this.tipoMembresia = tipoMembresia;
        this.precio = precio;
    }
    
    

    public Long getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(Long idMembresia) {
        this.idMembresia = idMembresia;
    }

    public TipoMembresia getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(TipoMembresia tipoMembresia) {
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

    @Override
    public String toString() {
        return "Membresia{" + "idMembresia=" + idMembresia + ", tipoMembresia=" + tipoMembresia + ", precio=" + precio + ", vigencia=" + vigencia + '}';
    }
}
