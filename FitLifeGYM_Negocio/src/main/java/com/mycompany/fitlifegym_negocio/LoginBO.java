
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesYViceversaAdapter;
import com.mycompany.fitlifegym_dtos.AdministradorLogueadoDTO;
import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.EstadoDTO;
import com.mycompany.fitlifegym_dtos.LoginAdminDTO;
import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.TipoMembresiaDTO;
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.entidades.Administrador;
import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import com.mycompany.fitlifegym_persistencia.entidades.TipoMembresia;

/**
 *
 * @author PC GAMER MASTER RACE
 */
public class LoginBO implements ILoginBO {

    private final IPersistenciaFachada fachada;

    public LoginBO(IPersistenciaFachada fachada) {
        this.fachada = fachada;
    }

    /**
     * Metodo para iniciar sesion a la vista de cliente
     * @param login las credenciales con las que se intentara
     * iniciar sesion
     * @return al cliente si es que pudo iniciar sesion
     * o nulo si no se pudo iniciar sesion
     * @throws NegocioException si ocurre un error al iniciar sesion
     */
    @Override
    public ClienteLogueadoDTO iniciarSesion(LoginDTO login) throws NegocioException {
        try {
            Cliente cliente = fachada.buscarPorCorreo(login.getCorreo());
            
            if (cliente == null) {
                return null;
            }
            if(!(login.getContrasenia().equals(cliente.getContrasenia()))){
                return null;
            }

            TipoMembresiaDTO tipoDTO = null;
            EstadoDTO estadoDTO = EstadoDTO.INACTIVO;

            if (cliente.getMembresíaComprada() != null) {
                estadoDTO = DtosAEntidadesYViceversaAdapter.adaptarEstadoDTO(cliente.getMembresíaComprada().getEstado());
                if (cliente.getMembresíaComprada().getMembresia() != null) {
                    TipoMembresia tipo = cliente.getMembresíaComprada().getMembresia().getTipoMembresia();
                    if (tipo != null) {
                        tipoDTO = DtosAEntidadesYViceversaAdapter.adaptarTipoMembresiaDTO(tipo);
                    }
                }
            }

            return new ClienteLogueadoDTO(cliente.getIdCliente(), cliente.getNombre(), cliente.getApellidos(), tipoDTO,estadoDTO);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al iniciar sesion. Credenciales invalidas", ex);
        }
    }

    /**
     * Metodo para iniciar sesion a la vista de administrador
     * @param login las credenciales con las que se intentara
     * iniciar sesion
     * @return al administrador si es que pudo iniciar sesion
     * o nulo si no se pudo iniciar sesion
     * @throws NegocioException si ocurre un error al iniciar sesion
     */
    @Override
    public AdministradorLogueadoDTO iniciarSesion(LoginAdminDTO login) throws NegocioException {
        try {
            Administrador administrador = fachada.consultarAdministradorPorUsuario(login.usuario());
            if (administrador == null) {
                return null;
            }
            if(!administrador.getContrasenia().equals(login.contrasenia())){
                return null;
            }
            return new AdministradorLogueadoDTO(administrador.getIdAdministrador(),administrador.getNombre(),administrador.getUsuario());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al iniciar sesion como administrador. Credenciales invalidas.",ex);
        }
        
    }

}
