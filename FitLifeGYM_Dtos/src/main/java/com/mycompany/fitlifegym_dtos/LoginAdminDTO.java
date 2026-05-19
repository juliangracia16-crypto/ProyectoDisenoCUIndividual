
package com.mycompany.fitlifegym_dtos;

/**
 * Datos que representan las credenciales
 * del administrador que intenta iniciar sesion
 * @author Julian
 */
public record LoginAdminDTO(
        String usuario, String contrasenia
    ) {

}
