/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.NuevaMembresiaCompradaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.MembresiaComprada;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface IMembresiaCompradaBO {

    public abstract MembresiaComprada guardar(NuevaMembresiaCompradaDTO compra) throws NegocioException;

    public abstract List<MembresiaComprada> obtenerTodas() throws NegocioException;
}
