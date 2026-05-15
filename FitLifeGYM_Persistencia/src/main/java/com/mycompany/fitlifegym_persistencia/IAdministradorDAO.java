
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.entidades.Administrador;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface IAdministradorDAO {
    public abstract Administrador consultarAdministradorPorUsuario(String usuario) throws PersistenciaException;
    public abstract List<Administrador> consultarAdministradores() throws PersistenciaException;
    public abstract Administrador consultarAdministradorPorId(String id) throws PersistenciaException;
}
