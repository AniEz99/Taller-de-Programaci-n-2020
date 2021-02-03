

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webservices.EspectaculoDto;
import webservices.EspectaculoNoExisteException_Exception;
import webservices.PlataformaNoExisteException_Exception;

/**
 * Servlet implementation class ConsultaEspFinalizado
 */
@WebServlet("/ConsultaEspFinalizado")
public class ConsultaEspFinalizado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaEspFinalizado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		
		HttpSession session = request.getSession();
		
		try {
			String plat, nomEsp;
			plat = request.getParameter("plataforma");
			nomEsp = request.getParameter("espectaculo");
			EspectaculoDto esp = port.obtenerDatosEspectaculo(plat, nomEsp);
			request.getSession().setAttribute("espectaculoDto", esp);
			request.getRequestDispatcher("/WEB-INF/ConsultaEspFinalizado.jsp").forward(request, response);	
		} catch (EspectaculoNoExisteException_Exception | PlataformaNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
