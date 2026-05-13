
package com.mycompany.fitlifegym_negocio;

import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import java.util.List;

/**
 *
 * @author Diego
 */
public interface IMembresiaBO {

    public abstract void guardar(NuevaMembresiaDTO membresia) throws NegocioException;

    public abstract List<NuevaMembresiaDTO> obtenerTodas() throws NegocioException;

    public abstract NuevaMembresiaDTO obtenerPorId(Long id)throws NegocioException;
}
