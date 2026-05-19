
package com.mycompany.fitlifegym_dtos;

/**
 * Representa la entidad Categoria 
 * transformada a solo lectura.
 * @author Julian
 */
public record CategoriaDTO(
        String id,
        String categoria
    ) {

    @Override
    public String toString() {
        return categoria;
    }
}
