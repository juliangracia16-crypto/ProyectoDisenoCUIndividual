
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.FiltrosConsultaIncidenteDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface IHistorialIncidentesBO {
    public abstract List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaIncidenteDTO filtros) throws NegocioException;
    public abstract List<ReporteIncidenteDTO> consultarReportesIncidentes() throws NegocioException;
    public abstract List<ReporteIncidenteDTO> consultarReportesIncidentesCliente(String id) throws NegocioException;
    public abstract ReporteIncidenteDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporte) throws NegocioException;
    public abstract ReporteIncidenteDTO eliminarReporteIncidente(String folio) throws NegocioException;
}
