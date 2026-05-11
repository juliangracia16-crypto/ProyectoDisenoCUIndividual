
package com.mycompany.fitlifegym_dtos;

/**
 *
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
