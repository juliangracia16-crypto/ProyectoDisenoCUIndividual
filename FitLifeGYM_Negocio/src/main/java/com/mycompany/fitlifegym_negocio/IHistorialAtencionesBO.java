
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.RegistroReporteAdminDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionGeneradoDTO;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface IHistorialAtencionesBO {
    
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
     * Metodo para consultar todos los reportes de atenciones
     * @return una lista con todos los reportes
     * @throws NegocioException si ocurre un error al consultar
     */
    public abstract List<ReporteAtencionDTO> consultarReportesAtenciones() throws NegocioException;
    
    /**
     * Metodo para consultar todos los reportes (Atenciones y Incidentes)
     * y agruparlos juntos para la vista de administrador. 
     * @return una lista con los dos tipos de reportes agrupados
     * @throws NegocioException si ocurre un error al agruparlos o al consultarlos
     */
    public abstract List<RegistroReporteAdminDTO> consultarTodosLosRegistrosReportes() throws NegocioException;
    
    /**
     * Metodo para consultar todos los reportes por filtro (Atenciones y Incidentes)
     * y agruparlos juntos para la vista de administrador. 
     * @param filtros por los que se consultaran todos los reportes
     * Por: nombre cliente, estado del reporte, categoria del reporte, fecha desde, fecha hasta
     * @return una lista con los dos tipos de reportes agrupados que coincidan con los filtros
     * @throws NegocioException si ocurre un error al consultarlos o si 
     * los filtros no cumplen con las validaciones establecidas
     */
    public abstract List<RegistroReporteAdminDTO> consultarTodosLosRegistrosReportesFiltrado(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException;
    
    /**
     * Metodo para consultar un reporte de atencion por folio
     * @param folio por el que se consultara el reporte
     * @return el reporte de atencion que corresponda al folio
     * @throws NegocioException si ocurre un error al consultar o
     * si no se encuentra ningun reporte relacionado a ese folio
     */
    public abstract ReporteAtencionDTO consultarReporteAtencionPorFolio(String folio) throws NegocioException;
    
    /**
     * Metodo para atender/resolver un reporte de incidente 
     * @param reporte objeto con los datos necesarios
     * para resolver un reporte de incidente
     * @return un reporte de atencion generado correctamente
     * despues de resolver el reporte de incidente
     * @throws NegocioException si ocurre un error al resolver el reporte
     * de incidente 
     */
    public abstract ReporteAtencionGeneradoDTO atenderReporteIncidente(AtenderReporteDTO reporte) throws NegocioException;
}
