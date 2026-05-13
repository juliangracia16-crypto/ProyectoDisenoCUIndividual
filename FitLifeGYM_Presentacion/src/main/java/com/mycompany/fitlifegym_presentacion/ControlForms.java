
package com.mycompany.fitlifegym_presentacion;

import com.mycompany.cuquejassugerencias.ICUQuejasSugerencias;
import com.mycompany.cuquejassugerencias.fabricaSubsitema.FabricaSubsistema;
import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.CategoriaDTO;
import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.EstadoDTO;
import com.mycompany.fitlifegym_dtos.EstadoReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaCompradaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.RenovarMembresiaDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteGeneradoDTO;
import com.mycompany.fitlifegym_dtos.TipoMembresiaDTO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import com.mycompany.fitlifegym_presentacion.sesion.SesionUsuario;
import com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado.IFuncionalidadRegistrarUsuario;
import com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado.presentacion.FabricaSubsistemaRegistroUsuario;
import com.mycompany.funcionalidadiniciarsesionrenovarmembresia.IFuncionalidadIniciarSesionRenovarMembresia;
import com.mycompany.funcionalidadiniciarsesionrenovarmembresia.fabricaSubsistema.FabricaSubsistemaIniciarSesion;
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
        //CASO DE USO
        this.funcionalidadCU = FabricaSubsistemaRegistroUsuario.crearSubsistemaRegistroUsuario();
        this.funcionalidad = FabricaSubsistemaIniciarSesion.crearSubsistemaIniciarSesion();
        this.quejasSugerenciasCU = FabricaSubsistema.crearSubsistema();
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
    
    public void navegarMisReportesGenerados(){
        mostrarPantalla(new MisReportesGeneradosFORM(this));
    }
    
    public void navegarMisReportesGenerados(FiltrosConsultaHistorialReportesNegocioDTO filtros){
        mostrarPantalla(new MisReportesGeneradosFORM(this,filtros));
    }
    
    public void navegarDetallesReporteSeleccionadoCliente(ReporteIncidenteDTO reporteIncidente){
        mostrarPantalla(new DetallesReporteSeleccionadoMisReportesGeneradosFORM(this,reporteIncidente));
    }
    
    public void navegarDetallesReporteSeleccionadoCliente(){
        mostrarPantalla(new DetallesReporteSeleccionadoMisReportesGeneradosFORM(this));
    }
    
    public void navegarReporteGenerado(){
        mostrarPantalla(new ReporteGeneradoFORM(this));
    }
    
    public void navegarSeleccionAdminCliente(){
        mostrarPantalla(new SeleccionarAdminClienteFORM(this));
    }
    
    public void navegarBuscadorMisReportesCliente(){
        mostrarPantalla(new BuscadorRegistrosReportesClientesIndividualFORM(this));
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
        NuevaMembresiaDTO membresiaBD = funcionalidad.buscarMembresiaPorTipo(membresia);
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

    public List<ClienteLogueadoDTO> consultarClientes() throws NegocioException {
        return funcionalidadCU.obtenerTodas();
    }

    //Modificado
    public ClienteLogueadoDTO iniciarSesion(String pin, String contrasenia) throws NegocioException {
        LoginDTO loginDTO = new LoginDTO(pin, contrasenia);
        ClienteLogueadoDTO cliente = funcionalidad.iniciarSesion(loginDTO);
        return cliente;
    }

    //Nuevo Para consultar las Membresias
    public List<NuevaMembresiaDTO> consultarMembresias() throws NegocioException {
        return funcionalidad.consultarMembresias();
    }

    // Para Consultar Los Tipos de Membresia
    public NuevaMembresiaDTO buscarMembresiaPorTipo(TipoMembresiaDTO tipo) throws NegocioException {
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
    public List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        return quejasSugerenciasCU.consultarReportesIncidentes(filtros);
    }

    @Override
    public List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        return quejasSugerenciasCU.consultarReportesAtenciones(filtros);
    }

    @Override
    public ReporteIncidenteGeneradoDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporteIncidente) throws NegocioException {
        return quejasSugerenciasCU.generarReporteIncidente(reporteIncidente);
    }

    @Override
    public ReporteAtencionDTO atenderReporteIncidente(AtenderReporteDTO reporteAtencion) throws NegocioException {
        return quejasSugerenciasCU.atenderReporteIncidente(reporteAtencion);
    }

    @Override
    public List<ReporteAtencionDTO> consultarTodosLosReportesAtenciones() throws NegocioException {
        return quejasSugerenciasCU.consultarTodosLosReportesAtenciones();
    }

    @Override
    public List<ReporteIncidenteDTO> consultarTodosLosReportesIncidentes() throws NegocioException {
        return quejasSugerenciasCU.consultarTodosLosReportesIncidentes();
    }

    @Override
    public ReporteIncidenteDTO consultarReporteIncidentePorFolio(String folio) throws NegocioException {
        return quejasSugerenciasCU.consultarReporteIncidentePorFolio(folio);
    }

}
