
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public record ReporteAtencionGeneradoDTO(
        String folio,
        String categoria,
        String estado,
        LocalDate fecha,
        String solucion,
        String imagen,
        String cliente
    ) {

}
