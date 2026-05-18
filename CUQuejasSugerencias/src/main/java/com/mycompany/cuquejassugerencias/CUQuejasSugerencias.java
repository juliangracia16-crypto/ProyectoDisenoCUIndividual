
package com.mycompany.cuquejassugerencias;

import com.mycompany.fitlifegym_dtos.AdministradorLogueadoDTO;
import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.CategoriaDTO;
import com.mycompany.fitlifegym_dtos.EstadoReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.LoginAdminDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.RegistroReporteAdminDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionGeneradoDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteGeneradoDTO;
import com.mycompany.fitlifegym_dtos.ReportePdfDTO;
import com.mycompany.fitlifegym_negocio.ICatalogosBO;
import com.mycompany.fitlifegym_negocio.IHistorialAtencionesBO;
import com.mycompany.fitlifegym_negocio.IHistorialIncidentesBO;
import com.mycompany.fitlifegym_negocio.IHistorialesReportesGeneralBO;
import com.mycompany.fitlifegym_negocio.ILoginBO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;


        
/**
 *
 * @author Julian
 */
public class CUQuejasSugerencias implements ICUQuejasSugerencias{
    private IHistorialIncidentesBO historialIncidentesBO;
    private IHistorialAtencionesBO historialAtencionesBO;
    private ICatalogosBO catalogosBO;
    private ILoginBO login;
    private IHistorialesReportesGeneralBO reportesGeneralesBO;
    private final String RUTA_LOGO_REPORTE_PDF = "/logo gym.jpg";
    private final String TITULO_REPORTE_PDF = "REPORTE DE REGISTROS DE QUEJAS Y SUGERENCIAS";

    public CUQuejasSugerencias(IHistorialIncidentesBO historialIncidentesBO, IHistorialAtencionesBO historialAtencionesBO, 
        ICatalogosBO catalogosBO, ILoginBO login, IHistorialesReportesGeneralBO reportesGeneralesBO) 
    {
        this.historialIncidentesBO = historialIncidentesBO;
        this.historialAtencionesBO = historialAtencionesBO;
        this.catalogosBO = catalogosBO;
        this.login = login;
        this.reportesGeneralesBO = reportesGeneralesBO;
    }
    
    @Override
    public List<CategoriaDTO> cargarCatalogoCategorias() throws NegocioException {
        List<CategoriaDTO> categoriasDTO = catalogosBO.consultarCatalogoCategorias();
        return categoriasDTO;
    }

    @Override
    public List<EstadoReporteDTO> cargarCatalogoEstados() throws NegocioException {
        List<EstadoReporteDTO> estadosDTO = catalogosBO.consultarCatalogoEstados();
        return estadosDTO;
    }

