
package com.mycompany.fitlifegym_dtos;

/**
 * Representa los datos extraidos del administrador 
 * una vez que este ya inicio sesion.
 * @author Julian
 */
public record AdministradorLogueadoDTO(
        String id, String usuario, String nombre
    ) {

}
