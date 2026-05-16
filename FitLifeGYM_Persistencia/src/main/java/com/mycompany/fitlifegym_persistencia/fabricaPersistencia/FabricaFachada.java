
package com.mycompany.fitlifegym_persistencia.fabricaPersistencia;

import com.mycompany.fitlifegym_persistencia.IAdministradorDAO;
import com.mycompany.fitlifegym_persistencia.ICatalogosDAO;
import com.mycompany.fitlifegym_persistencia.IClientesDAO;
import com.mycompany.fitlifegym_persistencia.IHistorialAtencionesDAO;
import com.mycompany.fitlifegym_persistencia.IHistorialIncidentesDAO;
import com.mycompany.fitlifegym_persistencia.IMembresiaCompradaDAO;
import com.mycompany.fitlifegym_persistencia.IMembresiaDAO;
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.PersistenciaFachada;

/**
 * Clase fabrica para crear una IPersistenciaFachada
 * @author Julian
 */
public class FabricaFachada {
    /**
     * Metodo estatico que inicializa la fachada
     * @return La interfaz IPersistenciaFachada
     */
    public static IPersistenciaFachada crearPersistenciaFachada(){
        
        IHistorialIncidentesDAO historialIncidentes = FabricaDAO.crearHistorialIncidenteDAO();
        IHistorialAtencionesDAO historialAtenciones = FabricaDAO.crearHistorialAtencionDAO();
        ICatalogosDAO catalogosDAO = FabricaDAO.crearCatalogoDAO();
        IMembresiaDAO membresiaDAO = FabricaDAO.crearMembresiaDAO();
        IMembresiaCompradaDAO membresiaCompradaDAO = FabricaDAO.crearMembresiaCompradaDAO();
        IClientesDAO clientesDAO = FabricaDAO.crearClientesDAO();
        IAdministradorDAO administradoresDAO = FabricaDAO.crearAdministradoresDAO();
        
        return  new PersistenciaFachada(historialAtenciones, historialIncidentes, catalogosDAO, clientesDAO, membresiaDAO, membresiaCompradaDAO, administradoresDAO);
    }
}
