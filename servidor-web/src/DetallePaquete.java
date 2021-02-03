

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webservices.EspectaculoDto;
import webservices.EspectadorDto;
import webservices.EspectadorNoExisteException_Exception;
import webservices.NoExistePaqueteException_Exception;
import webservices.PaqueteDto;
import webservices.PaqueteYaExisteException_Exception;
import webservices.PlataformaDto;
import webservices.UsuarioNoExisteException_Exception;


/**
 * Servlet implementation class DetallePaquete
 */
@WebServlet("/DetallePaquete")
public class DetallePaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetallePaquete() {
        super();
        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoExistePaqueteException_Exception {
    	HttpSession session = request.getSession();
    	String nomPaq = request.getParameter("paquete"); //ACA DEBERIA TRAERLO DE LA SESSION
    	
    	webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
    	
    	
    	if (session.getAttribute("usuarioLogueado") != null) {
    		request.setAttribute("tipousuario", "artista");
    		try {
    			EspectadorDto user = (EspectadorDto) session.getAttribute("usuarioLogueado");
    			request.setAttribute("tipousuario", "espectador");
    			boolean paqueteComprado = user.getPaquetes().stream().filter(p -> p.equals(nomPaq)).count() == 1;
        		request.setAttribute("paqueteComprado", paqueteComprado);
    		} catch (Exception e) {
    			
    		}
    	} 	
    	
    	PaqueteDto paq = port.getPaquete(nomPaq);
    	List<EspectaculoDto> esps = port.obtenerEspectaculosDePaquete(nomPaq).getList();
    	List<String> cats = port.getCategoriasDePaquete(nomPaq).getList();
		List<PlataformaDto> plataformas = port.obtenerPlataformas().getList();
		List<String> nombrePlataformas = new LinkedList<String>();
		plataformas.forEach(p -> nombrePlataformas.add(p.getNombre()));
    	request.setAttribute("plataformas", nombrePlataformas);
    	request.setAttribute("paquete", paq);
    	request.setAttribute("espDePaquete", esps);
    	request.setAttribute("catsPaq", cats);
    	request.getRequestDispatcher("/WEB-INF/DetallePaquete.jsp").forward(request, response);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		HttpSession session = request.getSession();
		try {
			processRequest(request, response);
		} catch (Exception ex) {
			session.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
    	
		HttpSession session = request.getSession();
		String nomPaq = (String) request.getParameter("paquete");
		
    	if (session.getAttribute("usuarioLogueado") != null) {
    		EspectadorDto user = (EspectadorDto) session.getAttribute("usuarioLogueado");
    		try {
				port.comprarPaquete(user.getNickname(), nomPaq);
				request.setAttribute("Correcto", "Paquete: " + nomPaq + " comprado con exito!");
				session.setAttribute("usuarioLogueado", port.obtenerUsuario(user.getNickname()));
			} catch (EspectadorNoExisteException_Exception | PaqueteYaExisteException_Exception | NoExistePaqueteException_Exception | UsuarioNoExisteException_Exception ex) {
				session.setAttribute("error", ex.getMessage());
				request.setAttribute("Error", ex.getMessage());
			}
    	}
    	doGet(request, response);
	}

}
