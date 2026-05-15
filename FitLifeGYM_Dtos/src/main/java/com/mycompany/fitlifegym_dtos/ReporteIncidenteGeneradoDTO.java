
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public record ReporteIncidenteGeneradoDTO(
        String folio,
        String categoria,
        EstadoReporteDTO estado,
        String asunto,
        LocalDate fecha,
        String descripcion,
        ImagenDTO imagen,
        String cliente
    ) {

}
