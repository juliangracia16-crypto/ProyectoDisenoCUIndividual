
package com.mycompany.fitlifegym_persistencia;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mycompany.fitlifegym_persistencia.ManejadorConexiones.obtenerCodecs;
import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Julian
 */
public class CatalogosDAO implements ICatalogosDAO{
    private static final String NOMBRE_COLECCION_CATEGORIAS = "categorias";
    private static final String NOMBRE_COLECCION_ESTADOS = "estados_reportes";
    
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
        MongoDatabase empresaBD = cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
        return empresaBD;
    }

    public MongoCollection obtenerColeccionCategorias(MongoDatabase baseDatos) {
        MongoCollection<Categoria> coleccion = baseDatos.getCollection(NOMBRE_COLECCION_CATEGORIAS,Categoria.class);
        return coleccion;
    }
    
    public MongoCollection obtenerColeccionEstados(MongoDatabase baseDatos) {
        MongoCollection<EstadoReporte> coleccion = baseDatos.getCollection(NOMBRE_COLECCION_ESTADOS,EstadoReporte.class);
        return coleccion;
    }
    
    @Override
    public List<EstadoReporte> consultarCatalogoEstados() throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<EstadoReporte> coleccion = this.obtenerColeccionEstados(empresaBD);
            
            List<EstadoReporte> estados = new LinkedList<>();
            coleccion.find().into(estados);
            
            return estados;
        }
    }

    @Override
    public List<Categoria> consultarCatalogoCategorias() throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Categoria> coleccion = this.obtenerColeccionCategorias(empresaBD);
            
            List<Categoria> categorias = new LinkedList<>();
            coleccion.find().into(categorias);
            
            return categorias;
        }
    }
    
}
