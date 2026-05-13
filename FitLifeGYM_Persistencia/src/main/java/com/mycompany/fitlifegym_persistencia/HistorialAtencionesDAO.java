
package com.mycompany.fitlifegym_persistencia;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UnwindOptions;
import com.mongodb.client.result.InsertOneResult;
import static com.mycompany.fitlifegym_persistencia.ManejadorConexiones.obtenerCodecs;
import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteAtencionPersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteAtencion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Julian
 */
public class HistorialAtencionesDAO implements IHistorialAtencionesDAO, IBaseMongoDAO{
    private static final String NOMBRE_COLECCION = "historial_atenciones"; 
    private static final String FOLIO_KEY = "folio";
    private static final String NOMBRE_CLIENTE_KEY = "cliente.nombre";
    private static final String FECHA_KEY = "fecha";
    private static final String CATEGORIA_KEY = "categoria.nombre";
    private static final String ESTADO_KEY = "estado.nombre"
            + "";
    
    @Override
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
        MongoDatabase empresaBD = cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
        return empresaBD;
    }

    @Override
    public MongoCollection obtenerColeccion(MongoDatabase baseDatos) {
        MongoCollection<ReporteAtencion> coleccion = baseDatos.getCollection(NOMBRE_COLECCION,ReporteAtencion.class);
        return coleccion;
    }
    
    @Override
    public List<ReporteAtencionPersistenciaDTO> consultarReportesAtencion() throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<ReporteAtencion> coleccion = this.obtenerColeccion(empresaBD);
            List<Bson> pipeline = Arrays.asList(
                    Aggregates.lookup(
                            "clientes",
                            "idCliente",
                            "_id",
                            "cliente"
                    ),
                    Aggregates.lookup(
                            "categorias",
                            "idCategoria",
                            "_id",
                            "categoria"
                    ),
                    Aggregates.lookup(
                            "estados",
                            "idEstado",
                            "_id",
                            "estado"
                    ),
                    Aggregates.lookup(
                            "imagenes",
                            "idImagen",
                            "_id",
                            "imagen"
                    ),
                    Aggregates.unwind("$cliente"),
                    Aggregates.unwind("$categoria"),
                    Aggregates.unwind("$estado"),
                    Aggregates.unwind("$imagen", new UnwindOptions().preserveNullAndEmptyArrays(true))
            );
            AggregateIterable<ReporteAtencionPersistenciaDTO> resultados = coleccion.aggregate(pipeline, ReporteAtencionPersistenciaDTO.class);
            List<ReporteAtencionPersistenciaDTO> reportes
                    = new ArrayList<>();

            resultados.into(reportes);

            return reportes;
        }
    }

    @Override
    public ReporteAtencion consultarReporteAtencionPorId(String id) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<ReporteAtencion> coleccion = this.obtenerColeccion(empresaBD);
            
            Document filtros = new Document().append(FOLIO_KEY,id);
            ReporteAtencion reporteAtencion = coleccion.find(filtros).first();
            if(reporteAtencion == null){
                throw new PersistenciaException("No se encontro ningun reporte de atencion con ese folio.");
            }
            return reporteAtencion;
        }
    }

    @Override
    public ReporteAtencion resolverReporte(ReporteAtencion reporte) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<ReporteAtencion> coleccion = this.obtenerColeccion(empresaBD);
            
            InsertOneResult resultado = coleccion.insertOne(reporte);
            if(!resultado.wasAcknowledged()){
                throw new PersistenciaException("No se pudo guardar el reporte de atencion correctamente.");
            }
            reporte.setId(resultado.getInsertedId().toString());
            return reporte;
        }
    }

    @Override
    public ReporteAtencion eliminarReporteAtencion(String idReporte) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<ReporteAtencion> coleccion = this.obtenerColeccion(empresaBD);
            Document filtros = new Document().append(FOLIO_KEY,idReporte);
            
            ReporteAtencion reporteAtencion = coleccion.findOneAndDelete(filtros);
            if(reporteAtencion == null){
                throw new PersistenciaException("No se pudo eliminar el reporte de atencion correctamente.");
            }
            return reporteAtencion;
        }
    }

    @Override
    public List<ReporteAtencion> consultarReportesAtencionesFiltros(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<ReporteAtencion> coleccion = this.obtenerColeccion(empresaBD);
            
            List<Bson> docFiltros = new ArrayList<>();

            if (filtros.cliente() != null && !filtros.cliente().isBlank()) {
                docFiltros.add(Filters.regex(NOMBRE_CLIENTE_KEY, filtros.cliente(),"i"));
            }

            if (filtros.fechaDesde() != null) {
                docFiltros.add(Filters.gte(FECHA_KEY, filtros.fechaDesde()));
            }

            if (filtros.fechaHasta() != null) {
                docFiltros.add(Filters.lte(FECHA_KEY, filtros.fechaHasta()));
            }

            if (filtros.categoria().getCategoria() != null && !filtros.categoria().getCategoria().isBlank()) {
                docFiltros.add(Filters.eq(CATEGORIA_KEY, filtros.categoria().getCategoria()));
            }

            if (filtros.estado().getEstado() != null && !filtros.estado().getEstado().isBlank()) {
                docFiltros.add(Filters.eq(ESTADO_KEY, filtros.estado().getEstado()));
            }

            Bson consultaFinal;

            if (docFiltros.isEmpty()) {
                consultaFinal = new Document();
            } else {
                consultaFinal = Filters.and(docFiltros);
            }

            return coleccion.find(consultaFinal).into(new ArrayList<>());
        }
    }

    
    
}
