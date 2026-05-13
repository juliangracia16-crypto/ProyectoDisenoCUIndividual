
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteGeneradoDTO;
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteIncidentePersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import com.mycompany.fitlifegym_persistencia.entidades.Imagen;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteIncidente;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Julian
 */
public class HistorialIncidentesBO implements IHistorialIncidentesBO{
    private final IPersistenciaFachada fachada;
    private final String ESTADO_INICIAL_REPORTE = "SIN RESOLVER";

    public HistorialIncidentesBO(IPersistenciaFachada fachada) {
        this.fachada = fachada;
    }
    
    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        try {
            FiltrosConsultaHistorialReportesDTO filtrosPersistencia = DtosAEntidadesAdapter.adaptarFiltrosDTO(filtros);
            List<ReporteIncidentePersistenciaDTO> reportesIncidentes = fachada.consultarReportesIncidentesFiltros(filtrosPersistencia);
            List<ReporteIncidenteDTO> reportesIncidentesDTO = new LinkedList<>();

            for (ReporteIncidentePersistenciaDTO reporte : reportesIncidentes) {
                ReporteIncidenteDTO reporteDTO = DtosAEntidadesAdapter.adaptarReporteIncidenteEntidad(reporte);
                reportesIncidentesDTO.add(reporteDTO);
            }
            return reportesIncidentesDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar todos los reportes de incidentes.", ex);
        }
    }

    @Override
    public ReporteIncidenteGeneradoDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporte) throws NegocioException {
        try {
            Imagen imagen = DtosAEntidadesAdapter.adaptarImagenDTO(reporte.imagen());
            Imagen imagenGuardada = fachada.guardarImagen(imagen);
//            Imagen imagenGuardada = null;
//            if(imagenGuardada != null){
//                imagenGuardada = fachada.guardarImagen(imagen);
//            }
            EstadoReporte estado = fachada.consultarEstadoPorNombre(ESTADO_INICIAL_REPORTE);
            Categoria categoria = fachada.consultarCategoriaPorNombre(reporte.categoria().categoria());
            
            
            ReporteIncidente reporteIncidente = DtosAEntidadesAdapter.adaptarReporteIncidenteDTO(reporte);
            reporteIncidente.setIdEstado(estado.getId());
            if(imagenGuardada == null){
                reporteIncidente.setIdImagen(null);
            }else{
                reporteIncidente.setIdImagen(imagenGuardada.getId());
            }
            reporteIncidente.setIdCategoria(categoria.getId());
            reporteIncidente.setIdCliente(reporte.cliente().getIdCliente());
            
            ReporteIncidente reporteIncidenteGenerado = fachada.generarReporteIncidente(reporteIncidente);
            ReporteIncidenteGeneradoDTO reporteIncidenteDTO = DtosAEntidadesAdapter.adaptarReporteIncidenteEntidad(reporteIncidenteGenerado);
            return reporteIncidenteDTO;
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al generar reporte de incidente.",ex);
        }
    }

    @Override
    public ReporteIncidenteDTO eliminarReporteIncidente(String folio) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentes() throws NegocioException {
        try {
            List<ReporteIncidentePersistenciaDTO> reportesIncidentes = fachada.consultarReportesIncidentes();
            List<ReporteIncidenteDTO> reportesIncidentesDTO = new LinkedList<>();
            
            for(ReporteIncidentePersistenciaDTO reporte: reportesIncidentes){
                ReporteIncidenteDTO reporteDTO = DtosAEntidadesAdapter.adaptarReporteIncidenteEntidad(reporte);
                reportesIncidentesDTO.add(reporteDTO);
            }
            return reportesIncidentesDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar todos los reportes de incidentes.",ex);
        }
    }
    
    @Override
    public ReporteIncidenteDTO consultarReporteIncidentePorFolio(String folio) throws NegocioException {
        try {
            ReporteIncidentePersistenciaDTO reportesIncidentes = fachada.consultarReporteIncidentePorFolio(folio);
            ReporteIncidenteDTO reporteDTO = DtosAEntidadesAdapter.adaptarReporteIncidenteEntidad(reportesIncidentes);
            return reporteDTO;
        }catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar todos los reportes de incidentes.", ex);
        }
    }
    
}
