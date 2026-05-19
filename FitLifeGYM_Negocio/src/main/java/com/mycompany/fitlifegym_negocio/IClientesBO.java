
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface IClientesBO {
    
    /**
     * Metodo para registrar un nuevo cliente al gimnasio
     * @param cliente datos del cliente nuevo a registrar
     * @return dto con el cliente registrado 
     * @throws NegocioException si ocurre un error al registrarlo
     */
    public abstract ClienteLogueadoDTO registrarCliente(NuevoClienteDTO cliente)throws NegocioException;

    /**
     * Metodo para buscar un cliente por su ID
     * @param id por el cual buscaremos al cliente
     * @return cliente encontrado con ese ID
     * @throws NegocioException si ocurre un error al consultar
     * o si no se encuentra ningun cliente con ese ID
     */
    public abstract ClienteLogueadoDTO buscarClientePorId(String id)throws NegocioException;

    /**
     * Metodo para consultar todos los clientes
     * @return una lista con todos los clientes
     * @throws NegocioException si ocurre un error al consultar
     */
    public abstract List<ClienteLogueadoDTO> consultarClientes() throws NegocioException;
}
