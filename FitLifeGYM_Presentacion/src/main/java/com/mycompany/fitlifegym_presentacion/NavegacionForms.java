
package com.mycompany.fitlifegym_presentacion;

import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.TipoMembresiaDTO;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Diego
 */
public class NavegacionForms {

    private JFrame frameActual;
    private ControlSubsistemaCUBase controlCUBase;
    private ControlSubsistemaQuejasSugerencias controlQuejas;
    
    public NavegacionForms() {
        this.controlCUBase = new ControlSubsistemaCUBase();
        this.controlQuejas = new ControlSubsistemaQuejasSugerencias();   
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

    //frames CU BASE
    public void navegarMenuPrincipal() {
        mostrarPantalla(new MainFitLifeFORM(this));
    }

    public void navegarBenificios(NuevoClienteDTO cliente) {
        mostrarPantalla(new BeneficiosFORM(this, cliente, controlCUBase));
    }

    public void navegarBienvenida() {
        mostrarPantalla(new BienvenidaFORM(this,controlCUBase));
    }

    public void navegarMetodosPago(TipoMembresiaDTO membresia, NuevoClienteDTO cliente) {
        mostrarPantalla(new SuscribirseFORM(this, membresia, cliente));
    }
    
    public void navegarIniciarSesion() {
        mostrarPantalla(new IniciarSesionFORM(this,controlCUBase));
    }
    
    //FORMS CU
    public void navegarInicioBuzonQuejas(){
        mostrarPantalla(new InicioBuzonQuejasFORM(this));
    }
    
    public void navegarGenerarNuevoReporte(){
        mostrarPantalla(new GenerarNuevoReporteFORM(this,controlQuejas));
    }
    
    public void navegarMisReportesGenerados(){
        mostrarPantalla(new MisReportesGeneradosFORM(this,controlQuejas));
    }
    
    public void navegarMisReportesGenerados(FiltrosConsultaHistorialReportesNegocioDTO filtros){
        mostrarPantalla(new MisReportesGeneradosFORM(this,filtros,controlQuejas));
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
        mostrarPantalla(new BuscadorRegistrosReportesClientesIndividualFORM(this,controlQuejas));
    }
    
    public void navegarMenuPrincipalAdmin(){
        mostrarPantalla(new MenuPrincipalAdminFORM(this));
    }
    
    public void navegarReportesQuejasClientes(){
        mostrarPantalla(new ReportesQuejasClientesFORM(this,controlQuejas));
    }
    
    public void navegarReportesQuejasClientes(FiltrosConsultaHistorialReportesNegocioDTO filtros) {
        mostrarPantalla(new ReportesQuejasClientesFORM(this, filtros,controlQuejas));
    }
    
    public void navegarDetallesReporteSeleccionadoForm(){
        mostrarPantalla(new DetallesReporteSeleccionadoFORM(this));
    }
    
    public void navegarDetallesReporteSeleccionadoForm(ReporteIncidenteDTO reporte){
        mostrarPantalla(new DetallesReporteSeleccionadoFORM(this, reporte));
    }
    
    public void navegarDetallesReporteSeleccionadoForm(ReporteAtencionDTO reporte){
        mostrarPantalla(new DetallesReporteSeleccionadoFORM(this, reporte));
    }
    
    public void navegarResolverReporteSeleccionado(){
        mostrarPantalla(new ResolverReporteSeleccionadoFORM(this, controlQuejas));
    }
    
    public void navegarResolverReporteSeleccionado(ReporteIncidenteDTO reporte){
        mostrarPantalla(new ResolverReporteSeleccionadoFORM(this, reporte, controlQuejas));
    }
    
    public void navegarInicioSesionAdmin(){
        mostrarPantalla(new InicioSesionAdminFORM(this, controlQuejas));
    }
    
    public void navegarBuscadorAdmin(){
        mostrarPantalla(new BuscadorRegistrosReportesClientesFORM(this,controlQuejas));
    }
    
    //Dialogs CU BASE
    public void navegarRegistrarse() {
        mostrarDialogo(new RegistrarseFORM(this.frameActual, true, this,controlCUBase));
    }

    public void navegarTransferenciaMetodo(TipoMembresiaDTO membresia, NuevoClienteDTO cliente) {
        mostrarDialogo(new TransferenciaFORM(this.frameActual, true, this, membresia, cliente,controlCUBase));
    }

    public void navegarTarjetaMetodo(TipoMembresiaDTO membresia, NuevoClienteDTO cliente) {
        mostrarDialogo(new TarjetaFORM(this.frameActual, true, this, membresia, cliente,controlCUBase));
    }

    public void navegarIniciarSesionPaypal(TipoMembresiaDTO membresia, NuevoClienteDTO cliente) {
        mostrarDialogo(new IniciarSesionPaypalFORM(this.frameActual, true, this, membresia, cliente,controlCUBase));
    }

}
