

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

import webservices.EspectaculoDto;
import webservices.UsuarioDto;
import webservices.ImagenDto;
import webservices.StringList;
import webservices.UsuarioNoExisteException_Exception;


/**
 * Servlet implementation class AltaFuncion
 */
@WebServlet("/AltaFuncion")
@MultipartConfig
public class AltaFuncion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaFuncion() {
        super();
        
        // TODO Auto-generated constructor stub
    }
    
    
    protected void processGET(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
    	
    	HttpSession session = req.getSession();
		if (session.getAttribute("usuarioLogueado") == null || session.getAttribute("usuarioLogueado").getClass() == webservices.EspectadorDto.class) {
			resp.sendRedirect("Home");
		} else {
	    	
	    	try {
	    		String artistaLogeado = ((UsuarioDto)session.getAttribute("usuarioLogueado")).getNickname();
	    		List<EspectaculoDto> espectaculosDelArtista = port.getEspectaculoAprobadosArtista(artistaLogeado).getList();
	    		List<String> nombreArtistas = port.getNombreArtistas().getList();
	    		req.setAttribute("espectaculos", espectaculosDelArtista);
	    		req.setAttribute("artistas", nombreArtistas);
	        	req.getRequestDispatcher("/WEB-INF/AltaFuncion.jsp").forward(req, resp);
	    		
	    	}catch (UsuarioNoExisteException_Exception e) {
	    		resp.getWriter().append("No usser");
	    	}
		}
    } 
    
    protected void processPOST(HttpServletRequest req, HttpServletResponse resp) throws Exception{
    	webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
    	
		HttpSession session = req.getSession();
		String nickArtista =  ((UsuarioDto)session.getAttribute("usuarioLogueado")).getNickname();
    	String nombreEspectaculo = (String) req.getParameter("espectaculo");
    	String funcion = req.getParameter("nombreFuncion");
    	String auxFechaFuncion = req.getParameter("fechaFuncion");
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Date fechaFuncion = new Date();
    	try {
    		fechaFuncion = formatter.parse(auxFechaFuncion);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	GregorianCalendar dateGC = new GregorianCalendar();
		dateGC.setTime(fechaFuncion);
		XMLGregorianCalendar xmldateGC = null;
		try {
			xmldateGC = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateGC);
		} catch (DatatypeConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	int hora = Integer.parseInt(req.getParameter("horas"));
    	int minutos = Integer.parseInt(req.getParameter("minutos"));
    	String[] artistasArray = req.getParameterValues("invitados");
    	StringList artistasInvitados = new StringList();
    	if (artistasArray != null) {
	    	for(String art: artistasArray) {
	    		artistasInvitados.getList().add(art);
	    	}
    	} 
    
    	
    	ImagenDto imgDto = new ImagenDto();
		
		Part imgPart = req.getPart("imagen");
		if (imgPart.getSize() > 0) {
			InputStream stream = imgPart.getInputStream();
			byte[] content = new byte[stream.available()];
			stream.read(content);
			imgDto = new ImagenDto();
			imgDto.setContent(content);
			imgDto.setName(imgPart.getSubmittedFileName());
		}
    	boolean exito = port.confirmarAltaFuncion2(funcion, hora, minutos, xmldateGC, artistasInvitados, nombreEspectaculo, nickArtista, imgDto);
    	if (exito) {
    		req.setAttribute("Correcto", "Se dio de alta la funcion: " + funcion + " .");
    	}else {
    		req.setAttribute("Error", "Error al dar de alta la funcion.");
    	}
    	doGet(req, resp);
	}
		
    	
    	
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processGET(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processPOST(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}