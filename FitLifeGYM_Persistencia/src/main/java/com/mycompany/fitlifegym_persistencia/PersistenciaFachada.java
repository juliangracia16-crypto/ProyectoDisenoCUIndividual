
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteAtencionPersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteIncidentePersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import com.mycompany.fitlifegym_persistencia.entidades.Imagen;
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
public class PersistenciaFachada implements IPersistenciaFachada{
    private IHistorialAtencionesDAO historialAtenciones;
    private IHistorialIncidentesDAO historialIncidentes;
    private IImagenesDAO imagenes;
    private ICatalogosDAO catalogos;
    private IClientesDAO clientes;
    private IMembresiaDAO membresias;
    private IMembresiaCompradaDAO  membresiaComprada;

    public PersistenciaFachada(IHistorialAtencionesDAO historialAtenciones, IHistorialIncidentesDAO historialIncidentes, IImagenesDAO imagenes, ICatalogosDAO catalogos,
        IClientesDAO clientes, IMembresiaDAO membresias, IMembresiaCompradaDAO membresiaComprada
    ) {
        this.historialAtenciones = historialAtenciones;
        this.historialIncidentes = historialIncidentes;
        this.imagenes = imagenes;
        this.catalogos = catalogos;
        this.clientes = clientes;
        this.membresias = membresias;
        this.membresiaComprada = membresiaComprada;
    }
    
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
    public List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentes() throws PersistenciaException {
        List<ReporteIncidentePersistenciaDTO> reportesIncidentes = historialIncidentes.consultarReportesIncidentes();
        return reportesIncidentes;
    }
    
    @Override
    public List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentesFiltros(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException {
        List<ReporteIncidentePersistenciaDTO> reportesIncidentes = historialIncidentes.consultarReportesIncidentesFiltros(filtros);
        return reportesIncidentes;
    }
    
    @Override
    public ReporteIncidentePersistenciaDTO consultarReporteIncidentePorFolio(String folio) throws PersistenciaException {
        ReporteIncidentePersistenciaDTO reporteIncidente = historialIncidentes.consultarReporteIncidentePorFolio(folio);
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
    //Reportes de Atenciones
    @Override
    public List<ReporteAtencionPersistenciaDTO> consultarReportesAtencion() throws PersistenciaException {
        List<ReporteAtencionPersistenciaDTO> reportesAtenciones = historialAtenciones.consultarReportesAtencion();
        return reportesAtenciones;
    }
    
    @Override
    public List<ReporteAtencionPersistenciaDTO> consultarReportesAtencionFiltros(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException {
        List<ReporteAtencionPersistenciaDTO> reportesAtencion = historialAtenciones.consultarReportesAtencionesFiltros(filtros);
        return reportesAtencion;
    }
    
    @Override
    public ReporteAtencionPersistenciaDTO consultarReporteAtencionPorFolio(String folio) throws PersistenciaException {
        ReporteAtencionPersistenciaDTO reporteAtencion = historialAtenciones.consultarReporteAtencionPorFolio(folio);
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

    @Override
    public Categoria consultarCategoriaPorNombre(String nombre) throws PersistenciaException {
        Categoria categoria = catalogos.consultarCategoriaPorNombre(nombre);
        return categoria;
    }

    @Override
    public EstadoReporte consultarEstadoPorNombre(String nombre) throws PersistenciaException {
        EstadoReporte estado = catalogos.consultarEstadoPorNombre(nombre);
        return estado;
    }
    
    // METODOS DEL CU BASE
    @Override
    public Membresia guardar(Membresia membresia) throws PersistenciaException {
        Membresia membresiaGuardada = membresias.guardar(membresia);
        return membresiaGuardada;
    }

    @Override
    public List<Membresia> obtenerTodas() throws PersistenciaException {
        List<Membresia> membresiasObtenidas = membresias.obtenerTodas();
        return membresiasObtenidas;
    }

    @Override
    public Membresia obtenerPorId(Long id) throws PersistenciaException {
        Membresia membresia = membresias.obtenerPorId(id);
        return membresia;
    }

    @Override
    public MembresiaComprada guardar(MembresiaComprada compra) throws PersistenciaException {
        MembresiaComprada membresia = membresiaComprada.guardar(compra);
        return membresia;
    }

    @Override
    public List<MembresiaComprada> obtenerTodasMembresiasCompradas() throws PersistenciaException {
        List<MembresiaComprada> membresiasObtenidas = membresiaComprada.obtenerTodas();
        return membresiasObtenidas;
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) throws PersistenciaException {
        Cliente clienteObtenido = clientes.registrarCliente(cliente);
        return clienteObtenido;
    }

    @Override
    public Cliente consultarClientePorId(String id) throws PersistenciaException {
        Cliente cliente = clientes.consultarClientePorId(id);
        return cliente;
    }

    @Override
    public List<Cliente> consultarClientes() throws PersistenciaException {
        List<Cliente> clientesObtenidos = clientes.consultarClientes();
        return clientesObtenidos;
    }

    @Override
    public Cliente buscarPorPin(String pin) throws PersistenciaException {
        Cliente cliente = clientes.buscarPorPin(pin);
        return cliente;
    }

    @Override
    public void actualizarMembresia(String idCliente, TipoMembresia nuevaMembresia) throws PersistenciaException {
        
    }

    
    
}
