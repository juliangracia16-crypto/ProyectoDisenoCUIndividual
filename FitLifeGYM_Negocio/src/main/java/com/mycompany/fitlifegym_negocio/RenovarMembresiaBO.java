
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
import com.mycompany.fitlifegym_dtos.RenovarMembresiaDTO;
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.entidades.TipoMembresia;

/**
 *
 * @author PC GAMER MASTER RACE
 */
public class RenovarMembresiaBO implements IRenovarMembresiaBO {
    private final IPersistenciaFachada fachada;

    public RenovarMembresiaBO(IPersistenciaFachada fachada) {
        this.fachada = fachada;
    }

    @Override
    public void renovarMembresia(RenovarMembresiaDTO dto) throws NegocioException{
        TipoMembresia tipo = DtosAEntidadesAdapter.adaptarTipoMembresia(dto.getTipoMembresia());
        try {
            fachada.actualizarMembresia(dto.getIdCliente(), tipo);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al renovar la membresia.",ex);
        }
    }
    
}
