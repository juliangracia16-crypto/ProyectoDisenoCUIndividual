
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import com.mycompany.fitlifegym_persistencia.entidades.Imagen;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteAtencion;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteIncidente;
import java.util.List;

/**
 *
 * @author Julian
 */
public class PersistenciaFachada implements IPersistenciaFachada{
    private IHistorialAtencionesDAO historialAtenciones;
    private IHistorialIncidentesDAO historialIncidentes;
    private IImagenesDAO imagenes;
    private ICatalogosDAO catalogos;
    
    //Imagenes
    @Override
    public Imagen consultarImagen(String idImagen) throws PersistenciaException {
        Imagen imagen = imagenes.consultarImagen(idImagen);
        return imagen;
    }

    @Override
    public Imagen guardarImagen(Imagen imagen) throws PersistenciaException {
        Imagen imagenGuardada = imagenes.guardarImagen(imagen);
        return imagenGuardada;
    }
    //Reportes de Incidentes
    @Override
    public List<ReporteIncidente> consultarReportesIncidentes() throws PersistenciaException {
        return null; //TODO
    }

    @Override
    public ReporteIncidente consultarReporteIncidentePorId(String id) throws PersistenciaException {
        ReporteIncidente reporteIncidente = historialIncidentes.consultarReporteIncidentePorId(id);
        return reporteIncidente;
    }

    @Override
    public ReporteIncidente generarReporteIncidente(ReporteIncidente reporteIncidente) throws PersistenciaException {
        ReporteIncidente reporteIncidenteGuardado = historialIncidentes.generarReporteIncidente(reporteIncidente);
        return reporteIncidenteGuardado;
    }

    @Override
    public ReporteIncidente eliminarReporteIncidente(String idReporte) throws PersistenciaException {
        ReporteIncidente reporteIncidenteEliminado = historialIncidentes.eliminarReporteIncidente(idReporte);
        return reporteIncidenteEliminado;
    }
    //Re[prtes de Atenciones
    @Override
    public List<ReporteAtencion> consultarReportesAtencion() throws PersistenciaException {
        return null; //TODO
    }

    @Override
    public ReporteAtencion consultarReporteAtencionPorId(String id) throws PersistenciaException {
        ReporteAtencion reporteAtencion = historialAtenciones.consultarReporteAtencionPorId(id);
        return reporteAtencion;
    }

    @Override
    public ReporteAtencion resolverReporte(ReporteAtencion reporte) throws PersistenciaException {
        ReporteAtencion reporteAtencion = historialAtenciones.resolverReporte(reporte);
        return reporteAtencion;
    }

    @Override
    public ReporteAtencion eliminarReporteAtencion(String idReporte) throws PersistenciaException {
        ReporteAtencion reporteAtencionEliminado = historialAtenciones.eliminarReporteAtencion(idReporte);
        return reporteAtencionEliminado;
    }
    //Catalogod de Estados y Categorias de los Reportes
    @Override
    public List<EstadoReporte> consultarCatalogoEstados() throws PersistenciaException {
        List<EstadoReporte> estadosReportes = catalogos.consultarCatalogoEstados();
        return estadosReportes;
    }

    @Override
    public List<Categoria> consultarCatalogoCategorias() throws PersistenciaException {
        List<Categoria> categorias = catalogos.consultarCatalogoCategorias();
        return categorias;
    }
    
}
