
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteAtencionPersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteAtencion;
import java.util.List;

/**
 * Define las operaciones de acceso y manipulación de datos relacionadas con los
 * reportes de atencion en el sistema.
 *
 * Esta interfaz establece el contrato que debe implementar la capa de
 * persistencia para la gestión de los reportes de atencion.
 * @author Julian
 */
public interface IHistorialAtencionesDAO {
    
    /**
     * Metodo para consultar todos los reportes de atencion
     * @return una lista con los reportes de atencion hasta ese momento 
     * @throws PersistenciaException si ocurre un error al consultar los reportes de atencion
     */
    public abstract List<ReporteAtencionPersistenciaDTO> consultarReportesAtencion() throws PersistenciaException;
    
    /**
     * Metodo para consultar los reportes de atencion filtrados
     * @param filtros por los que se buscaran los reportes ( Por: nombre de cliente, fecha desde, fecha hasta, estado del reporte y categoria del reporte)
     * @return una lista con los reportes de atencion que coincidan con los filtros
     * @throws PersistenciaException si ocurre un error al consultar los reportes de atencion
     */
    public abstract List<ReporteAtencionPersistenciaDTO> consultarReportesAtencionesFiltros(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException;
    
    /**
     * Metodo para consultar un reporte de atencion por su folio
     * @param folio del reporte atencion a consultar
     * @return el reporte atencion correspondiente al folio
     * @throws PersistenciaException si ocurre un error al consultar o si no se encuentra
     * ningun reporte que coincida con el folio
     */
    public abstract ReporteAtencionPersistenciaDTO consultarReporteAtencionPorFolio(String folio) throws PersistenciaException;
    
    /**
     * Metodo para resolver un reporte de atencion
     * @param reporte resuelto que se registrara
     * @return el reporte registrado correctamente
     * @throws PersistenciaException si ocurre un error al registrar el reporte de atencion
     */
    public abstract ReporteAtencion resolverReporte(ReporteAtencion reporte) throws PersistenciaException;
}
