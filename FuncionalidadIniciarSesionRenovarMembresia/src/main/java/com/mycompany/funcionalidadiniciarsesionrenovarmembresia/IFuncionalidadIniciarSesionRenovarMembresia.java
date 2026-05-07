/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.funcionalidadiniciarsesionrenovarmembresia;

import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.RenovarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.TipoMembresiaDTO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import com.mycompany.fitlifegym_persistencia.entidades.Membresia;
import java.util.List;

/**
 *
 * @author PC GAMER MASTER RACE
 */
public interface IFuncionalidadIniciarSesionRenovarMembresia {
    public abstract ClienteLogueadoDTO iniciarSesion(LoginDTO login) throws NegocioException;
    public abstract List<Membresia> consultarMembresias() throws NegocioException;
    public abstract void renovarMembresia(RenovarMembresiaDTO dto) throws NegocioException;
    public abstract Membresia buscarMembresiaPorTipo(TipoMembresiaDTO tipo) throws NegocioException;
    
}
