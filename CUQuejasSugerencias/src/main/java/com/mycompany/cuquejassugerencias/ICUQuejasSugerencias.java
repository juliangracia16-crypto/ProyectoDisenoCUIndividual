
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
import com.mycompany.fitlifegym_negocio.NegocioException;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface ICUQuejasSugerencias {
    //Catalogos
    /**
     * Metodo que consulta todas las categorias
     * @return una lista con las categorias 
     * @throws NegocioException si ocurre un error al consultar
     */
    public abstract List<CategoriaDTO> cargarCatalogoCategorias() throws NegocioException;
    
    /**
     * Metodo que consulta todos los estados de los reportes
     * @return una lista con todos los estados
     * @throws NegocioException si ocurre un error al consultar 
     */
    public abstract List<EstadoReporteDTO> cargarCatalogoEstados()throws NegocioException;
    
    //Reportes Incidentes
    /**
     * Metodo para consultar todos los reportes de incidentes
     * utilizando filtros
     * @param filtros por los que se consultara los reportes
     * Por: nombre cliente, estado reporte, categoria reporte, fecha desde, fecha hasta
     * @return una lista con los reportes que coincidan con los filtros o
     * todos los reportes si los filtros van vacios
     * @throws NegocioException si ocurre un error al consultar o si los
     * filtros no cumplen las validaciones establecidas
     */
    public abstract List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException;
    
    /**
     * Metodo para consultar todos los reportes de incidentes generados por un cliente
     * utilizando filtros
     * @param filtros por los que se consultara los reportes
     * Por: estado reporte, categoria reporte, fecha desde, fecha hasta
     * @return una lista con los reportes del cliente que coincidan con los filtros o
     * todos los reportes del cliente si los filtros van vacios
     * @throws NegocioException si ocurre un error al consultar o si los
     * filtros no cumplen las validaciones establecidas
     */
    public abstract List<ReporteIncidenteDTO> consultarReportesIncidentesPorCliente(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException;
    
    /**
     * Metodo para generar un reporte de incidente
     * @param reporteIncidente objeto con los datos necesarios
     * para generar un nuevo reporte de incidente
     * @return el reporte de incidente generado correctamente
     * @throws NegocioException si ocurre un error al generar el reporte
     */
    public abstract ReporteIncidenteGeneradoDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporteIncidente) throws NegocioException;
    
    /**
     * Metodo para consultar todos los reportes de incidentes
     * @return una lista con todos los reportes 
     * @throws NegocioException si ocurre un error al consultar
     */
    public abstract List<ReporteIncidenteDTO> consultarTodosLosReportesIncidentes() throws NegocioException;
    
    /**
     * Metodo para consultar un reporte de incidente por folio
     * @param folio por el que se consultara el reporte
     * @return el reporte de incidente que coincida con el folio
     * @throws NegocioException si ocurre un error al consultar o 
     * si no se encuentra ningun reporte relacionado a ese folio
     */
    public abstract ReporteIncidenteDTO consultarReporteIncidentePorFolio(String folio) throws NegocioException;
    
    // Reportes Atenciones
    /**
     * Metodo para consultar todos los reportes de atenciones
     * @return una lista con todos los reportes
     * @throws NegocioException si ocurre un error al consultar
     */
    public abstract List<ReporteAtencionDTO> consultarTodosLosReportesAtenciones() throws NegocioException;
    
    /**
     * Metodo para consultar todos los reportes de atenciones
     * utilizando filtros
     * @param filtros por los que se consultara los reportes
     * Por: nombre cliente, estado reporte, categoria reporte, fecha desde, fecha hasta
     * @return una lista con los reportes que coincidan con los filtros o
     * todos los reportes si los filtros van vacios
     * @throws NegocioException si ocurre un error al consultar o si los
     * filtros no cumplen las validaciones establecidas
     */
    public abstract List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException;
    
    /**
     * Metodo para atender/resolver un reporte de incidente 
     * @param reporteAtencion objeto con los datos necesarios
     * para resolver un reporte de incidente
     * @return un reporte de atencion generado correctamente
     * despues de resolver el reporte de incidente
     * @throws NegocioException si ocurre un error al resolver el reporte
     * de incidente 
     */
    public abstract ReporteAtencionGeneradoDTO atenderReporteIncidente(AtenderReporteDTO reporteAtencion) throws NegocioException;
    
    /**
     * Metodo para consultar un reporte de atencion por folio
     * @param folio por el que se consultara el reporte
     * @return el reporte de atencion que corresponda al folio
     * @throws NegocioException si ocurre un error al consultar o
     * si no se encuentra ningun reporte relacionado a ese folio
     */
    public abstract ReporteAtencionDTO consultarReporteAtencionPorFolio(String folio) throws NegocioException;
    
    //Vista admin
    /**
     * Metodo para consultar todos los reportes (Atenciones y Incidentes)
     * y agruparlos juntos para la vista de administrador. 
     * @return una lista con los dos tipos de reportes agrupados
     * @throws NegocioException si ocurre un error al agruparlos o al consultarlos
     */
    public abstract List<RegistroReporteAdminDTO> consultarTodosLosReportes() throws NegocioException;
    
    /**
     * Metodo para consultar todos los reportes por filtro (Atenciones y Incidentes)
     * y agruparlos juntos para la vista de administrador. 
     * @param filtros por los que se consultaran todos los reportes
     * Por: nombre cliente, estado del reporte, categoria del reporte, fecha desde, fecha hasta
     * @return una lista con los dos tipos de reportes agrupados que coincidan con los filtros
     * @throws NegocioException si ocurre un error al consultarlos o si 
     * los filtros no cumplen con las validaciones establecidas
     */
    public abstract List<RegistroReporteAdminDTO> consultarTodosLosReportesFiltrados(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException;
    
    /**
     * Metodo para iniciar sesion a la vista de administrador
     * @param login las credenciales con las que se intentara
     * iniciar sesion
     * @return al administrador si es que pudo iniciar sesion
     * o nulo si no se pudo iniciar sesion
     * @throws NegocioException si ocurre un error al iniciar sesion
     */
    public abstract AdministradorLogueadoDTO iniciarSesion(LoginAdminDTO login) throws NegocioException;
    
    //Generar pdf
    /**
     * Metodo para generar un reporte pdf del historial
     * del registro de los reportes (Atencion y Incidente)
     * @param generarReportePdf objeto con los datos necesarios
     * para generar el reporte pdf
     * @return el reporte pdf generado correctamente 
     * @throws NegocioException si ocurre un error al generar el reporte pdf
     */
    public abstract byte[] generarReportePdf(List<RegistroReporteAdminDTO> generarReportePdf) throws NegocioException;
}
