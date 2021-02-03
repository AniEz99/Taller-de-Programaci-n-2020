import java.io.IOException;
import java.io.InputStream;
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

import webservices.CategoriaDto;
import webservices.CategoriaDtoList;
import webservices.ImagenDto;
import webservices.PlataformaDto;
import webservices.UsuarioDto;

/**
 * Servlet implementation class Espectaculo
 */
@WebServlet("/AltaEspectaculo")
@MultipartConfig
public class AltaEspectaculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaEspectaculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		
		HttpSession session = request.getSession();
		if (session.getAttribute("usuarioLogueado") == null || session.getAttribute("usuarioLogueado").getClass() == webservices.EspectadorDto.class) {
			response.sendRedirect("Home");
		} else {
			List<PlataformaDto> plats = port.obtenerPlataformas().getList();
			List<CategoriaDto> cats = port.listarCategorias().getList();
			request.setAttribute("plataformas", plats);
			request.setAttribute("categorias", cats);
			request.getRequestDispatcher("/WEB-INF/AltaEspectaculo.jsp").forward(request, response);
		}
		
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		
		HttpSession session = request.getSession();
		System.out.print(request.getParameter("plataforma"));
		String nPlat = (String) request.getParameter("plataforma");
		UsuarioDto usLog = (UsuarioDto)session.getAttribute("usuarioLogueado");
		String nArt = usLog.getNickname();
		String nombre = (String) request.getParameter("nombreEspectaculo");
		String descripcion = (String) request.getParameter("desEspectaculo");
		System.out.print(request.getParameter("durEspectaculoH"));
		System.out.print(request.getParameter("durEspectaculoM"));
		int duracionHoras = Integer.parseInt(request.getParameter("durEspectaculoH"));
		int duracionMinutos = Integer.parseInt(request.getParameter("durEspectaculoM"));
		int minEspectadores = Integer.parseInt(request.getParameter("cantEspMin"));
		int maxEspectadores = Integer.parseInt(request.getParameter("cantEspMax"));
		String url = (String) request.getParameter("urlEspectaculo");
		String urlVid = (String) request.getParameter("urlVideo");
		float costo = Float.parseFloat((String)request.getParameter("costoEspectaculo"));
		
		String desPremio = (String) request.getParameter("desPremio");
		int cantPremio = Integer.parseInt(request.getParameter("cantPremio"));
		
		ImagenDto imgDto = null;
		
		Part imgPart = request.getPart("imagen");
		
		if (imgPart.getSize() > 0) {
			InputStream stream = imgPart.getInputStream();
			byte[] content = new byte[stream.available()];
			stream.read(content);
			imgDto = new ImagenDto();
			imgDto.setContent(content);
			imgDto.setName(imgPart.getSubmittedFileName());
		} else {
			imgDto = new ImagenDto();
		}
	
		
		String[] categoriasArr = request.getParameterValues("categorias");
		List<CategoriaDto> cats = port.listarCategorias().getList();
		CategoriaDtoList categorias = new CategoriaDtoList();
		for (CategoriaDto cat : cats) {
			for (String str : categoriasArr) {
				if (cat.getNombre().equals(str)) {
					categorias.getList().add(cat);
				}
			}
		}
		
		GregorianCalendar dateGC = new GregorianCalendar();
		dateGC.setTime(new Date());
		XMLGregorianCalendar xmldateGC = null;
		try {
			xmldateGC = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateGC);
		} catch (DatatypeConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		try {
			port.confirmarAltaEspectaculo(nPlat, nArt, nombre, descripcion, duracionHoras, duracionMinutos, minEspectadores, maxEspectadores, url, costo, xmldateGC , imgDto,  categorias);
			port.guardarUrlVideo(urlVid, nPlat, nombre);			
			port.guardarPremios(desPremio, cantPremio, nombre, nPlat);
			request.setAttribute("Correcto", "Alta exitosa del Espectaculo: " + nombre + ".");
		} catch (Exception e) {
			request.setAttribute("Error", e.getMessage());
			e.printStackTrace();
		}
		doGet(request, response);
		
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
