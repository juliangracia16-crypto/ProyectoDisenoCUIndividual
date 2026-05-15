
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface IClientesDAO {
    public abstract Cliente registrarCliente(Cliente cliente) throws PersistenciaException;
    public abstract Cliente consultarClientePorId(String id)throws PersistenciaException;
    public abstract List<Cliente> consultarClientes()throws PersistenciaException;
    public abstract Cliente buscarPorPin(String pin)throws PersistenciaException;
    
    
}
