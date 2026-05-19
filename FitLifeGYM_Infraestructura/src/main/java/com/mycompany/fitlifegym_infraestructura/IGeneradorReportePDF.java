
package com.mycompany.fitlifegym_infraestructura;

import com.mycompany.fitlifegym_infraestructura.dtos.ReportePdfDTOInfraestructura;


/**
 * Interfaz que establece el metodo para generar
 * un reporte pdf de los registros
 * @author Julian
 */
public interface IGeneradorReportePDF {
    
    /**
     * Metodo para generar un reporte pdf
     * @param generarReportePdf objeto con los datos necesarios
     * para poder generar el pdf
     * @return un arreglo de byte que es el pdf
     * generado correctamente
     * @throws InfraestructuraException si ocurre un error al generar
     * el reporte pdf
     */
    public abstract byte[] generarReportePDF(ReportePdfDTOInfraestructura generarReportePdf) throws InfraestructuraException;
}
