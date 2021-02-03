

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webservices.ArtistaDto;
import webservices.EspectaculoDto;
import webservices.EspectaculoDtoList;
import webservices.EspectaculoNoExisteException_Exception;
import webservices.EspectadorDto;
import webservices.EspectadorNoExisteException_Exception;
import webservices.FuncionDto;
import webservices.FuncionNoExisteException_Exception;
import webservices.PaqueteDto;
import webservices.PlataformaNoExisteException_Exception;
import webservices.RegistroDto;
import webservices.UsuarioNoExisteException_Exception;

/**
 * Servlet implementation class DetalleFuncion
 */
@WebServlet("/DetalleFuncion")
public class DetalleFuncion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetalleFuncion() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PlataformaNoExisteException_Exception, EspectaculoNoExisteException_Exception, EspectadorNoExisteException_Exception, UsuarioNoExisteException_Exception {
    	webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
    	
    	HttpSession session = request.getSession();
    
	    String nomFunc = (String) request.getParameter("funcion");
		String nomEsp = (String) request.getParameter("espectaculo");
		String nomPlat = (String) request.getParameter("plataforma");
		String nomSorteo = (String) request.getParameter("hacerSorteo");
		
		if (nomSorteo != null) {
			if (nomSorteo.equals("sortear")) {
				try {
					port.hacerSorteoPremio(nomEsp, nomPlat, nomFunc);
				} catch (PlataformaNoExisteException_Exception | EspectaculoNoExisteException_Exception | FuncionNoExisteException_Exception e) {
					session.setAttribute("error", e.getMessage());
					request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
				}
			}
		}
				
		if (session.getAttribute("usuarioLogueado") != null) {
			try {
				ArtistaDto user = (ArtistaDto) session.getAttribute("usuarioLogueado");
				EspectaculoDtoList espectaculosArt = port.getEspectaculoAprobadosArtista(user.getNickname());
				List<EspectaculoDto> listaEspectaculos = espectaculosArt.getList();
				boolean organizador = false;
	    		for(EspectaculoDto esp: listaEspectaculos){
	  				if (esp.getNombre().equals(nomEsp)) {
	  					organizador = true;
	  				}
				}
	    		request.setAttribute("organizador", organizador);
				request.setAttribute("tipousuario", "artista");
			} catch (ClassCastException e) {
				
			}
			try {
				EspectadorDto user = (EspectadorDto) session.getAttribute("usuarioLogueado");
				request.setAttribute("tipousuario", "espectador");
				List<RegistroDto> regsAmostrar = port.obtenerRegistrosEspectador(user.getNickname()).getList();
				List<RegistroDto> regs = port.obtenerTodosRegistrosEspectador(user.getNickname()).getList();
				boolean yaRegistrado = false;
	    		for(RegistroDto reg: regs){
	  				if (reg.getNombreFuncion().equals(nomFunc)) {
	  					yaRegistrado = true;
	  				}
				}
	    		request.setAttribute("registrado", yaRegistrado);
	    		List<PaqueteDto> paqs = port.obtenerPaquetesEspectadorEspectaculo(user.getNickname()).getList();
	    		request.setAttribute("registros", regsAmostrar);
	    		request.setAttribute("paquetes", paqs);
			} catch (ClassCastException e) {
				
			}
		}
		request.setAttribute("plataforma", nomPlat);
		FuncionDto func = port.seleccionarFuncion(nomFunc, nomEsp, nomPlat);
		
		
		// Controla si la funcion ya fue iniciada para saber si sortear o no
		Date fechaActual = new Date();
		boolean fechaCorrecta = false;
		int fechaSorteo = fechaActual.compareTo(func.getFechaInicio().toGregorianCalendar().getTime());
		if (fechaSorteo > 0) {
			fechaCorrecta = true;
		}
		request.setAttribute("fechaCorrecta", fechaCorrecta);
		
		
		EspectaculoDto esp = port.obtenerDatosEspectaculo(nomPlat, nomEsp);
		
		request.setAttribute("yaSorteado", func.isSorteado());
		request.setAttribute("funcion", func);
		request.setAttribute("espectaculo", esp);
		
		if (request.getParameterMap().containsKey("tipoGet")) {
			request.getRequestDispatcher("WEB-INF/Mobile/DetalleFuncionMobile.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/WEB-INF/DetalleFuncion.jsp").forward(request, response);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | PlataformaNoExisteException_Exception | EspectaculoNoExisteException_Exception | EspectadorNoExisteException_Exception | UsuarioNoExisteException_Exception e) {
			session.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | PlataformaNoExisteException_Exception | EspectaculoNoExisteException_Exception | EspectadorNoExisteException_Exception | UsuarioNoExisteException_Exception e) {
			e.printStackTrace();
		}
	}

}
