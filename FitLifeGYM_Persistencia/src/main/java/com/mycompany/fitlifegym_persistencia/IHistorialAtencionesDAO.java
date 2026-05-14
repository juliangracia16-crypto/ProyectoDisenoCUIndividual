
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteAtencionPersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteAtencion;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface IHistorialAtencionesDAO {
    public abstract List<ReporteAtencionPersistenciaDTO> consultarReportesAtencion() throws PersistenciaException;
    public abstract List<ReporteAtencionPersistenciaDTO> consultarReportesAtencionesFiltros(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException;
    public abstract ReporteAtencionPersistenciaDTO consultarReporteAtencionPorFolio(String folio) throws PersistenciaException;
    public abstract ReporteAtencion resolverReporte(ReporteAtencion reporte) throws PersistenciaException;
    public abstract ReporteAtencion eliminarReporteAtencion(String idReporte) throws PersistenciaException;
}
