
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;
import java.util.List;

/**
 * Datos necesarios para que el reporte pdf sea generado
 * correctamente
 * @author Julian
 */
public record ReportePdfDTO(
        List<RegistroReporteAdminDTO> registros,
        LocalDate fechaPdfGenerado,
        String tituloReporte,
        byte[] imagen
    ) {

}
