/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_presentacion;

import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.EstadoDTO;
import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaCompradaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import com.mycompany.fitlifegym_dtos.RenovarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.TipoMembresiaDTO;
import com.mycompany.fitlifegym_negocio.ClientesBO;
import com.mycompany.fitlifegym_negocio.IClientesBO;
import com.mycompany.fitlifegym_negocio.ILoginBO;
import com.mycompany.fitlifegym_negocio.IMembresiaBO;
import com.mycompany.fitlifegym_negocio.IRenovarMembresiaBO;
import com.mycompany.fitlifegym_negocio.LoginBO;
import com.mycompany.fitlifegym_negocio.MembresiaBO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import com.mycompany.fitlifegym_negocio.RenovarMembresiaBO;
import com.mycompany.fitlifegym_persistencia.ClientesListDAO;
import com.mycompany.fitlifegym_persistencia.IClientesDAO;
import com.mycompany.fitlifegym_persistencia.IMembresiaDAO;
import com.mycompany.fitlifegym_persistencia.MembresiaListDAO;
import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import com.mycompany.fitlifegym_persistencia.entidades.Membresia;
import com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado.FuncionalidadRegistroUsuario;
import com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado.IFuncionalidadRegistrarUsuario;
import com.mycompany.funcionalidadiniciarsesionrenovarmembresia.FuncionalidadIniciarSesionRenovarMembresia;
import com.mycompany.funcionalidadiniciarsesionrenovarmembresia.IFuncionalidadIniciarSesionRenovarMembresia;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class ControlForms {

    private JFrame frameActual;
    private ClienteLogueadoDTO clienteActual;
    private IFuncionalidadRegistrarUsuario funcionalidadCU;
    private IFuncionalidadIniciarSesionRenovarMembresia funcionalidad;

    public ControlForms() {
        IClientesDAO dao = new ClientesListDAO();
        IClientesBO negocio = new ClientesBO(dao);
        IMembresiaDAO membresiaDAO = new MembresiaListDAO();
        IMembresiaBO membresiaBO = new MembresiaBO(membresiaDAO);
        ILoginBO loginBO = new LoginBO(dao);
        IRenovarMembresiaBO renovarBO = new RenovarMembresiaBO(dao);
        this.funcionalidadCU = new FuncionalidadRegistroUsuario(negocio);
        this.funcionalidad = new FuncionalidadIniciarSesionRenovarMembresia(loginBO, membresiaBO, renovarBO);
    }

    private void mostrarPantalla(JFrame nuevoFrame) {
        if (this.frameActual != null) {
            this.frameActual.dispose();
        }

        this.frameActual = nuevoFrame;
        this.frameActual.setResizable(false);
        frameActual.setVisible(true);
    }

    private void mostrarDialogo(JDialog nuevoDialogo) {
        nuevoDialogo.setResizable(false);
        nuevoDialogo.setLocationRelativeTo(this.frameActual);
        nuevoDialogo.setVisible(true);
    }

    //frames
    public void navegarMenuPrincipal() {
        mostrarPantalla(new MainFitLifeFORM(this));
    }

    public void navegarBenificios(NuevoClienteDTO cliente) {
        mostrarPantalla(new BeneficiosFORM(this, cliente));
    }

    public void navegarBienvenida(ClienteLogueadoDTO cliente) {
        mostrarPantalla(new BienvenidaFORM(this, cliente));
    }

    public void navegarMetodosPago(TipoMembresiaDTO membresia, NuevoClienteDTO cliente) {
        mostrarPantalla(new SuscribirseFORM(this, membresia, cliente));
    }

    //Dialogs
    public void navegarRegistrarse() {
        mostrarDialogo(new RegistrarseFORM(this.frameActual, true, this));
    }

    public void navegarIniciarSesion() {
        mostrarDialogo(new IniciarSesionFORM(this.frameActual, true, this));
    }

    public void navegarTransferenciaMetodo(TipoMembresiaDTO membresia, NuevoClienteDTO cliente) {
        mostrarDialogo(new TransferenciaFORM(this.frameActual, true, this, membresia, cliente));
    }

    public void navegarTarjetaMetodo(TipoMembresiaDTO membresia, NuevoClienteDTO cliente) {
        mostrarDialogo(new TarjetaFORM(this.frameActual, true, this, membresia, cliente));
    }

    public void navegarIniciarSesionPaypal(TipoMembresiaDTO membresia, NuevoClienteDTO cliente) {
        mostrarDialogo(new IniciarSesionPaypalFORM(this.frameActual, true, this, membresia, cliente));
    }

    //control
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
        Membresia membresiaBD = funcionalidad.buscarMembresiaPorTipo(membresia);
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
        funcionalidadCU.validarDatosUsuario(clienteDTO);
    }

    public void procesarPagoTarjeta(NuevoClienteDTO cliente, String numeroTarjeta, String cvv, String fechaVencimiento,String nombreTitular) throws NegocioException {
        funcionalidadCU.validarTarjeta(cvv, numeroTarjeta, fechaVencimiento,nombreTitular);

        // Si hay cliente logueado es pos es renovacion
        if (this.clienteActual != null) {
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

        funcionalidadCU.RegistrarUsuario(cliente);
    }
    
    public void procesarPagoPaypal(NuevoClienteDTO cliente, String correo, String contrasenia) throws NegocioException {
        funcionalidadCU.validarPaypal(correo, contrasenia);

        // Si hay cliente logueado es pos es renovacion
        if (this.clienteActual != null) {
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

        funcionalidadCU.RegistrarUsuario(cliente);
    }
    
    public void procesarPagoTransferencia(NuevoClienteDTO cliente) throws NegocioException {
        // Si hay cliente logueado es pos es renovacion
        if (this.clienteActual != null) {
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

        funcionalidadCU.RegistrarUsuario(cliente);
    }

    public ClienteLogueadoDTO getClienteActual() {
        return clienteActual;
    }

    public List<Cliente> consultarClientes() throws NegocioException {
        return funcionalidadCU.obtenerTodas();
    }

    //Modificado
    public ClienteLogueadoDTO iniciarSesion(String pin, String contrasenia) throws NegocioException {
        LoginDTO loginDTO = new LoginDTO(pin, contrasenia);
        this.clienteActual = funcionalidad.iniciarSesion(loginDTO);
        return this.clienteActual;
    }

    //Nuevo Para consultar las Membresias
    public List<Membresia> consultarMembresias() throws NegocioException {
        return funcionalidad.consultarMembresias();
    }

    // Para Consultar Los Tipos de Membresia
    public Membresia buscarMembresiaPorTipo(TipoMembresiaDTO tipo) throws NegocioException {
        return funcionalidad.buscarMembresiaPorTipo(tipo);
    }

    //Nuevo(lo agregrege para la renovacion)
    public void renovarMembresia(TipoMembresiaDTO tipoDTO) throws NegocioException {
        if (this.clienteActual == null) {
            throw new NegocioException("No hay un cliente logueado para renovar membresia.");
        }

        RenovarMembresiaDTO dto = new RenovarMembresiaDTO(clienteActual.getIdCliente(), tipoDTO);
        funcionalidad.renovarMembresia(dto);
    }

}
