
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 *
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
