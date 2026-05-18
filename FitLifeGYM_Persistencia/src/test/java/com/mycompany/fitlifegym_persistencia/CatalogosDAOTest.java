//
//package com.mycompany.fitlifegym_persistencia;
//
//import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
//import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeEach;
//
///**
// * Pruebas unitarias para los metodos de CatalogosDAO
// * @author Julian
// */
//public class CatalogosDAOTest {
//    private ICatalogosDAO dao;
//    
//    public CatalogosDAOTest() {
//    }
//    
//    @BeforeEach()
//    public void init(){
//        this.dao = new CatalogosDAO();
//    }
//    
//    @Test
//    public void testConsultarTodasLasCategoriasFuncionaOk() {
//        int categoriasEsperadas = 2;
//        assertDoesNotThrow(()->{
//            List<Categoria> categorias = dao.consultarCatalogoCategorias();
//            assertNotNull(categorias);
//            assertEquals(categoriasEsperadas, categorias.size());
//        });
//    }
//    
//    @Test
//    public void testConsultarTodosLosEstadosFuncionaOk() {
//        int estadosEsperados = 2;
//        assertDoesNotThrow(()->{
//            List<EstadoReporte> estados = dao.consultarCatalogoEstados();
//            assertNotNull(estados);
//            assertEquals(estadosEsperados, estados.size());
//        });
//    }
//    
//    @Test
//    public void testConsultarEstadoPorNombreFuncionaOk(){
//        String nombreEstado = "RESUELTO";
//        assertDoesNotThrow(() -> {
//            EstadoReporte estado = dao.consultarEstadoPorNombre(nombreEstado);
//            assertNotNull(estado.getId());
//            assertEquals(nombreEstado, estado.getEstado());
//        });
//    }
//    
//    @Test
//    public void testConsultarCategoriaPorNombreFuncionaOk(){
//        String nombreCategoria = "QUEJA";
//        assertDoesNotThrow(() -> {
//            Categoria categoria = dao.consultarCategoriaPorNombre(nombreCategoria);
//            assertNotNull(categoria.getId());
//            assertEquals(nombreCategoria, categoria.getCategoria());
//        });
//    }
//    
//}
