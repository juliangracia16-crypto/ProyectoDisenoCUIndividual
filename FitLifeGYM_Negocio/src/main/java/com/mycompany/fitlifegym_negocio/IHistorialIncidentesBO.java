
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteGeneradoDTO;
import java.util.List;

/**
 * Clase que establece las operaciones 
 * para manejar los reportes incidentes
 * @author Julian
 */
public interface IHistorialIncidentesBO {
    
    /**
     * Metodo para consultar todos los reportes de incidente con filtros.
     * @param filtros por los que se consultara
     * Por: nombre cliente, estado del reporte, categoria del reporte, fecha desde, fecha hasta
     * @return una lista de los reportes que coincidan con los filtros. Si los filtros
     * estan vacios regresa todos los reportes
     * @throws NegocioException si ocurre un error al consultar 
     */
    public abstract List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException;
    
    /**
     * Metodo para consultar todos los reportes de incidentes generados por un cliente.
     * Utilizando filtros.
     * @param filtros por los que se consultara
     * Por: estado del reporte, categoria del reporte, fecha desde, fecha hasta
     * @return una lista de los reportes del cliente que coincidan con los filtros
     * o todos los reportes del cliente si los filtros van vacios.
     * @throws NegocioException si ocurre un error al consultar
     */
    public abstract List<ReporteIncidenteDTO> consultarReportesIncidentesPorCliente(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException;
    
    /**
     * Metodo para consultar todos los reportes de incidentes
     * @return todos los reportes de incidentes 
     * @throws NegocioException si ocurre un error al consultar 
     */
    public abstract List<ReporteIncidenteDTO> consultarReportesIncidentes() throws NegocioException;
    
    /**
     * Metodo para consultar un reporte de incidente por folio
     * @param folio por el que se consultara
     * @return el reporte de incidente que coincida con ese folio
     * @throws NegocioException si ocurre un error al consultar o 
     * si no se encuentra ningun reporte con ese folio
     */
    public abstract ReporteIncidenteDTO consultarReporteIncidentePorFolio(String folio) throws NegocioException;
    
    /**
     * Metodo para generar un nuevo reporte de incidente 
     * @param reporte datos necesarios para generar el reporte de incidente
     * @return el reporte incidente generado correctamente
     * @throws NegocioException si ocurre un error al generar el reporte
     */
    public abstract ReporteIncidenteGeneradoDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporte) throws NegocioException;
}
