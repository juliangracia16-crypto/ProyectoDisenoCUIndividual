
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 * Datos que representa un reporte de atencion
 * cuando se utiliza para consultar/lectura.
 * @author Julian
 */
public record ReporteAtencionDTO(
        String folio, 
        String asunto,
        String solucion,
        CategoriaDTO categoria, 
        LocalDate fecha, 
        EstadoReporteDTO estado,
        ImagenDTO imagen,
        ClienteLogueadoDTO cliente
    ) {

}
