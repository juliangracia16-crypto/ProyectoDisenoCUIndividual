
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import java.util.List;

/**
 * Define las operaciones de acceso y manipulación de datos relacionadas con las
 * categorias y los estados en el sistema.
 *
 * Esta interfaz establece el contrato que debe implementar la capa de
 * persistencia para la gestión de estados y categorias.
 * @author Julian
 */
public interface ICatalogosDAO {
    /**
     * Metodo que consulta los estados que puede tener un reporte
     * @return una lista con los estados 
     * @throws PersistenciaException si ocurre un error durante la consulta
     */
    public abstract List<EstadoReporte> consultarCatalogoEstados() throws PersistenciaException;
    
    /**
     * Metodo que consulta las categorias que puede tener un reporte
     * @return una lista con las categorias 
     * @throws PersistenciaException si ocurre un error durante la consulta
     */
    public abstract List<Categoria> consultarCatalogoCategorias() throws PersistenciaException;
    
    /**
     * Metodo para consultar una categoria por su nombre 
     * @param nombre de la categoria a consultar
     * @return la categoria correspondiente a ese nombre
     * @throws PersistenciaException si ocurre un error al consultar o si no se encuentra una categoria con ese nombre
     */
    public abstract Categoria consultarCategoriaPorNombre(String nombre) throws PersistenciaException;
    
    /**
     * Metodo para consultar un estado por su nombre
     * @param nombre del estado a consultar
     * @return el estado correspondiente a ese nombre
     * @throws PersistenciaException si ocurre un error al consultar o si no se encuentra un estado con ese nombre
     */
    public abstract EstadoReporte consultarEstadoPorNombre(String nombre) throws PersistenciaException;
}
