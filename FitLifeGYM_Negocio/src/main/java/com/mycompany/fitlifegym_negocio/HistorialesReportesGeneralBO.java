
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
import com.mycompany.fitlifegym_dtos.RegistroReporteAdminDTO;
import com.mycompany.fitlifegym_dtos.ReportePdfDTO;
import com.mycompany.fitlifegym_infraestructura.IGeneradorReportePDF;
import com.mycompany.fitlifegym_infraestructura.InfraestructuraException;
import com.mycompany.fitlifegym_infraestructura.dtos.RegistroReporteAdminDTOInfraestructura;
import com.mycompany.fitlifegym_infraestructura.dtos.ReportePdfDTOInfraestructura;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Julian
 */
public class HistorialesReportesGeneralBO implements IHistorialesReportesGeneralBO{
    private final IGeneradorReportePDF generadorPdf;

    public HistorialesReportesGeneralBO(IGeneradorReportePDF generadorPdf) {
        this.generadorPdf = generadorPdf;
    }
    
    @Override
    public byte[] generarReportePdf(ReportePdfDTO reportePdfDTO) throws NegocioException {
        try {
            ReportePdfDTOInfraestructura datosPdf = DtosAEntidadesAdapter.adaptarDatosReportePdfAInfraestructura(reportePdfDTO);
            
            List<RegistroReporteAdminDTO> registros = reportePdfDTO.registros();
            List<RegistroReporteAdminDTOInfraestructura> registrosInfraestructura = new LinkedList<>();
            
            for(RegistroReporteAdminDTO r: registros){
                RegistroReporteAdminDTOInfraestructura reporte = DtosAEntidadesAdapter.adaptarRegistroReportesAdminDTOAInfraestructura(r);
                registrosInfraestructura.add(reporte);
            }
            datosPdf.setRegistros(registrosInfraestructura);
            byte[] pdfGenerado = generadorPdf.generarReportePDF(datosPdf);
            return pdfGenerado;
        } catch (InfraestructuraException ex) {
            throw new NegocioException("Error al generar el PDF.",ex);
        }
    }
    
}
