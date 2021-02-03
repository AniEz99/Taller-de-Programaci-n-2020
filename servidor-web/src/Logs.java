

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webservices.AutenticacionException_Exception;
import webservices.LogActividadDto;

/**
 * Servlet implementation class Logs
 */
@WebServlet("/Logs")
public class Logs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logs() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String llave = request.getParameter("llave");
		if (llave == null) {
			request.setAttribute("Error", new webservices.PublicadorService().getPublicadorPort().generarUrlAccesoLogs());
			request.getRequestDispatcher("Home").forward(request, response);
		}
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		List<LogActividadDto> logs;
		try {
			logs = port.getLogs(llave).getList();
			request.setAttribute("logs", logs);
			request.getRequestDispatcher("/WEB-INF/LogsDashboard.jsp").forward(request, response);
		} catch (AutenticacionException_Exception e) {
			request.setAttribute("Error", e.getMessage());
			request.getRequestDispatcher("Home").forward(request, response);
		}
				
	}


}
