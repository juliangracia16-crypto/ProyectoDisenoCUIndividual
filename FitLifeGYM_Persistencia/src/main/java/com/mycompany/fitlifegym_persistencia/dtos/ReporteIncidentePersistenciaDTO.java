
package com.mycompany.fitlifegym_persistencia.dtos;

import java.time.LocalDate;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 * DTO auxiliar utilizado para mapear resultados de consultas agregadas de
 * MongoDB en la capa de persistencia.
 *
 * Permite recibir información relacionada proveniente de operaciones lookup y
 * proyecciones personalizadas.
 * @author Julian
 */
public class ReporteIncidentePersistenciaDTO {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private String folio;
    private CategoriaPersistenciaDTO categoria;
    private EstadoReportePersistenciaDTO estadoReporte;
    private String asunto;
    private LocalDate fecha;
    private String descripcion;
    private ImagenPersistenciaDTO imagen;
    private ClientePersistenciaDTO cliente;

    public ReporteIncidentePersistenciaDTO() {
    }

    public ReporteIncidentePersistenciaDTO(String id, String folio, CategoriaPersistenciaDTO categoria, EstadoReportePersistenciaDTO estadoReporte, String asunto, LocalDate fecha, String descripcion, ImagenPersistenciaDTO imagen, ClientePersistenciaDTO cliente) {
        this.id = id;
        this.folio = folio;
        this.categoria = categoria;
        this.estadoReporte = estadoReporte;
        this.asunto = asunto;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.cliente = cliente;
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

    public CategoriaPersistenciaDTO getCategoria() {
        return categoria;
    }

    public EstadoReportePersistenciaDTO getEstadoReporte() {
        return estadoReporte;
    }

    public String getAsunto() {
        return asunto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ImagenPersistenciaDTO getImagen() {
        return imagen;
    }

    public ClientePersistenciaDTO getCliente() {
        return cliente;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setCategoria(CategoriaPersistenciaDTO categoria) {
        this.categoria = categoria;
    }

    public void setEstadoReporte(EstadoReportePersistenciaDTO estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(ImagenPersistenciaDTO imagen) {
        this.imagen = imagen;
    }

    public void setCliente(ClientePersistenciaDTO cliente) {
        this.cliente = cliente;
    }
    
}
