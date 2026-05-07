package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.entidades.Membresia;
import com.mycompany.fitlifegym_persistencia.entidades.TipoMembresia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julian
 */
public class MembresiaListDAO implements IMembresiaDAO {

    private static List<Membresia> membresias = new ArrayList<>();
    private static Long contadorID = 4L;
    
    static {
        membresias.add(new Membresia(1L, TipoMembresia.BRONCE, 300.0, LocalDate.now().plusMonths(1)));
        membresias.add(new Membresia(2L, TipoMembresia.PLATA,  500.0, LocalDate.now().plusMonths(1)));
        membresias.add(new Membresia(3L, TipoMembresia.ORO,    750.0, LocalDate.now().plusMonths(1)));
    }

    @Override
    public Membresia guardar(Membresia membresia) {
        membresia.setIdMembresia(contadorID);
        contadorID++;
        this.membresias.add(membresia);
        return membresia;
    }

    @Override
    public List<Membresia> obtenerTodas() {
        return this.membresias;
    }

    @Override
    public Membresia obtenerPorId(Long id) {
        return this.membresias.stream()
                .filter(m -> m.getIdMembresia().equals(id))
                .findFirst()
                .orElse(null);
    }

}
