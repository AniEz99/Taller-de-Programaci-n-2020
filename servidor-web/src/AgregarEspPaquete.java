

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webservices.EspectaculoDto;
import webservices.PaqueteDto;

/**
 * Servlet implementation class AgregarEspPaquete
 */
@WebServlet("/AgregarEspPaquete")
public class AgregarEspPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarEspPaquete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		
		String[] platArray = request.getParameterValues("plataforma");
		String paquetenombre = request.getParameter("paquete");
		try {
			List<EspectaculoDto> espectaculosPlataforma = port.obtenerEspectaculosPorPlataforma(platArray[0]).getList();
			PaqueteDto paqueteSeleccionado = port.getPaquete(paquetenombre);
			List<String> espectaculosNoEnPaquete = new LinkedList<String>();
			for(EspectaculoDto espPlat: espectaculosPlataforma) {
				if (!paqueteSeleccionado.getIdEspectaculos().contains(espPlat.getNombre())) {
					espectaculosNoEnPaquete.add(espPlat.getNombre());
				}
			}
			if(espectaculosNoEnPaquete.size() == 0) {espectaculosNoEnPaquete.add("No hay espectaculos en esta plataforma para agregar");}
			request.setAttribute("espectaculos", espectaculosNoEnPaquete);
			request.setAttribute("plataforma", platArray[0]);
			request.setAttribute("paquete", paquetenombre);
			request.getRequestDispatcher("/WEB-INF/AgregarEspPaquete.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		
		
		try {
			if(!request.getParameter("espectaculo").equals("No hay espectaculos en esta plataforma para agregar")) {
				String plat = request.getParameter("plataforma");
				String paq = request.getParameter("paquete");
				String esp = request.getParameter("espectaculo");
				port.agregarEspectaculoAPaquete(paq, plat, esp);
			}
			response.sendRedirect("DetallePaquete?paquete=" + request.getParameter("paquete"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
