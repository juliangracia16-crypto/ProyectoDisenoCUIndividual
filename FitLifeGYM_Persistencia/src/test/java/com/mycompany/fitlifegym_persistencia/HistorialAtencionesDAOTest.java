
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteAtencionPersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteAtencion;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Julian
 */
public class HistorialAtencionesDAOTest {
    private IHistorialAtencionesDAO dao;
    
    public HistorialAtencionesDAOTest() {
    }
    
    @BeforeEach
    public void init(){
        this.dao = new HistorialAtencionesDAO();
    }
    
    @Test
    public void testConsultarTodosLosReportesDeAtencionFuncionaOk() {
        int reportesEsperados = 4;
        assertDoesNotThrow( ()-> {
            List<ReporteAtencionPersistenciaDTO> reportes = dao.consultarReportesAtencion();
            assertNotNull(reportes);
            assertEquals(reportesEsperados, reportes.size());
        });
    }
    
    @Test
    public void testConsultarReportesDeAtencionPorFolioFuncionaOk() {
        String asuntoEsperado = "Prueba que la imagen lleva id";
        String folio = "LPUHA";
        assertDoesNotThrow( ()-> {
            ReporteAtencionPersistenciaDTO reporte = dao.consultarReporteAtencionPorFolio(folio);
            assertNotNull(reporte);
            assertEquals(asuntoEsperado, reporte.getAsunto());
        });
    }
    
    @Test
    public void testConsultarReportesDeAtencionFiltradoPorNombreClienteFuncionaOk() {
        int reportesEsperados = 3;
        FiltrosConsultaHistorialReportesDTO filtros = new FiltrosConsultaHistorialReportesDTO("Ju",null,null,null,null);
        assertDoesNotThrow( ()-> {
            List<ReporteAtencionPersistenciaDTO> reportes = dao.consultarReportesAtencionesFiltros(filtros);
            assertNotNull(reportes);
            assertEquals(reportesEsperados, reportes.size());
        });
    }
    
    @Test
    public void testConsultarReportesDeAtencionFiltradoPorFechaDesdeFuncionaOk() {
        int reportesEsperados = 4;
        FiltrosConsultaHistorialReportesDTO filtros = new FiltrosConsultaHistorialReportesDTO(null,null,null,LocalDate.of(2026, Month.MAY, 14),null);
        assertDoesNotThrow( ()-> {
            List<ReporteAtencionPersistenciaDTO> reportes = dao.consultarReportesAtencionesFiltros(filtros);
            assertNotNull(reportes);
            assertEquals(reportesEsperados, reportes.size());
        });
    }
    
    @Test
    public void testConsultarReportesDeAtencionFiltradoPorFechaHastaFuncionaOk() {
        int reportesEsperados = 3;
        FiltrosConsultaHistorialReportesDTO filtros = new FiltrosConsultaHistorialReportesDTO(null,null,null,null,LocalDate.of(2026, Month.MAY, 14));
        assertDoesNotThrow( ()-> {
            List<ReporteAtencionPersistenciaDTO> reportes = dao.consultarReportesAtencionesFiltros(filtros);
            assertNotNull(reportes);
            assertEquals(reportesEsperados, reportes.size());
        });
    }
    
    @Test
    public void testConsultarReportesDeAtencionFiltradoPorEstadoFuncionaOk() {
        int reportesEsperados = 0;
        EstadoReporte estadoFiltro = new EstadoReporte("SIN RESOLVER");
        FiltrosConsultaHistorialReportesDTO filtros = new FiltrosConsultaHistorialReportesDTO(null,estadoFiltro,null,null,null);
        assertDoesNotThrow( ()-> {
            List<ReporteAtencionPersistenciaDTO> reportes = dao.consultarReportesAtencionesFiltros(filtros);
            assertNotNull(reportes);
            assertEquals(reportesEsperados, reportes.size());
        });
    }
    
    @Test
    public void testConsultarReportesDeAtencionFiltradoPorCategoriaFuncionaOk() {
        int reportesEsperados = 1;
        Categoria categoria = new Categoria("QUEJA");
        FiltrosConsultaHistorialReportesDTO filtros = new FiltrosConsultaHistorialReportesDTO(null,null,categoria,null,null);
        assertDoesNotThrow( ()-> {
            List<ReporteAtencionPersistenciaDTO> reportes = dao.consultarReportesAtencionesFiltros(filtros);
            assertNotNull(reportes);
            assertEquals(reportesEsperados, reportes.size());
        });
    }
    
    @Test
    public void testConsultarReportesDeAtencionConTodosLosFiltrosFuncionaOk() {
        int reportesEsperados = 2;
        Categoria categoria = new Categoria("SUGERENCIA");
        EstadoReporte estadoFiltro = new EstadoReporte("RESUELTO");
        FiltrosConsultaHistorialReportesDTO filtros = new FiltrosConsultaHistorialReportesDTO("Ju",estadoFiltro,categoria,LocalDate.of(2026, Month.MAY, 14),LocalDate.of(2026, Month.MAY, 15));
        assertDoesNotThrow( ()-> {
            List<ReporteAtencionPersistenciaDTO> reportes = dao.consultarReportesAtencionesFiltros(filtros);
            assertNotNull(reportes);
            assertEquals(reportesEsperados, reportes.size());
        });
    }
    
    @Test
    public void testResolverReporteDeAtencionFuncionaOk(){
        EstadoReporte estadoFiltro = new EstadoReporte("RESUELTO");
        
        ReporteAtencion reporte  = new ReporteAtencion(
            "A07L7",
            "Manteniminto a la maquina",
            LocalDate.of(2026, Month.MAY, 16),
            estadoFiltro,
            "6a001c699b03705600b6e1c2",
            null,
            "6a011ad99b03705600b6e1d1"
        );
        assertDoesNotThrow( ()-> {
            ReporteAtencion reporteGenerado = dao.resolverReporte(reporte);
            assertNotNull(reporteGenerado.getId());
        });
    }
}
