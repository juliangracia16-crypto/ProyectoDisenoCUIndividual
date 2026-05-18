
package com.mycompany.fitlifegym_infraestructura;

import com.mycompany.fitlifegym_infraestructura.dtos.ReportePdfDTOInfraestructura;


/**
 *
 * @author Julian
 */
public interface IGeneradorReportePDF {
    public abstract byte[] generarReportePDF(ReportePdfDTOInfraestructura generarReportePdf) throws InfraestructuraException;
}
