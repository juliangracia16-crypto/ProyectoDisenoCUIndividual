/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado;

import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import java.util.List;


/**
 *
 * @author Diego
 */
public interface IFuncionalidadRegistrarUsuario {

    public abstract void RegistrarUsuario(NuevoClienteDTO clienteDTO) throws NegocioException;
    
    public abstract List<Cliente> obtenerTodas() throws NegocioException;
    
    public abstract void validarDatosUsuario(NuevoClienteDTO clienteDTO)throws NegocioException;
    
    public abstract void validarTarjeta(String cvv, String numeroTarjeta, String fechaVencimiento,String nombreTitular)throws NegocioException;
    
    public abstract void validarPaypal(String correo, String contrasenia)throws NegocioException;
}
