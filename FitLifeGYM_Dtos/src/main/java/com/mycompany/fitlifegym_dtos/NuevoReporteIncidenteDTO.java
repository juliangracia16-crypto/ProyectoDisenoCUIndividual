
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 * Datos necesarios para que un reporte de incidente
 * se pueda generar.
 * @author Julian
 */
public record NuevoReporteIncidenteDTO(
        String asunto,
        CategoriaDTO categoria,
        String descripcion,
        LocalDate fecha,
        ImagenDTO imagen,
        ClienteLogueadoDTO cliente
    ) {

}
