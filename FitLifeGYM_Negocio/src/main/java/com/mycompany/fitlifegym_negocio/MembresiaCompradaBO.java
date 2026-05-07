/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
import com.mycompany.fitlifegym_dtos.NuevaMembresiaCompradaDTO;
import com.mycompany.fitlifegym_persistencia.IMembresiaCompradaDAO;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.entidades.MembresiaComprada;
import java.util.List;

/**
 *
 * @author Diego
 */
public class MembresiaCompradaBO implements IMembresiaCompradaBO {


    private IMembresiaCompradaDAO membresiaComprada;

    private IMembresiaCompradaDAO membresiaCompradaDAO;

    public MembresiaCompradaBO(IMembresiaCompradaDAO membresiaCompradaDAO) {
        this.membresiaCompradaDAO = membresiaCompradaDAO;
    }

    @Override
    public MembresiaComprada guardar(NuevaMembresiaCompradaDTO membresiaCompradaDTO) throws NegocioException {
        if(membresiaCompradaDTO.getMembresia() == null){
            throw new NegocioException("La membresia no puede ser nula.");
        }
        
        if(membresiaCompradaDTO.getFechaInicio() == null){
            throw new NegocioException("La fecha de incio no puede ser nula.");
        }
        
        if(membresiaCompradaDTO.getFechaFin() == null){
            throw new NegocioException("La fecha final no puede ser nula.");
        }
        
        if(membresiaCompradaDTO.getFechaFin().isBefore(membresiaCompradaDTO.getFechaInicio())){
            throw new NegocioException("La fecha fianl no puede ser anterior a la fecha de inicio.");
        }
        
        if(membresiaCompradaDTO.getPrecioPagado() == null || membresiaCompradaDTO.getPrecioPagado()<= 0){
            throw new NegocioException("El precio que se va a pagar debe ser mayor a 0.");
        }
        
        if(membresiaCompradaDTO.getEstado() == null){
            throw new NegocioException("El estado de la membresía no puede ser nula.");
        }
        MembresiaComprada mebresiaComprada = DtosAEntidadesAdapter.adaptarMembresiaCompradaDTO(membresiaCompradaDTO);

        try {
            return membresiaComprada.guardar(mebresiaComprada);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al guardar la membresia comprada.",ex);
        }
    }

    @Override
    public List<MembresiaComprada> obtenerTodas() throws NegocioException {
        try {
            return membresiaComprada.obtenerTodas();
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener todas las membresias compradas.",ex);
        }
    }
}
