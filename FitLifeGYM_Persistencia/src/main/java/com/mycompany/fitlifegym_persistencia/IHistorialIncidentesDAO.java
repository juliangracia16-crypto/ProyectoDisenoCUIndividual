
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteIncidentePersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteIncidente;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface IHistorialIncidentesDAO {
    public abstract List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentes() throws PersistenciaException;
    public abstract List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentesFiltros(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException;
    public abstract ReporteIncidentePersistenciaDTO consultarReporteIncidentePorFolio(String folio) throws PersistenciaException;
    public abstract ReporteIncidente generarReporteIncidente(ReporteIncidente reporteIncidente) throws PersistenciaException;
    public abstract ReporteIncidente actualizarEstadoReporteIncidente(ReporteIncidente reporteIncidente) throws PersistenciaException;
}
