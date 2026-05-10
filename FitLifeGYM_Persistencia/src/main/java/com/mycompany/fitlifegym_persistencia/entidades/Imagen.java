
package com.mycompany.fitlifegym_persistencia.entidades;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Julian
 */
public class Imagen {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private byte[] imagen;

    public Imagen() {
    }

    public Imagen(String id, byte[] imagen) {
        this.id = id;
        this.imagen = imagen;
    }
    
    public Imagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
}
