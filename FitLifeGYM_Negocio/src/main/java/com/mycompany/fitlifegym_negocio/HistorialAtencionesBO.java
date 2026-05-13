
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteAtencion;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Julian
 */
public class HistorialAtencionesBO implements IHistorialAtencionesBO{
    private final IPersistenciaFachada fachada;
    private final String ESTADO_REPORTE_RESUELTO = "RESUELTO";

    public HistorialAtencionesBO(IPersistenciaFachada fachada) {
        this.fachada = fachada;
    }
    
    @Override
    public List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        try {
            FiltrosConsultaHistorialReportesDTO filtrosPersistencia = DtosAEntidadesAdapter.adaptarFiltrosDTO(filtros);
            List<ReporteAtencion> reportesAtenciones = fachada.consultarReportesAtencionFiltros(filtrosPersistencia);
            List<ReporteAtencionDTO> reportesAtencionesDTO = new LinkedList<>();
            for(ReporteAtencion r: reportesAtenciones){
                ReporteAtencionDTO reporteDTO = DtosAEntidadesAdapter.adaptarReporteAtencionEntidad(r);
                reportesAtencionesDTO.add(reporteDTO);
            }
            return reportesAtencionesDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar los reportes de atención.",ex);
        }
    }

    @Override
    public ReporteAtencionDTO atenderReporteIncidente(AtenderReporteDTO reporte) throws NegocioException {
//        try {
//            EstadoReporte estado = fachada.consultarEstadoPorNombre(ESTADO_REPORTE_RESUELTO);
//            ReporteIncidente reporteIncidente = fachada.consultarReporteIncidentePorId(reporte.folio());
//            reporteIncidente.setIdEstado(estado.getId());
//            ReporteIncidenteDTO reporteIncidenteDTO  = DtosAEntidadesAdapter.adaptarReporteIncidenteEntidad(reporteIncidente);
//            
//            ReporteAtencionDTO reporteAtencionDTO = new ReporteAtencionDTO(
//                    reporteIncidenteDTO.folio(),
//                    reporte.solucion(),
//                    reporteIncidenteDTO.categoria(),
//                    reporteIncidenteDTO.fecha(),
//                    reporteIncidenteDTO.estado(),
//                    reporte.imagen(),
//                    reporteIncidenteDTO.cliente()
//            );
//            
//            Imagen imagen = DtosAEntidadesAdapter.adaptarImagenDTO(reporte.imagen());
//            fachada.guardarImagen(imagen);
//            
//            ReporteAtencion reporteAtencion = DtosAEntidadesAdapter.adaptarReporteAtencionDTO(reporteAtencionDTO);
//            ReporteAtencion reporteAtendido = fachada.resolverReporte(reporteAtencion);
//            
//            ReporteAtencionDTO reporteAtendidoDTO = DtosAEntidadesAdapter.adaptarReporteAtencionEntidad(reporteAtendido);
//            return reporteAtendidoDTO;
//        } catch (PersistenciaException ex) {
//            throw new NegocioException("No se pudo atender el reporte de incidente correctamente.",ex);
//        }
        return null;
    }

    @Override
    public ReporteAtencionDTO eliminarReporteAtencion(String folio) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ReporteAtencionDTO> consultarReportesAtenciones() throws NegocioException {
//        try {
//            List<ReporteAtencionDTO> reportesAtencionDTO = new LinkedList<>();
//            List<ReporteAtencion> reportesAtencion = fachada.consultarReportesAtencion();
//            for(ReporteAtencion reporte: reportesAtencion){
//                ReporteAtencionDTO reporteDTO = DtosAEntidadesAdapter.adaptarReporteAtencionEntidad(reporte);
//                reportesAtencionDTO.add(reporteDTO);
//            }
//            return reportesAtencionDTO;
//        } catch (PersistenciaException ex) {
//            throw new NegocioException("Error al cargar todos los reportes de atenciones.");
//        }
        return null;
        
    }
    
}
