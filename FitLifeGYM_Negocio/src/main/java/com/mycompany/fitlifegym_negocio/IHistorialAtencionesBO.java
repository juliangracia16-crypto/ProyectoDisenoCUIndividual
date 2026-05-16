
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.RegistroReporteAdminDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionGeneradoDTO;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface IHistorialAtencionesBO {
    public abstract List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException;
    public abstract List<ReporteAtencionDTO> consultarReportesAtenciones() throws NegocioException;
    public abstract List<RegistroReporteAdminDTO> consultarTodosLosRegistrosReportes() throws NegocioException;
    public abstract List<RegistroReporteAdminDTO> consultarTodosLosRegistrosReportesFiltrado(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException;
    public abstract ReporteAtencionDTO consultarReporteAtencionPorFolio(String folio) throws NegocioException;
    public abstract ReporteAtencionGeneradoDTO atenderReporteIncidente(AtenderReporteDTO reporte) throws NegocioException;
}
