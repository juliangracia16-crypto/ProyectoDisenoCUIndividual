/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaDTO;
import com.mycompany.fitlifegym_persistencia.IMembresiaDAO;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.entidades.Membresia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class MembresiaBO implements IMembresiaBO {

    private IMembresiaDAO membresiaDAO;
   

    public MembresiaBO(IMembresiaDAO membresiaDAO) {
        this.membresiaDAO = membresiaDAO;
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
            membresiaDAO.guardar(membresia);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al guardar la membresia.",ex);
        }
    }

    @Override
    public List<Membresia> obtenerTodas() throws NegocioException{
        try {
            return membresiaDAO.obtenerTodas();
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener las membresias",ex);
        }
    }

    @Override
    public Membresia obtenerPorId(Long id) throws NegocioException{

        if(id == null){
            throw new NegocioException("El ID no puede ser nulo.");
        }

        try {
            return membresiaDAO.obtenerPorId(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener membresia por ID.",ex);
        }
    }
}
