
package com.mycompany.fitlifegym_persistencia.entidades;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Objects;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Julian
 */
public class ReporteIncidente {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private String folio;
    private String asunto;
    private String descripcion;
    private LocalDate fecha;
    private EstadoReporte estadoReporte;
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idCategoria;
    private Imagen imagen;
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idCliente;

    public ReporteIncidente() {
    }
    
    public ReporteIncidente(String id, String asunto, String descripcion, LocalDate fecha, EstadoReporte estadoReporte, String idCategoria, Imagen imagen, String idCliente) {
        this.id = id;
        this.folio = this.generarFolio();
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estadoReporte = estadoReporte;
        this.idCategoria = idCategoria;
        this.imagen = imagen;
        this.idCliente = idCliente;
    }

    public ReporteIncidente(String id, String folio, String asunto, String descripcion, LocalDate fecha, EstadoReporte estadoReporte, String idCategoria, Imagen imagen, String idCliente) {
        this.id = id;
        this.folio = folio;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estadoReporte = estadoReporte;
        this.idCategoria = idCategoria;
        this.imagen = imagen;
        this.idCliente = idCliente;
    }
    
    public ReporteIncidente(String asunto, String descripcion, LocalDate fecha, EstadoReporte estadoReporte, String idCategoria, Imagen imagen, String idCliente) {
        this.folio = this.generarFolio();
        this.asunto = asunto;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
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
        final ReporteIncidente other = (ReporteIncidente) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "ReporteIncidente{" + "id=" + id + ", folio=" + folio + ", asunto=" + asunto + ", descripcion=" + descripcion + ", fecha=" + fecha + ", estadoReporte=" + estadoReporte + ", idCategoria=" + idCategoria + ", imagen=" + imagen + ", idCliente=" + idCliente + '}';
    }
    
    private String generarFolio(){
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder generarFolio = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int indice = random.nextInt(caracteres.length());
            generarFolio.append(caracteres.charAt(indice));
        }

        return generarFolio.toString();
    }
}
