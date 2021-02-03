

import java.io.IOException;
import java.io.OutputStream;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.commons.io.IOUtils;


import java.io.ByteArrayInputStream;


/**
 * Servlet implementation class descargarPDF
 */
@WebServlet("/descargarPDF")
public class descargarPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public descargarPDF() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
				          
        String nomFunc = (String) request.getParameter("funcion");
		String nomEsp = (String) request.getParameter("espectaculo");
		String nomPremio = (String) request.getParameter("premio");
		String nomEspec = (String) request.getParameter("espectador");
		String fecha = (String) request.getParameter("fecha");
		String fechaFin = (String) request.getParameter("fechaFin");
		
    	byte[] pdfReport = port.generarPdf(nomPremio, nomEspec, nomFunc, nomEsp, fecha, fechaFin);

        String mimeType =  "application/pdf";
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "comprobantePremio.pdf"));

        response.setContentLength(pdfReport.length);

        ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);
        OutputStream outStream = response.getOutputStream();

        byte[] buf = new byte[1024];
        long total = 0;
        while (true) {
          int r = inStream.read(buf);
          if (r == -1) {
            break;
          }
          outStream.write(buf, 0, r);
          total += r;
        }
        
        inStream.close();
        outStream.close();

         
		//response.sendRedirect("/servidor-web/Home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
