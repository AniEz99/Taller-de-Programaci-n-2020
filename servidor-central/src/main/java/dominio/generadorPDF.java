package dominio;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class generadorPDF {
	
	 public ByteArrayOutputStream getPDF(String nomPremio, String nomEspec, String nomFunc, String nomEsp, String fecha, String fechaFin) {

	        // Creamos la instancia de memoria en la que se escribirá el archivo temporalmente
		 
	        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
	            Document document = new Document(PageSize.A4);
	            
	            // Formatos varios
	            Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	                    Font.BOLD);
	            Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                    Font.NORMAL, BaseColor.RED);
	            Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	                    Font.NORMAL);
	            Font smallNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	                    Font.BOLD);
	            
	            
	            Paragraph preface = new Paragraph();
	            Paragraph tituloParr = new Paragraph("Comprobante de premio", catFont);
	            tituloParr.setAlignment(Element.ALIGN_CENTER);
	            preface.add(tituloParr);
	            preface.add( Chunk.NEWLINE );
	            preface.add(new Paragraph("Felicitaciones " + nomEspec + "!", smallNormal));
	            preface.add(new Paragraph(
	                    "Has ganado el premio \"" + nomPremio + "\", puedes retirarlo en nuestro local hasta la fecha " + fechaFin,
	                    smallBold));
	            
				preface.add( Chunk.NEWLINE );
				preface.add(new Paragraph("Datos premio", smallNormal));
				Paragraph parrFunc = new Paragraph("- Funcion: " + nomFunc, smallBold);
				parrFunc.setIndentationLeft(35);
	            preface.add(parrFunc);
	            Paragraph parrEsp = new Paragraph("- Espectaculo: " + nomEsp, smallBold);
	            parrEsp.setIndentationLeft(35);
	            preface.add(parrEsp);
	            Paragraph parrFecha = new Paragraph("- Fecha ganado: " + fecha, smallBold);
	            parrFecha.setIndentationLeft(35);
	            preface.add(parrFecha);
	            preface.add( Chunk.NEWLINE );
	            preface.add( Chunk.NEWLINE );
	            preface.add( Chunk.NEWLINE );
	            preface.add( Chunk.NEWLINE );
	            preface.add( Chunk.NEWLINE );
	            preface.add( Chunk.NEWLINE );
	            preface.add( Chunk.NEWLINE );
	            preface.add( Chunk.NEWLINE );
	            Paragraph piePag = new Paragraph(
	                    "Para retirar el premio es necesario presentar el comprobante, no se entregaran premios fuera de fecha.",
	                    redFont);
	            piePag.setAlignment(Element.ALIGN_CENTER);
	            preface.add(piePag);


	            // Asignamos la variable ByteArrayOutputStream bos donde se escribirá el documento
	            try {
					PdfWriter.getInstance(document, bos);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            document.open();
	            try {
					document.add(preface);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            document.close();
	            return bos;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
}
