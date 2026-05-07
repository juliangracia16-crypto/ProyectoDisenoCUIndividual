/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.funcionalidadiniciarsesionrenovarmembresia;

import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.RenovarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.TipoMembresiaDTO;
import com.mycompany.fitlifegym_negocio.ILoginBO;
import com.mycompany.fitlifegym_negocio.IMembresiaBO;
import com.mycompany.fitlifegym_negocio.IRenovarMembresiaBO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import com.mycompany.fitlifegym_persistencia.entidades.Membresia;
import java.util.List;

/**
 *
 * @author PC GAMER MASTER RACE
 */
public class FuncionalidadIniciarSesionRenovarMembresia implements IFuncionalidadIniciarSesionRenovarMembresia {
    private final ILoginBO loginBO;
    private final IMembresiaBO membresiaBO;
    private final IRenovarMembresiaBO renovarMembresiaBO;

    public FuncionalidadIniciarSesionRenovarMembresia(ILoginBO loginBO, IMembresiaBO membresiaBO, IRenovarMembresiaBO renovarMembresiaBO) {
        this.loginBO = loginBO;
        this.membresiaBO = membresiaBO;
        this.renovarMembresiaBO = renovarMembresiaBO;
    }

    @Override
    public ClienteLogueadoDTO iniciarSesion(LoginDTO login) throws NegocioException {
        if (login == null) {
            throw new NegocioException("Los datos de inicio de sesion no pueden ser nulos.");
        }

        if (login.getPin() == null || login.getPin().isBlank()) {
            throw new NegocioException("El PIN no puede estar vacio.");
        }

        if (!login.getPin().matches("\\d{4}")) {
            throw new NegocioException("El PIN debe ser de 4 digitos numericos.");
        }

        if (login.getContrasenia() == null || login.getContrasenia().isBlank()) {
            throw new NegocioException("La contraseña no puede estar vacia.");
        }

        return loginBO.iniciarSesion(login);
    }

    @Override
    public List<Membresia> consultarMembresias() throws NegocioException {
        List<Membresia> membresias = membresiaBO.obtenerTodas();

        if (membresias == null || membresias.isEmpty()) {
            throw new NegocioException("No hay tipos de membresia disponibles.");
        }

        return membresias;
    }

    @Override
    public void renovarMembresia(RenovarMembresiaDTO dto) throws NegocioException {
        if (dto == null) {
            throw new NegocioException("Los datos de la renovacion no pueden ser nulos.");
        }

        if (dto.getIdCliente() == null) {
            throw new NegocioException("El ID del cliente no puede ser nulo.");
        }

        if (dto.getIdCliente() <= 0) {
            throw new NegocioException("El ID del cliente debe ser mayor a 0.");
        }

        if (dto.getTipoMembresia() == null) {
            throw new NegocioException("Se debe de elegir un tipo de membresia.");
        }

        renovarMembresiaBO.renovarMembresia(dto);
    }

    @Override
    public Membresia buscarMembresiaPorTipo(TipoMembresiaDTO tipo) throws NegocioException {
        if (tipo == null) {
            throw new NegocioException("El tipo de membresia no puede ser nulo.");
        }

        List<Membresia> membresias = membresiaBO.obtenerTodas();
        if (membresias == null || membresias.isEmpty()) {
            throw new NegocioException("No hay membresias disponibles.");
        }

        for (Membresia m : membresias) {
            if (m.getTipoMembresia().name().equals(tipo.name())) {
                return m;
            }
        }

        throw new NegocioException("No se encontro la membresia '" + tipo + "' en la BD.");
    }
    
}
