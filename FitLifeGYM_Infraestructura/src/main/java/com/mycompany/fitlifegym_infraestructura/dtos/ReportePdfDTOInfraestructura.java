
package com.mycompany.fitlifegym_infraestructura.dtos;

import java.time.LocalDate;
import java.util.List;

/**
 * Datos necesarios para que el reporte pdf en general pueda ser
 * generado correctamente
 * @author Julian
 */
public class ReportePdfDTOInfraestructura {
    private List<RegistroReporteAdminDTOInfraestructura> registros;
    private LocalDate fechaPdfGenerado;
    private String tituloReporte;
    private byte[] imagen;

    public ReportePdfDTOInfraestructura(List<RegistroReporteAdminDTOInfraestructura> registros, LocalDate fechaPdfGenerado, String tituloReporte, byte[] imagen) {
        this.registros = registros;
        this.fechaPdfGenerado = fechaPdfGenerado;
        this.tituloReporte = tituloReporte;
        this.imagen = imagen;
    }

    public List<RegistroReporteAdminDTOInfraestructura> getRegistros() {
        return registros;
    }

    public LocalDate getFechaPdfGenerado() {
        return fechaPdfGenerado;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public byte[] getImagen() {
        return imagen;
    }
    
    
    public void setRegistros(List<RegistroReporteAdminDTOInfraestructura> registros){
        this.registros = registros;
    }

}
