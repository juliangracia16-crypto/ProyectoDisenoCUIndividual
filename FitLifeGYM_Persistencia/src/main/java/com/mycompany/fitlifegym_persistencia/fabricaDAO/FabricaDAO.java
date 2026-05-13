
package com.mycompany.fitlifegym_persistencia.fabricaDAO;

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
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.ImagenesDAO;
import com.mycompany.fitlifegym_persistencia.MembresiaCompradaListDAO;
import com.mycompany.fitlifegym_persistencia.MembresiaListDAO;
import com.mycompany.fitlifegym_persistencia.PersistenciaFachada;

/**
 *
 * @author Julian
 */
public class FabricaDAO {
    public static IPersistenciaFachada crearPersistenciaFachada(){
        
        IHistorialIncidentesDAO historialIncidentes = new HistorialIncidentesDAO();
        IHistorialAtencionesDAO historialAtenciones = new HistorialAtencionesDAO();
        ICatalogosDAO catalogosDAO = new CatalogosDAO();
        IImagenesDAO imagenesDAO = new ImagenesDAO();
        IMembresiaDAO membresiaDAO = new MembresiaListDAO();
        IMembresiaCompradaDAO membresiaCompradaDAO = new MembresiaCompradaListDAO();
        IClientesDAO clientesDAO = new ClientesDAO();
        
        return  new PersistenciaFachada(historialAtenciones, historialIncidentes, imagenesDAO, catalogosDAO, clientesDAO, membresiaDAO, membresiaCompradaDAO);
    }
}
