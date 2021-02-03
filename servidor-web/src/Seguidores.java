

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webservices.UsuarioDto;

/**
 * Servlet implementation class Seguidores
 */
@WebServlet("/Seguidores")
public class Seguidores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Seguidores() {
        super();
    }

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
				
		String seguidor = (String) request.getParameter("seguidor");
		String seguido = (String) request.getParameter("seguido");
		String accion = (String) request.getParameter("accion");
		if (accion.equals("seguir")) {
			try {
				port.seguir(seguidor, seguido);
				session.setAttribute("usuarioLogueado", port.obtenerUsuario(((UsuarioDto)session.getAttribute("usuarioLogueado")).getNickname()));
				response.sendRedirect("Usuarios?nickname=" + seguido);
			} catch (Exception e) {
				request.getSession().setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/errores/ErrorLogin.jsp").forward(request, response);
			}			
		} else if (accion.equals("dejarDeSeguir")) {
			try {
				port.dejarDeSeguir(seguidor, seguido);
				session.setAttribute("usuarioLogueado", port.obtenerUsuario(((UsuarioDto)session.getAttribute("usuarioLogueado")).getNickname()));
				response.sendRedirect("Usuarios?nickname=" + seguido);
			} catch (Exception e) {
				request.getSession().setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/errores/ErrorLogin.jsp").forward(request, response);
			}
		}
	}

}
