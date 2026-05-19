
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.ReportePdfDTO;

/**
 * Interfaz que establece el metodo para generar un reporte pdf
 * del historial de los registros 
 * @author Julian
 */
public interface IHistorialesReportesGeneralBO {
    /**
     * Metodo para generar reporte pdf del historial de los registros
     * @param reportePdfDTO datos necesarios para generar el reporte pdf
     * @return arreglo de byte que representan el pdf generado correctamente
     * @throws NegocioException si ocurre un error al generar el reporte pdf
     */
    public abstract byte[] generarReportePdf(ReportePdfDTO reportePdfDTO) throws NegocioException;
}
