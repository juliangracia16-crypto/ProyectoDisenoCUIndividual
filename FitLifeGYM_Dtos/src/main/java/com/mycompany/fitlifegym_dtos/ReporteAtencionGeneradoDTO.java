
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public record ReporteAtencionGeneradoDTO(
        String folio,
        String categoria,
        EstadoReporteDTO estado,
        LocalDate fecha,
        String solucion,
        ImagenDTO imagen,
        String cliente
    ) {

}