    @Override
    public ReporteIncidenteDTO consultarReporteIncidentePorFolio(String folio) throws NegocioException {
        ReporteIncidenteDTO reporteIncidente = historialIncidentesBO.consultarReporteIncidentePorFolio(folio);
        return reporteIncidente;
    }
    
    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        validarNombreFiltro(filtros.cliente());
        validarFechasFiltro(filtros.fechaDesde(),filtros.fechaHasta());
        List<ReporteIncidenteDTO> reportesIncidentes = historialIncidentesBO.consultarReportesIncidentes(filtros);
        return reportesIncidentes;
    }
    
    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentesPorCliente(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        validarFechasFiltro(filtros.fechaDesde(), filtros.fechaHasta());
        List<ReporteIncidenteDTO> reportesIncidentes = historialIncidentesBO.consultarReportesIncidentesPorCliente(filtros);
        return reportesIncidentes;
    }

    @Override
    public List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        validarNombreFiltro(filtros.cliente());
        validarFechasFiltro(filtros.fechaDesde(),filtros.fechaHasta());
        List<ReporteAtencionDTO> reportesAtenciones = historialAtencionesBO.consultarReportesAtenciones(filtros);
        return reportesAtenciones;
    }

    @Override
    public ReporteIncidenteGeneradoDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporteIncidente) throws NegocioException {
        validarAsuntoReporteIncidente(reporteIncidente.asunto());
        validarDescripcionReporteIncidente(reporteIncidente.descripcion());
        if(reporteIncidente.imagen() != null){
            validarImagen(reporteIncidente.imagen().imagen());
        }
        ReporteIncidenteGeneradoDTO reporteIncidenteGenerado = historialIncidentesBO.generarReporteIncidente(reporteIncidente);
        return reporteIncidenteGenerado;
    }

    @Override
    public ReporteAtencionGeneradoDTO atenderReporteIncidente(AtenderReporteDTO reporteAtencion) throws NegocioException {
        validarSolucionReporteAtencion(reporteAtencion.solucion());
        if(reporteAtencion.imagen() != null){
            validarImagen(reporteAtencion.imagen().imagen());
        }
        ReporteAtencionGeneradoDTO reporteIncidenteAtendido = historialAtencionesBO.atenderReporteIncidente(reporteAtencion);
        return reporteIncidenteAtendido;
    }
    @Override
    public List<ReporteAtencionDTO> consultarTodosLosReportesAtenciones() throws NegocioException {
        List<ReporteAtencionDTO> reportesAtenciones = historialAtencionesBO.consultarReportesAtenciones();
        return reportesAtenciones;
    }

    @Override
    public List<ReporteIncidenteDTO> consultarTodosLosReportesIncidentes() throws NegocioException {
        List<ReporteIncidenteDTO> reportesIncidentes = historialIncidentesBO.consultarReportesIncidentes();
        return reportesIncidentes;
    }
    
    @Override
    public ReporteAtencionDTO consultarReporteAtencionPorFolio(String folio) throws NegocioException {
        ReporteAtencionDTO reporteAtencion = historialAtencionesBO.consultarReporteAtencionPorFolio(folio);
        return reporteAtencion;
    }
    
    @Override
    public List<RegistroReporteAdminDTO> consultarTodosLosReportes() throws NegocioException {
        List<RegistroReporteAdminDTO> reportes = historialAtencionesBO.consultarTodosLosRegistrosReportes();
        return reportes;
    }
    
    @Override
    public List<RegistroReporteAdminDTO> consultarTodosLosReportesFiltrados(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        validarFechasFiltro(filtros.fechaDesde(), filtros.fechaHasta());
        List<RegistroReporteAdminDTO> reportes = historialAtencionesBO.consultarTodosLosRegistrosReportesFiltrado(filtros);
        return reportes;
    }
    
    @Override
    public byte[] generarReportePdf(List<RegistroReporteAdminDTO> registros) throws NegocioException {
        String tituloReporte = TITULO_REPORTE_PDF;
        InputStream input = getClass().getResourceAsStream(RUTA_LOGO_REPORTE_PDF);
        byte[] logo;
        try {
            logo = input.readAllBytes();
            ReportePdfDTO generarReportePdf = new ReportePdfDTO(
                    registros, LocalDate.now(), tituloReporte,logo
            );
            byte[] reportePdf = reportesGeneralesBO.generarReportePdf(generarReportePdf);
            return reportePdf;
        }catch (IOException ex) {
            throw new NegocioException("Error al cargar el logo de la imagen.");
        } catch (NegocioException ex) {
            throw new NegocioException("No se pudo generar el reporte pdf correctamente.",ex);
        }
    }
    @Override
    public AdministradorLogueadoDTO iniciarSesion(LoginAdminDTO loginDTO) throws NegocioException {
        validarInicioSesion(loginDTO);
        AdministradorLogueadoDTO administrador = login.iniciarSesion(loginDTO);
        return administrador;
    }
    private void validarDescripcionReporteIncidente(String descripcion) throws NegocioException{
        if(descripcion.isEmpty() || descripcion == null){
            throw new NegocioException("La descripcion del reporte de incidente no debe estar vacía.");
        }
        if(descripcion.trim().length()>255){
            throw new NegocioException("La longitud maxima de la descripcion es de 255 caracteres.");
        }
        if(descripcion.trim().length()<15){
            throw new NegocioException("La longitud minima de la descripcion es de 15 caracteres.");
        }
    }
    private void validarImagen(byte[] imagen) throws NegocioException{
        
        int maxBytes = 5 * 1024 * 1024;
        if(imagen.length > maxBytes){
            throw new NegocioException("El tamaño maximo de la imagen es de 5 Mb.");
        }
        boolean esPNG =
            imagen.length >= 4
            && (imagen[0] & 0xFF) == 0x89
            && (imagen[1] & 0xFF) == 0x50
            && (imagen[2] & 0xFF) == 0x4E
            && (imagen[3] & 0xFF) == 0x47;

        boolean esJPG =
            imagen.length >= 2
            && (imagen[0] & 0xFF) == 0xFF
            && (imagen[1] & 0xFF) == 0xD8;
        
        if(!esPNG && !esJPG){
            throw new NegocioException("Solo se permiten imagenes PNG o JPG.");
        }
    }
    private void validarFechasFiltro(LocalDate fechaDesde, LocalDate fechaHasta) throws NegocioException{
        if(fechaDesde == null){
            return;
        }
        if(fechaHasta == null){
            return;
        }
        if(fechaDesde.isAfter(LocalDate.now()) || fechaHasta.isAfter(LocalDate.now())){
            throw new NegocioException("Las fecha ingresadas no pueden ser posterior a la fecha actual."); 
        }
        if (fechaDesde.isAfter(fechaHasta)) {
            throw new NegocioException("La fecha desde no puede ser posterior a la fecha hasta.");
        }
    }
    private void validarSolucionReporteAtencion(String solucion) throws NegocioException{
        if(solucion.isEmpty() || solucion == null){
            throw new NegocioException("La solucion del reporte de atencion no debe estar vacía.");
        }
        if(solucion.trim().length()>255){
            throw new NegocioException("La longitud maxima de la solucion es de 255 caracteres.");
        }
        if(solucion.trim().length()<15){
            throw new NegocioException("La longitud minima de la solucion es de 15 caracteres.");
        }
    }
    private void validarNombreFiltro(String nombre) throws NegocioException{
        if(nombre.trim().length() > 30){
            throw new NegocioException("El campo para filtrar por nombre debe tener maximo 30 caracteres.");
        }
    }
    private void validarAsuntoReporteIncidente(String asunto) throws NegocioException{
        if(asunto.isEmpty() || asunto == null){
            throw new NegocioException("El asunto del reporte es obligatorio.");
        }
        if(asunto.trim().length()>100){
            throw new NegocioException("El asunto debe contener como maximo 100 caracteres.");
        }
        if(asunto.trim().length() < 5){
            throw new NegocioException("El asunto debe contener como minimo 5 caracteres.");
        }
    }
    private void validarInicioSesion(LoginAdminDTO login) throws NegocioException{
        if(login == null){
            throw new NegocioException("Los datos de inicio de sesion no pueden ser nulos.");
        }
        if(login.contrasenia().isBlank() || login.contrasenia() == null){
            throw new NegocioException("La contraseña no debe estar vacia.");
        }
        if(login.usuario().isBlank() || login.usuario() == null){
            throw new NegocioException("El usuario es obligatorio.");
        }
    }
 
}
