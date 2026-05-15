
package com.mycompany.fitlifegym_persistencia;

import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import static com.mycompany.fitlifegym_persistencia.ManejadorConexiones.obtenerCodecs;
import com.mycompany.fitlifegym_persistencia.dtos.FiltrosConsultaHistorialReportesDTO;
import com.mycompany.fitlifegym_persistencia.dtos.ReporteIncidentePersistenciaDTO;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteIncidente;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Julian
 */
public class HistorialIncidentesDAO implements IHistorialIncidentesDAO, IBaseMongoDAO{
    
    private static final String NOMBRE_COLECCION = "historial_incidentes"; 
    private static final String FOLIO_KEY = "folio";
    
    @Override
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
        MongoDatabase empresaBD = cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
        return empresaBD;
    }

    @Override
    public MongoCollection obtenerColeccion(MongoDatabase baseDatos) {
        MongoCollection<ReporteIncidente> coleccion = baseDatos.getCollection(NOMBRE_COLECCION,ReporteIncidente.class);
        return coleccion;
    }
    
    @Override
    public List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentes() throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {

            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Document> coleccion = empresaBD.getCollection(NOMBRE_COLECCION);
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
                    Aggregates.unwind("$cliente"),
                    Aggregates.unwind("$categoria")
            );
            AggregateIterable<ReporteIncidentePersistenciaDTO> resultados = coleccion.aggregate(pipeline, ReporteIncidentePersistenciaDTO.class);
            List<ReporteIncidentePersistenciaDTO> reportes = new ArrayList<>();
            resultados.into(reportes);
            return reportes;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar todos los reportes de incidentes.", ex);
        }
    }

    @Override
    public ReporteIncidentePersistenciaDTO consultarReporteIncidentePorFolio(String folio) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Document> coleccion = empresaBD.getCollection(NOMBRE_COLECCION);
            List<Bson> pipeline = new ArrayList<>();
            pipeline.add(
                    Aggregates.match(
                            Filters.eq(FOLIO_KEY, folio)
                    )
            );
            pipeline.add(
                    Aggregates.lookup(
                            "clientes",
                            "idCliente",
                            "_id",
                            "cliente"
                    )
            );
            pipeline.add(
                    Aggregates.lookup(
                            "categorias",
                            "idCategoria",
                            "_id",
                            "categoria"
                    )
            );
            pipeline.add(Aggregates.unwind("$cliente"));
            pipeline.add(Aggregates.unwind("$categoria"));
            ReporteIncidentePersistenciaDTO reporteIncidente = coleccion.aggregate(pipeline, ReporteIncidentePersistenciaDTO.class).first();
            if(reporteIncidente == null){
                throw new PersistenciaException("No se encontro ningun reporte con ese folio.");
            }
            return reporteIncidente;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar el reportes de incidente por folio.", ex);
        }    
    }

    @Override
    public ReporteIncidente generarReporteIncidente(ReporteIncidente reporteIncidente) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<ReporteIncidente> coleccion = this.obtenerColeccion(empresaBD);
            InsertOneResult resultado = coleccion.insertOne(reporteIncidente);
            
            if(!resultado.wasAcknowledged()){
                throw new PersistenciaException("No se pudo registrar correctamente el reporte de incidente.");
            }
            if (resultado.getInsertedId() != null) {
                reporteIncidente.setId(
                    resultado.getInsertedId()
                    .asObjectId()
                    .getValue()
                    .toHexString()
                );
            }
            return reporteIncidente;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al guardar el reportes de incidente.", ex);
        }
    }

    @Override
    public List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentesFiltros(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {

            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Document> coleccion = empresaBD.getCollection(NOMBRE_COLECCION);

            List<Bson> pipeline = new ArrayList<>();
            List<Bson> filtrosBusqueda = new ArrayList<>();

            if (filtros.cliente() != null && !filtros.cliente().isBlank()) {
                filtrosBusqueda.add(Filters.eq("idCliente", new ObjectId(filtros.cliente())));
            }

            if (filtros.fechaDesde() != null) {
                filtrosBusqueda.add(Filters.gte("fecha", filtros.fechaDesde()));
            }

            if (filtros.fechaHasta() != null) {
                filtrosBusqueda.add(Filters.lte("fecha", filtros.fechaHasta()));
            }

            if (!filtrosBusqueda.isEmpty()) {
                pipeline.add(Aggregates.match(Filters.and(filtrosBusqueda)));
            }

            pipeline.add(
                    Aggregates.lookup(
                            "clientes",
                            "idCliente",
                            "_id",
                            "cliente"
                    )
            );
            pipeline.add(
                    Aggregates.lookup(
                            "categorias",
                            "idCategoria",
                            "_id",
                            "categoria"
                    )
            );
            pipeline.add(Aggregates.unwind("$cliente"));
            pipeline.add(Aggregates.unwind("$categoria"));
            
            if (filtros.categoria() != null && filtros.categoria().getCategoria() != null && !filtros.categoria().getCategoria().isBlank()) {
                pipeline.add(
                        Aggregates.match(
                                Filters.eq(
                                        "categoria.nombre",
                                        filtros.categoria().getCategoria()
                                )
                        )
                );
            }
            if (filtros.estado() != null && filtros.estado().getEstado() != null && !filtros.estado().getEstado().isBlank()) {
                pipeline.add(
                        Aggregates.match(
                                Filters.eq(
                                        "estadoReporte.nombre",
                                        filtros.estado().getEstado()
                                )
                        )
                );
            }

            AggregateIterable<ReporteIncidentePersistenciaDTO> resultados  = coleccion.aggregate(pipeline,ReporteIncidentePersistenciaDTO.class);
            List<ReporteIncidentePersistenciaDTO> reportes = new ArrayList<>();
            resultados.into(reportes);

            return reportes;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar reportes de incidentes por filtros.", ex);
        }
    }

    @Override
    public ReporteIncidente actualizarEstadoReporteIncidente(ReporteIncidente reporteIncidente) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {

            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<ReporteIncidente> coleccion = this.obtenerColeccion(empresaBD);
            UpdateResult resultado = coleccion.updateOne(
                Filters.eq("_id",new ObjectId(reporteIncidente.getId())),
                    Updates.set(
                       "estadoReporte",
                        reporteIncidente.getEstadoReporte()
                    )
            );

            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaException("No se encontró el reporte incidente.");
            }

            if (resultado.getModifiedCount() == 0) {
                throw new PersistenciaException("El estado no se pudo actualizar.");
            }
            return reporteIncidente;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al guardar el reporte de incidente.", ex);
        }
    }
    
}
