
package com.mycompany.fitlifegym_persistencia;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mycompany.fitlifegym_persistencia.ManejadorConexiones.obtenerCodecs;
import com.mycompany.fitlifegym_persistencia.entidades.Administrador;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Clase que implementa los metodos de la interfaz IAdministradorDAO, y la interfaz
 * IBaseMongoDAO para manejar los metodos de conexion a la BD. Utilizando
 * MongoDB como motor para realizar operaciones.
 * @author Julian
 */
public class AdministradorDAO implements IAdministradorDAO, IBaseMongoDAO{
    private final String NOMBRE_COLECCION = "administradores";
    private final String USUARIO_KEY = "usuario";
    private final String ID_KEY = "_id";
    
    /**
     * Metodo que obtiene la base de datos
     * @param cliente es la conexion para poder acceder a la base de datos
     * @return la base de datos
     */
    @Override
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
        MongoDatabase empresaBD = cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
        return empresaBD;
    }
    
    /**
     * Metodo que obtiene la coleccion en la que trabajaremos ( Administradores )
     * @param baseDatos donde trabajaremos
     * @return la coleccion donde se trabajara
     */
    @Override
    public MongoCollection obtenerColeccion(MongoDatabase baseDatos) {
        MongoCollection<Administrador> coleccion = baseDatos.getCollection(NOMBRE_COLECCION, Administrador.class);
        return coleccion;
    }
    
    /**
     * Metodo para consultar un administradir por su usuario
     * @param usuario del administrador que se busca
     * @return el administrador que se encuentre con ese usuario
     * @throws PersistenciaException si falla algo en la conexion a la base de datos
     */
    @Override
    public Administrador consultarAdministradorPorUsuario(String usuario) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {

            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Administrador> coleccion = this.obtenerColeccion(empresaBD);

            Document filtros = new Document().append(USUARIO_KEY, usuario);
            Administrador administrador = coleccion.find(filtros).first();
            if (administrador == null) {
                throw new PersistenciaException("No existe ningun administrador con ese usuario.");
            }
            return administrador;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar el administrador por usuario.", ex);
        }
    }
    
    /**
     * Metodo para consultar un administrador por su ID
     * @param id del administrador que se busca
     * @return el administrador encontrado con ese ID
     * @throws PersistenciaException si no se encuentra ningun administradir con ese ID
     * o si falla algo en la conexion a la base de datos
     */
    @Override
    public Administrador consultarAdministradorPorId(String id) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {

            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Administrador> coleccion = this.obtenerColeccion(empresaBD);

            Document filtros = new Document().append(ID_KEY, new ObjectId(id));
            Administrador administrador = coleccion.find(filtros).first();
            if (administrador == null) {
                throw new PersistenciaException("No existe ningun administrador con ese ID.");
            }
            return administrador;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar el administrador por id.", ex);
        }
    }
    
}
