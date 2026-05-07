package com.mycompany.fitlifegym_persistencia.entidades;

/**
 *
 * @author Julian
 */
public class Tarjeta {
    private String numero;
    private String titular;
    private String fechaExpiracion;
    private String cvv;

    public Tarjeta() {
    }
    
    public Tarjeta(String numero, String titular, String fechaExpiracion, String cvv) {
        this.numero = numero;
        this.titular = titular;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
