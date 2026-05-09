
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.entidades.ReporteAtencion;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface IHistorialAtencionesDAO {
    public abstract List<ReporteAtencion> consultarReportesAtencion() throws PersistenciaException; //agregar como parametro la dto de los filtros
    public abstract ReporteAtencion consultarReporteAtencionPorId(String id) throws PersistenciaException;
    public abstract ReporteAtencion resolverReporte(ReporteAtencion reporte) throws PersistenciaException;
    public abstract ReporteAtencion eliminarReporteAtencion(String idReporte) throws PersistenciaException;
}
