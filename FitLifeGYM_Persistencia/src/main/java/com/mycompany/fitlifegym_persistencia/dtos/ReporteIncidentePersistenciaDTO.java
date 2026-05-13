
package com.mycompany.fitlifegym_persistencia.dtos;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public class ReporteIncidentePersistenciaDTO {
    private String folio;
    private CategoriaPersistenciaDTO categoria;
    private EstadoReportePersistenciaDTO estado;
    private String asunto;
    private LocalDate fecha;
    private String descripcion;
    private ImagenPersistenciaDTO imagen;
    private ClientePersistenciaDTO cliente;

    public ReporteIncidentePersistenciaDTO() {
    }
    
    public ReporteIncidentePersistenciaDTO(String folio, CategoriaPersistenciaDTO categoria, EstadoReportePersistenciaDTO estado, String asunto, LocalDate fecha, String descripcion, ImagenPersistenciaDTO imagen, ClientePersistenciaDTO cliente) {
        this.folio = folio;
        this.categoria = categoria;
        this.estado = estado;
        this.asunto = asunto;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.cliente = cliente;
    }

    public String getFolio() {
        return folio;
    }

    public CategoriaPersistenciaDTO getCategoria() {
        return categoria;
    }

    public EstadoReportePersistenciaDTO getEstado() {
        return estado;
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

    public void setEstado(EstadoReportePersistenciaDTO estado) {
        this.estado = estado;
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
