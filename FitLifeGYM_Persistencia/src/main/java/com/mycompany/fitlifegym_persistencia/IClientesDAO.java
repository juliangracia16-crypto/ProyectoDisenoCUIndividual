
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import java.util.List;

/**
 * Define las operaciones de acceso y manipulación de datos relacionadas con los
 * clientes en el sistema.
 *
 * Esta interfaz establece el contrato que debe implementar la capa de
 * persistencia para la gestión de clientes.
 * @author Julian
 */
public interface IClientesDAO {
    
    /**
     *  Metodo para registrar un cliente 
     * @param cliente que se quiere registrar
     * @return el cliente registrado correctamente
     * @throws PersistenciaException si ocurre un error al registrar el cliente
     */
    public abstract Cliente registrarCliente(Cliente cliente) throws PersistenciaException;
    
    /**
     * Metodo para consultar un cliente por su ID
     * @param id del cliente que se consultara
     * @return el cliente correspondiente a ese ID
     * @throws PersistenciaException si ocurre un error al consultar al cliente o 
     * si no se encuentra ningun cliente con ese ID
     */
    public abstract Cliente consultarClientePorId(String id)throws PersistenciaException;
    
    /**
     * Metodo para consultar todos los clientes
     * @return una lista con los clientes que hay hasta ese momento.
     * @throws PersistenciaException si ocurre un error al consultar los clientes
     */
    public abstract List<Cliente> consultarClientes()throws PersistenciaException;
    
    /**
     * Metodo para consultar un cliente por su correo
     * @param correo del cliente que se consultara
     * @return el cliente correspondiente a ese correo
     * @throws PersistenciaException si ocurre un error al consultar al cliente o
     * si no se encuentra ningun cliente con ese Pin
     */
    public abstract Cliente buscarPorCorreo(String correo)throws PersistenciaException;
   
}
