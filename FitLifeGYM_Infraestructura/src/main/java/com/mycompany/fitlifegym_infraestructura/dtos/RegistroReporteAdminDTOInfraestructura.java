
package com.mycompany.fitlifegym_infraestructura.dtos;

import java.time.LocalDate;

/**
 * Representa los datos de un registro para 
 * llenar la tabla del reporte pdf
 * @author Julian
 */
public record RegistroReporteAdminDTOInfraestructura(
        String folio,
        String tipo,
        String asunto,
        String nombreCategoria,
        String nombreEstado,
        LocalDate fecha,
        String nombreCliente
    ) {

}
