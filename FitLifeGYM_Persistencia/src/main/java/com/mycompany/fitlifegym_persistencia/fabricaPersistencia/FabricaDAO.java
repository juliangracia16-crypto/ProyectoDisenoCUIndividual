
package com.mycompany.fitlifegym_persistencia.fabricaPersistencia;

import com.mycompany.fitlifegym_persistencia.AdministradorDAO;
import com.mycompany.fitlifegym_persistencia.CatalogosDAO;
import com.mycompany.fitlifegym_persistencia.ClientesDAO;
import com.mycompany.fitlifegym_persistencia.HistorialAtencionesDAO;
import com.mycompany.fitlifegym_persistencia.HistorialIncidentesDAO;
import com.mycompany.fitlifegym_persistencia.IAdministradorDAO;
import com.mycompany.fitlifegym_persistencia.ICatalogosDAO;
import com.mycompany.fitlifegym_persistencia.IClientesDAO;
import com.mycompany.fitlifegym_persistencia.IHistorialAtencionesDAO;
import com.mycompany.fitlifegym_persistencia.IHistorialIncidentesDAO;
import com.mycompany.fitlifegym_persistencia.IMembresiaCompradaDAO;
import com.mycompany.fitlifegym_persistencia.IMembresiaDAO;
import com.mycompany.fitlifegym_persistencia.MembresiaCompradaListDAO;
import com.mycompany.fitlifegym_persistencia.MembresiaListDAO;

/**
 * Clase fabrica que crea las DAO
 * @author Julian
 */
public class FabricaDAO {
    
    /**
     * Metodo estatico que inicializa la implementacion de CatalogosDAO
     * @return la interfaz de CatalogosDAO
     */
    public static ICatalogosDAO crearCatalogoDAO(){
        ICatalogosDAO catalogosDAO = new CatalogosDAO();
        return catalogosDAO;
    }
    
    /**
     * Metodo estatico que inicializa la implementacion de HistorialIncidentesDAO
     * @return la interfaz de  HistorialIncidentesDAO
     */
    public static IHistorialIncidentesDAO crearHistorialIncidenteDAO(){
        IHistorialIncidentesDAO historialIncidentes = new HistorialIncidentesDAO();
        return historialIncidentes;
    }
    
    /**
     * Metodo estatico que inicializa la implementacion de HistorialAtencionesDAO
     * @return la interfaz de  HistorialAtencionesDAO
     */
    public static IHistorialAtencionesDAO crearHistorialAtencionDAO(){
        IHistorialAtencionesDAO historialAtenciones = new HistorialAtencionesDAO();
        return historialAtenciones;
    }
    
    /**
     * Metodo estatico que inicializa la implementacion de MembresiaDAO
     * @return la interfaz de  MembresiaDAO
     */
    public static IMembresiaDAO crearMembresiaDAO(){
        IMembresiaDAO membresiaDAO = new MembresiaListDAO();
        return membresiaDAO;
    }
    
    /**
     * Metodo estatico que inicializa la implementacion de MembresiaCompradaDAO
     * @return la interfaz de  MembresiaCompradaDAO
     */
    public static IMembresiaCompradaDAO crearMembresiaCompradaDAO(){
        IMembresiaCompradaDAO membresiaCompradaDAO = new MembresiaCompradaListDAO();
        return membresiaCompradaDAO;
    }
    
    /**
     * Metodo estatico que inicializa la implementacion de ClientesDAO
     * @return la interfaz de  ClientesDAO
     */
    public static IClientesDAO crearClientesDAO(){
        IClientesDAO clientesDAO = new ClientesDAO();
        return clientesDAO;
    }
    
    /**
     * Metodo estatico que inicializa la implementacion de AdministradorDAO
     * @return la interfaz de  AdministradorDAO
     */
    public static IAdministradorDAO crearAdministradoresDAO(){
        IAdministradorDAO administradoresDAO = new AdministradorDAO();
        return administradoresDAO;
    }
    
}
