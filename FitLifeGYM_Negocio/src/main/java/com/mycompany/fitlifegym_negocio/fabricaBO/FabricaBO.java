
package com.mycompany.fitlifegym_negocio.fabricaBO;

import com.mycompany.fitlifegym_infraestructura.singletonGeneradorPdf.SingletonGenerarReportePdf;
import com.mycompany.fitlifegym_negocio.CatalogosBO;
import com.mycompany.fitlifegym_negocio.ClientesBO;
import com.mycompany.fitlifegym_negocio.HistorialAtencionesBO;
import com.mycompany.fitlifegym_negocio.HistorialIncidentesBO;
import com.mycompany.fitlifegym_negocio.HistorialesReportesGeneralBO;
import com.mycompany.fitlifegym_negocio.ICatalogosBO;
import com.mycompany.fitlifegym_negocio.IClientesBO;
import com.mycompany.fitlifegym_negocio.IHistorialAtencionesBO;
import com.mycompany.fitlifegym_negocio.IHistorialIncidentesBO;
import com.mycompany.fitlifegym_negocio.IHistorialesReportesGeneralBO;
import com.mycompany.fitlifegym_negocio.ILoginBO;
import com.mycompany.fitlifegym_negocio.IMembresiaBO;
import com.mycompany.fitlifegym_negocio.IMembresiaCompradaBO;
import com.mycompany.fitlifegym_negocio.IRenovarMembresiaBO;
import com.mycompany.fitlifegym_negocio.LoginBO;
import com.mycompany.fitlifegym_negocio.MembresiaBO;
import com.mycompany.fitlifegym_negocio.MembresiaCompradaBO;
import com.mycompany.fitlifegym_negocio.RenovarMembresiaBO;
import com.mycompany.fitlifegym_persistencia.fabricaPersistencia.FabricaFachada;

/**
 *
 * @author Julian
 */
public class FabricaBO {
    
    /**
     * Metodo para inicializar IHistorialIncidentesBO
     * @return IHistorialIncidentesBO inicializada
     */
    public static IHistorialIncidentesBO crearHistorialIncidenteBO(){
        return new HistorialIncidentesBO( FabricaFachada.crearPersistenciaFachada());
    }
    
    /**
     * Metodo para inicializar IHistorialAtencionesBO
     * @return IHistorialAtencionesBO inicializada
     */
    public static IHistorialAtencionesBO crearHistorialAtencionBO(){
        return new HistorialAtencionesBO( FabricaFachada.crearPersistenciaFachada());
    }
    
    /**
     * Metodo para inicializar ICatalogosBO
     * @return ICatalogosBO inicializada
     */
    public static ICatalogosBO crearCatalogosBO(){
        return new CatalogosBO( FabricaFachada.crearPersistenciaFachada());
    }
    
    /**
     * Metodo para inicializar IMembresiaBO
     * @return IMembresiaBO inicializada
     */
    public static IMembresiaBO crearMembresiaBO(){
        return new MembresiaBO( FabricaFachada.crearPersistenciaFachada());
    }
    
    /**
     * Metodo para inicializar IMembresiaCompradaBO
     * @return IMembresiaCompradaBO inicializada
     */
    public static IMembresiaCompradaBO crearMembresiaCompradaBO(){
        return new MembresiaCompradaBO( FabricaFachada.crearPersistenciaFachada());
    }
    
    /**
     * Metodo para inicializar IClientesBO
     * @return IClientesBO inicializada
     */
    public static IClientesBO crearClientesBO(){
        return new ClientesBO( FabricaFachada.crearPersistenciaFachada());
    }
    
    /**
     * Metodo para inicializar ILoginBO
     * @return ILoginBO inicializada
     */
    public static ILoginBO crearLoginBO(){
        return new LoginBO( FabricaFachada.crearPersistenciaFachada());
    }
    
    /**
     * Metodo para inicializar IRenovarMembresiaBO
     * @return IRenovarMembresiaBO inicializada
     */
    public static IRenovarMembresiaBO crearRenovarMembresiaBO(){
        return new RenovarMembresiaBO( FabricaFachada.crearPersistenciaFachada());
    }
    
    /**
     * Metodo para inicializar IHistorialesReportesGeneralBO
     * @return IHistorialesReportesGeneralBO inicializada
     */
    public static IHistorialesReportesGeneralBO crearReportesGeneralesBO(){
        return new HistorialesReportesGeneralBO(SingletonGenerarReportePdf.getInstancia());
    }
}
