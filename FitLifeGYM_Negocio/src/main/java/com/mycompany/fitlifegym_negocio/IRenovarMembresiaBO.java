/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.RenovarMembresiaDTO;

/**
 *
 * @author PC GAMER MASTER RACE
 */
public interface IRenovarMembresiaBO {
    public abstract void renovarMembresia(RenovarMembresiaDTO dto) throws NegocioException; 
}
