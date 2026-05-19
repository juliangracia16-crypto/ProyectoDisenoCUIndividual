
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
    private final IHistorialIncidentesBO historialIncidentesBO;
    private final IHistorialAtencionesBO historialAtencionesBO;
    private final ICatalogosBO catalogosBO;
    private final ILoginBO login;
    private final IHistorialesReportesGeneralBO reportesGeneralesBO;
    private final String RUTA_LOGO_REPORTE_PDF = "/logo gym.jpg";
    private final String TITULO_REPORTE_PDF = "REPORTE DE REGISTROS DE QUEJAS Y SUGERENCIAS";
    
    /**
     * Constructor que recibe e inicializa las BO necesarias para que el subsistema funcione correctamente
     * @param historialIncidentesBO la BO para manejar lo relacionado a los reportes de incidentes
     * @param historialAtencionesBO la BO para manejar lo relacionado a los reportes de atenciones
     * @param catalogosBO la BO para manejar lo relacionado con los estados y categorias de los reportes
     * @param login la BO para manejar el login como administrador
     * @param reportesGeneralesBO la BO para manejar lo realcionado a generar el reporte general de historial
     * de reportes
     */
    public CUQuejasSugerencias(IHistorialIncidentesBO historialIncidentesBO, IHistorialAtencionesBO historialAtencionesBO, 
        ICatalogosBO catalogosBO, ILoginBO login, IHistorialesReportesGeneralBO reportesGeneralesBO) 
    {
        this.historialIncidentesBO = historialIncidentesBO;
        this.historialAtencionesBO = historialAtencionesBO;
        this.catalogosBO = catalogosBO;
        this.login = login;
        this.reportesGeneralesBO = reportesGeneralesBO;
    }
    
    /**
     * Metodo para consultar todas las categorias 
     * @return una lista con las categorias
     * @throws NegocioException si oucrre un error al consultar
     */
    @Override
    public List<CategoriaDTO> cargarCatalogoCategorias() throws NegocioException {
        List<CategoriaDTO> categoriasDTO = catalogosBO.consultarCatalogoCategorias();
        return categoriasDTO;
    }
    
    /**
     * Metoco para consultar todos los estados que puede tener un reporte
     * @return una lista con todos los estados que pueden tener los reportes
     * @throws NegocioException si ocurre un error al consultar 
     */
    @Override
    public List<EstadoReporteDTO> cargarCatalogoEstados() throws NegocioException {
        List<EstadoReporteDTO> estadosDTO = catalogosBO.consultarCatalogoEstados();
        return estadosDTO;
    }

    /**
     * Metodo para consultar un reporte de incidente por su folio
     * @param folio del reporte que se consultara
     * @return el reporte de incidente correspondiente a ese folio
     * @throws NegocioException si ocurre un error al consultar o
     * si no se encuentra algun reporte con ese folio
     */
    @Override
    public ReporteIncidenteDTO consultarReporteIncidentePorFolio(String folio) throws NegocioException {
        ReporteIncidenteDTO reporteIncidente = historialIncidentesBO.consultarReporteIncidentePorFolio(folio);
        return reporteIncidente;
    }
    
    /**
     * Metodo para consultar todos los reportes de incidentes con filtros
     * @param filtros por los que se consultara (Por: nombre cliente, categoria del reporte, estado del reporte, fecha desde, fecha hasta)
     * @return una lista con los reportes incidentes que coincidan con los filtros o
     * regresa todos si los filtros van vacios.
     * @throws NegocioException si ocurre un error al consultar o 
     * si los filtros son invalidos
     */
    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        validarNombreFiltro(filtros.cliente());
        validarFechasFiltro(filtros.fechaDesde(),filtros.fechaHasta());
        List<ReporteIncidenteDTO> reportesIncidentes = historialIncidentesBO.consultarReportesIncidentes(filtros);
        return reportesIncidentes;
    }
    
    /**
     * Metodo para consultar todos los reportes de incidentes generados por un cliente
     * Tambien pueden filtrarse los reportes generados por un cliente
     * @param filtros por los que se consultara (Por: estado del reporte, categoria del reporte, fecha desde, fecha hasta).
     * @return una lista con los reportes del cliente que coincidan con los filtros 
     * o regresa todos los reportes si los filtros van vacios.
     * @throws NegocioException si ocurre un error al consultar
     * o si los filtros son invalidos.
     */
    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentesPorCliente(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        validarFechasFiltro(filtros.fechaDesde(), filtros.fechaHasta());
        List<ReporteIncidenteDTO> reportesIncidentes = historialIncidentesBO.consultarReportesIncidentesPorCliente(filtros);
        return reportesIncidentes;
    }

    /**
     * Metodo para consultar todos los reportes de atenciones con filtros
     * @param filtros por los que se consultara (Por: nombre de cliente, estado del reporte, categoria del reporte, fecha desde fecha hasta)
     * @return una lista con los reportes que coincidan con los filtros o 
     * todos los reportes si los filtros van vacios. 
     * @throws NegocioException si ocurre un error al consultar
     * o si los filtros son invalidos.
     */
    @Override
    public List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        validarNombreFiltro(filtros.cliente());
        validarFechasFiltro(filtros.fechaDesde(),filtros.fechaHasta());
        List<ReporteAtencionDTO> reportesAtenciones = historialAtencionesBO.consultarReportesAtenciones(filtros);
        return reportesAtenciones;
    }

    /**
     * Metodo para generar un reporte de incidente 
     * @param reporteIncidente objeto con los datos necesarios para generar un nuevo 
     * reporte de incidente 
     * @return el reporte de incidente generado correctamente
     * @throws NegocioException si ocurre un error al generar el reporte o 
     * si no cumple con las validaciones establecidas
     */
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

    /**
     * Metodo para atender/resolver un reporte de incidente
     * @param reporteAtencion objeto con los datos necesarios para resolver 
     * el reporte de incidente
     * @return el reporte atencion generado despues de resolver correctamente
     * el reporte de incidente
     * @throws NegocioException si ocurre un error al resolver el reporte
     * o si no cumple con las validaciones establecidas
     */
    @Override
    public ReporteAtencionGeneradoDTO atenderReporteIncidente(AtenderReporteDTO reporteAtencion) throws NegocioException {
        validarSolucionReporteAtencion(reporteAtencion.solucion());
        if(reporteAtencion.imagen() != null){
            validarImagen(reporteAtencion.imagen().imagen());
        }
        ReporteAtencionGeneradoDTO reporteIncidenteAtendido = historialAtencionesBO.atenderReporteIncidente(reporteAtencion);
        return reporteIncidenteAtendido;
    }
    
    /**
     * Metodo para consultar todos los reportes de atenciones
     * @return una lista con todos los reportes de atenciones 
     * @throws NegocioException si ocurre un error al consultar 
     */
    @Override
    public List<ReporteAtencionDTO> consultarTodosLosReportesAtenciones() throws NegocioException {
        List<ReporteAtencionDTO> reportesAtenciones = historialAtencionesBO.consultarReportesAtenciones();
        return reportesAtenciones;
    }

    /**
     * Metodo para consultar todos los reportes de atenciones
     * @return una lista con todos los reportes de incidentes
     * @throws NegocioException si ocurre un error al consultar  
     */
    @Override
    public List<ReporteIncidenteDTO> consultarTodosLosReportesIncidentes() throws NegocioException {
        List<ReporteIncidenteDTO> reportesIncidentes = historialIncidentesBO.consultarReportesIncidentes();
        return reportesIncidentes;
    }
    
    /**
     * Metodo para consultar un reporte de atencion por su folio
     * @param folio por el que se consultara el reporte de atencion
     * @return el reporte de atencion correspondiente a ese folio
     * @throws NegocioException si ocurre un error al consultar
     * o si no se encuentra ningun reporte relacionado al folio 
     */
    @Override
    public ReporteAtencionDTO consultarReporteAtencionPorFolio(String folio) throws NegocioException {
        ReporteAtencionDTO reporteAtencion = historialAtencionesBO.consultarReporteAtencionPorFolio(folio);
        return reporteAtencion;
    }
    
    /**
     * Metodo que consulta todos los reportes (Incidentes y Atenciones) para
     * agruparlos en un solo historial y mostrarlos en vista de administrador
     * @return una lista con todos los reportes agrupados en uno solo
     * @throws NegocioException si ocurre un error al consultar o agrupar los 
     * dos tipos de reportes
     */
    @Override
    public List<RegistroReporteAdminDTO> consultarTodosLosReportes() throws NegocioException {
        List<RegistroReporteAdminDTO> reportes = historialAtencionesBO.consultarTodosLosRegistrosReportes();
        return reportes;
    }
    
    /**
     * Metodo que consulta todos los reportes (Incidentes y Atenciones) con filtros
     * para agruparlos en un solo historial y mostrarlos en vista de administrador
     * @param filtros por los que se consultaran todos los reportes 
     * Por: nombre de cliente, estado del reporte, categoria del reporte, fecha desde, fecha hasta 
     * @return una lista con los reportes que coincidan con los filtros
     * @throws NegocioException si ocurre un error al consultar o 
     * si los filtros no cumplen las validaciones establecidas.
     */
    @Override
    public List<RegistroReporteAdminDTO> consultarTodosLosReportesFiltrados(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        validarFechasFiltro(filtros.fechaDesde(), filtros.fechaHasta());
        List<RegistroReporteAdminDTO> reportes = historialAtencionesBO.consultarTodosLosRegistrosReportesFiltrado(filtros);
        return reportes;
    }
    
    /**
     * Metodo para agrupar los datos necesarios para generar 
     * un reporte pdf de los registros solicitados
     * @param registros a los cuales se les generara el reporte pdf
     * @return el reporte pdf generado correctamente 
     * @throws NegocioException si ocurre un error al generar el reporte pdf
     */
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
    
    /**
     * Metodo para iniciar sesion desde la vista de administrador 
     * @param loginDTO las credenciales del administrador
     * @return el administrador si pudo iniciar sesion o no
     * @throws NegocioException si ocurre un error al iniciar sesion
     * o si las credenciales no cumplen con las validaciones establecidas
     */
    @Override
    public AdministradorLogueadoDTO iniciarSesion(LoginAdminDTO loginDTO) throws NegocioException {
        validarInicioSesion(loginDTO);
        AdministradorLogueadoDTO administrador = login.iniciarSesion(loginDTO);
        return administrador;
    }
    
    /**
     * Metodo privado que se encarga de validar la 
     * descripcion de un reporte de incidente
     * No debe venir vacia, o nula 
     * Longitud minima 15 caracteres
     * Longitud maxima 255 caracteres 
     * @param descripcion que se validara
     * @throws NegocioException si no cumple con las validaciones establecidas
     */
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
    
    /**
     * Metodo privado para validar la imagen que se insertara en el reporte 
     * de atencion y/o en el reporte de atencion.
     * El tamaño maximo de la imagen es 5 Mb
     * Solo se permite formato PNG o JPG
     * @param imagen que se validara 
     * @throws NegocioException si no cumple con las validaciones establecidas
     */
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

        boolean esJPG = imagen.length >= 2 && (imagen[0] & 0xFF) == 0xFF && (imagen[1] & 0xFF) == 0xD8;
        
        if(!esPNG && !esJPG){
            throw new NegocioException("Solo se permiten imagenes PNG o JPG.");
        }
    }
    
    /**
     * Metodo privado que valida las fechas que se usan para filtrar.
     * La fecha desde y fecha hasta son opcional, por lo cual si estas
     * vienen nulas, simplemente no continuamos con las validaciones.
     * Fecha desde no puede ser mayor a la fecha hasta
     * La fecha desde no puede ser mayor a la fecha actual
     * La fecha hasta no puede ser mayor a la fecha actual
     * @param fechaDesde fecha que se validara
     * @param fechaHasta fecha que se validara
     * @throws NegocioException si no cumple con las validaciones establecidas
     */
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
    
    /**
     * Metodo privado para validar la solucion de un reporte de atencion
     * La solucion debe tener minimo 15 caracteres
     * La solucion debe tener maximo 255 caracteres 
     * La solucion no dede estar vacia ni nula
     * @param solucion que se validara
     * @throws NegocioException si no cumple con las validaciones establecidas
     */
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
    
    /**
     * Metodo privado para validar el nombre del cliente cuando se consulta con filtros
     * El nombre es opcional, puede venir nulo o estar vacio. Tambien puede ser 
     * desde una sola letra, pues se puede buscar por coincidencias de letra tambien. No
     * solo por nombre completo.
     * El nombre debe tener un maximo de 30 caracteres
     * @param nombre que se validara 
     * @throws NegocioException si no cumple con las validaciones establecidas
     */
    private void validarNombreFiltro(String nombre) throws NegocioException{
        if(nombre.trim().length() > 30){
            throw new NegocioException("El campo para filtrar por nombre debe tener maximo 30 caracteres.");
        }
    }
    
    /**
     * Metodo privado para validar el asunto de un reporte de incidente
     * El asunto no debe estar vacio, ni nulo
     * El asunto debe tener minimo 5 caracteres
     * El asunto debe tener maximo 80 caracteres
     * @param asunto que se validara
     * @throws NegocioException si no cumple con las validaciones establecidas
     */
    private void validarAsuntoReporteIncidente(String asunto) throws NegocioException{
        if(asunto.isEmpty() || asunto == null){
            throw new NegocioException("El asunto del reporte es obligatorio.");
        }
        if(asunto.trim().length()>80){
            throw new NegocioException("El asunto debe contener como maximo 100 caracteres.");
        }
        if(asunto.trim().length() < 5){
            throw new NegocioException("El asunto debe contener como minimo 5 caracteres.");
        }
    }
    
    /**
     * Metodo privado para validar las credenciales con las que se intenta
     * iniciar sesion como administrador.
     * Las credenciales no deben venir nulas, ni vacias
     * La lognitud maxima para la contraseña y para el usuario
     * es de 30 caracteres
     * @param login credenciales a validar 
     * @throws NegocioException 
     */
    private void validarInicioSesion(LoginAdminDTO login) throws NegocioException{
        if(login == null){
            throw new NegocioException("Los datos de inicio de sesion no pueden ser nulos.");
        }
        if(login.contrasenia().isBlank() || login.contrasenia() == null){
            throw new NegocioException("La contraseña no debe estar vacia.");
        }
        if(login.contrasenia().length() > 30){
            throw new NegocioException("La contraseña debe tener maximo 30 caracteres.");
        }
        if(login.usuario().isBlank() || login.usuario() == null){
            throw new NegocioException("El usuario es obligatorio.");
        }
        if(login.usuario().length() > 30){
            throw new NegocioException("El usuario debe de tener maximo 30 caracteres.");
        }
    }
 
}
