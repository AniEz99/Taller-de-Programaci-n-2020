

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webservices.ArtistaDto;
import webservices.CategoriaDto;
import webservices.EspectaculoDto;
import webservices.EspectaculoNoExisteException_Exception;
import webservices.Estado;
import webservices.FuncionDto;
import webservices.PaqueteDto;
import webservices.PlataformaNoExisteException_Exception;


/**
 * Servlet implementation class DetalleEspectaculo
 */
@WebServlet("/DetalleEspectaculo")
public class DetalleEspectaculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetalleEspectaculo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /* El metodo get va a estar asociado a la seleccion de un espectaculo
     * mediante el caso de uso "Consulta espectaculo" en el que se proporciona
     * el nombre de la plataforma y el nombre del espectaculo
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		
		HttpSession session = request.getSession();
		try {
				
			String plat, nomEsp;
			plat = request.getParameter("plataforma");
			nomEsp = request.getParameter("espectaculo");
			request.setAttribute("plataforma", plat);
			
			EspectaculoDto esp = port.obtenerDatosEspectaculo(plat, nomEsp);
			List<CategoriaDto> listaCategorias =  esp.getCategoriasAsociadas();
			List<FuncionDto> listaFunciones =  esp.getFuncionesAsociadas();
			List<PaqueteDto> listaPaquetes =  esp.getPaquetesAsociados();
			if (session.getAttribute("usuarioLogueado") != null && session.getAttribute("usuarioLogueado").getClass() == webservices.ArtistaDto.class) {
				ArtistaDto artistaLogueado = (ArtistaDto) session.getAttribute("usuarioLogueado");
				if(esp.getOrganizador().getNickname().equals(artistaLogueado.getNickname()) && esp.getEstado() == Estado.ACEPTADO) {
					request.setAttribute("puedeFinalizar", true);
				}
				else {
					request.setAttribute("puedeFinalizar", false);
				}
			}else {request.setAttribute("puedeFinalizar", false);}	
						

			request.setAttribute("categorias", listaCategorias);
			request.setAttribute("funciones", listaFunciones);
			request.setAttribute("paquetes", listaPaquetes);
			request.setAttribute("espectaculo", esp); 
			
			if (request.getParameterMap().containsKey("tipoGet")) {
				request.getRequestDispatcher("WEB-INF/Mobile/DetalleEspectaculoMobile.jsp").forward(request, response);
			}
			request.getRequestDispatcher("WEB-INF/DetalleEspectaculo.jsp").forward(request, response);
		} catch (PlataformaNoExisteException_Exception ex) {
			session.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
		} catch (EspectaculoNoExisteException_Exception ex) {
			session.setAttribute("error", ex.getMessage());
			request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
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
