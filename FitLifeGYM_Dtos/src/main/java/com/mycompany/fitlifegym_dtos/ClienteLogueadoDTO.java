
package com.mycompany.fitlifegym_dtos;

/**
 *
 * @author PC GAMER MASTER RACE
 */
public class ClienteLogueadoDTO {

    private final String idCliente;

    private final String nombre;
    private  String apellido;
    private  TipoMembresiaDTO membresiaActiva;

    private  EstadoDTO estadoMembresia;

    public ClienteLogueadoDTO(String idCliente, String nombre, String apellido, TipoMembresiaDTO membresiaActiva, EstadoDTO estadoMembresia) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.membresiaActiva = membresiaActiva;
        this.estadoMembresia = estadoMembresia;
    }
    
    public ClienteLogueadoDTO(String idCliente, String nombre, String apellido){
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public ClienteLogueadoDTO(String idCliente, String nombre){
        this.idCliente = idCliente;
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getNombreCompleto() {
        return nombre;
    }

    public TipoMembresiaDTO getMembresiaActiva() {
        return membresiaActiva;
    }

    public EstadoDTO getEstadoMembresia() {
        return estadoMembresia;
    }

    

}
