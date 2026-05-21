
package Adapter;

import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.CategoriaDTO;
import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.EstadoDTO;
import com.mycompany.fitlifegym_dtos.EstadoReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_dtos.ImagenDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaCompradaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.RegistroReporteAdminDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionGeneradoDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteGeneradoDTO;
import com.mycompany.fitlifegym_dtos.ReportePdfDTO;
import com.mycompany.fitlifegym_dtos.TipoMembresiaDTO;
import com.mycompany.fitlifegym_dtos.TipoReporteDTO;
import com.mycompany.fitlifegym_persistencia.dtos.CategoriaPersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ClientePersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.dtos.EstadoReportePersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ImagenPersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteAtencionPersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteIncidentePersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import com.mycompany.fitlifegym_persistencia.entidades.Estado;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import com.mycompany.fitlifegym_persistencia.entidades.Imagen;
import com.mycompany.fitlifegym_persistencia.entidades.Membresia;
import com.mycompany.fitlifegym_persistencia.entidades.MembresiaComprada;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteAtencion;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteIncidente;
import com.mycompany.fitlifegym_persistencia.entidades.TipoMembresia;
import com.mycompany.fitlifegym_infraestructura.dtos.RegistroReporteAdminDTOInfraestructura;
import com.mycompany.fitlifegym_infraestructura.dtos.ReportePdfDTOInfraestructura;
import java.time.LocalDate;

/**
 *
 * @author Diego
 */
public class DtosAEntidadesYViceversaAdapter {

    public static Membresia adaptarMembresia(NuevaMembresiaDTO nuevaMembresiaDTO) {
        TipoMembresia tipoMembresiaDominio = TipoMembresia.BRONCE;

        if (nuevaMembresiaDTO.getTipoMembresia() == TipoMembresiaDTO.PLATA) {
            tipoMembresiaDominio = TipoMembresia.PLATA;
        } else if (nuevaMembresiaDTO.getTipoMembresia() == TipoMembresiaDTO.ORO) {
            tipoMembresiaDominio = TipoMembresia.ORO;
        }

        Membresia membresia = new Membresia(tipoMembresiaDominio,
                nuevaMembresiaDTO.getPrecio(),
                nuevaMembresiaDTO.getVigencia());

        return membresia;
    }

    public static MembresiaComprada adaptarMembresiaCompradaDTO(NuevaMembresiaCompradaDTO membresiaCompradaDTO) {
        Membresia membresia = adaptarMembresia(membresiaCompradaDTO.getMembresia());
        Estado estadoDominio = Estado.ACTIVO;

        if (membresiaCompradaDTO.getEstado() == EstadoDTO.INACTIVO) {
            estadoDominio = Estado.INACTIVO;
        }

        MembresiaComprada membresiaComprada = new MembresiaComprada(membresia,
                membresiaCompradaDTO.getFechaInicio(),
                membresiaCompradaDTO.getFechaFin(),
                membresiaCompradaDTO.getPrecioPagado(),
                estadoDominio);

        return membresiaComprada;
    }

    public static Cliente adaptarClienteDTO(NuevoClienteDTO clienteDTO) {
        MembresiaComprada membresiaComprada = null;

        if (clienteDTO.getMembresíaComprada() != null) {
            membresiaComprada = adaptarMembresiaCompradaDTO(clienteDTO.getMembresíaComprada());
        }
        Cliente clienteNuevo = new Cliente(clienteDTO.getNombre(), clienteDTO.getApellidos(),
                clienteDTO.getCorreo(), clienteDTO.getTelefono(),
                clienteDTO.getContrasenia(),
                clienteDTO.getFechaNacimiento(),
                clienteDTO.getPin(),
                membresiaComprada);

        return clienteNuevo;
    }

    public static TipoMembresia adaptarTipoMembresia(TipoMembresiaDTO tipoDTO) {
        if (tipoDTO == TipoMembresiaDTO.PLATA) {
            return TipoMembresia.PLATA;
        } else if (tipoDTO == TipoMembresiaDTO.ORO) {
            return TipoMembresia.ORO;
        }
        return TipoMembresia.BRONCE;
    }

