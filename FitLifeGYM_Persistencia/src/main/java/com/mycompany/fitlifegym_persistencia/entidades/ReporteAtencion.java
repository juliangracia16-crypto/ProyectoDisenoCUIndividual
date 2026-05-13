
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
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idEstado;
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idCategoria;
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idImagen;
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idCliente;

    public ReporteAtencion(String id, String folio, String solucion, LocalDate fecha, String idEstado, String idCategoria, String idImagen, String idCliente) {
        this.id = id;
        this.folio = folio;
        this.solucion = solucion;
        this.fecha = fecha;
        this.idEstado = idEstado;
        this.idCategoria = idCategoria;
        this.idImagen = idImagen;
        this.idCliente = idCliente;
    }

    public ReporteAtencion(String folio, String solucion, LocalDate fecha, String idEstado, String idCategoria, String idImagen, String idCliente) {
        this.folio = folio;
        this.solucion = solucion;
        this.fecha = fecha;
        this.idEstado = idEstado;
        this.idCategoria = idCategoria;
        this.idImagen = idImagen;
        this.idCliente = idCliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
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

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(String idImagen) {
        this.idImagen = idImagen;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
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
        return "ReporteAtencion{" + "id=" + id + ", folio=" + folio + ", solucion=" + solucion + ", fecha=" + fecha + ", estado=" + idEstado + ", imagen=" + idImagen + ", cliente=" + idCliente + '}';
    }
    
}
