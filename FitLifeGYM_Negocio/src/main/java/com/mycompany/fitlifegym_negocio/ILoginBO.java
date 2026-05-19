
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.AdministradorLogueadoDTO;
import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.LoginAdminDTO;
import com.mycompany.fitlifegym_dtos.LoginDTO;

/**
 *
 * @author PC GAMER MASTER RACE
 */
public interface ILoginBO {
    
    /**
     * Metodo para iniciar sesion a la vista de cliente
     * @param login las credenciales con las que se intentara
     * iniciar sesion
     * @return al cliente si es que pudo iniciar sesion
     * o nulo si no se pudo iniciar sesion
     * @throws NegocioException si ocurre un error al iniciar sesion
     */
    public abstract ClienteLogueadoDTO iniciarSesion(LoginDTO login) throws NegocioException;
    
    /**
     * Metodo para iniciar sesion a la vista de administrador
     * @param login las credenciales con las que se intentara
     * iniciar sesion
     * @return al administrador si es que pudo iniciar sesion
     * o nulo si no se pudo iniciar sesion
     * @throws NegocioException si ocurre un error al iniciar sesion
     */
    public abstract AdministradorLogueadoDTO iniciarSesion(LoginAdminDTO login) throws NegocioException;
}
