
package com.mycompany.fitlifegym_persistencia.dtos;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Julian
 */
public class EstadoReportePersistenciaDTO{
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private String nombre;

    public EstadoReportePersistenciaDTO() {
    }
    
    public EstadoReportePersistenciaDTO(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
