
package com.mycompany.fitlifegym_persistencia;

import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
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

/**
 * Clase que implementa los metodos de la interfaz IHistorialAtencionessDAO, y la
 * interfaz IBaseMongoDAO para manejar los metodos de conexion a la BD.
 * Utilizando MongoDB como motor para realizar operaciones.
 * @author Julian
 */
public class HistorialAtencionesDAO implements IHistorialAtencionesDAO, IBaseMongoDAO{
    private static final String NOMBRE_COLECCION = "historial_atenciones"; 
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
     * Metodo que obtiene la coleccion en la que trabajaremos ( Historial Atenciones )
     * @param baseDatos donde trabajaremos
     * @return la coleccion donde se trabajara
     */
    @Override
    public MongoCollection obtenerColeccion(MongoDatabase baseDatos) {
        MongoCollection<ReporteAtencion> coleccion = baseDatos.getCollection(NOMBRE_COLECCION,ReporteAtencion.class);
        return coleccion;
    }
    
    /**
     * Metodo para consultar todos los reportes de atencion.
     * Se utiliza en vista de administrador para ver los reportes ya resueltos.
     * @return una lista con todos los reportes de atencion hasta el momento de la consulta
     * @throws PersistenciaException si falla algo al momento de conexion a la base de datos
     */
    @Override
    public List<ReporteAtencionPersistenciaDTO> consultarReportesAtencion() throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
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
            AggregateIterable<ReporteAtencionPersistenciaDTO> resultados = coleccion.aggregate(pipeline, ReporteAtencionPersistenciaDTO.class);
            List<ReporteAtencionPersistenciaDTO> reportes
                    = new ArrayList<>();

            resultados.into(reportes);

            return reportes;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar todos los reportes de atenciones.", ex);
        }
    }
    
    /**
     * Meetodo para consultar un reporte de atencion mediante su folio
     * @param folio del reporte que buscamos
     * @return el reporte atencion que se encuentre con ese folio
     * @throws PersistenciaException si no se encuentra ningun reporte con ese folio
     * o si falla algo al momento de la conexion a la base de datos
     */
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
            pipeline.add(Aggregates.unwind("$cliente"));
            pipeline.add(Aggregates.unwind("$categoria"));

            ReporteAtencionPersistenciaDTO reporteIncidente = coleccion.aggregate(pipeline, ReporteAtencionPersistenciaDTO.class).first();
            if (reporteIncidente == null) {
                throw new PersistenciaException("No se encontro ningun reporte con ese folio.");
            }
            return reporteIncidente;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar el reporte de atencion por folio.", ex);
        }
    }
    
    /**
     * Metodo para guardar un reporte de atencion, el cual representa un reporte incidente resuelto.
     * @param reporte de atencion que se quiere guardar
     * @return el reporte registrado con su ID generado en la base de datos
     * @throws PersistenciaException si no se puede registrar el reporte de atencion o 
     * si falla algo al momento de conexion a la base de datos
     */
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
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al guardar el reporte de atencion.", ex);
        }
    }
    
    /**
     * Metodo para consultar los reportes de atencion mediante filtros 
     * @param filtros por los que se buscara el reporte de atencion ( Por: nombre de cliente, fecha desde, fecha hasta, estado y categoria )
     * @return una lista con los reportes que coincidan con los filtros
     * @throws PersistenciaException si falla algo al momento de conexion a la base de datos
     */
    @Override
    public List<ReporteAtencionPersistenciaDTO> consultarReportesAtencionesFiltros(FiltrosConsultaHistorialReportesDTO filtros) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
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
            AggregateIterable<ReporteAtencionPersistenciaDTO> resultados = coleccion.aggregate(pipeline, ReporteAtencionPersistenciaDTO.class);
            List<ReporteAtencionPersistenciaDTO> reportes = new ArrayList<>();
            resultados.into(reportes);

            return reportes;
        }catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar todos los reportes de atencion con filtros.", ex);
        }
    }
 
}
