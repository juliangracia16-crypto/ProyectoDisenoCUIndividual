
package com.mycompany.fitlifegym_dtos;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public record ReporteAtencionDTO(
        String folio, 
        CategoriaDTO categoria, 
        LocalDate fecha, 
        EstadoReporteDTO estado, 
        String idCliente, 
        String asunto
    ) {

}
