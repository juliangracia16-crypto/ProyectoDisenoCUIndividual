
package com.mycompany.cuquejassugerencias;

import com.mycompany.fitlifegym_dtos.AtenderReporteDTO;
import com.mycompany.fitlifegym_dtos.CategoriaDTO;
import com.mycompany.fitlifegym_dtos.EstadoReporteDTO;
import com.mycompany.fitlifegym_dtos.FiltrosConsultaHistorialReportesNegocioDTO;
import com.mycompany.fitlifegym_dtos.NuevoReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteAtencionDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteDTO;
import com.mycompany.fitlifegym_dtos.ReporteIncidenteGeneradoDTO;
import com.mycompany.fitlifegym_negocio.ICatalogosBO;
import com.mycompany.fitlifegym_negocio.IHistorialAtencionesBO;
import com.mycompany.fitlifegym_negocio.IHistorialIncidentesBO;
import com.mycompany.fitlifegym_negocio.NegocioException;
import java.time.LocalDate;
import java.util.List;


        
/**
 *
 * @author Julian
 */
public class CUQuejasSugerencias implements ICUQuejasSugerencias{
    private IHistorialIncidentesBO historialIncidentesBO;
    private IHistorialAtencionesBO historialAtencionesBO;
    private ICatalogosBO catalogosBO;

    public CUQuejasSugerencias(IHistorialIncidentesBO historialIncidentesBO, IHistorialAtencionesBO historialAtencionesBO, ICatalogosBO catalogosBO) {
        this.historialIncidentesBO = historialIncidentesBO;
        this.historialAtencionesBO = historialAtencionesBO;
        this.catalogosBO = catalogosBO;
    }
    
    @Override
    public List<CategoriaDTO> cargarCatalogoCategorias() throws NegocioException {
        List<CategoriaDTO> categoriasDTO = catalogosBO.consultarCatalogoCategorias();
        return categoriasDTO;
    }

    @Override
    public List<EstadoReporteDTO> cargarCatalogoEstados() throws NegocioException {
        List<EstadoReporteDTO> estadosDTO = catalogosBO.consultarCatalogoEstados();
        return estadosDTO;
    }

    @Override
    public ReporteIncidenteDTO consultarReporteIncidentePorFolio(String folio) throws NegocioException {
        ReporteIncidenteDTO reporteIncidente = historialIncidentesBO.consultarReporteIncidentePorFolio(folio);
        return reporteIncidente;
    }
    
    @Override
    public List<ReporteIncidenteDTO> consultarReportesIncidentes(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        validarFechasFiltro(filtros.fechaDesde(),filtros.fechaHasta());
        List<ReporteIncidenteDTO> reportesIncidentes = historialIncidentesBO.consultarReportesIncidentes(filtros);
        return reportesIncidentes;
    }

    @Override
    public List<ReporteAtencionDTO> consultarReportesAtenciones(FiltrosConsultaHistorialReportesNegocioDTO filtros) throws NegocioException {
        validarFechasFiltro(filtros.fechaDesde(),filtros.fechaHasta());
        validarNombreFiltro(filtros.cliente());
        List<ReporteAtencionDTO> reportesAtenciones = historialAtencionesBO.consultarReportesAtenciones(filtros);
        return reportesAtenciones;
    }

    @Override
    public ReporteIncidenteGeneradoDTO generarReporteIncidente(NuevoReporteIncidenteDTO reporteIncidente) throws NegocioException {
        validarDescripcionReporteIncidente(reporteIncidente.descripcion());
        if(reporteIncidente.imagen() != null){
            validarImagen(reporteIncidente.imagen().imagen());
        }
        validarAsuntoReporteIncidente(reporteIncidente.asunto());
        ReporteIncidenteGeneradoDTO reporteIncidenteGenerado = historialIncidentesBO.generarReporteIncidente(reporteIncidente);
        return reporteIncidenteGenerado;
    }

