/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

import com.mycompany.fitlifegym_dtos.CategoriaDTO;
import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.EstadoDTO;
import com.mycompany.fitlifegym_dtos.EstadoReporteDTO;
import com.mycompany.fitlifegym_dtos.ImagenDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaCompradaDTO;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import com.mycompany.fitlifegym_dtos.NuevoClienteDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.TipoMembresiaDTO;
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

    

    public static EstadoDTO adaptarEstadoDTO(Estado estado) {
        if (estado == Estado.ACTIVO) {
            return EstadoDTO.ACTIVO;
        }
        return EstadoDTO.INACTIVO;
    }
    
    //==============================================
    public static ReporteAtencion adaptarReporteAtencionDTO(ReporteAtencionDTO reporteAtencionDTO){
        Imagen imagen = adaptarImagenDTO(reporteAtencionDTO.imagen());
        Cliente cliente = adaptarClienteDTO(reporteAtencionDTO.cliente());
        EstadoReporte estado = adaptarEstadoReporteDTO(reporteAtencionDTO.estado());
        Categoria categoria = adaptarCategoriaDTO(reporteAtencionDTO.categoria());
        ReporteAtencion reporteAtencion = new ReporteAtencion(
                reporteAtencionDTO.folio(),
                reporteAtencionDTO.solucion(),
                reporteAtencionDTO.fecha(),
                estado,
                categoria,
                imagen,
                cliente
        );
        return reporteAtencion;
    }
    
    public static ReporteAtencionDTO adaptarReporteAtencionEntidad(ReporteAtencion reporteAtencion){
        ImagenDTO imagenDTO = adaptarImagenEntidad(reporteAtencion.getImagen());
        EstadoReporteDTO estadoDTO = adaptarEstadoReporteEntidad(reporteAtencion.getEstado());
        CategoriaDTO categoriaDTO = adaptarCategoriaEntidad(reporteAtencion.getCategoria());
        ClienteLogueadoDTO clienteDTO = adaptarClienteEntidad(reporteAtencion.getCliente());
        
        ReporteAtencionDTO reporteAtencionDTO = new ReporteAtencionDTO(
                reporteAtencion.getFolio(),
                reporteAtencion.getSolucion(),
                categoriaDTO,
                reporteAtencion.getFecha(),
                estadoDTO,
                imagenDTO,
                clienteDTO
        );
        return reporteAtencionDTO;
    }
    
    public static ReporteIncidente adaptarReporteIncidenteDTO(NuevoReporteIncidenteDTO reporteIncidenteDTO){
        Imagen imagen = adaptarImagenDTO(reporteIncidenteDTO.imagen());
        Cliente cliente = adaptarClienteDTO(reporteIncidenteDTO.cliente());
        Categoria categoria = adaptarCategoriaDTO(reporteIncidenteDTO.categoria());
        
        ReporteIncidente reporteIncidente = new ReporteIncidente(
                reporteIncidenteDTO.asunto(),
                reporteIncidenteDTO.descripcion(),
                reporteIncidenteDTO.fecha(),
                null,
                categoria,
                imagen,
                cliente
        );
        return reporteIncidente;
    }
    
    public static ReporteIncidenteDTO adaptarReporteIncidenteEntidad(ReporteIncidente reporteIncidente){
        CategoriaDTO categoriaDTO = adaptarCategoriaEntidad(reporteIncidente.getCategoria());
        EstadoReporteDTO estadoDTO = adaptarEstadoReporteEntidad(reporteIncidente.getEstado());
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
    
    public static CategoriaDTO adaptarCategoriaEntidad(Categoria categoria){
        CategoriaDTO categoriaDTO = new CategoriaDTO(categoria.getId(),categoria.getCategoria());
        return categoriaDTO;
    }
    
    public static Categoria adaptarCategoriaDTO(CategoriaDTO categoria){
        Categoria categoriaEntidad = new Categoria(categoria.categoria());
        return categoriaEntidad;
    }
    
    public static EstadoReporteDTO adaptarEstadoReporteEntidad(EstadoReporte estado){
        EstadoReporteDTO estadoReporte = new EstadoReporteDTO(estado.getId(),estado.getEstado());
        return estadoReporte;
    }
    
    public static EstadoReporte adaptarEstadoReporteDTO(EstadoReporteDTO estadoReporte){
        EstadoReporte estado = new EstadoReporte(estadoReporte.estado());
        return estado;
    }
    
    public static ClienteLogueadoDTO adaptarClienteEntidad(Cliente cliente){
        ClienteLogueadoDTO clienteLogueado = new ClienteLogueadoDTO(
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getApellidos()
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
        Imagen imagenEntidad = new Imagen(imagen.imagen());
        return imagenEntidad;
    }

    public static ImagenDTO adaptarImagenEntidad(Imagen imagen){
        if(imagen == null){
            return null;
        }
        ImagenDTO imagenDTO = new ImagenDTO(imagen.getImagen());
        return imagenDTO;
    }
}
