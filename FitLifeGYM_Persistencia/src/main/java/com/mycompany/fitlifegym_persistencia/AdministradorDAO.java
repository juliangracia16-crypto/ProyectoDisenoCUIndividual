
package com.mycompany.fitlifegym_persistencia;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mycompany.fitlifegym_persistencia.ManejadorConexiones.obtenerCodecs;
import com.mycompany.fitlifegym_persistencia.entidades.Administrador;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Julian
 */
public class AdministradorDAO implements IAdministradorDAO, IBaseMongoDAO{
    private final String NOMBRE_COLECCION = "administradores";
    private final String USUARIO_KEY = "usuario";
    private final String ID_KEY = "_id";
    
    @Override
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
        MongoDatabase empresaBD = cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
        return empresaBD;
    }

    @Override
    public MongoCollection obtenerColeccion(MongoDatabase baseDatos) {
        MongoCollection<Administrador> coleccion = baseDatos.getCollection(NOMBRE_COLECCION, Administrador.class);
        return coleccion;
    }
    
    @Override
    public Administrador consultarAdministradorPorUsuario(String usuario) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {

            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Administrador> coleccion = this.obtenerColeccion(empresaBD);

            Document filtros = new Document().append(USUARIO_KEY, usuario);
            Administrador clienteEncontrado = coleccion.find(filtros).first();
            if (clienteEncontrado == null) {
                throw new PersistenciaException("No existe ningun administrador con ese usuario.");
            }
            return clienteEncontrado;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar el administrador por usuario.", ex);
        }
    }

    @Override
    public List<Administrador> consultarAdministradores() throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {

            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Administrador> coleccion = this.obtenerColeccion(empresaBD);
            List<Administrador> administradores = new LinkedList<>();
            coleccion.find().into(administradores);
            return administradores;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar todos los administradores.", ex);
        }
    }

    @Override
    public Administrador consultarAdministradorPorId(String id) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {

            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Administrador> coleccion = this.obtenerColeccion(empresaBD);

            Document filtros = new Document().append(ID_KEY, id);
            Administrador clienteEncontrado = coleccion.find(filtros).first();
            if (clienteEncontrado == null) {
                throw new PersistenciaException("No existe ningun administrador con ese ID.");
            }
            return clienteEncontrado;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar el administrador por id.", ex);
        }
    }
    
}
