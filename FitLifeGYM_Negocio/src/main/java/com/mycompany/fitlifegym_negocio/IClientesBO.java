
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface IClientesBO {

    public abstract ClienteLogueadoDTO registrarCliente(NuevoClienteDTO cliente)throws NegocioException;

    public abstract ClienteLogueadoDTO buscarClientePorId(String id)throws NegocioException;

    public abstract List<ClienteLogueadoDTO> consultarClientes() throws NegocioException;
}
