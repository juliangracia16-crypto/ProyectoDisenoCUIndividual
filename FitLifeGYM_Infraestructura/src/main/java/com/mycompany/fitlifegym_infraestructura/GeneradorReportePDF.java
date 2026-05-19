
package com.mycompany.fitlifegym_infraestructura;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.fitlifegym_infraestructura.dtos.RegistroReporteAdminDTOInfraestructura;
import com.mycompany.fitlifegym_infraestructura.dtos.ReportePdfDTOInfraestructura;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Clase que implementa la interfaz IGeneradorReportePDF
 * para generar el reporte pdf.
 * Se genera utilizando la libreria itextpdf
 * @author Julian
 */
public class GeneradorReportePDF implements IGeneradorReportePDF{
    
    /**
     * Metodo que genera el reporte pdf.
     * @param generarReportePdf los datos con los que se generara el reporte
     * @return el reporte pdf generado en un arreglo de bytes
     * @throws InfraestructuraException si ocurre un error al generar el reporte
     */
    @Override
    public byte[] generarReportePDF(ReportePdfDTOInfraestructura generarReportePdf) throws InfraestructuraException{
        try {
            // Documento horizontal
            Document documento = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
            ByteArrayOutputStream salida = new ByteArrayOutputStream();
            PdfWriter.getInstance(documento, salida);
            documento.open();
            // FUENTES
            Font fuenteTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font fuenteSubtitulo = new Font(Font.FontFamily.HELVETICA, 11);
            Font fuenteHeaders = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
            Font fuenteContenido = new Font(Font.FontFamily.HELVETICA, 10);
            // LOGO
            Image logo = Image.getInstance(generarReportePdf.getImagen());
            logo.scaleToFit(80, 80);
            logo.setAlignment(Element.ALIGN_CENTER);
            documento.add(logo);
            // ESPACIO
            documento.add(new Paragraph(" "));
            // TITULO
            Paragraph titulo = new Paragraph(generarReportePdf.getTituloReporte(),fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            // FECHA
            Paragraph fecha = new Paragraph("FECHA GENERADO: "+ generarReportePdf.getFechaPdfGenerado(),fuenteSubtitulo);
            fecha.setAlignment(Element.ALIGN_RIGHT);
            documento.add(fecha);
            documento.add(new Paragraph(" "));
            // TABLA
            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);
            // ANCHOS DE COLUMNAS
            tabla.setWidths(new float[]{2f,7f,3f,3f,4f,3f });
            tabla.setSpacingBefore(10f);

            // HEADERS
            agregarHeader(tabla, "FOLIO", fuenteHeaders);
            agregarHeader(tabla, "ASUNTO", fuenteHeaders);
            agregarHeader(tabla, "CATEGORIA", fuenteHeaders);
            agregarHeader(tabla, "ESTADO", fuenteHeaders);
            agregarHeader(tabla, "CLIENTE", fuenteHeaders);
            agregarHeader(tabla, "FECHA", fuenteHeaders);

            // REGISTROS
            for (RegistroReporteAdminDTOInfraestructura registro : generarReportePdf.getRegistros()) {

                agregarCeldaCentro(tabla, registro.folio(), fuenteContenido);

                agregarCeldaIzquierda(tabla, registro.asunto(), fuenteContenido);

                agregarCeldaCentro(
                        tabla,
                        registro.nombreCategoria(),
                        fuenteContenido
                );

                agregarCeldaCentro(
                        tabla,
                        registro.nombreEstado(),
                        fuenteContenido
                );

                agregarCeldaCentro(
                        tabla,
                        registro.nombreCliente(),
                        fuenteContenido
                );

                agregarCeldaCentro(
                        tabla,
                        registro.fecha().toString(),
                        fuenteContenido
                );
            }
            documento.add(tabla);
            
            documento.close();
            return salida.toByteArray();
        } catch (DocumentException | IOException ex) {
            throw new InfraestructuraException("Error al guardar el reporte de registros a pdf");
        }
        
    }
    
    /**
     * Metodo para agregar a la celda de la tabla donde ira el titulo
     * que vaya centrado, alineado y con buen tamaño de letra.
     * @param tabla donde se agregara el header
     * @param texto que se le agregara al header
     * @param fuente que tendra el header que agregaremos
     */
    private void agregarHeader(PdfPTable tabla,String texto,Font fuente) {

        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(8);
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        tabla.addCell(celda);
    }

    /**
     * Metodo para alinear el texto que se 
     * mostrara en la tabla al centro
     * @param tabla donde se agregara
     * @param texto que se agregara a la celda de la tabla
     * @param fuente que tendra el texto que agregaremos
     */
    private void agregarCeldaCentro(PdfPTable tabla,String texto,Font fuente) {
        
        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(6);
        tabla.addCell(celda);
    }

    /**
     * Metodo para alinear el texto que se mostrara 
     * en la tabla a la izquierda
     * @param tabla donde se alineara el texto
     * @param texto que se alineara
     * @param fuente que tendra el texto que aliniemos y
     * agreguemos
     */
    private void agregarCeldaIzquierda(PdfPTable tabla,String texto,Font fuente) {
        
        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(6);
        tabla.addCell(celda);
    }
    
}
