
package com.mycompany.fitlifegym_persistencia;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mycompany.fitlifegym_persistencia.ManejadorConexiones.obtenerCodecs;
import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Clase que implementa los metodos de la interfaz ICatalogosDAO
 * utilizando MongoDB como motor de base de datos para las operaciones
 * que se realizan
 * @author Julian
 */
public class CatalogosDAO implements ICatalogosDAO{
    private static final String NOMBRE_COLECCION_CATEGORIAS = "categorias";
    private static final String NOMBRE_COLECCION_ESTADOS = "estados_reportes";
    private static final String NOMBRE_KEY = "nombre";
    
    /**
     * Metodo para obtener la base de datos 
     * @param cliente es la conexion para poder acceder a la base de datos
     * @return la base de datos mongo
     */
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
        MongoDatabase empresaBD = cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
        return empresaBD;
    }
    
    /**
     * Metodo para obtener la coleccion donde trabajaremos ( Categorias )
     * @param baseDatos donde trabajaremos
     * @return la coleccion donde estaremos trabajando
     */
    public MongoCollection obtenerColeccionCategorias(MongoDatabase baseDatos) {
        MongoCollection<Categoria> coleccion = baseDatos.getCollection(NOMBRE_COLECCION_CATEGORIAS,Categoria.class);
        return coleccion;
    }
    
    /**
     * Metodo para obtener la coleccion donde trabajaremos ( Estados Reportes )
     * @param baseDatos donde trabajaremos
     * @return la coleccion donde estaremos trabajando
     */
    public MongoCollection obtenerColeccionEstados(MongoDatabase baseDatos) {
        MongoCollection<EstadoReporte> coleccion = baseDatos.getCollection(NOMBRE_COLECCION_ESTADOS,EstadoReporte.class);
        return coleccion;
    }
    
    /**
     * Metodo que obtiene todos los estados que hay en la coleccion ( Estados Reporte ) 
     * @return una lista con todos los estados encontrados
     * @throws PersistenciaException si falla algo al momento de acceder a la base de datos
     */
    @Override
    public List<EstadoReporte> consultarCatalogoEstados() throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            precargarEstados();
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<EstadoReporte> coleccion = this.obtenerColeccionEstados(empresaBD);
            
            List<EstadoReporte> estados = new LinkedList<>();
            coleccion.find().into(estados);
            
            return estados;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar todos los estados del catalogo de estados reportes.", ex);
        }
    }
    
    /**
     * Metodo que obtiene todos los estados que hay en la coleccion ( Categorias ) 
     * @return una lista con todas las categorias encontradas
     * @throws PersistenciaException si falla algo al momento de acceder a la base de datos
     */
    @Override
    public List<Categoria> consultarCatalogoCategorias() throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            precargarCategorias();
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Categoria> coleccion = this.obtenerColeccionCategorias(empresaBD);
            
            List<Categoria> categorias = new LinkedList<>();
            coleccion.find().into(categorias);
            
            return categorias;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar todas las categorias del catalogo de categorias.", ex);
        }
    }
    
    /**
     * Metodo que obtiene una categoria por su nombre
     * @param nombre de la categoria que buscamos
     * @return la categoria encontrada
     * @throws PersistenciaException  si falla algo al conectarnos a la base de datos
     */
    @Override
    public Categoria consultarCategoriaPorNombre(String nombre) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            precargarCategorias();
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Categoria> coleccion = this.obtenerColeccionCategorias(empresaBD);
            
            Categoria categoria = coleccion.find(eq(NOMBRE_KEY,nombre)).first();
            return categoria;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar la categoria del catalogo de categorias.", ex);
        }
    }
    
    /**
     * Metodo que obtiene un estado por su nombre
     * @param nombre del estado que buscamos
     * @return el estado encontrado 
     * @throws PersistenciaException si falla algo al conectarnos a la base de datos 
     */
    @Override
    public EstadoReporte consultarEstadoPorNombre(String nombre) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            precargarEstados();
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<EstadoReporte> coleccion = this.obtenerColeccionEstados(empresaBD);
            
            EstadoReporte estado = coleccion.find(eq(NOMBRE_KEY,nombre)).first();
            return estado;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar el estado del catalogo de estados.", ex);
        }
    }
    
    /**
     * Metodo para precargar los estados de los reportes
     * a la base de datos al momento de ejecutarse el programa.
     */
    private void precargarEstados() {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<EstadoReporte> coleccion = this.obtenerColeccionEstados(empresaBD);

            if (coleccion.countDocuments() == 0) {

                List<EstadoReporte> estados = List.of(
                    new EstadoReporte(new ObjectId().toHexString(),"SIN RESOLVER"),
                    new EstadoReporte(new ObjectId().toHexString(),"RESUELTO")
                );
                coleccion.insertMany(estados);
            }
        }      
    }
    
    /**
     * Metodo para precargar las categorias de los reportes
     * a la base de datos al momento de ejecutarse el programa.
     */
    private void precargarCategorias() {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Categoria> coleccion = this.obtenerColeccionCategorias(empresaBD);

            if (coleccion.countDocuments() == 0) {

                List<Categoria> categorias = List.of(
                    new Categoria(new ObjectId().toHexString(),"QUEJA"),
                    new Categoria(new ObjectId().toHexString(),"SUGERENCIA")
                );
                coleccion.insertMany(categorias);
            }
        }      
    }
}
