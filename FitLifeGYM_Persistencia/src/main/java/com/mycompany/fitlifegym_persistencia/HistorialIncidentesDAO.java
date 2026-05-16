
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
 * Clase que implementa los metodos de la interfaz IHistorialIncidentesDAO, y la
 * interfaz IBaseMongoDAO para manejar los metodos de conexion a la BD.
 * Utilizando MongoDB como motor para realizar operaciones.
 * @author Julian
 */
public class HistorialIncidentesDAO implements IHistorialIncidentesDAO, IBaseMongoDAO{
    
    private static final String NOMBRE_COLECCION = "historial_incidentes"; 
    private static final String FOLIO_KEY = "folio";
    
    /**
     * Metodo que obtiene la base de datos
     * @param cliente es la conexion para poder acceder a la base de datos
     * @return la base de datos
     */
    @Override
    public MongoDatabase obtenerBaseDatos(MongoClient cliente) {
        MongoDatabase empresaBD = cliente.getDatabase(ManejadorConexiones.BASE_DATOS).withCodecRegistry(obtenerCodecs());
        return empresaBD;
    }
    
    /**
     * Metodo que obtiene la coleccion en la que trabajaremos ( Historial Incidentes )
     * @param baseDatos donde trabajaremos
     * @return la coleccion donde se trabajara
     */
    @Override
    public MongoCollection obtenerColeccion(MongoDatabase baseDatos) {
        MongoCollection<ReporteIncidente> coleccion = baseDatos.getCollection(NOMBRE_COLECCION,ReporteIncidente.class);
        return coleccion;
    }
    
    /**
     * Metodo para consultar todos los reportes de incidentes.
     * Utilizado para mostrarle todos los reportes de incidentes al administrador.
     * @return una lista con todos los reportes de incidentes hasta ese momento
     * @throws PersistenciaException si falla algo en la conexion a la base de datos
     */
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
    
    /**
     * Metodo para consultar un reporte de incidente por su folio
     * @param folio del reporte que se busca
     * @return el reporte de incidente encontrado con ese folio 
     * @throws PersistenciaException si no se encuentra ningun reporte con ese folio
     * o si falla algo en la conexion a la base de datos
     */
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
    
    /**
     * Metodo para guardar un reporte de incidente generado por un cliente.
     * @param reporteIncidente que se quiere guardar
     * @return el reporte registrado con su ID generado en la base de datos
     * @throws PersistenciaException si no se puede guardar correctamente el reporte
     * o si falla algo en la conexion de la base de datos
     */
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

    /**
     * Metodo para consultar los reportes de incidentes utilizando filtros.
     * Este metodo es utilizado para cuando se consulta desde la vista de administrador.
     * @param filtros para buscar reportes ( Por: nombre del cliente, fecha desde, fecha hasta, categoria o estado)
     * @return una lista con los reportes que coincidan con los filtros
     * @throws PersistenciaException si falla algo al momento de la conexion
     * a la base de datos
     */
    @Override
    public List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentesFiltros(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {

            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<Document> coleccion = empresaBD.getCollection(NOMBRE_COLECCION);

            List<Bson> pipeline = new ArrayList<>();
            List<Bson> filtrosBusqueda = new ArrayList<>();
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
            if (filtros.cliente() != null && !filtros.cliente().isBlank()) {
                pipeline.add(
                        Aggregates.match(
                                Filters.regex(
                                        "cliente.nombre",
                                        filtros.cliente(),
                                        "i"
                                )
                        )
                );
            }
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
    
    /**
     * Metodo que actualiza el estado de un reporte de incidente cuando este ya ha sido resuelto.
     * Este metodo se utiliza en la vista de administrador al momento de resolver un reporte.
     * @param reporteIncidente que contiene el estado al que se cambiara el reporte de incidente
     * @return el reporte incidente con su estado actualizado
     * @throws PersistenciaException si no se encuentra el reporte, si no se puede actualizar
     * o si falla algo al momento de conexion a la base de datos
     */
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
    
    /**
     * Metodo para consultar reportes de incidentes filtrados por cliente.
     * Este metodo se utiliza en vista usuario cuando el cliente quiere ver 
     * los reportes que el ha generado.
     * @param filtros para buscar reportes ( Por: fecha desde, fecha hasta, categoria o estado)
     * @return una lista con los reportes que coincidan con los filtros y sean del cliente que busco.
     * @throws PersistenciaException si falla algo al momento de conexion a la base de datos.
     */
    @Override
    public List<ReporteIncidentePersistenciaDTO> consultarReportesIncidentesPorCliente(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException {
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

                AggregateIterable<ReporteIncidentePersistenciaDTO> resultados = coleccion.aggregate(pipeline, ReporteIncidentePersistenciaDTO.class);
                List<ReporteIncidentePersistenciaDTO> reportes = new ArrayList<>();
                resultados.into(reportes);
                return reportes;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar todos los reportes de incidentes del cliente.", ex);
        }
    }
    
}
