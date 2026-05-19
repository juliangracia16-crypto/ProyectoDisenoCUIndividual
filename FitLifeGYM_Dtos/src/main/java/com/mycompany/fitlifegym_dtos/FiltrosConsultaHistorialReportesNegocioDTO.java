
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 * Datos que puede llevar una consulta con filtros.
 * Utilizada para consultar con filtros los reportes de atencion
 * e incidentes.
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
