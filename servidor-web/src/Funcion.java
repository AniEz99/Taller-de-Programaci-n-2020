

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webservices.EspectaculoNoAceptadoException_Exception;
import webservices.EspectaculoNoExisteException_Exception;
import webservices.EspectadorDto;
import webservices.EspectadorNoExisteException_Exception;
import webservices.FuncionNoExisteException_Exception;
import webservices.PlataformaNoExisteException_Exception;
import webservices.StringList;
import webservices.UsuarioDto;
import webservices.UsuarioNoExisteException_Exception;
import webservices.YaExisteRegistroException_Exception;

/**
 * Servlet implementation class Funcion
 */
@WebServlet("/Funcion")
public class Funcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Funcion() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
    	
		HttpSession session = request.getSession();
		EspectadorDto user = (EspectadorDto) session.getAttribute("usuarioLogueado");
		
		String nomFunc = (String) request.getParameter("funcion");
		String nomEsp = (String) request.getParameter("espectaculo");
		String nomPlat = (String) request.getParameter("plataforma");	
		String accion = (String) request.getParameter("accionDeComprar");
		
		if ((session.getAttribute("usuarioLogueado") != null)) {
			if (accion.equals("registroNormal")) {
				try {
					port.altaRegistroComun(user.getNickname(), nomPlat, nomEsp, nomFunc);
					request.setAttribute("Correcto", "Registro con exito a la funcion: " + nomFunc + ".");
				} catch (PlataformaNoExisteException_Exception | EspectaculoNoExisteException_Exception | EspectadorNoExisteException_Exception | FuncionNoExisteException_Exception | YaExisteRegistroException_Exception | EspectaculoNoAceptadoException_Exception e) {
					request.setAttribute("Error", e.getMessage());
				}
			} else if (accion.equals("registroCanje")) {
				
				String[] nombresFuncionesRegistros = request.getParameterValues("registrosCanjeados");
				StringList nomFuncRegList = new StringList();
				for (String nom : nombresFuncionesRegistros) {
					nomFuncRegList.getList().add(nom);
				}
				try {
					port.altaRegistroConCanje(user.getNickname(), nomPlat, nomEsp, nomFunc, nomFuncRegList);
					request.setAttribute("Correcto", "Registro con exito a la funcion: " + nomFunc + ".");
				} catch (Exception e) {
					request.setAttribute("Error", e.getMessage());
				}
				
			} else if (accion.equals("registroPaquete")) {
				String nombrePaquete = (String) request.getParameter("paqueteUsado");
				try {
					port.altaRegistroPaquete(user.getNickname(), nomPlat, nomEsp, nomFunc, nombrePaquete);
					request.setAttribute("Correcto", "Registro con exito a la funcion: " + nomFunc + ".");
				} catch (PlataformaNoExisteException_Exception | EspectaculoNoExisteException_Exception | EspectadorNoExisteException_Exception | FuncionNoExisteException_Exception | YaExisteRegistroException_Exception e) {
					request.setAttribute("Error", e.getMessage());
				} 
			}
			try {
				session.setAttribute("usuarioLogueado", port.obtenerUsuario(((UsuarioDto)session.getAttribute("usuarioLogueado")).getNickname()));
			} catch (UsuarioNoExisteException_Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("plataforma", nomPlat);
			request.setAttribute("espectaculo", nomEsp);
			request.setAttribute("funcion", nomFunc);
			request.getRequestDispatcher("DetalleFuncion").forward(request, response);
		}
	}
}
