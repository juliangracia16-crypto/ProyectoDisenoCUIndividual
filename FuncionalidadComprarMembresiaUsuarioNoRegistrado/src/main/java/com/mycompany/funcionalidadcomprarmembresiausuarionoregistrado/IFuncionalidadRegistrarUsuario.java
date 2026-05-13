
package com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado;

import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import java.util.List;


/**
 *
 * @author Diego
 */
public interface IFuncionalidadRegistrarUsuario {

    public abstract void RegistrarUsuario(NuevoClienteDTO clienteDTO) throws NegocioException;
    
    public abstract List<ClienteLogueadoDTO> obtenerTodas() throws NegocioException;
    
    public abstract void validarDatosUsuario(NuevoClienteDTO clienteDTO)throws NegocioException;
    
}
