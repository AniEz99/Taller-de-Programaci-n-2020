
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webservices.ArtistaDto;
import webservices.UsuarioDto;
import webservices.UsuarioNoExisteException_Exception;

/**
 * Servlet implementation class Autenticacion
 */
@WebServlet("/Autenticacion")
public class Autenticacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Autenticacion() {
    	super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("usuarioLogueado") == null)
		{
			throw new ServletException("No hay usuario logueado!");
		}
		else
		{
			
			Cookie galleta = new Cookie("rememberUser", "");
			galleta.setMaxAge(0);
			response.addCookie(galleta);
			
			session.setAttribute("usuarioLogueado", null);
			response.sendRedirect("Home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		
		HttpSession session = request.getSession();
		String rememberme = request.getParameter("recordarme");
		String usuario = request.getParameter("usuario");
		String contrasenia = request.getParameter("contrasenia");
		
		try {
			UsuarioDto dto = port.login(usuario, contrasenia);
			
			if (request.getParameter("redirect").equals("false")) {
				response.setStatus(HttpServletResponse.SC_OK);
				if (!request.getParameter("mobile").isEmpty() && (dto instanceof ArtistaDto)) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}
			} else {
				if ("true".equals(rememberme)) {
					Cookie galleta = new Cookie("rememberUser", usuario);
					galleta.setMaxAge(525960*60);
					response.addCookie(galleta);
					//session.setMaxInactiveInterval(525960*60);
				}
				if (!request.getParameter("mobile").isEmpty()) {
					session.setAttribute("usuarioLogueado", dto);
					request.getRequestDispatcher("/WEB-INF/Mobile/HomeMovil.jsp").forward(request, response);
				}
				else {
					session.setAttribute("usuarioLogueado", dto);
					response.sendRedirect("Home");
				}				
			}
		}
		catch (Exception e3) {
			if (request.getParameter("redirect").equals("false")) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			} else {
				request.setAttribute("Error", e3.getMessage());
				request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);				
			}
		}
	}
	

}
