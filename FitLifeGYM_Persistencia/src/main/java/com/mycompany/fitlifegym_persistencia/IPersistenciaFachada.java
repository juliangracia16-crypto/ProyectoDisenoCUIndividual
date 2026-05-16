
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteAtencionPersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteIncidentePersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.Administrador;
import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import com.mycompany.fitlifegym_persistencia.entidades.Membresia;
import com.mycompany.fitlifegym_persistencia.entidades.MembresiaComprada;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteAtencion;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteIncidente;
import com.mycompany.fitlifegym_persistencia.entidades.TipoMembresia;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface IPersistenciaFachada {
    //Reportes de Incidentes
    public abstract List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentes() throws PersistenciaException; 
    public abstract List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentesFiltros(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException;
    public abstract ReporteIncidentePersistenciaDTO consultarReporteIncidentePorFolio(String folio) throws PersistenciaException;
    public abstract ReporteIncidente generarReporteIncidente(ReporteIncidente reporteIncidente) throws PersistenciaException;
    public abstract ReporteIncidente actualizarEstadoReporteIncidente(ReporteIncidente reporteIncidente) throws PersistenciaException;
    public abstract List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentesPorCliente(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException;

    //Reportes de atenciones
    public abstract List<ReporteAtencionPersistenciaDTO> consultarReportesAtencion() throws PersistenciaException; 
    public abstract List<ReporteAtencionPersistenciaDTO> consultarReportesAtencionFiltros(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException;
    public abstract ReporteAtencionPersistenciaDTO consultarReporteAtencionPorFolio(String folio) throws PersistenciaException;
    public abstract ReporteAtencion resolverReporte(ReporteAtencion reporte) throws PersistenciaException;
    //Catalogos
    public abstract List<EstadoReporte> consultarCatalogoEstados() throws PersistenciaException;
    public abstract List<Categoria> consultarCatalogoCategorias() throws PersistenciaException;
    public abstract Categoria consultarCategoriaPorNombre(String nombre) throws PersistenciaException;
    public abstract EstadoReporte consultarEstadoPorNombre(String nombre) throws PersistenciaException;
    //Admin
    public abstract Administrador consultarAdministradorPorUsuario(String usuario) throws PersistenciaException;
    public abstract Administrador consultarAdministradorPorId(String id) throws PersistenciaException;
    //CU BASE
    public abstract Membresia guardar(Membresia membresia) throws PersistenciaException;
    public abstract List<Membresia> obtenerTodas() throws PersistenciaException;
    public abstract Membresia obtenerPorId(Long id) throws PersistenciaException;
    public abstract MembresiaComprada guardar(MembresiaComprada compra) throws PersistenciaException;
    public abstract List<MembresiaComprada> obtenerTodasMembresiasCompradas()throws PersistenciaException;
    public abstract Cliente registrarCliente(Cliente cliente) throws PersistenciaException;
    public abstract Cliente consultarClientePorId(String id) throws PersistenciaException;
    public abstract List<Cliente> consultarClientes() throws PersistenciaException;
    public abstract Cliente buscarPorPin(String pin) throws PersistenciaException;
    public abstract void actualizarMembresia(String idCliente, TipoMembresia nuevaMembresia) throws PersistenciaException;
}
