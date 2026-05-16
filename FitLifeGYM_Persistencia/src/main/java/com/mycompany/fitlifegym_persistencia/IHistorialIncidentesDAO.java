
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteIncidentePersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteIncidente;
import java.util.List;

/**
 * Define las operaciones de acceso y manipulación de datos relacionadas con los
 * reportes de incidentes en el sistema.
 *
 * Esta interfaz establece el contrato que debe implementar la capa de
 * persistencia para la gestión de los reportes de atencion.
 * @author Julian
 */
public interface IHistorialIncidentesDAO {
    
    /**
     * Metodo para consultar todos los reportes de incidentes.
     * Utilizado para la vista de administrador
     * @return una lista con todos los reportes de incidente hasta ese momento
     * @throws PersistenciaException si ocurre un error al consultar los reportes
     */
    public abstract List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentes() throws PersistenciaException;
    
    /**
     * Metodo para consultar los reportes de incidentes con filtros
     * Utilizado en la vista del administrador cuando se filtran los reportes
     * @param filtros para consultar los reportes ( Por: nombre cliente, fecha desde, fecha hasta, estado reporte y categoria reporte)
     * @return una lista con los reportes de incidente que coincidan con los filtros
     * @throws PersistenciaException si ocurre un error al consultar los reportes
     */
    public abstract List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentesFiltros(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException;
    
    /**
     * Metodo para consultar un reporte de incidente por su folio
     * @param folio del reporte a consultar
     * @return el reporte de incidente correspondiente al folio
     * @throws PersistenciaException si ocurre un error al consultar
     * o si no se encuentra ningun reporte con ese folio 
     */
    public abstract ReporteIncidentePersistenciaDTO consultarReporteIncidentePorFolio(String folio) throws PersistenciaException;
    
    /**
     * Metodo para registrar un reporte de incidente
     * @param reporteIncidente que se registrara
     * @return el reporte de incidente registrado correctamente 
     * @throws PersistenciaException si ocurre un error al registrar 
     */
    public abstract ReporteIncidente generarReporteIncidente(ReporteIncidente reporteIncidente) throws PersistenciaException;
    
    /**
     * Metodo para actualizar el estado de un reporte de incidente
     * @param reporteIncidente al que se le actualizara el estado
     * @return el reporte con el estado actualizado 
     * @throws PersistenciaException si ocurre un error al actualizar el estado del reporte
     */
    public abstract ReporteIncidente actualizarEstadoReporteIncidente(ReporteIncidente reporteIncidente) throws PersistenciaException;
    
    /**
     * Metodo para consultar los reportes de incidente generados por un cliente.
     * Utilizado en vista usuario para mostrar los reportes generados por un cliente.
     * Permite consultar con filtros tambien.
     * @param filtros para consultar ( fecha desde, fecha hasta, estado del reporte y categoria del reporte)
     * @return una lista que coincida con los filtros ( si aplica ) y que sean del cliente que los consulto
     * @throws PersistenciaException si ocurre un error al consultar los reportes
     */
    public abstract List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentesPorCliente(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException;
}
