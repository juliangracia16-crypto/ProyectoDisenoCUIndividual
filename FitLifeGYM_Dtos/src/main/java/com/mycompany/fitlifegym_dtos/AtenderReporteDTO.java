
package com.mycompany.fitlifegym_dtos;

/**
 * Datos necesarios para poder atender
 * un reporte de incidente y generar
 * un reporte de atencion
 * @author Julian
 */
public record AtenderReporteDTO(
        String folio,
        String solucion,
        ImagenDTO imagen
    ) {

}
