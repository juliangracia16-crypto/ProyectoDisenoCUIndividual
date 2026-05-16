
package com.mycompany.fitlifegym_presentacion;

import com.mycompany.cuquejassugerencias.ICUQuejasSugerencias;
import com.mycompany.cuquejassugerencias.fabricaSubsitema.FabricaSubsistema;
import com.mycompany.fitlifegym_dtos.AdministradorLogueadoDTO;
import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.CategoriaDTO;
import com.mycompany.fitlifegym_dtos.EstadoReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.LoginAdminDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.RegistroReporteAdminDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionGeneradoDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteGeneradoDTO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import java.util.List;

/**
 *
 * @author Julian
 */
public class ControlSubsistemaQuejasSugerencias implements ICUQuejasSugerencias{
    private ICUQuejasSugerencias quejasSugerenciasCU;
    
    public ControlSubsistemaQuejasSugerencias(){
        this.quejasSugerenciasCU = FabricaSubsistema.crearSubsistema();
    }
    
    @Override
    public List<CategoriaDTO> cargarCatalogoCategorias() throws NegocioException {
        return quejasSugerenciasCU.cargarCatalogoCategorias();
    }

    @Override
    public List<EstadoReporteDTO> cargarCatalogoEstados() throws NegocioException {
        return quejasSugerenciasCU.cargarCatalogoEstados();
    }

    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        return quejasSugerenciasCU.consultarReportesIncidentes(filtros);
    }

    @Override
    public List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        return quejasSugerenciasCU.consultarReportesAtenciones(filtros);
    }

    @Override
    public ReporteIncidenteGeneradoDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporteIncidente) throws NegocioException {
        return quejasSugerenciasCU.generarReporteIncidente(reporteIncidente);
    }

    @Override
    public ReporteAtencionGeneradoDTO atenderReporteIncidente(AtenderReporteDTO reporteAtencion) throws NegocioException {
        return quejasSugerenciasCU.atenderReporteIncidente(reporteAtencion);
    }

    @Override
    public List<ReporteAtencionDTO> consultarTodosLosReportesAtenciones() throws NegocioException {
        return quejasSugerenciasCU.consultarTodosLosReportesAtenciones();
    }

    @Override
    public List<ReporteIncidenteDTO> consultarTodosLosReportesIncidentes() throws NegocioException {
        return quejasSugerenciasCU.consultarTodosLosReportesIncidentes();
    }

    @Override
    public ReporteIncidenteDTO consultarReporteIncidentePorFolio(String folio) throws NegocioException {
        return quejasSugerenciasCU.consultarReporteIncidentePorFolio(folio);
    }

    @Override
    public List<RegistroReporteAdminDTO> consultarTodosLosReportes() throws NegocioException {
        return quejasSugerenciasCU.consultarTodosLosReportes();
    }

    @Override
    public ReporteAtencionDTO consultarReporteAtencionPorFolio(String folio) throws NegocioException {
        return quejasSugerenciasCU.consultarReporteAtencionPorFolio(folio);
    }

    @Override
    public byte[] generarReportePdf(List<RegistroReporteAdminDTO> generarReportePdf) throws NegocioException {
        return quejasSugerenciasCU.generarReportePdf(generarReportePdf);
    }

    @Override
    public AdministradorLogueadoDTO iniciarSesion(LoginAdminDTO login) throws NegocioException {
        return quejasSugerenciasCU.iniciarSesion(login);
    }

    @Override
    public List<RegistroReporteAdminDTO> consultarTodosLosReportesFiltrados(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        return quejasSugerenciasCU.consultarTodosLosReportesFiltrados(filtros);
    }

    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentesPorCliente(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        return quejasSugerenciasCU.consultarReportesIncidentesPorCliente(filtros);
    }
}
