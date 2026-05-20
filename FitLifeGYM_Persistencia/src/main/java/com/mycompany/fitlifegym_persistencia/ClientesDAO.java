
package com.mycompany.fitlifegym_persistencia;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mycompany.fitlifegym_persistencia.ManejadorConexiones.obtenerCodecs;
import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Clase que implementa los metodos de la interfaz IClientesDAO, y la
 * interfaz IBaseMongoDAO para manejar los metodos de conexion a la BD.
 * Utilizando MongoDB como motor para realizar operaciones.
 * @author Julian
 */
public class ClientesDAO implements IClientesDAO, IBaseMongoDAO{
    private static final String NOMBRE_COLECCION = "clientes";
    private static final String ID_KEY = "_id";
    private static final String CORREO_KEY = "correo";
    
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
     * Metodo que obtiene la coleccion en la que trabajaremos ( Clientes )
     * @param baseDatos donde trabajaremos
     * @return la coleccion donde se trabajara 
     */
    @Override
    public MongoCollection obtenerColeccion(MongoDatabase baseDatos) {
        MongoCollection<Cliente> coleccion = baseDatos.getCollection(NOMBRE_COLECCION,Cliente.class);
        return coleccion;
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Metodo que consulta un cliente por su ID
     * @param id del cliente que se quiere buscar
     * @return el cliente encontrado con ese ID
     * @throws PersistenciaException si falla algo en la conexion a la base de datos
     * o si no se encuentra un cliente con ese ID.
     */
    @Override
    public Cliente consultarClientePorId(String id) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Cliente> coleccion = this.obtenerColeccion(empresaBD);
            
            Document filtros = new Document().append(ID_KEY,new ObjectId(id));
            Cliente clienteEncontrado = coleccion.find(filtros).first();
            if(clienteEncontrado == null){
                throw new PersistenciaException("No existe ningun cliente con ese ID.");
            }
            return clienteEncontrado;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar el cliente por ID.", ex);
        }
    }
    
    /**
     * Metodo para consultar todos los clientes dentro de la coleccion
     * @return una lista con todos los clientes que hay al momento de consultar en la coleccion
     * @throws PersistenciaException si falla algo al conectarse a la base de datos
     */
    @Override
    public List<Cliente> consultarClientes() throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Cliente> coleccion = this.obtenerColeccion(empresaBD);
            List<Cliente> clientes = new LinkedList<>();
            coleccion.find().into(clientes);
            return clientes;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar todos los clientes.", ex);
        }
    }

    /**
     * Metodo para consultar un cliente por su correo
     * @param correo del cliente que buscaremos
     * @return el cliente encontrado con ese correo
     * @throws PersistenciaException si no se encuentra un cliente con ese correo
     * o si falla algo al momento de conectarse a la base de datos
     */
    @Override
    public Cliente buscarPorCorreo(String correo) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Cliente> coleccion = this.obtenerColeccion(empresaBD);
            
            Document filtros = new Document().append(CORREO_KEY,correo);
            Cliente clienteEncontrado = coleccion.find(filtros).first();
            if(clienteEncontrado == null){
                throw new PersistenciaException("No existe ningun cliente con ese ID.");
            }
            return clienteEncontrado;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar cliente por pin.", ex);
        }
    }
    
}
