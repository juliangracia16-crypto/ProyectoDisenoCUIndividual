
package com.mycompany.fitlifegym_dtos;

/**
 * Representa la entidad imagen, transformada para 
 * solo lectura.
 * @author Julian
 */
public record ImagenDTO(
        byte[] imagen, String id, String mimeType
    ) {

}
