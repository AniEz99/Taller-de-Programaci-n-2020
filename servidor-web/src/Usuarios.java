

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import webservices.ArtistaDto;
import webservices.EspectaculoMinDto;
import webservices.EspectadorDto;
import webservices.ImagenDto;
import webservices.PremioDto;
import webservices.UsuarioDto;
import webservices.UsuarioNoExisteException_Exception;

/**
 * Servlet implementation class Usuarios
 */
@WebServlet("/Usuarios")
@MultipartConfig
public class Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuarios() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get servidor central
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		
		if (request.getParameter("checkDisponibilidad") != null) {
			String input = request.getParameter("input");
			boolean disp = port.nicknameOCorreoDisponible(input);
			//response.setContentType("application/json;charset=UTF-8");
	        response.getWriter().write(disp ? "true" : "false");
		} else {
			String nick = request.getParameter("nickname");
			request.getSession().setAttribute("port", port);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			request.getSession().setAttribute("formater", formatter);
			try {
				// DETALLE USUARIO
				webservices.UsuarioDto usuario = port.obtenerUsuario(nick);
				try {
					ArtistaDto artista = (ArtistaDto) usuario;
					request.getSession().setAttribute("artistaConsultado", artista);
					request.getSession().setAttribute("usuarioConsultadoEsArtista", true);
					List<EspectaculoMinDto> auxList = artista.getEspectaculos().stream().filter(e -> e.getEstado().equals("ACEPTADO")).collect(Collectors.toList());
					request.getSession().setAttribute("espectaculosDelArtista", auxList);
					auxList = artista.getEspectaculos().stream().filter(e -> e.getEstado().equals("INGRESADO")).collect(Collectors.toList());
					request.getSession().setAttribute("espectaculosIngresadosDelArtista", auxList);
					auxList = artista.getEspectaculos().stream().filter(e -> e.getEstado().equals("RECHAZADO")).collect(Collectors.toList());
					request.getSession().setAttribute("espectaculosRechazadosDelArtista", auxList);
					auxList = artista.getEspectaculos().stream().filter(e -> e.getEstado().equals("FINALIZADO")).collect(Collectors.toList());
					request.getSession().setAttribute("espectaculosFinalizadosDelArtista", auxList);
				} catch (Exception e) {
					EspectadorDto espectador = (EspectadorDto) usuario;
					request.getSession().setAttribute("espectadorConsultado", espectador);
					request.getSession().setAttribute("usuarioConsultadoEsArtista", false);
					request.setAttribute("espectadorCons", espectador);
				}
				UsuarioDto usuarioLogueado = (UsuarioDto) request.getSession().getAttribute("usuarioLogueado");
				request.getSession().setAttribute("esMiPerfil", usuarioLogueado != null && usuarioLogueado.getNickname().equals(usuario.getNickname()) ? "true" : null);
				if (usuarioLogueado != null) {
					request.getSession().setAttribute("yaLoSigue", usuarioLogueado.getSeguidos().contains(usuario.getNickname()) ? "" : null);				
				}
			} catch (UsuarioNoExisteException_Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/DetalleUsuario.jsp").forward(request, response);		
		}
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get servidor central
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		
		// Modificar Usuario
		if (request.getParameter("modificarDatos").equals("true") ) {
			String newName = request.getParameter("name");
			String newSurname = request.getParameter("surname");
			String newPassword = request.getParameter("password");
			String newPasswordConf = request.getParameter("passwordConf");
			String newBDay = request.getParameter("bday");
			if (newPassword.equals(newPasswordConf)) {
				String newDesc = request.getParameter("desc");
				String newBio = request.getParameter("bio");
				String newWeb = request.getParameter("web");
				try {
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    		Date bdate = formatter.parse(newBDay); //Oct 27 00:00:00 UYT 2020 (EXAMPLE)
		    		GregorianCalendar bdateGC = new GregorianCalendar();
		    		bdateGC.setTime(bdate);
		    		XMLGregorianCalendar xmlbdateGC =  DatatypeFactory.newInstance().newXMLGregorianCalendar(bdateGC);
		    		
		    		String nick = request.getParameter("nickname");
					UsuarioDto usuario = port.obtenerUsuario(nick);
					ImagenDto imgDto = new ImagenDto();
					Part imgPart = request.getPart("imagen");
					
					if (imgPart.getSize() > 0) {
						InputStream stream = imgPart.getInputStream();
						byte[] content = new byte[stream.available()];
						stream.read(content);
						imgDto = new ImagenDto();
						imgDto.setContent(content);
						imgDto.setName(imgPart.getSubmittedFileName());
					}
					if ((boolean) request.getSession().getAttribute("usuarioConsultadoEsArtista")) {
						ArtistaDto aux = new ArtistaDto();
						aux.setNickname(usuario.getNickname());
						aux.setNombre(newName);
						aux.setApellido(newSurname);
						aux.setCorreo(usuario.getCorreo());
						aux.setFechaNacimiento(xmlbdateGC);
						aux.setDescripcion(newDesc);
						aux.setBiografia(newBio);
						aux.setSitioWeb(newWeb);
						aux.setContrasenia(newPassword);
						
						port.editarArtista(aux, imgDto);
					}
					else {
						EspectadorDto esp = new EspectadorDto();
						esp.setNickname(usuario.getNickname());
		    			esp.setNombre(newName);
		    			esp.setApellido(newSurname);
		    			esp.setCorreo(usuario.getCorreo());
		    			esp.setFechaNacimiento(xmlbdateGC);
		    			esp.setContrasenia(newPassword);
						
						port.editarEspectador(esp, imgDto);
					}
					request.setAttribute("Correcto", "Datos modificados con exito");
					doGet(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				request.setAttribute("Error", "Las contraseñas no coinciden");
				doGet(request, response);
			}
		} else { // REGISTRO DE USUARIO
			System.out.print("Esto es registro");
			String nick = request.getParameter("nickname");
	    	String name = request.getParameter("nombre");
	    	String surname = request.getParameter("apellido");
	    	String mail = request.getParameter("mail");
	    	String password = request.getParameter("contrasenia");
	    	String passwordConf = request.getParameter("confcontrasenia");
	    	String dateNac = request.getParameter("bday"); // yyyy/MM/ddv
	    	String tipo = request.getParameter("tipousuario");
	    	String desc = request.getParameter("inputDescripcion");
	    	String bio = request.getParameter("inputBiografia");
	    	String sitio = request.getParameter("inputSitio");
	    	
	    	ImagenDto imgDto = null;
			Part imgPart = request.getPart("imagen");
			
			if (imgPart.getSize() > 0) {
				InputStream stream = imgPart.getInputStream();
				byte[] content = new byte[stream.available()];
				stream.read(content);
				imgDto = new ImagenDto();
				imgDto.setContent(content);
				imgDto.setName(imgPart.getSubmittedFileName());
			}
	    	
	    	HttpSession session = request.getSession();
	    	try {
	    		
	    		
	    		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    		Date fechaNac = formatter.parse(dateNac); //Oct 27 00:00:00 UYT 2020 (EXAMPLE)
	    		GregorianCalendar bdateGC = new GregorianCalendar();
	    		bdateGC.setTime(fechaNac);
	    		XMLGregorianCalendar xmlbdateGC =  DatatypeFactory.newInstance().newXMLGregorianCalendar(bdateGC);
	    	
	    		
	    		
	    		if (password.equals(passwordConf)) {
		    		if ("espectador".equals(tipo)) {
		    			EspectadorDto esp = new EspectadorDto();
		    			esp.setNickname(nick);
		    			esp.setNombre(name);
		    			esp.setApellido(surname);
		    			esp.setCorreo(mail);
		    			esp.setFechaNacimiento(xmlbdateGC);
		    			esp.setContrasenia(password);
		    			
		    			port.registrarEspectador(esp, imgDto);
		    			session.setAttribute("usuarioLogueado", esp);
		    		}
		    		else {
		    			
		    			ArtistaDto art = new ArtistaDto();
		    			art.setNickname(nick);
		    			art.setNombre(name);
		    			art.setApellido(surname);
		    			art.setCorreo(mail);
		    			art.setFechaNacimiento(xmlbdateGC);
		    			art.setDescripcion(desc);
		    			art.setBiografia(bio);
		    			art.setSitioWeb(sitio);
		    			art.setContrasenia(password);
		    			
		    			port.registrarArtista(art, imgDto);
		    			session.setAttribute("usuarioLogueado", art);
		    		}
	    		}
	    		else {
	    			request.setAttribute("Error", "Confirmacion de contrasenia");
	    			request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
	    		}
	    		if (request.getHeader("User-Agent").indexOf("Mobile") != -1) {
					request.getRequestDispatcher("/WEB-INF/HomeMovil.jsp").forward(request, response);
				}
				else {
					response.sendRedirect("/servidor-web/Home");
				}
	    	} catch(Exception ex3) {
	    		request.setAttribute("Error", ex3.getMessage());
    			request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
	    	}
		}
	}
}
