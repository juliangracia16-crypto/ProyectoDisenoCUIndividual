
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.LoginDTO;

/**
 *
 * @author PC GAMER MASTER RACE
 */
public interface ILoginBO {
    public abstract ClienteLogueadoDTO iniciarSesion(LoginDTO login) throws NegocioException;
}
