
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Julian
 */
public record ReportePdfDTO(
        List<RegistroReporteAdminDTO> registros,
        LocalDate fechaPdfGenerado,
        String tituloReporte,
        byte[] imagen
    ) {

}
