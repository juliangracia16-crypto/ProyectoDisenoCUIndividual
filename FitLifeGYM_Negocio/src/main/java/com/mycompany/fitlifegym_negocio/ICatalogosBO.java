
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.CategoriaDTO;
import com.mycompany.fitlifegym_dtos.EstadoReporteDTO;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface ICatalogosBO {
    
    /**
     * Metodo que consulta todas las categorias
     * @return una lista con las categorias 
     * @throws NegocioException si ocurre un error al consultar
     */
    public abstract List<CategoriaDTO> consultarCatalogoCategorias() throws NegocioException;
    
    /**
     * Metodo que consulta todos los estados de los reportes
     * @return una lista con todos los estados
     * @throws NegocioException si ocurre un error al consultar 
     */
    public abstract List<EstadoReporteDTO> consultarCatalogoEstados() throws NegocioException;
}
