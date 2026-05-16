
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.entidades.Administrador;

/**
 * Define las operaciones de acceso y manipulación de datos relacionadas con los
 * administradores en el sistema.
 *
 * Esta interfaz establece el contrato que debe implementar la capa de
 * persistencia para la gestión de los administradores.
 * @author Julian
 */
public interface IAdministradorDAO {
    
    /**
     * Metodo para consultar un administrador por su usuario 
     * @param usuario del administrador a consultar
     * @return el administrador correspondiente a ese usuario
     * @throws PersistenciaException si ocurre un error al consultar el administrador
     * o si no se encuentra un administrador con ese ID
     */
    public abstract Administrador consultarAdministradorPorUsuario(String usuario) throws PersistenciaException;
    
    /**
     * Metodo para consultar un administrador por su ID
     * @param id del administrador a consultar
     * @return el administrador correspondiente a ese ID
     * @throws PersistenciaException si ocurre un error al consultar al administrador
     * o si no se encuentra un administrador con ese ID
     */
    public abstract Administrador consultarAdministradorPorId(String id) throws PersistenciaException;
}
