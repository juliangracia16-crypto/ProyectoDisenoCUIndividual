
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 * Representan un reporte incidente o atencion, segun su tipo.
 * Se utiliza para agrupar los dos tipos de reportes en una sola
 * tabla en la vista de administrador.
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
