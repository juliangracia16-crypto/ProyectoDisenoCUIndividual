
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public record FiltrosConsultaHistorialReportesNegocioDTO(
        String cliente, 
        EstadoReporteDTO estado,
        CategoriaDTO categoria,
        LocalDate fechaDesde,
        LocalDate fechaHasta
    ) {
    
}
