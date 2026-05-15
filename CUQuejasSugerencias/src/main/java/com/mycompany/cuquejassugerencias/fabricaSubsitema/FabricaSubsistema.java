
package com.mycompany.cuquejassugerencias.fabricaSubsitema;

import com.mycompany.cuquejassugerencias.CUQuejasSugerencias;
import com.mycompany.cuquejassugerencias.ICUQuejasSugerencias;
import com.mycompany.fitlifegym_negocio.ICatalogosBO;
import com.mycompany.fitlifegym_negocio.IGeneradorReportePDF;
import com.mycompany.fitlifegym_negocio.IHistorialAtencionesBO;
import com.mycompany.fitlifegym_negocio.IHistorialIncidentesBO;
import com.mycompany.fitlifegym_negocio.fabricaBO.FabricaBO;

/**
 *
 * @author Julian
 */
public class FabricaSubsistema {
    public static ICUQuejasSugerencias crearSubsistema(){
        
        IHistorialAtencionesBO historialAtencionesBO = FabricaBO.crearHistorialAtencionBO();
        IHistorialIncidentesBO historialIncidentesBO = FabricaBO.crearHistorialIncidenteBO();
        ICatalogosBO catalogosBO = FabricaBO.crearCatalogosBO();
        IGeneradorReportePDF generadorPDF = null;
        
        return new CUQuejasSugerencias(historialIncidentesBO, historialAtencionesBO, catalogosBO, generadorPDF);
    }
}
