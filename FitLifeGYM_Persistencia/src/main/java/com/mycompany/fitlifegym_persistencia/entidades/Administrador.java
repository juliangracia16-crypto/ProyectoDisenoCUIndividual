
package com.mycompany.fitlifegym_persistencia.entidades;

import java.util.Objects;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Julian
 */
public class Administrador {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    String idAdministrador;
    String nombre;
    String usuario;
    String contrasenia;

    public Administrador() {
    }

    public Administrador(String idAdministrador, String nombre, String usuario, String contrasenia) {
        this.idAdministrador = idAdministrador;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Administrador(String nombre, String usuario, String contrasenia) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(String idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idAdministrador);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Administrador other = (Administrador) obj;
        return Objects.equals(this.idAdministrador, other.idAdministrador);
    }

    @Override
    public String toString() {
        return "Administrador{" + "idAdministrador=" + idAdministrador + ", nombre=" + nombre + ", usuario=" + usuario + ", contrasenia=" + contrasenia + '}';
    }
    
    
}
