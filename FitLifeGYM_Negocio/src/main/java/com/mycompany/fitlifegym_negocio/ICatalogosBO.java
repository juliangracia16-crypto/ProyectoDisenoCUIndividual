
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.CategoriaDTO;
import com.mycompany.fitlifegym_dtos.EstadoReporteDTO;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface ICatalogosBO {
    public abstract List<CategoriaDTO> consultarCatalogoCategorias() throws NegocioException;
    public abstract List<EstadoReporteDTO> consultarCatalogoEstados() throws NegocioException;
}
