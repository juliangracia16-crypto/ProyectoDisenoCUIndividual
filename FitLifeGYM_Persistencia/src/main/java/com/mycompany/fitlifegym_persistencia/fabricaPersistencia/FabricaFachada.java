
package com.mycompany.fitlifegym_persistencia.fabricaPersistencia;

import com.mycompany.fitlifegym_persistencia.ICatalogosDAO;
import com.mycompany.fitlifegym_persistencia.IClientesDAO;
import com.mycompany.fitlifegym_persistencia.IHistorialAtencionesDAO;
import com.mycompany.fitlifegym_persistencia.IHistorialIncidentesDAO;
import com.mycompany.fitlifegym_persistencia.IImagenesDAO;
import com.mycompany.fitlifegym_persistencia.IMembresiaCompradaDAO;
import com.mycompany.fitlifegym_persistencia.IMembresiaDAO;
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.PersistenciaFachada;

/**
 *
 * @author Julian
 */
public class FabricaFachada {
    public static IPersistenciaFachada crearPersistenciaFachada(){
        
        IHistorialIncidentesDAO historialIncidentes = FabricaDAO.crearHistorialIncidenteDAO();
        IHistorialAtencionesDAO historialAtenciones = FabricaDAO.crearHistorialAtencionDAO();
        ICatalogosDAO catalogosDAO = FabricaDAO.crearCatalogoDAO();
        IImagenesDAO imagenesDAO = FabricaDAO.crearImagenDAO();
        IMembresiaDAO membresiaDAO = FabricaDAO.crearMembresiaDAO();
        IMembresiaCompradaDAO membresiaCompradaDAO = FabricaDAO.crearMembresiaCompradaDAO();
        IClientesDAO clientesDAO = FabricaDAO.crearClientesDAO();
        
        return  new PersistenciaFachada(historialAtenciones, historialIncidentes, imagenesDAO, catalogosDAO, clientesDAO, membresiaDAO, membresiaCompradaDAO);
    }
}
