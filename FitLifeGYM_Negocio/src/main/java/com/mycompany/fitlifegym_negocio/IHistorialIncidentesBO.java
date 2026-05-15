
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteGeneradoDTO;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface IHistorialIncidentesBO {
    public abstract List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException;
    public abstract List<ReporteIncidenteDTO> consultarReportesIncidentes() throws NegocioException;
    public abstract ReporteIncidenteDTO consultarReporteIncidentePorFolio(String folio) throws NegocioException;
    public abstract ReporteIncidenteGeneradoDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporte) throws NegocioException;
}
