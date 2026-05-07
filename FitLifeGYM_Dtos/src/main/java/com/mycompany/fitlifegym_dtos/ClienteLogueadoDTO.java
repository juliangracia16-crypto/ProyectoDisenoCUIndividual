/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_dtos;

/**
 *
 * @author PC GAMER MASTER RACE
 */
public class ClienteLogueadoDTO {

    private final Long idCliente;

    private final String nombreCompleto;

    private final TipoMembresiaDTO membresiaActiva;

    private final EstadoDTO estadoMembresia;

    public ClienteLogueadoDTO(Long idCliente, String nombreCompleto, TipoMembresiaDTO membresiaActiva, EstadoDTO estadoMembresia) {
        this.idCliente = idCliente;
        this.nombreCompleto = nombreCompleto;
        this.membresiaActiva = membresiaActiva;
        this.estadoMembresia = estadoMembresia;
    }

    

    public Long getIdCliente() {
        return idCliente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public TipoMembresiaDTO getMembresiaActiva() {
        return membresiaActiva;
    }

    public EstadoDTO getEstadoMembresia() {
        return estadoMembresia;
    }

    

}
