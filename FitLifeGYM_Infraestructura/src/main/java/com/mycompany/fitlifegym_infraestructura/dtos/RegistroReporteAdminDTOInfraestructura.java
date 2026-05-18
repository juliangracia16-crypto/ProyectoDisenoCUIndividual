
package com.mycompany.fitlifegym_infraestructura.dtos;

import java.time.LocalDate;

/**
 *
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
