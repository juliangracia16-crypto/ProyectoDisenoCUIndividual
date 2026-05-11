
package com.mycompany.fitlifegym_persistencia;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import static com.mycompany.fitlifegym_persistencia.ManejadorConexiones.obtenerCodecs;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteIncidente;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Julian
 */
public class HistorialIncidentesDAO implements IHistorialIncidentesDAO, IBaseMongoDAO{
    
    private static final String NOMBRE_COLECCION = "historial_incidentes"; 
    private static final String FOLIO_KEY = "folio";
    private static final String ID_CLIENTE_KEY = "cliente._id";
    
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
    public List<ReporteIncidente> consultarReportesIncidentes() throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {

            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<ReporteIncidente> coleccion = this.obtenerColeccion(empresaBD);
            List<ReporteIncidente> reportesIncidentes = new LinkedList<>();
            coleccion.find().into(reportesIncidentes);
            return reportesIncidentes;
        }
    }

    @Override
    public ReporteIncidente consultarReporteIncidentePorId(String id) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<ReporteIncidente> coleccion = this.obtenerColeccion(empresaBD);
            
            Document filtros = new Document().append(FOLIO_KEY,id);
            ReporteIncidente reporteIncidente = coleccion.find(filtros).first();
            
            if(reporteIncidente == null){
                throw new PersistenciaException("No se encontro ningun reporte con ese folio.");
            }
            
            return reporteIncidente;
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
            
            reporteIncidente.setId(resultado.getInsertedId().toString());
            return reporteIncidente;
        }
    }

    @Override
    public ReporteIncidente eliminarReporteIncidente(String idReporte) throws PersistenciaException {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            
            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<ReporteIncidente> coleccion = this.obtenerColeccion(empresaBD);
            Document filtros = new Document().append(FOLIO_KEY,idReporte);
            
            ReporteIncidente reporteIncidente = coleccion.findOneAndDelete(filtros);
            if(reporteIncidente == null){
                throw new PersistenciaException("No se pudo eliminar el reporte de incidente correctamente.");
            }
            
            return reporteIncidente;
        }
    }

    @Override
    public List<ReporteIncidente> consultarReportesIncidentesFiltros() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ReporteIncidente> consultarReportesIncidentesCliente(String id) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {

            MongoDatabase empresaBD = this.obtenerBaseDatos(cliente);
            MongoCollection<ReporteIncidente> coleccion = this.obtenerColeccion(empresaBD);
            
            List<ReporteIncidente> reportesIncidentes = new LinkedList<>();
            coleccion.find(Filters.eq(ID_CLIENTE_KEY, new ObjectId(id))).into(reportesIncidentes);
            return reportesIncidentes;
        }
    }

    
    
}
