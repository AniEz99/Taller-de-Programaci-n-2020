

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import webservices.ImagenDto;
import webservices.PaqueteDto;
import webservices.UsuarioDto;

/**
 * Servlet implementation class AltaPaquete
 */
@WebServlet("/AltaPaquete")
@MultipartConfig
public class AltaPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaPaquete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		if (session.getAttribute("usuarioLogueado") == null || session.getAttribute("usuarioLogueado").getClass() == webservices.EspectadorDto.class) {
			response.sendRedirect("Home");
		} else {
			request.getRequestDispatcher("/WEB-INF/AltaPaquete.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
    	
		HttpSession session = request.getSession();
		String nom = request.getParameter("nombre");
		String descr = (String) request.getParameter("descripcion");
		String unParsed_fechaInicio = request.getParameter("fechaInicio");
		String unParsed_fechaFin = request.getParameter("fechaFin");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaIni = new Date(); 
		Date fechaF = new Date();
		try {
			fechaIni = formatter.parse(unParsed_fechaInicio);
			fechaF = formatter.parse(unParsed_fechaFin);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		float desc = Float.parseFloat(request.getParameter("descuento"));
		String nArt = ((UsuarioDto)session.getAttribute("usuarioLogueado")).getNickname();
		
		ImagenDto imgDto = new ImagenDto();
		Part imgPart = request.getPart("imagen");
		if (imgPart.getSize() > 0) {
			InputStream stream = imgPart.getInputStream();
			byte[] content = new byte[stream.available()];
			stream.read(content);
			imgDto.setContent(content);
			imgDto.setName(imgPart.getSubmittedFileName());
		}
		try {
			PaqueteDto paq = new PaqueteDto();
			paq.setNombre(nom);
			paq.setDescripcion(descr);
			
			GregorianCalendar dateIniGC = new GregorianCalendar();
			dateIniGC.setTime(fechaIni);
			XMLGregorianCalendar xmldateIniGC = null;
			try {
				xmldateIniGC = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateIniGC);
			} catch (DatatypeConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			GregorianCalendar dateFinGC = new GregorianCalendar();
			dateFinGC.setTime(fechaF);
			XMLGregorianCalendar xmldateFinGC = null;
			try {
				xmldateFinGC = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateFinGC);
			} catch (DatatypeConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			GregorianCalendar dateRegGC = new GregorianCalendar();
			dateRegGC.setTime(new Date());
			XMLGregorianCalendar xmldateRegGC = null;
			try {
				xmldateRegGC = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateRegGC);
			} catch (DatatypeConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			paq.setFechaInicio(xmldateIniGC);
			paq.setFechaFin(xmldateFinGC);
			paq.setFechaRegistro(xmldateRegGC);
			paq.setDescuento(desc);
			port.crearPaquete(paq, imgDto, nArt);
			request.setAttribute("Correcto", "Alta exitosa de paquete: " + nom + ".");
		} catch (Exception e) {
			request.setAttribute("Error", e.getMessage());
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
