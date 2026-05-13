
package com.mycompany.fitlifegym_persistencia.dtos;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public class ReporteAtencionPersistenciaDTO {
    private String folio;
    private String solucion;
    private CategoriaPersistenciaDTO categoria;
    private LocalDate fecha;
    private EstadoReportePersistenciaDTO estado;
    private ImagenPersistenciaDTO imagen;
    private ClientePersistenciaDTO cliente;

    public ReporteAtencionPersistenciaDTO() {
    }
    
    public ReporteAtencionPersistenciaDTO(String folio, String solucion, CategoriaPersistenciaDTO categoria, LocalDate fecha, EstadoReportePersistenciaDTO estado, ImagenPersistenciaDTO imagen, ClientePersistenciaDTO cliente) {
        this.folio = folio;
        this.solucion = solucion;
        this.categoria = categoria;
        this.fecha = fecha;
        this.estado = estado;
        this.imagen = imagen;
        this.cliente = cliente;
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

    public EstadoReportePersistenciaDTO getEstado() {
        return estado;
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

    public void setEstado(EstadoReportePersistenciaDTO estado) {
        this.estado = estado;
    }

    public void setImagen(ImagenPersistenciaDTO imagen) {
        this.imagen = imagen;
    }

    public void setCliente(ClientePersistenciaDTO cliente) {
        this.cliente = cliente;
    }
    
}
