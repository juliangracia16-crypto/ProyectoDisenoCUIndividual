
package com.mycompany.fitlifegym_dtos;

/**
 * Representa la entidad EstadoReporte transformada
 * a solo lectura.
 * @author Julian
 */
public record EstadoReporteDTO(
        String id,
        String estado
    ) {
    
    @Override
    public String toString() {
        return estado;
    }
}
