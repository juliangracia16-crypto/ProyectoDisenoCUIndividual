
package com.mycompany.cuquejassugerencias;

import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.CategoriaDTO;
import com.mycompany.fitlifegym_dtos.EstadoReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaIncidenteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultasAtencionesDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface ICUQuejasSugerencias {
    public abstract List<CategoriaDTO> cargarCatalogoCategorias() throws NegocioException;
    public abstract List<EstadoReporteDTO> cargarCatalogoEstados()throws NegocioException;
    public abstract List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaIncidenteDTO filtros) throws NegocioException;
    public abstract List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultasAtencionesDTO filtros) throws NegocioException;
    public abstract List<ReporteIncidenteDTO> consultarReportesIncidentesCliente(String id) throws NegocioException;
    public abstract List<ReporteAtencionDTO> consultarTodosLosReportesAtenciones() throws NegocioException;
    public abstract List<ReporteIncidenteDTO> consultarTodosLosReportesIncidentes() throws NegocioException;
    public abstract ReporteIncidenteDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporteIncidente) throws NegocioException;
    public abstract ReporteAtencionDTO atenderReporteIncidente(AtenderReporteDTO reporteAtencion) throws NegocioException;
    //TODO 
    //Lo necesario para generar los reportes a pdf 
}
