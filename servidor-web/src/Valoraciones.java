

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webservices.EspectaculoNoExisteException_Exception;
import webservices.EspectadorNoExisteException_Exception;
import webservices.PlataformaNoExisteException_Exception;
import webservices.UsuarioDto;
import webservices.UsuarioNoExisteException_Exception;
import webservices.ValoracionDto;
import webservices.ValoracionNoValidaException_Exception;

/**
 * Servlet implementation class Valoraciones
 */
@WebServlet("/Valoraciones")
public class Valoraciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Valoraciones() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		
		
		String plataforma = request.getParameter("plataforma");
		String espectaculo = request.getParameter("espectaculo");
		String usuario = request.getParameter("usuario");
		ValoracionDto valoracion = new ValoracionDto();
		valoracion.setEstrellas(Integer.parseInt(request.getParameter("valoracion")));
		
		try {
			port.valorarEspectaculo(plataforma, espectaculo, usuario, valoracion);
			request.getSession().setAttribute("usuarioLogueado", port.obtenerUsuario(((UsuarioDto)request.getSession().getAttribute("usuarioLogueado")).getNickname()));
			request.setAttribute("Correcto", "Valoracion agregada correctamente.");
		} catch (PlataformaNoExisteException_Exception | EspectaculoNoExisteException_Exception | EspectadorNoExisteException_Exception
				| ValoracionNoValidaException_Exception | UsuarioNoExisteException_Exception e) {
			request.setAttribute("Error", e.getMessage());
		}
		
		request.getRequestDispatcher("DetalleEspectaculo").forward(request, response);
		
//		RequestDispatcher rd = request.getRequestDispatcher(request.getHeader("referer").substring(35));
//		response.setHeader("referer", request.getHeader("referer"));
//		rd.forward(request, response);
		
	}

}
