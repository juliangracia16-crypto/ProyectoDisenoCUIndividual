//
//package com.mycompany.fitlifegym_persistencia;
//
//import com.mycompany.fitlifegym_persistencia.entidades.Administrador;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeEach;
//
///**
// *
// * @author Julian
// */
//public class AdministradorDAOTest {
//    private IAdministradorDAO dao;
//    
//    public AdministradorDAOTest() {
//    }
//    
//    @BeforeEach()
//    public void init() {
//        this.dao = new AdministradorDAO();
//    }
//    
//    @Test
//    public void testConsultarAdministradorPorUsuarioFuncionaOk() {
//        String usuario = "kike16";
//        String nombreEsperado = "Enrique";
//        assertDoesNotThrow(() -> {
//            Administrador administrador = dao.consultarAdministradorPorUsuario(usuario);
//            assertNotNull(administrador.getIdAdministrador());
//            assertEquals(nombreEsperado, administrador.getNombre());
//        });
//    }
//    
//    @Test
//    public void testConsultarAdministradorPorIdFuncionaOk() {
//        String id = "6a0770895eb8bf4e3e7f7959";
//        String nombreEsperado = "Enrique";
//        assertDoesNotThrow(() -> {
//            Administrador administrador = dao.consultarAdministradorPorId(id);
//            assertNotNull(administrador.getIdAdministrador());
//            assertEquals(nombreEsperado, administrador.getNombre());
//        });
//    }
//    
//}
