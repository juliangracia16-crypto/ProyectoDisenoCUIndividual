
package com.mycompany.fitlifegym_persistencia.entidades;

import java.time.LocalDate;
import java.util.Objects;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Julian
 */
public class ReporteAtencion {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private String folio;
    private String solucion;
    private LocalDate fecha;
    private EstadoReporte estado;
    private Categoria categoria;
    private Imagen imagen;
    private Cliente cliente;

    public ReporteAtencion(String id, String folio, String solucion, LocalDate fecha, EstadoReporte estado, Categoria categoria, Imagen imagen, Cliente cliente) {
        this.id = id;
        this.folio = folio;
        this.solucion = solucion;
        this.fecha = fecha;
        this.estado = estado;
        this.categoria = categoria;
        this.imagen = imagen;
        this.cliente = cliente;
    }

    public ReporteAtencion(String folio, String solucion, LocalDate fecha, EstadoReporte estado, Categoria categoria, Imagen imagen, Cliente cliente) {
        this.folio = folio;
        this.solucion = solucion;
        this.fecha = fecha;
        this.estado = estado;
        this.categoria = categoria;
        this.imagen = imagen;
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadoReporte getEstado() {
        return estado;
    }

    public void setEstado(EstadoReporte estado) {
        this.estado = estado;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReporteAtencion other = (ReporteAtencion) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "ReporteAtencion{" + "id=" + id + ", folio=" + folio + ", solucion=" + solucion + ", fecha=" + fecha + ", estado=" + estado + ", imagen=" + imagen + ", cliente=" + cliente + '}';
    }
    
}
