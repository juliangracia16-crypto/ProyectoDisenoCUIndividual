
package com.mycompany.fitlifegym_presentacion;

import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.EstadoDTO;
import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaCompradaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import com.mycompany.fitlifegym_dtos.RenovarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.TipoMembresiaDTO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import com.mycompany.fitlifegym_presentacion.sesion.SesionUsuario;
import com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado.IFuncionalidadRegistrarUsuario;
import com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado.presentacion.FabricaSubsistemaRegistroUsuario;
import com.mycompany.funcionalidadiniciarsesionrenovarmembresia.IFuncionalidadIniciarSesionRenovarMembresia;
import com.mycompany.funcionalidadiniciarsesionrenovarmembresia.fabricaSubsistema.FabricaSubsistemaIniciarSesion;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Julian
 */
public class ControlSubsistemaCUBase {
    private IFuncionalidadRegistrarUsuario funcionalidadRegistrarUsuario;
    private IFuncionalidadIniciarSesionRenovarMembresia funcionalidadIniciarSesion;
    
    public ControlSubsistemaCUBase() {
        this.funcionalidadRegistrarUsuario = FabricaSubsistemaRegistroUsuario.crearSubsistemaRegistroUsuario();
        this.funcionalidadIniciarSesion = FabricaSubsistemaIniciarSesion.crearSubsistemaIniciarSesion();
    }
    
    public TipoMembresiaDTO seleccionarMembresia(String tipo) {
        TipoMembresiaDTO tipoMembresiaDTO;
        switch (tipo) {
            case "ORO":
                tipoMembresiaDTO = TipoMembresiaDTO.ORO;
                break;
            case "PLATA":
                tipoMembresiaDTO = TipoMembresiaDTO.PLATA;
                break;
            default:
                tipoMembresiaDTO = TipoMembresiaDTO.BRONCE;
        }

        return tipoMembresiaDTO;
    }

    public void asignarMembresiaCliente(NuevoClienteDTO cliente, TipoMembresiaDTO membresia) throws NegocioException {
        NuevaMembresiaDTO membresiaBD = funcionalidadIniciarSesion.buscarMembresiaPorTipo(membresia);
        double precio = membresiaBD.getPrecio();
        LocalDate hoy = LocalDate.now();

        NuevaMembresiaDTO membresiaDTO = new NuevaMembresiaDTO(membresia, precio, hoy.plusMonths(1));
        NuevaMembresiaCompradaDTO membresiaCompradaDTO = new NuevaMembresiaCompradaDTO(
                membresiaDTO,
                hoy,
                hoy.plusMonths(1),
                precio,
                EstadoDTO.ACTIVO
        );
        cliente.setMembresíaComprada(membresiaCompradaDTO);
    }

    public void registrarCliente(NuevoClienteDTO clienteDTO) throws NegocioException { //debiar de llamarse Validar datos cliente
        funcionalidadRegistrarUsuario.validarDatosUsuario(clienteDTO);
    }

    public void procesarPagoTarjeta(NuevoClienteDTO cliente, String numeroTarjeta, String cvv, String fechaVencimiento,String nombreTitular) throws NegocioException {

        // Si hay cliente logueado es pos es renovacion
        if (SesionUsuario.getInstancia().getClienteActual() != null) {
            if (cliente.getMembresíaComprada() == null) {
                throw new NegocioException("No se ha seleccionado ninguna membresia.");
            }
            TipoMembresiaDTO tipo = cliente.getMembresíaComprada().getMembresia().getTipoMembresia();
            renovarMembresia(tipo);
            return;
        }

        // Si no hay logueado es registro nuevo
        if (cliente == null) {
            throw new NegocioException("No hay datos del cliente para registrar.");
        }
        if (cliente.getMembresíaComprada() == null) {
            throw new NegocioException("No se ha seleccionado ninguna membresia.");
        }

        funcionalidadRegistrarUsuario.RegistrarUsuario(cliente);
    }
    
    public void procesarPagoPaypal(NuevoClienteDTO cliente, String correo, String contrasenia) throws NegocioException {

        // Si hay cliente logueado es pos es renovacion
        if (SesionUsuario.getInstancia().getClienteActual() != null) {
            if (cliente.getMembresíaComprada() == null) {
                throw new NegocioException("No se ha seleccionado ninguna membresia.");
            }
            TipoMembresiaDTO tipo = cliente.getMembresíaComprada().getMembresia().getTipoMembresia();
            renovarMembresia(tipo);
            return;
        }

        // Si no hay logueado es registro nuevo
        if (cliente == null) {
            throw new NegocioException("No hay datos del cliente para registrar.");
        }
        if (cliente.getMembresíaComprada() == null) {
            throw new NegocioException("No se ha seleccionado ninguna membresia.");
        }

        funcionalidadRegistrarUsuario.RegistrarUsuario(cliente);
    }
    
    public void procesarPagoTransferencia(NuevoClienteDTO cliente) throws NegocioException {
        // Si hay cliente logueado es pos es renovacion
        if (SesionUsuario.getInstancia().getClienteActual() != null) {
            if (cliente.getMembresíaComprada() == null) {
                throw new NegocioException("No se ha seleccionado ninguna membresia.");
            }
            TipoMembresiaDTO tipo = cliente.getMembresíaComprada().getMembresia().getTipoMembresia();
            renovarMembresia(tipo);
            return;
        }

        // Si no hay logueado es registro nuevo
        if (cliente == null) {
            throw new NegocioException("No hay datos del cliente para registrar.");
        }
        if (cliente.getMembresíaComprada() == null) {
            throw new NegocioException("No se ha seleccionado ninguna membresia.");
        }

        funcionalidadRegistrarUsuario.RegistrarUsuario(cliente);
    }

    public ClienteLogueadoDTO getClienteActual() {
        return SesionUsuario.getInstancia().getClienteActual();
    }

    public List<ClienteLogueadoDTO> consultarClientes() throws NegocioException {
        return funcionalidadRegistrarUsuario.obtenerTodas();
    }

    //Modificado
    public ClienteLogueadoDTO iniciarSesion(String pin, String contrasenia) throws NegocioException {
        LoginDTO loginDTO = new LoginDTO(pin, contrasenia);
        ClienteLogueadoDTO cliente = funcionalidadIniciarSesion.iniciarSesion(loginDTO);
        return cliente;
    }

    //Nuevo Para consultar las Membresias
    public List<NuevaMembresiaDTO> consultarMembresias() throws NegocioException {
        return funcionalidadIniciarSesion.consultarMembresias();
    }

    // Para Consultar Los Tipos de Membresia
    public NuevaMembresiaDTO buscarMembresiaPorTipo(TipoMembresiaDTO tipo) throws NegocioException {
        return funcionalidadIniciarSesion.buscarMembresiaPorTipo(tipo);
    }

    //Nuevo(lo agregrege para la renovacion)
    public void renovarMembresia(TipoMembresiaDTO tipoDTO) throws NegocioException {
        if (SesionUsuario.getInstancia().getClienteActual() == null) {
            throw new NegocioException("No hay un cliente logueado para renovar membresia.");
        }

        RenovarMembresiaDTO dto = new RenovarMembresiaDTO(SesionUsuario.getInstancia().getClienteActual().getIdCliente(), tipoDTO);
        funcionalidadIniciarSesion.renovarMembresia(dto);
    }
}