    public static TipoMembresiaDTO adaptarTipoMembresiaDTO(TipoMembresia tipo) {
        if (tipo == TipoMembresia.PLATA) {
            return TipoMembresiaDTO.PLATA;
        } else if (tipo == TipoMembresia.ORO) {
            return TipoMembresiaDTO.ORO;
        }
        return TipoMembresiaDTO.BRONCE;
    }
    
    public static TipoMembresiaDTO adaptarTipoMembresiaEntidad(TipoMembresia tipo){
        return TipoMembresiaDTO.valueOf(tipo.name());
    }
    
    public static NuevaMembresiaDTO adaptarMembresiaEntidad(Membresia membresia){
        TipoMembresiaDTO tipo = adaptarTipoMembresiaEntidad(membresia.getTipoMembresia());
        NuevaMembresiaDTO membresiaDTO = new NuevaMembresiaDTO(
                tipo,
                membresia.getPrecio(),
                membresia.getVigencia()
        );
        return membresiaDTO;
    }

    public static EstadoDTO adaptarEstadoDTO(Estado estado) {
        if (estado == Estado.ACTIVO) {
            return EstadoDTO.ACTIVO;
        }
        return EstadoDTO.INACTIVO;
    }
    
    //==============================================
    //Adapters de reportes de atencion
    
    /**
     * Metodo que adapta de Reporte Atencion DTO a Reporte Atencion entidad.
     * Se genera solo con el folio y la solucion. Los demas datos se setean
     * en el metod de la BO
     * @param reporteAtencionDTO objeto con los datos para resolver un reporte de incidente
     * @return  reporte de atencion entidad
     */
    public static ReporteAtencion adaptarReporteAtencionDTO(AtenderReporteDTO reporteAtencionDTO){
        ReporteAtencion reporteAtencion = new ReporteAtencion(
                reporteAtencionDTO.folio(),
                reporteAtencionDTO.solucion(),
                null,
                null,
                null,
                null,
                null
        );
        return reporteAtencion;
    }
    
    /**
     * Metodo que adapta un reporte de atencion entidad a 
     * ReporteAtencionGeneradoDTO que representa al reporte
     * de atencion entidad generado correcta mente
     * @param reporteAtencion entidad que se genero correctamente
     * @return la dto del reporte atencion generado
     */
    public static ReporteAtencionGeneradoDTO adaptarReporteAtencionEntidad(ReporteAtencion reporteAtencion){
        EstadoReporteDTO estadoDTO = adaptarEstadoReporteEntidad(reporteAtencion.getEstadoReporte());
        ImagenDTO imagenDTO = adaptarImagenEntidad(reporteAtencion.getImagen());
        ReporteAtencionGeneradoDTO reporteAtencionDTO = new ReporteAtencionGeneradoDTO(
                reporteAtencion.getFolio(),
                reporteAtencion.getIdCategoria(),
                estadoDTO,
                reporteAtencion.getFecha(),
                reporteAtencion.getSolucion(),
                imagenDTO,
                reporteAtencion.getIdCliente()
        );
        return reporteAtencionDTO;
    }
    
    /**
     * Metodo que adapta de ReporteAtencionPersistenciaDTO (representa un reporte de atencion solo para lectura)
     * a ReporteAtencionDTO. 
     * @param reporteAtencion con los datos de lectura de un reporte de atencion
     * @return dto del reporte de atencion solo para lectura
     */
    public static ReporteAtencionDTO adaptarReporteAtencionEntidad(ReporteAtencionPersistenciaDTO reporteAtencion){
        CategoriaDTO categoriaDTO = adaptarCategoriaEntidad(reporteAtencion.getCategoria());
        EstadoReporteDTO estadoDTO = adaptarEstadoReporteEntidad(reporteAtencion.getEstadoReporte());
        ClienteLogueadoDTO clienteDTO = adaptarClienteEntidad(reporteAtencion.getCliente());
        ImagenDTO imagenDTO = adaptarImagenEntidad(reporteAtencion.getImagen());
        ReporteAtencionDTO reporteAtencionDTO = new ReporteAtencionDTO(
                reporteAtencion.getFolio(),
                reporteAtencion.getAsunto(),
                reporteAtencion.getSolucion(),
                categoriaDTO,
                reporteAtencion.getFecha(),
                estadoDTO,
                imagenDTO,
                clienteDTO
        );
        return reporteAtencionDTO;
    }
    
