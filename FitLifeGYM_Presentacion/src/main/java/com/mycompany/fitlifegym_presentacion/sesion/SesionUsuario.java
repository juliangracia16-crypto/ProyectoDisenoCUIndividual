
package com.mycompany.fitlifegym_presentacion.sesion;

import com.mycompany.fitlifegym_dtos.ClienteLogueadoDTO;

/**
 *
 * @author Julian
 */
public class SesionUsuario {
    /**
     * Instancia única del singleton.
     */
    private static SesionUsuario instancia;

    /**
     * Cliente autenticado actualmente.
     */
    private ClienteLogueadoDTO clienteActual;

    /**
     * Constructor privado.
     */
    private SesionUsuario() {
    }

    /**
     * Regresa la instancia única.
     *
     * @return Instancia de SesionUsuario.
     */
    public static SesionUsuario getInstancia() {

        if (instancia == null) {
            instancia = new SesionUsuario();
        }

        return instancia;
    }

    /**
     * Inicia la sesión del cliente.
     *
     * @param cliente Cliente autenticado.
     */
    public void iniciarSesion(ClienteLogueadoDTO cliente) {

        this.clienteActual = cliente;
    }

    /**
     * Cierra la sesión actual.
     */
    public void cerrarSesion() {
        clienteActual = null;
    }

    /**
     * Regresa el cliente autenticado.
     *
     * @return Cliente actual.
     */
    public ClienteLogueadoDTO getClienteActual() {
        return clienteActual;
    }

    /**
     * Indica si existe una sesión activa.
     *
     * @return true si hay sesión activa.
     */
    public boolean haySesionActiva() {
        return clienteActual != null;
    }
}
