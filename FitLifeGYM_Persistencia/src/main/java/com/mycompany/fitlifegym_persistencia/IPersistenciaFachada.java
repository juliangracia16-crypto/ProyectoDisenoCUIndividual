
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
public interface IPersistenciaFachada {
    //Imagenes
    public abstract Imagen consultarImagen(String idImagen) throws PersistenciaException;
    public abstract Imagen guardarImagen(Imagen imagen) throws PersistenciaException;
    //Reportes de Incidentes
    public abstract List<ReporteIncidente> consultarReportesIncidentes() throws PersistenciaException; //Agregar como parametro DTO con los filtros
    public abstract ReporteIncidente consultarReporteIncidentePorId(String id) throws PersistenciaException;
    public abstract ReporteIncidente generarReporteIncidente(ReporteIncidente reporteIncidente) throws PersistenciaException;
    public abstract ReporteIncidente eliminarReporteIncidente(String idReporte) throws PersistenciaException;
    //Reportes de atenciones
    public abstract List<ReporteAtencion> consultarReportesAtencion() throws PersistenciaException; //agregar como parametro la dto de los filtros
    public abstract ReporteAtencion consultarReporteAtencionPorId(String id) throws PersistenciaException;
    public abstract ReporteAtencion resolverReporte(ReporteAtencion reporte) throws PersistenciaException;
    public abstract ReporteAtencion eliminarReporteAtencion(String idReporte) throws PersistenciaException;
    //Catalogos
    public abstract List<EstadoReporte> consultarCatalogoEstados() throws PersistenciaException;
    public abstract List<Categoria> consultarCatalogoCategorias() throws PersistenciaException;
    public abstract Categoria consultarCategoriaPorNombre(String nombre) throws PersistenciaException;
    public abstract EstadoReporte consultarEstadoPorNombre(String nombre) throws PersistenciaException;
}
