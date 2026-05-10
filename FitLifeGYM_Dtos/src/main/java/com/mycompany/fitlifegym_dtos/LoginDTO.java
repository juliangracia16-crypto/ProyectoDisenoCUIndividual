
package com.mycompany.fitlifegym_dtos;

/**
 *
 * @author PC GAMER MASTER RACE
 */
public class LoginDTO {
    private final String pin;
    private final String contrasenia;

    public LoginDTO(String pin, String contrasenia) {
        this.pin = pin;
        this.contrasenia = contrasenia;
    }

    public String getPin() {
        return pin;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    
}
