
package com.mycompany.fitlifegym_dtos;

/**
 *
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
