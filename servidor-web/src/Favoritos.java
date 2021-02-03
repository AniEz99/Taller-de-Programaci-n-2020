

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webservices.UsuarioDto;


/**
 * Servlet implementation class Favoritos
 */
@WebServlet("/Favoritos")
public class Favoritos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Favoritos() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("accion");
		
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
    	
    	
		if (action.equals("marcar")) {
			//Fabrica.getControladorEspectaculos().marcarEspectaculoFavorito()
			try {
				port.marcarEspectaculoFavorito(
						request.getParameter("usuario"),
						request.getParameter("plataforma"),
						request.getParameter("espectaculo"));
				request.setAttribute("Correcto", "Ahora este espectaculo es uno de tus favoritos!");
			} catch (Exception e) {
				request.setAttribute("Error", e.getMessage());
			}
		} else if (action.equals("desmarcar")) {
			try {
				port.desmarcarEspectaculoFavorito(
						request.getParameter("usuario"),
						request.getParameter("plataforma"),
						request.getParameter("espectaculo"));
				request.setAttribute("Correcto", "Este espectaculo dejo de ser uno de tus favoritos!");
			} catch (Exception e) {
				request.setAttribute("Error", e.getMessage());
			}
		} else {
			request.setAttribute("Error", "Acci�n no soportada");
		}
		
		try {
			request.getSession().setAttribute("usuarioLogueado", port.obtenerUsuario(((UsuarioDto)request.getSession().getAttribute("usuarioLogueado")).getNickname()));
		} catch (Exception e) {
			request.setAttribute("Error", e.getMessage());
		}
		request.getRequestDispatcher("DetalleEspectaculo").forward(request, response);
		
//		request.getRequestDispatcher(request.getHeader("referer")).forward(request, response);
	}

}
