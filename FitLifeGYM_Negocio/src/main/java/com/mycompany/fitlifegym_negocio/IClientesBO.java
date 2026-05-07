/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface IClientesBO {

    public abstract Cliente registrarCliente(NuevoClienteDTO cliente)throws NegocioException;

    public abstract Cliente buscarClientePorId(Long id)throws NegocioException;

    public abstract List<Cliente> consultarClientes() throws NegocioException;
}
