package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import com.mycompany.fitlifegym_persistencia.entidades.Estado;
import com.mycompany.fitlifegym_persistencia.entidades.Membresia;
import com.mycompany.fitlifegym_persistencia.entidades.MembresiaComprada;
import com.mycompany.fitlifegym_persistencia.entidades.TipoMembresia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Julian
 */
public class ClientesListDAO implements IClientesDAO {

    private static final Logger LOGGER = Logger.getLogger(ClientesListDAO.class.getName());
    private static List<Cliente> clientes = new ArrayList<>();
    private static Long contadorID = 5L;

    @Override
    public Cliente registrarCliente(Cliente cliente) throws PersistenciaException {
        cliente.setIdCliente(contadorID);
        contadorID++;
        this.clientes.add(cliente);
        return cliente;
    }

    @Override
    public Cliente consultarClientePorId(Long id) throws PersistenciaException {
        return this.clientes.stream()
                .filter(m -> m.getIdCliente().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Cliente> consultarClientes() throws PersistenciaException {
        return this.clientes;
    }

    @Override
    public Cliente buscarPorPin(String pin) throws PersistenciaException {
        for (Cliente c : clientes) {
            if (c.getPin().equals(pin)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void actualizarMembresia(Long idCliente, TipoMembresia nuevaMembresia) throws PersistenciaException {
        try {
            Cliente cliente = consultarClientePorId(idCliente);
            if (cliente != null) {
                double precio;

                if (nuevaMembresia == TipoMembresia.ORO) {
                    precio = 750.0;
                } else if (nuevaMembresia == TipoMembresia.PLATA) {
                    precio = 500.0;
                } else {
                    precio = 300.0;
                }

                MembresiaComprada nuevaCompra = new MembresiaComprada(
                        new Membresia(nuevaMembresia, precio),
                        LocalDate.now(),
                        LocalDate.now().plusMonths(1),
                        precio,
                        Estado.ACTIVO
                );
                cliente.setMembresíaComprada(nuevaCompra);
            }
        } catch (PersistenciaException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al actualizar membresia", ex);
        }
    }

    static {
        clientes.add(new Cliente(
                1L, "Jorge", "Gonzalez", "jorge@gmail.com", "6441234567", "contrasenia",
                LocalDate.of(1995, 5, 20), "1234", new MembresiaComprada(
                new Membresia(TipoMembresia.ORO, 750.0), LocalDate.now(),
                LocalDate.now().plusMonths(1), 750.0, Estado.ACTIVO)));

        clientes.add(new Cliente(
                2L, "Maria", "Lopez", "maria@gmail.com", "6449876543", "contrasenia",
                LocalDate.of(1990, 10, 15), "5678",
                new MembresiaComprada(new Membresia(TipoMembresia.PLATA, 500.0),
                        LocalDate.now(), LocalDate.now().plusMonths(1),
                        500.0, Estado.ACTIVO)));

        clientes.add(new Cliente(
                3L, "Carlos", "Ramirez", "carlos@gmail.com", "6441112233", "contrasenia",
                LocalDate.of(1985, 2, 10), "9999",
                new MembresiaComprada(new Membresia(TipoMembresia.BRONCE, 300.0),
                        LocalDate.now(), LocalDate.now().plusMonths(1), 300.0, Estado.ACTIVO)));

        clientes.add(new Cliente(
                4L, "Ana", "Torres", "ana@gmail.com", "6445554433", "contrasenia",
                LocalDate.of(2000, 1, 1), "1111", null));
    }
}