    @Override
    public ReporteAtencionDTO atenderReporteIncidente(AtenderReporteDTO reporteAtencion) throws NegocioException {
        validarImagen(reporteAtencion.imagen().imagen());
        validarSolucionReporteAtencion(reporteAtencion.solucion());
        ReporteAtencionDTO reporteIncidenteAtendido = historialAtencionesBO.atenderReporteIncidente(reporteAtencion);
        return reporteIncidenteAtendido;
    }
    @Override
    public List<ReporteAtencionDTO> consultarTodosLosReportesAtenciones() throws NegocioException {
        List<ReporteAtencionDTO> reportesAtenciones = historialAtencionesBO.consultarReportesAtenciones();
        return reportesAtenciones;
    }

    @Override
    public List<ReporteIncidenteDTO> consultarTodosLosReportesIncidentes() throws NegocioException {
        List<ReporteIncidenteDTO> reportesIncidentes = historialIncidentesBO.consultarReportesIncidentes();
        return reportesIncidentes;
    }
    
    private void validarDescripcionReporteIncidente(String descripcion) throws NegocioException{
        if(descripcion.isEmpty() || descripcion == null){
            throw new NegocioException("La descripcion del reporte de incidente no debe estar vacía.");
        }
        if(descripcion.trim().length()>255){
            throw new NegocioException("La longitud maxima de la descripcion es de 255 caracteres.");
        }
        if(descripcion.trim().length()<15){
            throw new NegocioException("La longitud minima de la descripcion es de 15 caracteres.");
        }
    }
    private void validarImagen(byte[] imagen) throws NegocioException{
        
        int maxBytes = 5 * 1024 * 1024;
        if(imagen.length > maxBytes){
            throw new NegocioException("El tamaño maximo de la imagen es de 5 Mb.");
        }
        boolean esPNG =
            imagen.length >= 4
            && (imagen[0] & 0xFF) == 0x89
            && (imagen[1] & 0xFF) == 0x50
            && (imagen[2] & 0xFF) == 0x4E
            && (imagen[3] & 0xFF) == 0x47;

        boolean esJPG =
            imagen.length >= 2
            && (imagen[0] & 0xFF) == 0xFF
            && (imagen[1] & 0xFF) == 0xD8;
        
        if(!esPNG && !esJPG){
            throw new NegocioException("Solo se permiten imagenes PNG o JPG.");
        }
    }
    private void validarFechasFiltro(LocalDate fechaDesde, LocalDate fechaHasta) throws NegocioException{
        if(fechaDesde == null){
            return;
        }
        if(fechaHasta == null){
            return;
        }
        if(fechaDesde.isAfter(LocalDate.now()) || fechaHasta.isAfter(LocalDate.now())){
            throw new NegocioException("La fecha no puede ser posterior a la fecha actual."); 
        }
    }
    private void validarSolucionReporteAtencion(String solucion) throws NegocioException{
        if(solucion.isEmpty() || solucion == null){
            throw new NegocioException("La solucion del reporte de atencion no debe estar vacía.");
        }
        if(solucion.trim().length()>255){
            throw new NegocioException("La longitud maxima de la solucion es de 255 caracteres.");
        }
        if(solucion.trim().length()<15){
            throw new NegocioException("La longitud minima de la solucion es de 15 caracteres.");
        }
    }
    private void validarNombreFiltro(String nombre) throws NegocioException{
        if(nombre.trim().length() > 50){
            throw new NegocioException("El campo para filtrar por nombre debe tener maximo 50 caracteres.");
        }
    }
    private void validarAsuntoReporteIncidente(String asunto) throws NegocioException{
        if(asunto.isEmpty() || asunto == null){
            throw new NegocioException("El asunto del reporte es obligatorio.");
        }
        if(asunto.trim().length()>100){
            throw new NegocioException("El asunto debe contener como maximo 100 caracteres.");
        }
        if(asunto.trim().length() < 5){
            throw new NegocioException("El asunto debe contener como minimo 5 caracteres.");
        }
    }

}
