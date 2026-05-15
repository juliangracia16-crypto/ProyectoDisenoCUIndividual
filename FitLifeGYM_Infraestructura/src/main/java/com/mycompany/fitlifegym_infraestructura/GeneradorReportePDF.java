
package com.mycompany.fitlifegym_infraestructura;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.fitlifegym_dtos.RegistroReporteAdminDTO;
import com.mycompany.fitlifegym_dtos.ReportePdfDTO;
import com.mycompany.fitlifegym_negocio.IGeneradorReportePDF;
import com.mycompany.fitlifegym_negocio.InfraestructuraException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author Julian
 */
public class GeneradorReportePDF implements IGeneradorReportePDF{

    @Override
    public byte[] generarReportePDF(ReportePdfDTO generarReportePdf) throws InfraestructuraException{
        try {
            Document documento = new Document();
            ByteArrayOutputStream salida = new ByteArrayOutputStream();
            PdfWriter.getInstance(documento, salida);
            documento.open();
            //Agregar Logo Imagen
            Image logo = Image.getInstance(generarReportePdf.imagen());
            logo.scaleToFit(50, 50);
            documento.add(logo);
            //Agregar el titulo al reporte
            Paragraph fecha = new Paragraph(
                    "FECHA GENERADO: " + generarReportePdf.fechaPdfGenerado().toString()
            );
            documento.add(fecha);
            //Agregar la tabla
            Paragraph titulo = new Paragraph(
                    generarReportePdf.tituloReporte()
            );
            documento.add(titulo);
            //Tabla donde iran los registros
            PdfPTable tabla = new PdfPTable(6);
            tabla.addCell("FOLIO");
            tabla.addCell("ASUNTO");
            tabla.addCell("CATEGORIA");
            tabla.addCell("ESTADO");
            tabla.addCell("CLIENTE");
            tabla.addCell("FECHA");
            //LLenar tabla con los registros
            for (RegistroReporteAdminDTO registro : generarReportePdf.registros()) {

                tabla.addCell(registro.folio());
                tabla.addCell(registro.asunto());
                tabla.addCell(registro.categoria().categoria());
                tabla.addCell(registro.estado().estado());
                tabla.addCell(registro.cliente().getNombre());
                tabla.addCell(registro.fecha().toString());
            }
            documento.add(tabla);
            //Retornar el pdf con toda la informacion
            documento.close();
            return salida.toByteArray();
        } catch (DocumentException | IOException ex) {
            throw new InfraestructuraException("Error al guardar el reporte de registros a pdf");
        }
    }
    
}
