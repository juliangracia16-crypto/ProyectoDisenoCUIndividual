
package com.mycompany.fitlifegym_persistencia.entidades;

/**
 *
 * @author Julian
 */
public class EstadoReporte {
    private String id;
    private String estado;

    public EstadoReporte() {
    }

    public EstadoReporte(String estado) {
        this.estado = estado;
    }

    public EstadoReporte(String id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
