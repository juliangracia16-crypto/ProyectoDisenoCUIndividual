
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.ReportePdfDTO;

/**
 *
 * @author Julian
 */
public interface IHistorialesReportesGeneralBO {
    public abstract byte[] generarReportePdf(ReportePdfDTO reportePdfDTO) throws NegocioException;
}
