
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public record FiltrosConsultaIncidenteDTO(
        EstadoReporteDTO estado,
        CategoriaDTO categoria,
        LocalDate fechaDesde,
        LocalDate fechaHasta
    ) {

}
