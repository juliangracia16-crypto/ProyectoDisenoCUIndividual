
package com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado;

import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import com.mycompany.fitlifegym_negocio.IClientesBO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import java.util.List;

/**
 *
 * @author Diego
 */
public class FuncionalidadRegistroUsuario implements IFuncionalidadRegistrarUsuario {

    private final IClientesBO clientesBO;

    public FuncionalidadRegistroUsuario(IClientesBO clientesBO) {
        this.clientesBO = clientesBO;
    }

    @Override
    public void RegistrarUsuario(NuevoClienteDTO clienteDTO) throws NegocioException {
        validarDatosUsuario(clienteDTO);
        try {
            clientesBO.registrarCliente(clienteDTO);
        } catch (NegocioException ex) {
            throw new NegocioException("Error al registrar el cliente.", ex);
        }
    }

    @Override
    public List<ClienteLogueadoDTO> obtenerTodas() throws NegocioException {
        try {
            return clientesBO.consultarClientes();
        } catch (NegocioException ex) {
            throw new NegocioException("Error al obtener todos los clientes", ex);
        }
    }

    @Override
    public void validarDatosUsuario(NuevoClienteDTO clienteDTO) throws NegocioException {
        if (clienteDTO.getNombre() == null || clienteDTO.getNombre().isEmpty()) {
            throw new NegocioException("El nombre del cliente no puede ser nulo.");
        }

        if (clienteDTO.getApellidos() == null || clienteDTO.getApellidos().isEmpty()) {
            throw new NegocioException("El appelido del cliente no puede ser nulo.");
        }

        if (clienteDTO.getCorreo() == null || !clienteDTO.getCorreo().contains("@")) {
            throw new NegocioException("El formato del correo no es válido.");
        }

        if (clienteDTO.getTelefono().isEmpty() || !clienteDTO.getTelefono().matches("\\d{10}")) {
            throw new NegocioException("Ingrese el formato válido del teléfono.");
        }

        if (clienteDTO.getPin() == null || !clienteDTO.getPin().matches("\\d{4}")) {
            throw new NegocioException("Ingrese un PIN con al menos 4 números.");
        }

    }

    private void validarTarjeta(String cvv, String numeroTarjeta, String fechaVencimiento, String nombreTitular) throws NegocioException {
        if (!numeroTarjeta.matches("\\d{16}")) {
            throw new NegocioException("La tarjeta debe tener exactamente 16 números.");
        }

        if (!cvv.matches("\\d{3}")) {
            throw new NegocioException("El CVV debe ser de 3 dígitos.");
        }

        if (!fechaVencimiento.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
            throw new NegocioException("Formato de fecha inválido (MM/YY)");
        }

        if (nombreTitular.isEmpty() || numeroTarjeta.isEmpty() || cvv.isEmpty() || fechaVencimiento.isEmpty()) {
            throw new NegocioException("Favor de llenar todos los campos");
        }
    }

    private void validarPaypal(String correo, String contrasenia) throws NegocioException {
        if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new NegocioException("Formato de correo inválido");
        }

        if (contrasenia.isEmpty()) {
            throw new NegocioException("La contraseña no puede estar vacía");
        }
    }

}
