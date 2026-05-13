
package com.mycompany.fitlifegym_persistencia.dtos;

import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import java.time.LocalDate;

/**
 *
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
