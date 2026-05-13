
package com.mycompany.cuquejassugerencias;

import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.CategoriaDTO;
import com.mycompany.fitlifegym_dtos.EstadoReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteGeneradoDTO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface ICUQuejasSugerencias {
    //Catalogos
    public abstract List<CategoriaDTO> cargarCatalogoCategorias() throws NegocioException;
    public abstract List<EstadoReporteDTO> cargarCatalogoEstados()throws NegocioException;
    //Reportes Incidentes
    public abstract List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException;
    public abstract List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException;
    public abstract ReporteIncidenteDTO consultarReporteIncidentePorFolio(String folio) throws NegocioException;
    //Reportes Atenciones
    public abstract List<ReporteAtencionDTO> consultarTodosLosReportesAtenciones() throws NegocioException;
    public abstract List<ReporteIncidenteDTO> consultarTodosLosReportesIncidentes() throws NegocioException;
    public abstract ReporteIncidenteGeneradoDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporteIncidente) throws NegocioException;
    public abstract ReporteAtencionDTO atenderReporteIncidente(AtenderReporteDTO reporteAtencion) throws NegocioException;
    //TODO 
    //Lo necesario para generar los reportes a pdf 
}
