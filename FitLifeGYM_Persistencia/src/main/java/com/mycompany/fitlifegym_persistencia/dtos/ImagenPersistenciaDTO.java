
package com.mycompany.fitlifegym_persistencia.dtos;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Julian
 */
public class ImagenPersistenciaDTO {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private byte[] imagen;
    private String mimeType;
    
    public ImagenPersistenciaDTO() {
    }
    
    public ImagenPersistenciaDTO(String id, byte[] imagen, String mimeType) {
        this.id = id;
        this.imagen = imagen;
        this.mimeType = mimeType;
    }

    public String getId() {
        return id;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
}
