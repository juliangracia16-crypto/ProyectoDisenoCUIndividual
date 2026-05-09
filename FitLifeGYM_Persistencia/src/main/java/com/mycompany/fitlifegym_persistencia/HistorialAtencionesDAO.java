
package com.mycompany.fitlifegym_persistencia;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import static com.mycompany.fitlifegym_persistencia.ManejadorConexiones.obtenerCodecs;
import com.mycompany.fitlifegym_persistencia.entidades.ReporteAtencion;
import java.util.List;
import org.bson.Document;

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
    public List<ReporteAtencion> consultarReportesAtencion() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    
    
}
