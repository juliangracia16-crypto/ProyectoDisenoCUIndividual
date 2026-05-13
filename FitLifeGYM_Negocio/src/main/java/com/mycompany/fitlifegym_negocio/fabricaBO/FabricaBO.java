
package com.mycompany.fitlifegym_negocio.fabricaBO;

import com.mycompany.fitlifegym_negocio.CatalogosBO;
import com.mycompany.fitlifegym_negocio.ClientesBO;
import com.mycompany.fitlifegym_negocio.HistorialAtencionesBO;
import com.mycompany.fitlifegym_negocio.HistorialIncidentesBO;
import com.mycompany.fitlifegym_negocio.ICatalogosBO;
import com.mycompany.fitlifegym_negocio.IClientesBO;
import com.mycompany.fitlifegym_negocio.IHistorialAtencionesBO;
import com.mycompany.fitlifegym_negocio.IHistorialIncidentesBO;
import com.mycompany.fitlifegym_negocio.ILoginBO;
import com.mycompany.fitlifegym_negocio.IMembresiaBO;
import com.mycompany.fitlifegym_negocio.IMembresiaCompradaBO;
import com.mycompany.fitlifegym_negocio.IRenovarMembresiaBO;
import com.mycompany.fitlifegym_negocio.LoginBO;
import com.mycompany.fitlifegym_negocio.MembresiaBO;
import com.mycompany.fitlifegym_negocio.MembresiaCompradaBO;
import com.mycompany.fitlifegym_negocio.RenovarMembresiaBO;
import com.mycompany.fitlifegym_persistencia.fabricaDAO.FabricaDAO;

/**
 *
 * @author Julian
 */
public class FabricaBO {
    
    public static IHistorialIncidentesBO crearHistorialIncidenteBO(){
        return new HistorialIncidentesBO( FabricaDAO.crearPersistenciaFachada());
    }
    public static IHistorialAtencionesBO crearHistorialAtencionBO(){
        return new HistorialAtencionesBO( FabricaDAO.crearPersistenciaFachada());
    }
    public static ICatalogosBO crearCatalogosBO(){
        return new CatalogosBO( FabricaDAO.crearPersistenciaFachada());
    }
    public static IMembresiaBO crearMembresiaBO(){
        return new MembresiaBO( FabricaDAO.crearPersistenciaFachada());
    }
    public static IMembresiaCompradaBO crearMembresiaCompradaBO(){
        return new MembresiaCompradaBO( FabricaDAO.crearPersistenciaFachada());
    }
    public static IClientesBO crearClientesBO(){
        return new ClientesBO( FabricaDAO.crearPersistenciaFachada());
    }
    public static ILoginBO crearLoginBO(){
        return new LoginBO( FabricaDAO.crearPersistenciaFachada());
    }
    public static IRenovarMembresiaBO crearRenovarMembresiaBO(){
        return new RenovarMembresiaBO( FabricaDAO.crearPersistenciaFachada());
    }
}
