/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.funcionalidadiniciarsesionrenovarmembresia;

import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import com.mycompany.fitlifegym_dtos.RenovarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.TipoMembresiaDTO;
import com.mycompany.fitlifegym_negocio.ILoginBO;
import com.mycompany.fitlifegym_negocio.IMembresiaBO;
import com.mycompany.fitlifegym_negocio.IRenovarMembresiaBO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import java.util.List;

/**
 *
 * @author PC GAMER MASTER RACE
 */
public class FuncionalidadIniciarSesionRenovarMembresia implements IFuncionalidadIniciarSesionRenovarMembresia {
    private final ILoginBO loginBO;
    private final IMembresiaBO membresiaBO;
    private final IRenovarMembresiaBO renovarMembresiaBO;
    private final String regexCorreo = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

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

        if (login.getCorreo() == null || login.getCorreo().isBlank()) {
            throw new NegocioException("El correo no puede estar vacio.");
        }
        
        if(!login.getCorreo().matches(regexCorreo)){
            throw new NegocioException("Formato de correo invalido.");
        }
        
        if (login.getContrasenia() == null || login.getContrasenia().isBlank()) {
            throw new NegocioException("La contraseña no puede estar vacia.");
        }
        
        ClienteLogueadoDTO cliente = loginBO.iniciarSesion(login);
        if(cliente == null){
            throw new NegocioException("Credenciales invalidas.");
        }
        return cliente;
    }

    @Override
    public List<NuevaMembresiaDTO> consultarMembresias() throws NegocioException {
        List<NuevaMembresiaDTO> membresias = membresiaBO.obtenerTodas();

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

        if (dto.getTipoMembresia() == null) {
            throw new NegocioException("Se debe de elegir un tipo de membresia.");
        }

        renovarMembresiaBO.renovarMembresia(dto);
    }

    @Override
    public NuevaMembresiaDTO buscarMembresiaPorTipo(TipoMembresiaDTO tipo) throws NegocioException {
        if (tipo == null) {
            throw new NegocioException("El tipo de membresia no puede ser nulo.");
        }

        List<NuevaMembresiaDTO> membresias = membresiaBO.obtenerTodas();
        if (membresias == null || membresias.isEmpty()) {
            throw new NegocioException("No hay membresias disponibles.");
        }

        for (NuevaMembresiaDTO m : membresias) {
            if (m.getTipoMembresia().name().equals(tipo.name())) {
                return m;
            }
        }

        throw new NegocioException("No se encontro la membresia '" + tipo + "' en la BD.");
    }
    
}
