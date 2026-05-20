
package com.mycompany.fitlifegym_dtos;

/**
 * Datos que representan las credenciales de un
 * cliente al intentar iniciar sesion
 * @author PC GAMER MASTER RACE
 */
public class LoginDTO {
    private final String correo;
    private final String contrasenia;

    public LoginDTO(String pin, String contrasenia) {
        this.correo = pin;
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    
}
