
package com.mycompany.fitlifegym_persistencia;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Interfaz que define los metodos para obtener
 * la base de datos y la coleccion en las DAO
 * @author Julian
 */
public interface IBaseMongoDAO {
    public abstract MongoDatabase obtenerBaseDatos(MongoClient cliente);
    public abstract MongoCollection obtenerColeccion(MongoDatabase baseDatos);
}
