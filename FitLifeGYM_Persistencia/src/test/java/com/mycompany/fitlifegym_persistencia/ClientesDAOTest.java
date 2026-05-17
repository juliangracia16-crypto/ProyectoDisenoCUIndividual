
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.entidades.Cliente;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Julian
 */
public class ClientesDAOTest {
    private IClientesDAO dao;
    
    public ClientesDAOTest() {
    }
    
    @BeforeEach
    public void init(){
        this.dao = new ClientesDAO();
    }
    
    @Test
    public void testConsultarClientePorPinFuncionaOk() {
        String pin = "2509";
        String nombreEsperado = "Julian";
        assertDoesNotThrow(() -> {
            Cliente cliente = dao.buscarPorPin(pin);
            assertNotNull(cliente.getIdCliente());
            assertEquals(nombreEsperado, cliente.getNombre());
        });
    }
    
    @Test
    public void testConsultarClientePorIdFuncionaOk() {
        String pin = "69ffcefb9b03705600b6e1c0";
        String nombreEsperado = "Julian";
        assertDoesNotThrow(() -> {
            Cliente cliente = dao.consultarClientePorId(pin);
            assertNotNull(cliente.getIdCliente());
            assertEquals(nombreEsperado, cliente.getNombre());
        });
    }
    
    @Test
    public void testConsultarTodosLosClientesFuncionaOk() {
        int clientesEsperados = 2;
        assertDoesNotThrow(() -> {
            List<Cliente> clientes = dao.consultarClientes();
            assertNotNull(clientes);
            assertEquals(clientesEsperados, clientes.size());
        });
    }
    
}
