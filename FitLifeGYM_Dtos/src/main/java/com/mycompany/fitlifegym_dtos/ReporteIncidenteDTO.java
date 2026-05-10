
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public record ReporteIncidenteDTO(
        String folio,
        CategoriaDTO categoria,
        EstadoReporteDTO estado,
        String asunto,
        LocalDate fecha,
        String descripcion,
        ImagenDTO imagen,
        ClienteLogueadoDTO cliente
    ) {

}
