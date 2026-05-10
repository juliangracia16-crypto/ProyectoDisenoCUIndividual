
package com.mycompany.fitlifegym_persistencia.entidades;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Julian
 */
public class Categoria {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    @BsonProperty("nombre")
    private String categoria;

    public Categoria() {
    }

    public Categoria(String id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public Categoria(String categoria) {
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    @Override
    public String toString() {
        return categoria;
    }
}