    //Adapters de reportes de incidentes
    
    /**
     * Metodo que adapta de NuevoReporteIncidenteDTO a Reporte Incidente entidad. Se
     * genera solo con el folio, la descripcion y la fecha. Los demas datos se setean en el
     * metodo de la BO.
     * @param reporteIncidenteDTO objeto con los datos para generar un reporte
     * de incidente
     * @return reporte de incidente entidad 
     */
    public static ReporteIncidente adaptarReporteIncidenteDTO(NuevoReporteIncidenteDTO reporteIncidenteDTO){
        ReporteIncidente reporteIncidente = new ReporteIncidente(
                reporteIncidenteDTO.asunto(),
                reporteIncidenteDTO.descripcion(),
                reporteIncidenteDTO.fecha(),
                null,
                null,
                null,
                null
        );
        return reporteIncidente;
    }
    
    /**
     * Metodo de adapta de ReporteIncidentePersistenciaDTO (dto que representa un reporte de incidente para solo lectura)
     * a Reporte Incidente entidad.
     * El estado va nulo, ya que se setea en el metodo de la BO.
     * @param reporteIncidente que adaptaremos a entidad
     * @return el reporte incidente entidad
     */
    public static ReporteIncidente adaptarReporteIncidenteDTO(ReporteIncidentePersistenciaDTO reporteIncidente){
        ImagenDTO imagenDTO = adaptarImagenEntidad(reporteIncidente.getImagen());
        Imagen imagen = adaptarImagenDTO(imagenDTO);
        ReporteIncidente reporteIncidenteEntidad = new ReporteIncidente(
                reporteIncidente.getId(),
                reporteIncidente.getFolio(),
                reporteIncidente.getAsunto(),
                reporteIncidente.getDescripcion(),
                reporteIncidente.getFecha(),
                null,
                reporteIncidente.getCategoria().getId(),
                imagen,
                reporteIncidente.getCliente().getId()
        );
        return reporteIncidenteEntidad;
    }
    
    /**
     * Metodo que adapta de Reporte Incidente entidad a 
     * ReporteIncidenteGeneradoDTO (representa el reporte de incidente generado). 
     * @param reporteIncidente generado
     * @return dto del reporte de incidente generado
     */
    public static ReporteIncidenteGeneradoDTO adaptarReporteIncidenteEntidad(ReporteIncidente reporteIncidente){
        EstadoReporteDTO estadoDTO = adaptarEstadoReporteEntidad(reporteIncidente.getEstadoReporte());
        ImagenDTO imagenDTO = adaptarImagenEntidad(reporteIncidente.getImagen());
        ReporteIncidenteGeneradoDTO reporteIncidenteDTO = new ReporteIncidenteGeneradoDTO(
                reporteIncidente.getFolio(),
                reporteIncidente.getIdCategoria(),
                estadoDTO,
                reporteIncidente.getAsunto(),
                reporteIncidente.getFecha(),
                reporteIncidente.getDescripcion(),
                imagenDTO,
                reporteIncidente.getIdCliente()
        );
        return reporteIncidenteDTO;
    }
    
    /**
     * Metodo que adapta de ReporteIncidentePersistenciaDTO (representa un reporte de incidente solo para lectura)
     * a ReporteIncidenteDTO. 
     * @param reporteIncidente con los datos de lectura de un reporte de incidente
     * @return dto del reporte de incidente solo para lectura 
     */
    public static ReporteIncidenteDTO adaptarReporteIncidenteEntidad(ReporteIncidentePersistenciaDTO reporteIncidente){
        CategoriaDTO categoriaDTO = adaptarCategoriaEntidad(reporteIncidente.getCategoria());
        EstadoReporteDTO estadoDTO = adaptarEstadoReporteEntidad(reporteIncidente.getEstadoReporte());
        ClienteLogueadoDTO clienteDTO = adaptarClienteEntidad(reporteIncidente.getCliente());
        ImagenDTO imagenDTO = adaptarImagenEntidad(reporteIncidente.getImagen());
        ReporteIncidenteDTO reporteIncidenteDTO = new ReporteIncidenteDTO(
                reporteIncidente.getFolio(),
                categoriaDTO,
                estadoDTO,
                reporteIncidente.getAsunto(),
                reporteIncidente.getFecha(),
                reporteIncidente.getDescripcion(),
                imagenDTO,
                clienteDTO
        );
        return reporteIncidenteDTO;
    }
    
