
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.entidades.ReporteIncidente;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface IHistorialIncidentesDAO {
    public abstract List<ReporteIncidente> consultarReportesIncidentes() throws PersistenciaException; //Agregar como parametro DTO con los filtros
    public abstract List<ReporteIncidente> consultarReportesIncidentesFiltros() throws PersistenciaException;
    public abstract List<ReporteIncidente> consultarReportesIncidentesCliente(String id) throws PersistenciaException;
    public abstract ReporteIncidente consultarReporteIncidentePorId(String id) throws PersistenciaException;
    public abstract ReporteIncidente generarReporteIncidente(ReporteIncidente reporteIncidente) throws PersistenciaException;
    public abstract ReporteIncidente eliminarReporteIncidente(String idReporte) throws PersistenciaException;
}
