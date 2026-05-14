
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
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Julian
 */
public class HistorialAtencionesDAO implements IHistorialAtencionesDAO, IBaseMongoDAO{
    private static final String NOMBRE_COLECCION = "historial_atenciones"; 
    private static final String FOLIO_KEY = "folio";
    
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
    public ReporteAtencionPersistenciaDTO consultarReporteAtencionPorFolio(String folio) throws PersistenciaException {
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
            pipeline.add(
                    Aggregates.lookup(
                            "estados_reportes",
                            "idEstado",
                            "_id",
                            "estado"
                    )
            );
            pipeline.add(
                    Aggregates.lookup(
                            "imagenes",
                            "idImagen",
                            "_id",
                            "imagen"
                    )
            );
            pipeline.add(Aggregates.unwind("$cliente"));
            pipeline.add(Aggregates.unwind("$categoria"));
            pipeline.add(Aggregates.unwind("$estado"));
            pipeline.add(Aggregates.unwind("$imagen", new UnwindOptions().preserveNullAndEmptyArrays(true)));

            ReporteAtencionPersistenciaDTO reporteIncidente = coleccion.aggregate(pipeline, ReporteAtencionPersistenciaDTO.class).first();
            if (reporteIncidente == null) {
                throw new PersistenciaException("No se encontro ningun reporte con ese folio.");
            }
            return reporteIncidente;
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
    public List<ReporteAtencionPersistenciaDTO> consultarReportesAtencionesFiltros(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
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
            pipeline.add(
                    Aggregates.lookup(
                            "estados_reportes",
                            "idEstado",
                            "_id",
                            "estado"
                    )
            );
            pipeline.add(
                    Aggregates.lookup(
                            "imagenes",
                            "idImagen",
                            "_id",
                            "imagen"
                    )
            );
            pipeline.add(Aggregates.unwind("$cliente"));
            pipeline.add(Aggregates.unwind("$categoria"));
            pipeline.add(Aggregates.unwind("$estado"));
            pipeline.add(Aggregates.unwind("$imagen", new UnwindOptions().preserveNullAndEmptyArrays(true)));
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
                                        "estado.nombre",
                                        filtros.estado().getEstado()
                                )
                        )
                );
            }
            AggregateIterable<ReporteAtencionPersistenciaDTO> resultados = coleccion.aggregate(pipeline, ReporteAtencionPersistenciaDTO.class);
            List<ReporteAtencionPersistenciaDTO> reportes = new ArrayList<>();
            resultados.into(reportes);

            return reportes;
        }
    }
 
}
