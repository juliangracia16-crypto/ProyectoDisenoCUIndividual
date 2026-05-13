
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import com.mycompany.fitlifegym_persistencia.IPersistenciaFachada;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.entidades.Membresia;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class MembresiaBO implements IMembresiaBO {

    private IPersistenciaFachada fachada;
   

    public MembresiaBO(IPersistenciaFachada fachada) {
        this.fachada = fachada;
    }

    @Override
    public void guardar(NuevaMembresiaDTO membresiaDTO) throws NegocioException{
        
        if(membresiaDTO.getTipoMembresia() == null){
            throw new NegocioException("El tipo de membresia no puede ser nulo.");
        }
        
        if(membresiaDTO.getPrecio() == null || membresiaDTO.getPrecio() <= 0){
            throw new NegocioException("El precio debe ser mayor a 0.");
        }
        
        if(membresiaDTO.getVigencia() == null){
            throw new NegocioException("La vigencia no puede ser nula.");
        }
        
        if(membresiaDTO.getVigencia().isBefore(LocalDate.now())){
            throw new NegocioException("La vigencia no puede ser una fecha pasada.");
        }
        Membresia membresia = DtosAEntidadesAdapter.adaptarMembresia(membresiaDTO);
        try {
            fachada.guardar(membresia);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al guardar la membresia.",ex);
        }
    }

    @Override
    public List<NuevaMembresiaDTO> obtenerTodas() throws NegocioException{
        try {
            List<Membresia> membresias =  fachada.obtenerTodas();
            List<NuevaMembresiaDTO> membresiasDTO = new LinkedList<>();
            for(Membresia m: membresias){
                NuevaMembresiaDTO membresiaDTO = DtosAEntidadesAdapter.adaptarMembresiaEntidad(m);
                membresiasDTO.add(membresiaDTO);
            }
            return membresiasDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener las membresias",ex);
        }
    }

    @Override
    public NuevaMembresiaDTO obtenerPorId(Long id) throws NegocioException{
        try {
            Membresia membresiaEntidad = fachada.obtenerPorId(id);
            NuevaMembresiaDTO membresia = DtosAEntidadesAdapter.adaptarMembresiaEntidad(membresiaEntidad);
            return membresia;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener membresia por ID.",ex);
        }
    }
}
