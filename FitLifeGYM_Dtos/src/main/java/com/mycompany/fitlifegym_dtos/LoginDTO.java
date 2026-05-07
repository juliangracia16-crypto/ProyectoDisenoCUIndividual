/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
