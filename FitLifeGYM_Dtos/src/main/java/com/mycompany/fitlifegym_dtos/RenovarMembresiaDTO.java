/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_dtos;

/**
 *
 * @author PC GAMER MASTER RACE
 */
public class RenovarMembresiaDTO {
    private final Long idCliente;
    private final TipoMembresiaDTO tipoMembresia;

    public RenovarMembresiaDTO(Long idCliente, TipoMembresiaDTO tipoMembresia) {
        this.idCliente = idCliente;
        this.tipoMembresia = tipoMembresia;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public TipoMembresiaDTO getTipoMembresia() {
        return tipoMembresia;
    }
    
}
