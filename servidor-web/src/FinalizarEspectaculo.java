import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webservices.EspectaculoNoExisteException_Exception;
import webservices.PlataformaNoExisteException_Exception;

/**
 * Servlet implementation class FinalizarEspectaculo
 */
@WebServlet("/FinalizarEspectaculo")
public class FinalizarEspectaculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarEspectaculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		
		// TODO Auto-generated method stub
		String nomEsp = request.getParameter("espectaculo");
		String plat = request.getParameter("plataforma");
		
		try {
			port.finalizarEspectaculo(plat, nomEsp);
		} catch (PlataformaNoExisteException_Exception | EspectaculoNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		response.sendRedirect("Home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
