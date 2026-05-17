
package com.mycompany.fitlifegym_persistencia;

import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteIncidentePersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.Categoria;
import com.mycompany.fitlifegym_persistencia.entidades.EstadoReporte;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteIncidente;
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
public class HistorialIncidentesDAOTest {
    private IHistorialIncidentesDAO dao;
    public HistorialIncidentesDAOTest() {
    }
    
    @BeforeEach
    public void init(){
        this.dao = new HistorialIncidentesDAO();
    }
    
    @Test
    public void testConsultarTodosLosReportesDeIncidentesFuncionaOk() {
        int reportesEsperados = 6;
        assertDoesNotThrow(()->{
            List<ReporteIncidentePersistenciaDTO> reportes = dao.consultarReportesIncidentes();
            assertNotNull(reportes);
            assertEquals(reportesEsperados, reportes.size());
        });
    }
    
    @Test
    public void testConsultarReportesDeAtencionPorFolioFuncionaOk() {
        String asuntoEsperado = "Prueba que la imagen lleva id";
        String folio = "LPUHA";
        assertDoesNotThrow( ()-> {
            ReporteIncidentePersistenciaDTO reporte = dao.consultarReporteIncidentePorFolio(folio);
            assertNotNull(reporte);
            assertEquals(asuntoEsperado, reporte.getAsunto());
        });
    }
    
    @Test
    public void testConsultarReportesDeAtencionFiltradoPorFechaDesdeFuncionaOk() {
        int reportesEsperados = 6;
        FiltrosConsultaHistorialReportesDTO filtros = new FiltrosConsultaHistorialReportesDTO(null, null, null, LocalDate.of(2026, Month.MAY, 14), null);
        assertDoesNotThrow(() -> {
            List<ReporteIncidentePersistenciaDTO> reportes = dao.consultarReportesIncidentesFiltros(filtros);
            assertNotNull(reportes);
            assertEquals(reportesEsperados, reportes.size());
        });
    }

    @Test
    public void testConsultarReportesDeAtencionFiltradoPorFechaHastaFuncionaOk() {
        int reportesEsperados = 3;
        FiltrosConsultaHistorialReportesDTO filtros = new FiltrosConsultaHistorialReportesDTO(null, null, null, null, LocalDate.of(2026, Month.MAY, 14));
        assertDoesNotThrow(() -> {
            List<ReporteIncidentePersistenciaDTO> reportes = dao.consultarReportesIncidentesFiltros(filtros);
            assertNotNull(reportes);
            assertEquals(reportesEsperados, reportes.size());
        });
    }

    @Test
    public void testConsultarReportesDeAtencionFiltradoPorEstadoFuncionaOk() {
        int reportesEsperados = 2;
        EstadoReporte estadoFiltro = new EstadoReporte("SIN RESOLVER");
        FiltrosConsultaHistorialReportesDTO filtros = new FiltrosConsultaHistorialReportesDTO(null, estadoFiltro, null, null, null);
        assertDoesNotThrow(() -> {
            List<ReporteIncidentePersistenciaDTO> reportes = dao.consultarReportesIncidentesFiltros(filtros);
            assertNotNull(reportes);
            assertEquals(reportesEsperados, reportes.size());
        });
    }

    @Test
    public void testConsultarReportesDeAtencionFiltradoPorCategoriaFuncionaOk() {
        int reportesEsperados = 2;
        Categoria categoria = new Categoria("QUEJA");
        FiltrosConsultaHistorialReportesDTO filtros = new FiltrosConsultaHistorialReportesDTO(null, null, categoria, null, null);
        assertDoesNotThrow(() -> {
            List<ReporteIncidentePersistenciaDTO> reportes = dao.consultarReportesIncidentesFiltros(filtros);
            assertNotNull(reportes);
            assertEquals(reportesEsperados, reportes.size());
        });
    }

    @Test
    public void testConsultarReportesDeAtencionConTodosLosFiltrosFuncionaOk() {
        int reportesEsperados = 2;
        Categoria categoria = new Categoria("SUGERENCIA");
        EstadoReporte estadoFiltro = new EstadoReporte("RESUELTO");
        FiltrosConsultaHistorialReportesDTO filtros = new FiltrosConsultaHistorialReportesDTO("Ju", estadoFiltro, categoria, LocalDate.of(2026, Month.MAY, 14), LocalDate.of(2026, Month.MAY, 15));
        assertDoesNotThrow(() -> {
            List<ReporteIncidentePersistenciaDTO> reportes = dao.consultarReportesIncidentesFiltros(filtros);
            assertNotNull(reportes);
            assertEquals(reportesEsperados, reportes.size());
        });
    }
    
    @Test
    public void testGenerarReporteIncidenteFuncionaOk(){
        EstadoReporte estado = new EstadoReporte("SIN RESOLVER");
        ReporteIncidente reporteIncidente = new ReporteIncidente(
                "Prueba unitaria del metodo",
                "Esto es una prueba para probar el metodo desde pruebas unitarias",
                LocalDate.now(),
                estado,
                "6a001c7d9b03705600b6e1c3",
                null,
                "6a011ad99b03705600b6e1d1"
        );
        assertDoesNotThrow(()->{
            ReporteIncidente reporte = dao.generarReporteIncidente(reporteIncidente);
            assertNotNull(reporte.getId());
        });
    }
    
    @Test
    public void testActualizarEstadoReporteIncidenteFuncionaOk(){
        EstadoReporte estado = new EstadoReporte("RESUELTO");
        ReporteIncidente reporteIncidente = new ReporteIncidente(
                "6a09629f6c22cfcde14f36a6",
                "9L8ER",
                "Prueba unitaria del metodo",
                "Esto es una prueba para probar el metodo desde pruebas unitarias",
                LocalDate.now(),
                estado,
                "6a001c7d9b03705600b6e1c3",
                null,
                "6a011ad99b03705600b6e1d1"
        );
        assertDoesNotThrow(()->{
            ReporteIncidente reporte = dao.actualizarEstadoReporteIncidente(reporteIncidente);
            assertNotNull(reporte);
            assertEquals(reporteIncidente.getEstadoReporte().getEstado(),reporte.getEstadoReporte().getEstado());
        });
    }
}