    //Adapter registros para mostrar en vista de admin
    
    /**
     * Metodo que adapta un ReporteIncidentePersistenciaDTO (reporte de incidente solo para lectura)
     * a un registro donde se agruparan los dos tipos de reportes para mostrarlos en la tabla 
     * de vista de administrador.
     * Aqui se le asigna el tipo de incidente para en la tabla poder distinguirlo.
     * @param reporte que adaptaremos
     * @return RegistroReporteAdminDTO que representa ya un registro mas global de los 
     * dos tipos de reportes agrupados.
     */
    public static RegistroReporteAdminDTO adaptarRegistrosReportesAdminDTO(ReporteIncidentePersistenciaDTO reporte){
        CategoriaDTO categoriaDTO = adaptarCategoriaEntidad(reporte.getCategoria());
        EstadoReporteDTO estadoDTO = adaptarEstadoReporteEntidad(reporte.getEstadoReporte());
        ClienteLogueadoDTO clienteDTO = adaptarClienteEntidad(reporte.getCliente());
        TipoReporteDTO tipo = TipoReporteDTO.INCIDENTE;
        RegistroReporteAdminDTO registroAdmin = new RegistroReporteAdminDTO(
                reporte.getFolio(),
                tipo,
                reporte.getAsunto(),
                categoriaDTO,
                estadoDTO,
                reporte.getFecha(),
                clienteDTO
        );
        return registroAdmin;
    }
    
    /**
     * Metodo que adapta un ReporteAtencionPersistenciaDTO (reporte de atencion solo para lectura)
     * a un registro donde se agruparan los dos tipos de reportes para mostrarlos en la tabla 
     * de vista de administrador.
     * Aqui se le asigna el tipo de atencion para en la tabla poder distinguirlo.
     * @param reporte que adaptaremos
     * @return RegistroReporteAdminDTO que representa ya un registro mas global de los 
     * dos tipos de reportes agrupados.
     */
    public static RegistroReporteAdminDTO adaptarRegistrosReportesAdminDTO(ReporteAtencionPersistenciaDTO reporte){
        CategoriaDTO categoriaDTO = adaptarCategoriaEntidad(reporte.getCategoria());
        EstadoReporteDTO estadoDTO = adaptarEstadoReporteEntidad(reporte.getEstadoReporte());
        ClienteLogueadoDTO clienteDTO = adaptarClienteEntidad(reporte.getCliente());
        TipoReporteDTO tipo = TipoReporteDTO.ATENCION;
        RegistroReporteAdminDTO registroAdmin = new RegistroReporteAdminDTO(
                reporte.getFolio(),
                tipo,
                reporte.getAsunto(),
                categoriaDTO,
                estadoDTO,
                reporte.getFecha(),
                clienteDTO
        );
        return registroAdmin;
    }
    
    //Adapters de DTOS para pdf (Convertir dto a dtoInfraestructura)
    
    /**
     * Metodo para convertir el registro del reporte para la vista de administrador
     * a registro de reporte para poder utilizarlo en infraestructura.
     * Esta conversion es para que infraestructura no conozca
     * negocio.
     * @param dto el registro que adaptaremos
     * @return registro adaptado para utilizar en infraestructura
     */
    public static RegistroReporteAdminDTOInfraestructura adaptarRegistroReportesAdminDTOAInfraestructura(RegistroReporteAdminDTO dto){
        RegistroReporteAdminDTOInfraestructura registroInfraestructura = new RegistroReporteAdminDTOInfraestructura(
                dto.folio(),
                dto.tipo().toString(),
                dto.asunto(),
                dto.categoria().categoria(),
                dto.estado().estado(),
                dto.fecha(),
                dto.cliente().getNombre()
        );
        return registroInfraestructura;
    }
    
