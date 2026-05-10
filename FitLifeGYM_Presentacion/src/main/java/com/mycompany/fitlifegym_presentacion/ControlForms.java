
package com.mycompany.fitlifegym_presentacion;

import com.mycompany.cuquejassugerencias.CUQuejasSugerencias;
import com.mycompany.cuquejassugerencias.ICUQuejasSugerencias;
import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.CategoriaDTO;
import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.EstadoDTO;
import com.mycompany.fitlifegym_dtos.EstadoReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaIncidenteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultasAtencionesDTO;
import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaCompradaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.RenovarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.TipoMembresiaDTO;
import com.mycompany.fitlifegym_negocio.CatalogosBO;
import com.mycompany.fitlifegym_negocio.ClientesBO;
import com.mycompany.fitlifegym_negocio.HistorialAtencionesBO;
import com.mycompany.fitlifegym_negocio.HistorialIncidentesBO;
import com.mycompany.fitlifegym_negocio.ICatalogosBO;
import com.mycompany.fitlifegym_negocio.IClientesBO;
import com.mycompany.fitlifegym_negocio.IHistorialAtencionesBO;
import com.mycompany.fitlifegym_negocio.IHistorialIncidentesBO;
import com.mycompany.fitlifegym_negocio.ILoginBO;
import com.mycompany.fitlifegym_negocio.IMembresiaBO;
import com.mycompany.fitlifegym_negocio.IRenovarMembresiaBO;
import com.mycompany.fitlifegym_negocio.LoginBO;
import com.mycompany.fitlifegym_negocio.MembresiaBO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import com.mycompany.fitlifegym_negocio.RenovarMembresiaBO;
import com.mycompany.fitlifegym_persistencia.CatalogosDAO;
import com.mycompany.fitlifegym_persistencia.ClientesDAO;
import com.mycompany.fitlifegym_persistencia.HistorialAtencionesDAO;
import com.mycompany.fitlifegym_persistencia.HistorialIncidentesDAO;
import com.mycompany.fitlifegym_persistencia.ICatalogosDAO;
import com.mycompany.fitlifegym_persistencia.IClientesDAO;
import com.mycompany.fitlifegym_persistencia.IHistorialAtencionesDAO;
import com.mycompany.fitlifegym_persistencia.IHistorialIncidentesDAO;
import com.mycompany.fitlifegym_persistencia.IImagenesDAO;
import com.mycompany.fitlifegym_persistencia.IMembresiaDAO;
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.ImagenesDAO;
import com.mycompany.fitlifegym_persistencia.MembresiaListDAO;
import com.mycompany.fitlifegym_persistencia.PersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import com.mycompany.fitlifegym_persistencia.entidades.Membresia;
import com.mycompany.fitlifegym_presentacion.sesion.SesionUsuario;
import com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado.FuncionalidadRegistroUsuario;
import com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado.IFuncionalidadRegistrarUsuario;
import com.mycompany.funcionalidadiniciarsesionrenovarmembresia.FuncionalidadIniciarSesionRenovarMembresia;
import com.mycompany.funcionalidadiniciarsesionrenovarmembresia.IFuncionalidadIniciarSesionRenovarMembresia;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Diego
 */
public class ControlForms implements ICUQuejasSugerencias{

    private JFrame frameActual;
    private IFuncionalidadRegistrarUsuario funcionalidadCU;
    private IFuncionalidadIniciarSesionRenovarMembresia funcionalidad;
    private ICUQuejasSugerencias quejasSugerenciasCU;

