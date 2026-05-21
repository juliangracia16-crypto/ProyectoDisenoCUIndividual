
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesYViceversaAdapter;
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
    
    /**
     * Metodo para consultar todos los reportes de incidentes
     * utilizando filtros
     * @param filtros por los que se consultara los reportes
     * Por: nombre cliente, estado reporte, categoria reporte, fecha desde, fecha hasta
     * @return una lista con los reportes que coincidan con los filtros o
     * todos los reportes si los filtros van vacios
     * @throws NegocioException si ocurre un error al consultar o si los
     * filtros no cumplen las validaciones establecidas
     */
    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        try {
            FiltrosConsultaHistorialReportesDTO filtrosPersistencia = DtosAEntidadesYViceversaAdapter.adaptarFiltrosDTO(filtros);
            List<ReporteIncidentePersistenciaDTO> reportesIncidentes = fachada.consultarReportesIncidentesFiltros(filtrosPersistencia);
            List<ReporteIncidenteDTO> reportesIncidentesDTO = new LinkedList<>();

            for (ReporteIncidentePersistenciaDTO reporte : reportesIncidentes) {
                ReporteIncidenteDTO reporteDTO = DtosAEntidadesYViceversaAdapter.adaptarReporteIncidenteEntidad(reporte);
                reportesIncidentesDTO.add(reporteDTO);
            }
            return reportesIncidentesDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar todos los reportes de incidentes.", ex);
        }
    }

    /**
     * Metodo para generar un reporte de incidente
     * @param reporte objeto con los datos necesarios
     * para generar un nuevo reporte de incidente
     * @return el reporte de incidente generado correctamente
     * @throws NegocioException si ocurre un error al generar el reporte
     */
    @Override
    public ReporteIncidenteGeneradoDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporte) throws NegocioException {
        try {
            Imagen imagen = DtosAEntidadesYViceversaAdapter.adaptarImagenDTO(reporte.imagen());
            EstadoReporte estado = fachada.consultarEstadoPorNombre(ESTADO_INICIAL_REPORTE);
            Categoria categoria = fachada.consultarCategoriaPorNombre(reporte.categoria().categoria());
            
            
            ReporteIncidente reporteIncidente = DtosAEntidadesYViceversaAdapter.adaptarReporteIncidenteDTO(reporte);
            reporteIncidente.setEstadoReporte(estado);
            if(imagen == null){
                reporteIncidente.setImagen(null);
            }else{
                reporteIncidente.setImagen(imagen);
            }
            reporteIncidente.setIdCategoria(categoria.getId());
            reporteIncidente.setIdCliente(reporte.cliente().getIdCliente());
            
            ReporteIncidente reporteIncidenteGenerado = fachada.generarReporteIncidente(reporteIncidente);
            ReporteIncidenteGeneradoDTO reporteIncidenteDTO = DtosAEntidadesYViceversaAdapter.adaptarReporteIncidenteEntidad(reporteIncidenteGenerado);
            return reporteIncidenteDTO;
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al generar reporte de incidente.",ex);
        }
    }
    
    /**
     * Metodo para consultar todos los reportes de incidentes
     * @return una lista con todos los reportes 
     * @throws NegocioException si ocurre un error al consultar
     */
    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentes() throws NegocioException {
        try {
            List<ReporteIncidentePersistenciaDTO> reportesIncidentes = fachada.consultarReportesIncidentes();
            List<ReporteIncidenteDTO> reportesIncidentesDTO = new LinkedList<>();
            
            for(ReporteIncidentePersistenciaDTO reporte: reportesIncidentes){
                ReporteIncidenteDTO reporteDTO = DtosAEntidadesYViceversaAdapter.adaptarReporteIncidenteEntidad(reporte);
                reportesIncidentesDTO.add(reporteDTO);
            }
            return reportesIncidentesDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar todos los reportes de incidentes.",ex);
        }
    }
    
    /**
     * Metodo para consultar un reporte de incidente por folio
     * @param folio por el que se consultara el reporte
     * @return el reporte de incidente que coincida con el folio
     * @throws NegocioException si ocurre un error al consultar o 
     * si no se encuentra ningun reporte relacionado a ese folio
     */
    @Override
    public ReporteIncidenteDTO consultarReporteIncidentePorFolio(String folio) throws NegocioException {
        try {
            ReporteIncidentePersistenciaDTO reportesIncidentes = fachada.consultarReporteIncidentePorFolio(folio);
            ReporteIncidenteDTO reporteDTO = DtosAEntidadesYViceversaAdapter.adaptarReporteIncidenteEntidad(reportesIncidentes);
            return reporteDTO;
        }catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar todos los reportes de incidentes.", ex);
        }
    }

    /**
     * Metodo para consultar todos los reportes de incidentes generados por un cliente
     * utilizando filtros
     * @param filtros por los que se consultara los reportes
     * Por: estado reporte, categoria reporte, fecha desde, fecha hasta
     * @return una lista con los reportes del cliente que coincidan con los filtros o
     * todos los reportes del cliente si los filtros van vacios
     * @throws NegocioException si ocurre un error al consultar o si los
     * filtros no cumplen las validaciones establecidas
     */
    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentesPorCliente(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        try {
            FiltrosConsultaHistorialReportesDTO filtrosPersistencia = DtosAEntidadesYViceversaAdapter.adaptarFiltrosDTO(filtros);
            List<ReporteIncidentePersistenciaDTO> reportesIncidentes = fachada.consultarReportesIncidentesPorCliente(filtrosPersistencia);
            List<ReporteIncidenteDTO> reportesIncidentesDTO = new LinkedList<>();

            for (ReporteIncidentePersistenciaDTO reporte : reportesIncidentes) {
                ReporteIncidenteDTO reporteDTO = DtosAEntidadesYViceversaAdapter.adaptarReporteIncidenteEntidad(reporte);
                reportesIncidentesDTO.add(reporteDTO);
            }
            return reportesIncidentesDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar todos los reportes de incidentes.", ex);
        }
    }
    
}
