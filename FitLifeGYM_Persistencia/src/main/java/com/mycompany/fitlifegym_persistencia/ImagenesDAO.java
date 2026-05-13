
package com.mycompany.fitlifegym_persistencia;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import static com.mycompany.fitlifegym_persistencia.ManejadorConexiones.obtenerCodecs;
import com.mycompany.fitlifegym_persistencia.entidades.Imagen;
import org.bson.Document;

/**
 *
 * @author Julian
 */
public class ImagenesDAO implements IImagenesDAO, IBaseMongoDAO{

    private static final String NOMBRE_COLECCION = "imagenes";
    private static final String ID_KEY = "_id";
    
    @Override
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
        MongoDatabase empresaBD = cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
        return empresaBD;
    }

    @Override
    public MongoCollection obtenerColeccion(MongoDatabase baseDatos) {
        MongoCollection<Imagen> coleccion = baseDatos.getCollection(NOMBRE_COLECCION,Imagen.class);
        return coleccion;
    }
    
    @Override
    public Imagen consultarImagen(String idImagen) throws PersistenciaException {
         try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Imagen> coleccionImagenes = this.obtenerColeccion(empresaBD);
            
            Document filtros = new Document().append(ID_KEY, idImagen);
            Imagen imagen = coleccionImagenes.find(filtros).first();
            
            if(imagen == null){
                throw new PersistenciaException("No se encontro ninguna imagen con ese ID.");
            }
            
            return imagen;
         }
    }

    @Override
    public Imagen guardarImagen(Imagen imagen) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Imagen> coleccionImagenes = this.obtenerColeccion(empresaBD);
            
            InsertOneResult resultado = coleccionImagenes.insertOne(imagen);
            
            if(!resultado.wasAcknowledged()){
                throw new PersistenciaException("No se pudo guardar la imagen correctamente.");
            }
            String id = resultado.getInsertedId().asObjectId().getValue().toHexString();
            imagen.setId(id);
            return imagen;
         }
    }

    
    
}
