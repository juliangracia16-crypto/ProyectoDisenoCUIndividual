
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public record ReporteIncidenteGeneradoDTO(
        String folio,
        String categoria,
        String estado,
        String asunto,
        LocalDate fecha,
        String descripcion,
        String imagen,
        String cliente
    ) {

}