    /**
     * Metodo para convertir los datos necesarios para que 
     * infraestructura generar el reporte pdf.
     * Esta conversion es para que infraestructura no conozca
     * negocio.
     * @param dto datos para generar el reporte
     * @return dto que infraestructura si puede utilizar.
     */
    public static ReportePdfDTOInfraestructura adaptarDatosReportePdfAInfraestructura(ReportePdfDTO dto){
        ReportePdfDTOInfraestructura datosPdf = new ReportePdfDTOInfraestructura(
                null,
                dto.fechaPdfGenerado(),
                dto.tituloReporte(),
                dto.imagen()
        );
        return datosPdf;
    }
    
    //Adapters de las entidades solas
    
    /**
     * Metodo que adapta de EstadoReportePersistenciaDTO (representa la entidad pero solo para lectura)
     * a EstadoReporteDTO para poder utilizarla en el subsistema y presentacion
     * @param estado que adaptaremos
     * @return dto del estado adaptado listo para usarse
     */
    public static EstadoReporteDTO adaptarEstadoReporteEntidad(EstadoReportePersistenciaDTO estado){
        EstadoReporteDTO estadoDTO = new EstadoReporteDTO(
                estado.getId(), estado.getNombre()
        );
        return estadoDTO;
    }
    
    /**
     * Metodo que adapta de ClientePersistenciaDTO (representa la entidad pero solo para lectura)
     * a ClienteLogueadoDTO para poder utilizarla en el subsistema y presentacion
     * @param cliente que adaptaremos
     * @return dto del cliente adaptado listo para usarse
     */
    public static ClienteLogueadoDTO adaptarClienteEntidad(ClientePersistenciaDTO cliente){
        ClienteLogueadoDTO clienteDTO = new ClienteLogueadoDTO(
                cliente.getId(), cliente.getNombre()
        );
        return clienteDTO;
    }
    
    /**
     * Metodo que adapta de ImagenPersistenciaDTO (representa la entidad pero solo para lectura)
     * a ImagenDTO para poder utilizarla en el subsistema y presentacion.
     * Se retorna null si la imagen no viene, ya que esta es opcional.
     * @param imagen que adaptaremos
     * @return dto de la imagen adaptada lista para usarse
     */
    public static ImagenDTO adaptarImagenEntidad(ImagenPersistenciaDTO imagen){
        if(imagen == null){
            return null;
        }
        ImagenDTO imagenDTO = new ImagenDTO(
                imagen.getImagen(),imagen.getId(), imagen.getMimeType()
        );
        return imagenDTO;
    }
    
    /**
     * Metodo que adapta de CategoriaPersistenciaDTO (representa la entidad pero solo para lectura)
     * a ImagenDTO para poder utilizarla en el subsistema y presentacion.
     * @param categoria que adaptaremos
     * @return dto de la categoria adaptada lista para usarse
     */
    public static CategoriaDTO adaptarCategoriaEntidad(CategoriaPersistenciaDTO categoria){
        CategoriaDTO categoriaDTO = new CategoriaDTO(
                categoria.getId(), categoria.getNombre()
        );
        return categoriaDTO;
    }
    
    /**
     * Metodo que adapta de la dto de filtros utilizada en negocio y presentacion a
     * la dto de filtros utilizadas en persistencia.
     * @param filtros que se adaptaran
     * @return los filtros listos para usarse en persistencia.
     */
    public static FiltrosConsultaHistorialReportesDTO adaptarFiltrosDTO(FiltrosConsultaHistorialReportesNegocioDTO filtros){
        Categoria categoria = adaptarCategoriaDTO(filtros.categoria());
        EstadoReporte estado = adaptarEstadoReporteDTO(filtros.estado());
        FiltrosConsultaHistorialReportesDTO filtrosPersistencia = new FiltrosConsultaHistorialReportesDTO(
                filtros.cliente(),
                estado,
                categoria,
                filtros.fechaDesde(),
                filtros.fechaHasta()
        );
        return filtrosPersistencia;
    }
    
