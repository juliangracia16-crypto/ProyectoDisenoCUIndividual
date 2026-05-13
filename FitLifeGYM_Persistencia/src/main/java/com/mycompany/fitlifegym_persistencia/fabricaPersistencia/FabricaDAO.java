
package com.mycompany.fitlifegym_persistencia.fabricaPersistencia;

import com.mycompany.fitlifegym_persistencia.CatalogosDAO;
import com.mycompany.fitlifegym_persistencia.ClientesDAO;
import com.mycompany.fitlifegym_persistencia.HistorialAtencionesDAO;
import com.mycompany.fitlifegym_persistencia.HistorialIncidentesDAO;
import com.mycompany.fitlifegym_persistencia.ICatalogosDAO;
import com.mycompany.fitlifegym_persistencia.IClientesDAO;
import com.mycompany.fitlifegym_persistencia.IHistorialAtencionesDAO;
import com.mycompany.fitlifegym_persistencia.IHistorialIncidentesDAO;
import com.mycompany.fitlifegym_persistencia.IImagenesDAO;
import com.mycompany.fitlifegym_persistencia.IMembresiaCompradaDAO;
import com.mycompany.fitlifegym_persistencia.IMembresiaDAO;
import com.mycompany.fitlifegym_persistencia.ImagenesDAO;
import com.mycompany.fitlifegym_persistencia.MembresiaCompradaListDAO;
import com.mycompany.fitlifegym_persistencia.MembresiaListDAO;

/**
 *
 * @author Julian
 */
public class FabricaDAO {
    
    public static ICatalogosDAO crearCatalogoDAO(){
        ICatalogosDAO catalogosDAO = new CatalogosDAO();
        return catalogosDAO;
    }
    public static IHistorialIncidentesDAO crearHistorialIncidenteDAO(){
        IHistorialIncidentesDAO historialIncidentes = new HistorialIncidentesDAO();
        return historialIncidentes;
    }
    public static IHistorialAtencionesDAO crearHistorialAtencionDAO(){
        IHistorialAtencionesDAO historialAtenciones = new HistorialAtencionesDAO();
        return historialAtenciones;
    }
    public static IMembresiaDAO crearMembresiaDAO(){
        IMembresiaDAO membresiaDAO = new MembresiaListDAO();
        return membresiaDAO;
    }
    public static IMembresiaCompradaDAO crearMembresiaCompradaDAO(){
        IMembresiaCompradaDAO membresiaCompradaDAO = new MembresiaCompradaListDAO();
        return membresiaCompradaDAO;
    }
    public static IImagenesDAO crearImagenDAO(){
        IImagenesDAO imagenesDAO = new ImagenesDAO();
        return imagenesDAO;
    }
    public static IClientesDAO crearClientesDAO(){
        IClientesDAO clientesDAO = new ClientesDAO();
        return clientesDAO;
    }
    
}
