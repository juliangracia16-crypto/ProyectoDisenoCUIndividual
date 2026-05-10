
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
import com.mycompany.fitlifegym_dtos.CategoriaDTO;
import com.mycompany.fitlifegym_dtos.EstadoReporteDTO;
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Julian
 */
public class CatalogosBO implements ICatalogosBO{

    private IPersistenciaFachada fachada;

    public CatalogosBO(IPersistenciaFachada fachada) {
        this.fachada = fachada;
    }
    
    @Override
    public List<CategoriaDTO> consultarCatalogoCategorias() throws NegocioException {
        try {
            List<Categoria> categorias = fachada.consultarCatalogoCategorias();
            List<CategoriaDTO> categoriasDTO = new LinkedList<>();
            for(Categoria c: categorias){
                CategoriaDTO categoriaDTO = DtosAEntidadesAdapter.adaptarCategoriaEntidad(c);
                categoriasDTO.add(categoriaDTO);
            }
            return categoriasDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar las categorias.",ex);
        }
    }

    @Override
    public List<EstadoReporteDTO> consultarCatalogoEstados() throws NegocioException {
        try {
            List<EstadoReporte> estados = fachada.consultarCatalogoEstados();
            List<EstadoReporteDTO> estadosDTO = new LinkedList<>();
            for(EstadoReporte e: estados){
                EstadoReporteDTO estadoDTO = DtosAEntidadesAdapter.adaptarEstadoReporteEntidad(e);
                estadosDTO.add(estadoDTO);
            }
            return estadosDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar los estados.",ex);
        }
    }
    
}
