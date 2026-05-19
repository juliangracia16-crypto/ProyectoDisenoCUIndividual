
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class ClientesBO implements IClientesBO {

    private final IPersistenciaFachada fachada;

    public ClientesBO(IPersistenciaFachada fachada) {
        this.fachada = fachada;
    }

    /**
     * Metodo para registrar un nuevo cliente al gimnasio
     * @param clienteDTO datos del cliente nuevo a registrar
     * @return dto con el cliente registrado 
     * @throws NegocioException si ocurre un error al registrarlo
     */
    @Override
    public ClienteLogueadoDTO registrarCliente(NuevoClienteDTO clienteDTO) throws NegocioException{
        Cliente cliente = DtosAEntidadesAdapter.adaptarClienteDTO(clienteDTO);      
        try {
            Cliente clienteGuardado = fachada.registrarCliente(cliente);
            ClienteLogueadoDTO clienteAgregadoDTO = DtosAEntidadesAdapter.adaptarClienteEntidad(clienteGuardado);
            return clienteAgregadoDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el cliente",ex);
        }
    }

    /**
     * Metodo para buscar un cliente por su ID
     * @param id por el cual buscaremos al cliente
     * @return cliente encontrado con ese ID
     * @throws NegocioException si ocurre un error al consultar
     * o si no se encuentra ningun cliente con ese ID
     */
    @Override
    public ClienteLogueadoDTO buscarClientePorId(String id) throws NegocioException{
        try {
            Cliente cliente = fachada.consultarClientePorId(id);
            ClienteLogueadoDTO clienteDTO = DtosAEntidadesAdapter.adaptarClienteEntidad(cliente);
            return clienteDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el cliente",ex);
        }
    }

    /**
     * Metodo para consultar todos los clientes
     * @return una lista con todos los clientes
     * @throws NegocioException si ocurre un error al consultar
     */
    @Override
    public List<ClienteLogueadoDTO> consultarClientes() throws NegocioException{
        try {
            List<Cliente> clientes = fachada.consultarClientes();
            List<ClienteLogueadoDTO> clientesDTO = new LinkedList<>();
            for(Cliente c: clientes){
                ClienteLogueadoDTO cliente = DtosAEntidadesAdapter.adaptarClienteEntidad(c);
                clientesDTO.add(cliente);
            }
            return clientesDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el cliente",ex);
        }
    }

}
