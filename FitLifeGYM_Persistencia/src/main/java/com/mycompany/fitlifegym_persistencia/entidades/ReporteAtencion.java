
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
    private String asunto;
    private String solucion;
    private LocalDate fecha;
    private EstadoReporte estadoReporte;
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idCategoria;
    private Imagen imagen;
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idCliente;

    public ReporteAtencion() {
    }
    
    
    public ReporteAtencion(String id, String folio, String solucion, LocalDate fecha, EstadoReporte estadoReporte, String idCategoria, Imagen imagen, String idCliente) {
        this.id = id;
        this.folio = folio;
        this.solucion = solucion;
        this.fecha = fecha;
        this.estadoReporte = estadoReporte;
        this.idCategoria = idCategoria;
        this.imagen = imagen;
        this.idCliente = idCliente;
    }
    
    public ReporteAtencion(String id, String folio, String asunto, String solucion, LocalDate fecha, EstadoReporte estadoReporte, String idCategoria, Imagen imagen, String idCliente) {
        this.id = id;
        this.asunto = asunto;
        this.folio = folio;
        this.solucion = solucion;
        this.fecha = fecha;
        this.estadoReporte = estadoReporte;
        this.idCategoria = idCategoria;
        this.imagen = imagen;
        this.idCliente = idCliente;
    }
    
    public ReporteAtencion(String folio, String solucion, LocalDate fecha, EstadoReporte estadoReporte, String idCategoria, Imagen imagen, String idCliente) {
        this.folio = folio;
        this.solucion = solucion;
        this.fecha = fecha;
        this.estadoReporte = estadoReporte;
        this.idCategoria = idCategoria;
        this.imagen = imagen;
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

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
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

    public EstadoReporte getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(EstadoReporte estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
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
        return "ReporteAtencion{" + "id=" + id + ", folio=" + folio + ", solucion=" + solucion + ", fecha=" + fecha + ", estado=" + estadoReporte + ", idCategoria=" + idCategoria + ", imagen=" + imagen + ", idCliente=" + idCliente + '}';
    }
    
}
