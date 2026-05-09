
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.entidades.Imagen;

/**
 *
 * @author Julian
 */
public interface IImagenesDAO {
    public abstract Imagen consultarImagen(String idImagen) throws PersistenciaException;
    public abstract Imagen guardarImagen(Imagen imagen) throws PersistenciaException;
}
