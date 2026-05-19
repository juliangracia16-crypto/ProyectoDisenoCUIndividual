
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 * Datos que representan un reporte de atencion
 * una vez que se genero correctamente 
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
