
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultasAtencionesDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import com.mycompany.fitlifegym_persistencia.entidades.Imagen;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteAtencion;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteIncidente;
import java.util.List;

/**
 *
 * @author Julian
 */
public class HistorialAtencionesBO implements IHistorialAtencionesBO{
    private IPersistenciaFachada fachada;
    private final String ESTADO_REPORTE_RESUELTO = "RESUELTO";

    public HistorialAtencionesBO(IPersistenciaFachada fachada) {
        this.fachada = fachada;
    }
    
    @Override
    public List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultasAtencionesDTO filtros) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReporteAtencionDTO atenderReporteIncidente(AtenderReporteDTO reporte) throws NegocioException {
        try {
            EstadoReporte estado = fachada.consultarEstadoPorNombre(ESTADO_REPORTE_RESUELTO);
            ReporteIncidente reporteIncidente = fachada.consultarReporteIncidentePorId(reporte.folio());
            reporteIncidente.setEstado(estado);
            ReporteIncidenteDTO reporteIncidenteDTO  = DtosAEntidadesAdapter.adaptarReporteIncidenteEntidad(reporteIncidente);
            
            ReporteAtencionDTO reporteAtencionDTO = new ReporteAtencionDTO(
                    reporteIncidenteDTO.folio(),
                    reporte.solucion(),
                    reporteIncidenteDTO.categoria(),
                    reporteIncidenteDTO.fecha(),
                    reporteIncidenteDTO.estado(),
                    reporte.imagen(),
                    reporteIncidenteDTO.cliente()
            );
            
            Imagen imagen = DtosAEntidadesAdapter.adaptarImagenDTO(reporte.imagen());
            fachada.guardarImagen(imagen);
            
            ReporteAtencion reporteAtencion = DtosAEntidadesAdapter.adaptarReporteAtencionDTO(reporteAtencionDTO);
            ReporteAtencion reporteAtendido = fachada.resolverReporte(reporteAtencion);
            
            ReporteAtencionDTO reporteAtendidoDTO = DtosAEntidadesAdapter.adaptarReporteAtencionEntidad(reporteAtendido);
            return reporteAtendidoDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo atender el reporte de incidente correctamente.",ex);
        }
    }

    @Override
    public ReporteAtencionDTO eliminarReporteAtencion(String folio) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
