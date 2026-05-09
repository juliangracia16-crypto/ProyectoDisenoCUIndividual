
package com.mycompany.fitlifegym_persistencia.entidades;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Julian
 */
public class ReporteIncidente {
    private String id;
    private String folio;
    private String asunto;
    private String descripcion;
    private LocalDate fecha;
    private EstadoReporte estado;
    private Categoria categoria;
    private Imagen imagen;
    private Cliente cliente;

    public ReporteIncidente() {
    }

    public ReporteIncidente(String id, String asunto, String descripcion, LocalDate fecha, EstadoReporte estado, Categoria categoria, Imagen imagen, Cliente cliente) {
        this.id = id;
        this.folio = this.generarFolio();
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.categoria = categoria;
        this.imagen = imagen;
        this.cliente = cliente;
    }

    public ReporteIncidente(String asunto, String descripcion, LocalDate fecha, EstadoReporte estado, Categoria categoria, Imagen imagen, Cliente cliente) {
        this.folio = this.generarFolio();
        this.asunto = asunto;
        this.descripcion = descripcion;
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

    public EstadoReporte getEstado() {
        return estado;
    }

    public void setEstado(EstadoReporte estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
        final ReporteIncidente other = (ReporteIncidente) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "ReporteIncidente{" + "id=" + id + ", folio=" + folio + ", asunto=" + asunto + ", descripcion=" + descripcion + ", fecha=" + fecha + ", estado=" + estado + ", categoria=" + categoria + ", imagen=" + imagen + ", cliente=" + cliente + '}';
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
