
package com.mycompany.fitlifegym_persistencia.dtos;

import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import java.time.LocalDate;

/**
 * DTO auxiliar utilizada para las consultas con filtros.
 * @author Julian
 */
public record FiltrosConsultaHistorialReportesDTO(
        String cliente, 
        EstadoReporte estado,
        Categoria categoria,
        LocalDate fechaDesde,
        LocalDate fechaHasta
    ) {
    
}
