
package com.mycompany.fitlifegym_infraestructura.singletonGeneradorPdf;

import com.mycompany.fitlifegym_infraestructura.GeneradorReportePDF;
import com.mycompany.fitlifegym_infraestructura.IGeneradorReportePDF;

/**
 * Clase singleton para obtener la instancia 
 * de la interfaz IGeneradorReportePDF
 * @author Julian
 */
public class SingletonGenerarReportePdf {
    /**
     * Instancia unica del singleton
     */
    private static IGeneradorReportePDF instancia;

    /**
     * Constructor privado
     */
    private SingletonGenerarReportePdf() {
    }
    
    /**
     * Obtiene la instancia unica 
     * @return instancia de IGeneradorReportePDF 
     */
    public static IGeneradorReportePDF getInstancia() {

        if (instancia == null) {
            instancia = new GeneradorReportePDF();
        }

        return instancia;
    }
}
