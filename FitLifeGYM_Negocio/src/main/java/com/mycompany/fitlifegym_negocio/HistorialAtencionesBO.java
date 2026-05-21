
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesYViceversaAdapter;
import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.RegistroReporteAdminDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionGeneradoDTO;
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteAtencionPersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteIncidentePersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import com.mycompany.fitlifegym_persistencia.entidades.Imagen;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteAtencion;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteIncidente;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Julian
 */
public class HistorialAtencionesBO implements IHistorialAtencionesBO{
    private final IPersistenciaFachada fachada;
    private final String ESTADO_REPORTE_RESUELTO = "RESUELTO";
    private final String ESTADO_REPORTE_SIN_RESOLVER = "SIN RESOLVER";

    public HistorialAtencionesBO(IPersistenciaFachada fachada) {
        this.fachada = fachada;
    }
    
    /**
     * Metodo para consultar todos los reportes de atenciones
     * utilizando filtros
     * @param filtros por los que se consultara los reportes
     * Por: nombre cliente, estado reporte, categoria reporte, fecha desde, fecha hasta
     * @return una lista con los reportes que coincidan con los filtros o
     * todos los reportes si los filtros van vacios
     * @throws NegocioException si ocurre un error al consultar o si los
     * filtros no cumplen las validaciones establecidas
     */
    @Override
    public List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        try {
            FiltrosConsultaHistorialReportesDTO filtrosPersistencia = DtosAEntidadesYViceversaAdapter.adaptarFiltrosDTO(filtros);
            List<ReporteAtencionPersistenciaDTO> reportesAtenciones = fachada.consultarReportesAtencionFiltros(filtrosPersistencia);
            List<ReporteAtencionDTO> reportesAtencionesDTO = new LinkedList<>();
            for(ReporteAtencionPersistenciaDTO r: reportesAtenciones){
                ReporteAtencionDTO reporteDTO = DtosAEntidadesYViceversaAdapter.adaptarReporteAtencionEntidad(r);
                reportesAtencionesDTO.add(reporteDTO);
            }
            return reportesAtencionesDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar los reportes de atención.",ex);
        }
    }

    /**
     * Metodo para atender/resolver un reporte de incidente 
     * @param reporte objeto con los datos necesarios
     * para resolver un reporte de incidente
     * @return un reporte de atencion generado correctamente
     * despues de resolver el reporte de incidente
     * @throws NegocioException si ocurre un error al resolver el reporte
     * de incidente 
     */
    @Override
    public ReporteAtencionGeneradoDTO atenderReporteIncidente(AtenderReporteDTO reporte) throws NegocioException {
        try {
            ReporteIncidentePersistenciaDTO reporteIncidente = fachada.consultarReporteIncidentePorFolio(reporte.folio());
            ReporteIncidente reporteIncidenteEntidad = DtosAEntidadesYViceversaAdapter.adaptarReporteIncidenteDTO(reporteIncidente);
            EstadoReporte estado = fachada.consultarEstadoPorNombre(ESTADO_REPORTE_RESUELTO);
            reporteIncidenteEntidad.setEstadoReporte(estado);
            
            Imagen imagen = null;
            if(reporte.imagen() != null){
                imagen = DtosAEntidadesYViceversaAdapter.adaptarImagenDTO(reporte.imagen());
            }
             
            Categoria categoria = fachada.consultarCategoriaPorNombre(reporteIncidente.getCategoria().getNombre());
            fachada.actualizarEstadoReporteIncidente(reporteIncidenteEntidad);
            
            ReporteAtencion reporteAtencion = DtosAEntidadesYViceversaAdapter.adaptarReporteAtencionDTO(reporte);
            reporteAtencion.setIdCategoria(categoria.getId());
            reporteAtencion.setEstadoReporte(estado);
            reporteAtencion.setFecha(reporteIncidente.getFecha());
            reporteAtencion.setIdCliente(reporteIncidente.getCliente().getId());
            reporteAtencion.setAsunto(reporteIncidente.getAsunto());
            if(imagen == null){
                reporteAtencion.setImagen(null);
            }else{
                reporteAtencion.setImagen(imagen);
            }
            
            ReporteAtencion reporteAtendido = fachada.resolverReporte(reporteAtencion);
            ReporteAtencionGeneradoDTO reporteAtendidoDTO = DtosAEntidadesYViceversaAdapter.adaptarReporteAtencionEntidad(reporteAtendido);
            return reporteAtendidoDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo atender el reporte de incidente correctamente.",ex);
        }
    }

    /**
     * Metodo para consultar todos los reportes de atenciones
     * @return una lista con todos los reportes
     * @throws NegocioException si ocurre un error al consultar
     */
    @Override
    public List<ReporteAtencionDTO> consultarReportesAtenciones() throws NegocioException {
        try {
            List<ReporteAtencionDTO> reportesAtencionDTO = new LinkedList<>();
            List<ReporteAtencionPersistenciaDTO> reportesAtencion = fachada.consultarReportesAtencion();
            for(ReporteAtencionPersistenciaDTO reporte: reportesAtencion){
                ReporteAtencionDTO reporteDTO = DtosAEntidadesYViceversaAdapter.adaptarReporteAtencionEntidad(reporte);
                reportesAtencionDTO.add(reporteDTO);
            }
            return reportesAtencionDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar todos los reportes de atenciones.");
        }
    }

    /**
     * Metodo para consultar todos los reportes (Atenciones y Incidentes)
     * y agruparlos juntos para la vista de administrador. 
     * @return una lista con los dos tipos de reportes agrupados
     * @throws NegocioException si ocurre un error al agruparlos o al consultarlos
     */
    @Override
    public List<RegistroReporteAdminDTO> consultarTodosLosRegistrosReportes() throws NegocioException {
        try {
            List<RegistroReporteAdminDTO> registrosReportes = new LinkedList<>();
            List<ReporteAtencionPersistenciaDTO> reportesAtencion = fachada.consultarReportesAtencion();
            EstadoReporte estado = fachada.consultarEstadoPorNombre(ESTADO_REPORTE_SIN_RESOLVER);
            FiltrosConsultaHistorialReportesDTO filtros = new FiltrosConsultaHistorialReportesDTO(null, estado,null,null,null);
            List<ReporteIncidentePersistenciaDTO> reporteIncidentes = fachada.consultarReportesIncidentesFiltros(filtros);
            
            for(ReporteAtencionPersistenciaDTO reporte: reportesAtencion){
                RegistroReporteAdminDTO reporteDTO = DtosAEntidadesYViceversaAdapter.adaptarRegistrosReportesAdminDTO(reporte);
                registrosReportes.add(reporteDTO);
            }
            for(ReporteIncidentePersistenciaDTO reporte: reporteIncidentes){
                RegistroReporteAdminDTO reporteIncidenteDTO = DtosAEntidadesYViceversaAdapter.adaptarRegistrosReportesAdminDTO(reporte);
                registrosReportes.add(reporteIncidenteDTO);
            }
            return registrosReportes;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar todos los registros de los reportes.");
        }
    }

    /**
     * Metodo para consultar un reporte de atencion por folio
     * @param folio por el que se consultara el reporte
     * @return el reporte de atencion que corresponda al folio
     * @throws NegocioException si ocurre un error al consultar o
     * si no se encuentra ningun reporte relacionado a ese folio
     */
    @Override
    public ReporteAtencionDTO consultarReporteAtencionPorFolio(String folio) throws NegocioException {
        try {
            ReporteAtencionPersistenciaDTO reporteAtencion = fachada.consultarReporteAtencionPorFolio(folio);
            ReporteAtencionDTO reporteAtencionDTO = DtosAEntidadesYViceversaAdapter.adaptarReporteAtencionEntidad(reporteAtencion);
            return reporteAtencionDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el reporte de atencion por folio.");
        }
    }

    /**
     * Metodo para consultar todos los reportes por filtro (Atenciones y Incidentes)
     * y agruparlos juntos para la vista de administrador. 
     * @param filtros por los que se consultaran todos los reportes
     * Por: nombre cliente, estado del reporte, categoria del reporte, fecha desde, fecha hasta
     * @return una lista con los dos tipos de reportes agrupados que coincidan con los filtros
     * @throws NegocioException si ocurre un error al consultarlos o si 
     * los filtros no cumplen con las validaciones establecidas
     */
    @Override
    public List<RegistroReporteAdminDTO> consultarTodosLosRegistrosReportesFiltrado(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        try {
            FiltrosConsultaHistorialReportesDTO filtrosPersistencia = DtosAEntidadesYViceversaAdapter.adaptarFiltrosDTO(filtros);
            List<RegistroReporteAdminDTO> registrosReportes = new LinkedList<>();
            List<ReporteAtencionPersistenciaDTO> reportesAtencion = fachada.consultarReportesAtencionFiltros(filtrosPersistencia);
            List<ReporteIncidentePersistenciaDTO> reporteIncidentes = fachada.consultarReportesIncidentesFiltros(filtrosPersistencia);

            for (ReporteAtencionPersistenciaDTO reporte : reportesAtencion) {
                RegistroReporteAdminDTO reporteDTO = DtosAEntidadesYViceversaAdapter.adaptarRegistrosReportesAdminDTO(reporte);
                registrosReportes.add(reporteDTO);
            }
            for (ReporteIncidentePersistenciaDTO reporte : reporteIncidentes) {
                RegistroReporteAdminDTO reporteIncidenteDTO = DtosAEntidadesYViceversaAdapter.adaptarRegistrosReportesAdminDTO(reporte);
                registrosReportes.add(reporteIncidenteDTO);
            }
            return registrosReportes;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar todos los registros de los reportes.");
        }
    }
    
}