    /**
     * Metodo que adapta de categoria entidad a categoria DTO.
     * La categoria puede ir nula debido a los filtros de consulta
     * @param categoria entidad que se adaptara 
     * @return dto de la categoria adaptada 
     */
    public static CategoriaDTO adaptarCategoriaEntidad(Categoria categoria){
        if(categoria == null){
            return null;
        }
        CategoriaDTO categoriaDTO = new CategoriaDTO(categoria.getId(),categoria.getCategoria());
        return categoriaDTO;
    }
    
    /**
     * Metodo que adapta la categoriaDTO a 
     * categoria entidad
     * La categoria puede ir nula debido a los filtros de consulta
     * @param categoria dto que se adaptara
     * @return categoria entidad adaptada
     */
    public static Categoria adaptarCategoriaDTO(CategoriaDTO categoria){
        if(categoria == null){
            return null;
        }
        Categoria categoriaEntidad = new Categoria(categoria.id(),categoria.categoria());
        return categoriaEntidad;
    }
    
    /**
     * Metodo que adapta de estado reporte entidad 
     * a estado reporte dto
     * @param estado entidad que se adaptara
     * @return estado reporte dto adaptado 
     */
    public static EstadoReporteDTO adaptarEstadoReporteEntidad(EstadoReporte estado){
        if(estado == null){
            return null;
        }
        EstadoReporteDTO estadoReporte = new EstadoReporteDTO(estado.getId(),estado.getEstado());
        return estadoReporte;
    }
    
    /**
     * Metodo que adapta de estado reporte dto
     * a estado reporte entidad
     * @param estadoReporte dto que se adaptara
     * @return estadoReporte entidad adaptado
     */
    public static EstadoReporte adaptarEstadoReporteDTO(EstadoReporteDTO estadoReporte){
        if(estadoReporte == null){
            return null;
        }
        EstadoReporte estado = new EstadoReporte(estadoReporte.id(),estadoReporte.estado());
        return estado;
    }
    
    /**
     * Metodo que adapta de cliente entidad 
     * a la dto del cliente que esta en la sesion
     * @param cliente entidad que se adaptara
     * @return la dto del cliente que esta en la sesion
     */
    public static ClienteLogueadoDTO adaptarClienteEntidad(Cliente cliente){
        ClienteLogueadoDTO clienteLogueado = new ClienteLogueadoDTO(
                cliente.getIdCliente(),
                cliente.getNombre()
        );
        return clienteLogueado;
    }
    
    /**
     * Metodo para adaptar de la dto del cliente que esta en la sesion
     * a cliente entidad
     * @param cliente dto que se adaptara
     * @return el cliente entidad
     */
    public static Cliente adaptarClienteDTO(ClienteLogueadoDTO cliente){
        Cliente clienteEntidad = new Cliente(
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getApellido()
        );
        return clienteEntidad;
    }
    
    /**
     * Metodo que adapta de imagen dto a imagen entidad.
     * Si la imagen es nula, regresa nulo ya que la imagen
     * es opcional.
     * @param imagen que se adaptara
     * @return imagen entidad adaptada
     */
    public static Imagen adaptarImagenDTO(ImagenDTO imagen){
        if(imagen == null){
            return null;
        }
        Imagen imagenEntidad = new Imagen(imagen.imagen(), imagen.mimeType());
        return imagenEntidad;
    }

    /**
     * Metodo que adapta de imagen entidad a imagen dto.
     * Si la imagen es nula, regresa nulo ya que la imagen
     * es opcional.
     * @param imagen que se adaptara
     * @return imagen dto adaptada
     */
    public static ImagenDTO adaptarImagenEntidad(Imagen imagen){
        if(imagen == null){
            return null;
        }
        ImagenDTO imagenDTO = new ImagenDTO(imagen.getImagen(),imagen.getId(), imagen.getMimeType()); 
        return imagenDTO;
    }
}
