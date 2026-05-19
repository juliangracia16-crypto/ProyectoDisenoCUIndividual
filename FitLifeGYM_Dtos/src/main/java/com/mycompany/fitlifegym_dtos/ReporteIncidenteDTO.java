
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 * Datos que representa un reporte de incidente 
 * cuando se utiliza para consultar/lectura
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
