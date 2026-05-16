
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
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
import java.time.LocalDate;
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
    
    @Override
    public List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        try {
            FiltrosConsultaHistorialReportesDTO filtrosPersistencia = DtosAEntidadesAdapter.adaptarFiltrosDTO(filtros);
            List<ReporteAtencionPersistenciaDTO> reportesAtenciones = fachada.consultarReportesAtencionFiltros(filtrosPersistencia);
            List<ReporteAtencionDTO> reportesAtencionesDTO = new LinkedList<>();
            for(ReporteAtencionPersistenciaDTO r: reportesAtenciones){
                ReporteAtencionDTO reporteDTO = DtosAEntidadesAdapter.adaptarReporteAtencionEntidad(r);
                reportesAtencionesDTO.add(reporteDTO);
            }
            return reportesAtencionesDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar los reportes de atención.",ex);
        }
    }

    @Override
    public ReporteAtencionGeneradoDTO atenderReporteIncidente(AtenderReporteDTO reporte) throws NegocioException {
        try {
            ReporteIncidentePersistenciaDTO reporteIncidente = fachada.consultarReporteIncidentePorFolio(reporte.folio());
            ReporteIncidente reporteIncidenteEntidad = DtosAEntidadesAdapter.adaptarReporteIncidenteDTO(reporteIncidente);
            EstadoReporte estado = fachada.consultarEstadoPorNombre(ESTADO_REPORTE_RESUELTO);
            reporteIncidenteEntidad.setEstadoReporte(estado);
            
            Imagen imagen = null;
            if(reporte.imagen() != null){
                imagen = DtosAEntidadesAdapter.adaptarImagenDTO(reporte.imagen());
            }
             
            Categoria categoria = fachada.consultarCategoriaPorNombre(reporteIncidente.getCategoria().getNombre());
            fachada.actualizarEstadoReporteIncidente(reporteIncidenteEntidad);
            
            ReporteAtencion reporteAtencion = DtosAEntidadesAdapter.adaptarReporteAtencionDTO(reporte);
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
            ReporteAtencionGeneradoDTO reporteAtendidoDTO = DtosAEntidadesAdapter.adaptarReporteAtencionEntidad(reporteAtendido);
            return reporteAtendidoDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo atender el reporte de incidente correctamente.",ex);
        }
    }

    @Override
    public List<ReporteAtencionDTO> consultarReportesAtenciones() throws NegocioException {
        try {
            List<ReporteAtencionDTO> reportesAtencionDTO = new LinkedList<>();
            List<ReporteAtencionPersistenciaDTO> reportesAtencion = fachada.consultarReportesAtencion();
            for(ReporteAtencionPersistenciaDTO reporte: reportesAtencion){
                ReporteAtencionDTO reporteDTO = DtosAEntidadesAdapter.adaptarReporteAtencionEntidad(reporte);
                reportesAtencionDTO.add(reporteDTO);
            }
            return reportesAtencionDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar todos los reportes de atenciones.");
        }
    }

    @Override
    public List<RegistroReporteAdminDTO> consultarTodosLosRegistrosReportes() throws NegocioException {
        try {
            List<RegistroReporteAdminDTO> registrosReportes = new LinkedList<>();
            List<ReporteAtencionPersistenciaDTO> reportesAtencion = fachada.consultarReportesAtencion();
            EstadoReporte estado = fachada.consultarEstadoPorNombre(ESTADO_REPORTE_SIN_RESOLVER);
            FiltrosConsultaHistorialReportesDTO filtros = new FiltrosConsultaHistorialReportesDTO(null, estado,null,null,null);
            List<ReporteIncidentePersistenciaDTO> reporteIncidentes = fachada.consultarReportesIncidentesFiltros(filtros);
            
            for(ReporteAtencionPersistenciaDTO reporte: reportesAtencion){
                RegistroReporteAdminDTO reporteDTO = DtosAEntidadesAdapter.adaptarRegistrosReportesAdminDTO(reporte);
                registrosReportes.add(reporteDTO);
            }
            for(ReporteIncidentePersistenciaDTO reporte: reporteIncidentes){
                RegistroReporteAdminDTO reporteIncidenteDTO = DtosAEntidadesAdapter.adaptarRegistrosReportesAdminDTO(reporte);
                registrosReportes.add(reporteIncidenteDTO);
            }
            return registrosReportes;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar todos los registros de los reportes.");
        }
    }

    @Override
    public ReporteAtencionDTO consultarReporteAtencionPorFolio(String folio) throws NegocioException {
        try {
            ReporteAtencionPersistenciaDTO reporteAtencion = fachada.consultarReporteAtencionPorFolio(folio);
            ReporteAtencionDTO reporteAtencionDTO = DtosAEntidadesAdapter.adaptarReporteAtencionEntidad(reporteAtencion);
            return reporteAtencionDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el reporte de atencion por folio.");
        }
    }

    @Override
    public List<RegistroReporteAdminDTO> consultarTodosLosRegistrosReportesFiltrado(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        try {
            FiltrosConsultaHistorialReportesDTO filtrosPersistencia = DtosAEntidadesAdapter.adaptarFiltrosDTO(filtros);
            List<RegistroReporteAdminDTO> registrosReportes = new LinkedList<>();
            List<ReporteAtencionPersistenciaDTO> reportesAtencion = fachada.consultarReportesAtencionFiltros(filtrosPersistencia);
            List<ReporteIncidentePersistenciaDTO> reporteIncidentes = fachada.consultarReportesIncidentesFiltros(filtrosPersistencia);

            for (ReporteAtencionPersistenciaDTO reporte : reportesAtencion) {
                RegistroReporteAdminDTO reporteDTO = DtosAEntidadesAdapter.adaptarRegistrosReportesAdminDTO(reporte);
                registrosReportes.add(reporteDTO);
            }
            for (ReporteIncidentePersistenciaDTO reporte : reporteIncidentes) {
                RegistroReporteAdminDTO reporteIncidenteDTO = DtosAEntidadesAdapter.adaptarRegistrosReportesAdminDTO(reporte);
                registrosReportes.add(reporteIncidenteDTO);
            }
            return registrosReportes;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar todos los registros de los reportes.");
        }
    }
    
}
