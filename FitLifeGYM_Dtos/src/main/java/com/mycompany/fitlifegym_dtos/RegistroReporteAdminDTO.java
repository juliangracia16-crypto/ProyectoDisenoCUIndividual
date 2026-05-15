
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public record RegistroReporteAdminDTO(
        String folio,
        TipoReporteDTO tipo,
        String asunto,
        CategoriaDTO categoria,
        EstadoReporteDTO estado,
        LocalDate fecha,
        ClienteLogueadoDTO cliente
    ) {

}
