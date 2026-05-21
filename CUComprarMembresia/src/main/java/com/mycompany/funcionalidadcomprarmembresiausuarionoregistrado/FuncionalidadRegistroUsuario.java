    
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
    private final String regexCorreo = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private final IClientesBO clientesBO;

    public FuncionalidadRegistroUsuario(IClientesBO clientesBO) {
        this.clientesBO = clientesBO;
    }

    @Override
    public ClienteLogueadoDTO RegistrarUsuario(NuevoClienteDTO clienteDTO) throws NegocioException {
        validarDatosUsuario(clienteDTO);
        try {
            ClienteLogueadoDTO cliente = clientesBO.registrarCliente(clienteDTO);
            return cliente;
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
        
        if(clienteDTO.getNombre().length() > 30){
            throw new NegocioException("El nombre debe contener maximo 30.");
        }        
        
        if(clienteDTO.getNombre().length() < 3){
            throw new NegocioException("El nombre debe contener minimo 3 caracteres.");
        }
        
        if (clienteDTO.getApellidos() == null || clienteDTO.getApellidos().isEmpty()) {
            throw new NegocioException("El apellido del cliente no puede ser nulo.");
        }

        if(clienteDTO.getApellidos().length() > 30){
            throw new NegocioException("El apellido debe tener maximo 30 caracteres.");
        }
        
        if(clienteDTO.getApellidos().length() < 5){
            throw new NegocioException("El apellido debe tener un minimo de 5 caracteres.");
        }
        
        if (clienteDTO.getCorreo() == null || clienteDTO.getCorreo().isBlank()) {
            throw new NegocioException("El correo no debe estar vacio.");
        }

        if(clienteDTO.getCorreo().length() > 50){
            throw new NegocioException("El correo debe tener como maximo 50 caracteres.");
        }
        
        if (!clienteDTO.getCorreo().matches(regexCorreo)) {
            throw new NegocioException("El formato del correo no es válido.");
        }
        
        if (clienteDTO.getTelefono().isEmpty() || !clienteDTO.getTelefono().matches("\\d{10}")) {
            throw new NegocioException("Ingrese el formato válido del teléfono.");
        }
        
        if(clienteDTO.getFechaNacimiento() == null){
            throw new NegocioException("La fecha de nacimiento es obligatoria.");
        }
        
        if(clienteDTO.getContrasenia() == null || clienteDTO.getContrasenia().isBlank()){
            throw new NegocioException("La contraseña es obligatoria.");
        }
        
        if(clienteDTO.getContrasenia().length() > 30){
            throw new NegocioException("La contraseña debe tener maximo 30 caracteres.");
        }

        if(clienteDTO.getContrasenia().length() < 5){
            throw new NegocioException("La contraseña debe tener minimo 5 caracteres.");
        }
        
        if (clienteDTO.getPin() == null || !clienteDTO.getPin().matches("\\d{4}")) {
            throw new NegocioException("Ingrese un PIN de 4 números.");
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
