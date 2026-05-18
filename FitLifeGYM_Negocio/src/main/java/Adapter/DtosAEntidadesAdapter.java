
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
public class DtosAEntidadesAdapter {

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
    public static EstadoReporteDTO adaptarEstadoReporteEntidad(EstadoReportePersistenciaDTO estado){
        EstadoReporteDTO estadoDTO = new EstadoReporteDTO(
                estado.getId(), estado.getNombre()
        );
        return estadoDTO;
    }
    
    public static ClienteLogueadoDTO adaptarClienteEntidad(ClientePersistenciaDTO cliente){
        ClienteLogueadoDTO clienteDTO = new ClienteLogueadoDTO(
                cliente.getId(), cliente.getNombre()
        );
        return clienteDTO;
    }
    
    public static ImagenDTO adaptarImagenEntidad(ImagenPersistenciaDTO imagen){
        if(imagen == null){
            return null;
        }
        ImagenDTO imagenDTO = new ImagenDTO(
                imagen.getImagen(),imagen.getId(), imagen.getMimeType()
        );
        return imagenDTO;
    }
    
    public static CategoriaDTO adaptarCategoriaEntidad(CategoriaPersistenciaDTO categoria){
        CategoriaDTO categoriaDTO = new CategoriaDTO(
                categoria.getId(), categoria.getNombre()
        );
        return categoriaDTO;
    }
    
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
    
    public static CategoriaDTO adaptarCategoriaEntidad(Categoria categoria){
        if(categoria == null){
            return null;
        }
        CategoriaDTO categoriaDTO = new CategoriaDTO(categoria.getId(),categoria.getCategoria());
        return categoriaDTO;
    }
    
    public static Categoria adaptarCategoriaDTO(CategoriaDTO categoria){
        if(categoria == null){
            return null;
        }
        Categoria categoriaEntidad = new Categoria(categoria.id(),categoria.categoria());
        return categoriaEntidad;
    }
    
    public static EstadoReporteDTO adaptarEstadoReporteEntidad(EstadoReporte estado){
        if(estado == null){
            return null;
        }
        EstadoReporteDTO estadoReporte = new EstadoReporteDTO(estado.getId(),estado.getEstado());
        return estadoReporte;
    }
    
    public static EstadoReporte adaptarEstadoReporteDTO(EstadoReporteDTO estadoReporte){
        if(estadoReporte == null){
            return null;
        }
        EstadoReporte estado = new EstadoReporte(estadoReporte.id(),estadoReporte.estado());
        return estado;
    }
    
    public static ClienteLogueadoDTO adaptarClienteEntidad(Cliente cliente){
        ClienteLogueadoDTO clienteLogueado = new ClienteLogueadoDTO(
                cliente.getIdCliente(),
                cliente.getNombre()
        );
        return clienteLogueado;
    }
    
    public static Cliente adaptarClienteDTO(ClienteLogueadoDTO cliente){
        Cliente clienteEntidad = new Cliente(
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getApellido()
        );
        return clienteEntidad;
    }
    
    public static Imagen adaptarImagenDTO(ImagenDTO imagen){
        if(imagen == null){
            return null;
        }
        Imagen imagenEntidad = new Imagen(imagen.imagen(), imagen.mimeType());
        return imagenEntidad;
    }

    public static ImagenDTO adaptarImagenEntidad(Imagen imagen){
        if(imagen == null){
            return null;
        }
        ImagenDTO imagenDTO = new ImagenDTO(imagen.getImagen(),imagen.getId(), imagen.getMimeType()); 
        return imagenDTO;
    }
}
