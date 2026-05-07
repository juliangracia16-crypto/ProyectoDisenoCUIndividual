/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import com.mycompany.fitlifegym_persistencia.IClientesDAO;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class ClientesBO implements IClientesBO {

    private final IClientesDAO clientesDAO;

    public ClientesBO(IClientesDAO clientesDAO) {
        this.clientesDAO = clientesDAO;
    }

    @Override
    public Cliente registrarCliente(NuevoClienteDTO clienteDTO) throws NegocioException{
        Cliente cliente = DtosAEntidadesAdapter.adaptarClienteDTO(clienteDTO);      
        try {
            return clientesDAO.registrarCliente(cliente);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el cliente",ex);
        }
    }

    @Override
    public Cliente buscarClientePorId(Long id) throws NegocioException{

        if(id == null){
            throw new NegocioException("Se debe de colocar un ID.");
        }
        
        try {
            return clientesDAO.consultarClientePorId(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el cliente",ex);
        }
    }

    @Override
    public List<Cliente> consultarClientes() throws NegocioException{
        try {
            return clientesDAO.consultarClientes();
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el cliente",ex);
        }
    }

}
