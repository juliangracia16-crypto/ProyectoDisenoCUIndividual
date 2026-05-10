
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaIncidenteDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteIncidente;
import java.util.List;

/**
 *
 * @author Julian
 */
public class HistorialIncidentesBO implements IHistorialIncidentesBO{
    private IPersistenciaFachada fachada;
    private final String ESTADO_INICIAL_REPORTE = "SIN_RESOLVER";

    public HistorialIncidentesBO(IPersistenciaFachada fachada) {
        this.fachada = fachada;
    }
    
    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaIncidenteDTO filtros) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReporteIncidenteDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporte) throws NegocioException {
        try {
            EstadoReporte estado = fachada.consultarEstadoPorNombre(ESTADO_INICIAL_REPORTE);
            ReporteIncidente reporteIncidente = DtosAEntidadesAdapter.adaptarReporteIncidenteDTO(reporte);
            reporteIncidente.setEstado(estado);
            ReporteIncidente reporteIncidenteGenerado = fachada.generarReporteIncidente(reporteIncidente);
            ReporteIncidenteDTO reporteIncidenteDTO = DtosAEntidadesAdapter.adaptarReporteIncidenteEntidad(reporteIncidenteGenerado);
            return reporteIncidenteDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al generar reporte de incidente.",ex);
        }
    }

    @Override
    public ReporteIncidenteDTO eliminarReporteIncidente(String folio) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
