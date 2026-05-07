package com.mycompany.fitlifegym_persistencia.entidades;

import java.time.LocalDate;

/**
 *
 * @author Julian
 */
public class Cliente {

    private Long idCliente;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String contrasenia;
    private LocalDate fechaNacimiento;
    private String pin;
    private MembresiaComprada membresíaComprada;
    

    public Cliente() {
    }

    public Cliente(Long idCliente, String nombre, String apellidos, String correo, String telefono, String contrasenia, LocalDate fechaNacimiento, String pin, MembresiaComprada membresíaComprada) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.fechaNacimiento = fechaNacimiento;
        this.pin = pin;
        this.membresíaComprada = membresíaComprada;
    }

    public Cliente(Long idCliente, String nombre, String apellidos, String correo, String telefono, String contrasenia, LocalDate fechaNacimiento, String pin) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.fechaNacimiento = fechaNacimiento;
        this.pin = pin;
    }

    public Cliente(String nombre, String apellidos, String correo, String telefono, String contrasenia, LocalDate fechaNacimiento, String pin, MembresiaComprada membresíaComprada) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.fechaNacimiento = fechaNacimiento;
        this.pin = pin;
        this.membresíaComprada = membresíaComprada;
    }
    
    public Cliente(Long idCliente, String nombre, String apellidos, String correo, String telefono, String pin, String contrasenia, MembresiaComprada membresiaComprada) {
    this.idCliente = idCliente;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.correo = correo;
    this.telefono = telefono;
    this.pin = pin;
    this.contrasenia = contrasenia;
    this.membresíaComprada = membresiaComprada;
}

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public MembresiaComprada getMembresíaComprada() {
        return membresíaComprada;
    }

    public void setMembresíaComprada(MembresiaComprada membresíaComprada) {
        this.membresíaComprada = membresíaComprada;
    }

    

    
}