    public ControlForms() {
        IClientesDAO dao = new ClientesDAO();
        IClientesBO negocio = new ClientesBO(dao);
        IMembresiaDAO membresiaDAO = new MembresiaListDAO();
        IMembresiaBO membresiaBO = new MembresiaBO(membresiaDAO);
        ILoginBO loginBO = new LoginBO(dao);
        IRenovarMembresiaBO renovarBO = new RenovarMembresiaBO(dao);
        //CASO DE USO
        IHistorialIncidentesDAO historialIncidentesDAO = new HistorialIncidentesDAO();
        IHistorialAtencionesDAO historialAtencionesDAO = new HistorialAtencionesDAO();
        ICatalogosDAO catalogosDAO = new CatalogosDAO();
        IImagenesDAO imagenesDAO = new ImagenesDAO();
        IPersistenciaFachada fachada = new PersistenciaFachada(historialAtencionesDAO,historialIncidentesDAO,imagenesDAO,catalogosDAO);
        IHistorialIncidentesBO historialIncidentesBO = new HistorialIncidentesBO(fachada);
        IHistorialAtencionesBO historialAtencionesBO = new HistorialAtencionesBO(fachada);
        ICatalogosBO catalogosBO = new CatalogosBO(fachada);
        this.funcionalidadCU = new FuncionalidadRegistroUsuario(negocio);
        this.funcionalidad = new FuncionalidadIniciarSesionRenovarMembresia(loginBO, membresiaBO, renovarBO);
        this.quejasSugerenciasCU = new CUQuejasSugerencias(historialIncidentesBO, historialAtencionesBO, catalogosBO);
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

    public void navegarBienvenida() {
        mostrarPantalla(new BienvenidaFORM(this));
    }

    public void navegarMetodosPago(TipoMembresiaDTO membresia, NuevoClienteDTO cliente) {
        mostrarPantalla(new SuscribirseFORM(this, membresia, cliente));
    }
    //FORMS CU
    public void navegarInicioBuzonQuejas(){
        mostrarPantalla(new InicioBuzonQuejasFORM(this));
    }
    
    public void navegarGenerarNuevoReporte(){
        mostrarPantalla(new GenerarNuevoReporteFORM(this));
    }
    
    public void navegarReporteGenerado(){
        mostrarPantalla(new ReporteGeneradoFORM(this));
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

        funcionalidadCU.RegistrarUsuario(cliente);
    }
    
    public void procesarPagoPaypal(NuevoClienteDTO cliente, String correo, String contrasenia) throws NegocioException {
        funcionalidadCU.validarPaypal(correo, contrasenia);

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

        funcionalidadCU.RegistrarUsuario(cliente);
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

        funcionalidadCU.RegistrarUsuario(cliente);
    }

    public ClienteLogueadoDTO getClienteActual() {
        return SesionUsuario.getInstancia().getClienteActual();
    }

    public List<Cliente> consultarClientes() throws NegocioException {
        return funcionalidadCU.obtenerTodas();
    }

    //Modificado
    public ClienteLogueadoDTO iniciarSesion(String pin, String contrasenia) throws NegocioException {
        LoginDTO loginDTO = new LoginDTO(pin, contrasenia);
        ClienteLogueadoDTO cliente = funcionalidad.iniciarSesion(loginDTO);
        return cliente;
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
        if (SesionUsuario.getInstancia().getClienteActual() == null) {
            throw new NegocioException("No hay un cliente logueado para renovar membresia.");
        }

        RenovarMembresiaDTO dto = new RenovarMembresiaDTO(SesionUsuario.getInstancia().getClienteActual().getIdCliente(), tipoDTO);
        funcionalidad.renovarMembresia(dto);
    }
    
    //Metodos del CU QUEJAS/SUGERENCIAS
    @Override
    public List<CategoriaDTO> cargarCatalogoCategorias() throws NegocioException {
        return quejasSugerenciasCU.cargarCatalogoCategorias();
    }

    @Override
    public List<EstadoReporteDTO> cargarCatalogoEstados() throws NegocioException {
        return quejasSugerenciasCU.cargarCatalogoEstados();
    }

    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaIncidenteDTO filtros) throws NegocioException {
        return quejasSugerenciasCU.consultarReportesIncidentes(filtros);
    }

    @Override
    public List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultasAtencionesDTO filtros) throws NegocioException {
        return quejasSugerenciasCU.consultarReportesAtenciones(filtros);
    }

    @Override
    public ReporteIncidenteDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporteIncidente) throws NegocioException {
        return quejasSugerenciasCU.generarReporteIncidente(reporteIncidente);
    }

    @Override
    public ReporteAtencionDTO atenderReporteIncidente(AtenderReporteDTO reporteAtencion) throws NegocioException {
        return quejasSugerenciasCU.atenderReporteIncidente(reporteAtencion);
    }

}
