/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitlifegym_negocio;

import Adapter.DtosAEntidadesAdapter;
import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;
import com.mycompany.fitlifegym_dtos.EstadoDTO;
import com.mycompany.fitlifegym_dtos.LoginDTO;
import com.mycompany.fitlifegym_dtos.TipoMembresiaDTO;
import com.mycompany.fitlifegym_persistencia.IClientesDAO;
import com.mycompany.fitlifegym_persistencia.PersistenciaException;
import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import com.mycompany.fitlifegym_persistencia.entidades.MembresiaComprada;
import com.mycompany.fitlifegym_persistencia.entidades.TipoMembresia;

/**
 *
 * @author PC GAMER MASTER RACE
 */
public class LoginBO implements ILoginBO {

    private final IClientesDAO clientesDAO;

    public LoginBO(IClientesDAO clientesDAO) {
        this.clientesDAO = clientesDAO;
    }

    @Override
    public ClienteLogueadoDTO iniciarSesion(LoginDTO login) throws NegocioException {
        try {
            Cliente cliente = clientesDAO.buscarPorPin(login.getPin());

            if (cliente == null) {
                return null;
            }

            String nombreCompleto = cliente.getNombre() + " " + cliente.getApellidos();
            TipoMembresiaDTO tipoDTO = null;
            EstadoDTO estadoDTO = EstadoDTO.INACTIVO;

            if (cliente.getMembresíaComprada() != null) {
                estadoDTO = DtosAEntidadesAdapter.adaptarEstadoDTO(cliente.getMembresíaComprada().getEstado());
                if (cliente.getMembresíaComprada().getMembresia() != null) {
                    TipoMembresia tipo = cliente.getMembresíaComprada().getMembresia().getTipoMembresia();
                    if (tipo != null) {
                        tipoDTO = DtosAEntidadesAdapter.adaptarTipoMembresiaDTO(tipo);
                    }
                }
            }

            return new ClienteLogueadoDTO(cliente.getIdCliente(), nombreCompleto, tipoDTO,estadoDTO);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al iniciar sesion", ex);
        }
    }

}
