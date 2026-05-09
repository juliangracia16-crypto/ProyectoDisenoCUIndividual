
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface ICatalogosDAO {
    public abstract List<EstadoReporte> consultarCatalogoEstados() throws PersistenciaException;
    public abstract List<Categoria> consultarCatalogoCategorias() throws PersistenciaException;
}
