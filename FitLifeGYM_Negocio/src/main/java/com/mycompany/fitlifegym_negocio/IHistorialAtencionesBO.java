
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface IHistorialAtencionesBO {
    public abstract List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException;
    public abstract List<ReporteAtencionDTO> consultarReportesAtenciones() throws NegocioException;
    public abstract ReporteAtencionDTO atenderReporteIncidente(AtenderReporteDTO reporte) throws NegocioException;
    public abstract ReporteAtencionDTO eliminarReporteAtencion(String folio) throws NegocioException;
}
