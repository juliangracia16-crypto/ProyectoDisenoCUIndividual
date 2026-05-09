
package com.mycompany.fitlifegym_persistencia;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 * Clase que gestiona la conexion a la Base de Datos
 * @author Julian
 */
public class ManejadorConexiones {
    public static final String CADENA_CONEXION = "mongodb://localhost:27017";
    public static final String BASE_DATOS = "quejas_sugerencias_cu";

    public static MongoClient crearConexion() {
        MongoClient mongoClient = MongoClients.create(CADENA_CONEXION);
        return mongoClient;
    }

    public static CodecRegistry obtenerCodecs() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        return pojoCodecRegistry;
    }
}
