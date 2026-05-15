
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.ReportePdfDTO;

/**
 *
 * @author Julian
 */
public interface IGeneradorReportePDF {
    public abstract byte[] generarReportePDF(ReportePdfDTO generarReportePdf) throws InfraestructuraException;
}
