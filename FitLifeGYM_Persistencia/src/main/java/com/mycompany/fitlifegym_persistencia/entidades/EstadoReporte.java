
package com.mycompany.fitlifegym_persistencia.entidades;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Julian
 */
public class EstadoReporte {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    @BsonProperty("nombre")
    private String estado;

    public EstadoReporte() {
    }

    public EstadoReporte(String estado) {
        this.estado = estado;
    }

    public EstadoReporte(String id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return estado;
    }
}
