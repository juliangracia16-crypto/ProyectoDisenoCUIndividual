
package com.mycompany.fitlifegym_persistencia.dtos;

import java.time.LocalDate;

/**
 * DTO auxiliar utilizado para mapear resultados de consultas
 * agregadas de MongoDB en la capa de persistencia.
 *
 * Permite recibir información relacionada proveniente de
 * operaciones lookup y proyecciones personalizadas.
 * 
 * @author Julian
 */
public class ReporteAtencionPersistenciaDTO {
    private String folio;
    private String asunto;
    private String solucion;
    private CategoriaPersistenciaDTO categoria;
    private LocalDate fecha;
    private EstadoReportePersistenciaDTO estadoReporte;
    private ImagenPersistenciaDTO imagen;
    private ClientePersistenciaDTO cliente;

    public ReporteAtencionPersistenciaDTO() {
    }
    
    public ReporteAtencionPersistenciaDTO(String folio, String asunto, String solucion, CategoriaPersistenciaDTO categoria, LocalDate fecha, EstadoReportePersistenciaDTO estado, ImagenPersistenciaDTO imagen, ClientePersistenciaDTO cliente) {
        this.folio = folio;
        this.asunto = asunto;
        this.solucion = solucion;
        this.categoria = categoria;
        this.fecha = fecha;
        this.estadoReporte = estado;
        this.imagen = imagen;
        this.cliente = cliente;
    }
    
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    public String getFolio() {
        return folio;
    }

    public String getSolucion() {
        return solucion;
    }

    public CategoriaPersistenciaDTO getCategoria() {
        return categoria;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public EstadoReportePersistenciaDTO getEstadoReporte() {
        return estadoReporte;
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

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public void setCategoria(CategoriaPersistenciaDTO categoria) {
        this.categoria = categoria;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstadoReporte(EstadoReportePersistenciaDTO estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public void setImagen(ImagenPersistenciaDTO imagen) {
        this.imagen = imagen;
    }

    public void setCliente(ClientePersistenciaDTO cliente) {
        this.cliente = cliente;
    }
    
}
