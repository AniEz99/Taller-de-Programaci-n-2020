

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webservices.ArtistaDto;
import webservices.CategoriaDto;
import webservices.EspectaculoDto;
import webservices.EspectaculoNoExisteException_Exception;
import webservices.EspectadorDto;
import webservices.FuncionDto;
import webservices.PaqueteDto;
import webservices.PlataformaNoExisteException_Exception;


/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /*
     * Cuando se pide página de busqueda
     */
    private void Busqueda(HttpServletRequest request, HttpServletResponse response) {
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		
		List<EspectaculoDto> espc = port.buscarEspectaculos(request.getParameter("busqueda")).getList();
		List<PaqueteDto> paqs = port.buscarPaquetes(request.getParameter("busqueda")).getList();
		List<CategoriaDto> cats = port.getCategorias().getList();
		List<String> plats = port.getNombrePlataformas().getList();
		request.setAttribute("query", request.getParameter("busqueda"));
		request.setAttribute("espectaculos", espc);
		request.setAttribute("paquetes", paqs);
		request.setAttribute("categorias", cats);
		request.setAttribute("plataformas", plats);
		try {
			request.getRequestDispatcher("/WEB-INF/Search.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*
     * Cuando se pide un listado
     */
    private void Listado(HttpServletRequest request, HttpServletResponse response) {
    	webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		
		String tipoLista = request.getParameter("tipoLista");
    	switch(tipoLista) {
    	case "paquetes":
    		List<PaqueteDto> paqs = port.obtenerPaquetes().getList();
    		request.setAttribute("paquetes", paqs);
    		try {
				request.getRequestDispatcher("/WEB-INF/Listado.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		break;
    	case "plataformas":
    		String plataforma = request.getParameter("plataforma");
    		List<EspectaculoDto> espectaculos = new LinkedList<EspectaculoDto>();
    		try {
				espectaculos = port.obtenerEspectaculosAceptadosPorPlataforma(plataforma).getList();
			} catch (PlataformaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		List<PaqueteDto> paquetes = port.obtenerPaquetesPorPlataforma(plataforma).getList();
    		request.setAttribute("paquetes", paquetes);
    		request.setAttribute("espectaculos", espectaculos);
    		try {
				request.getRequestDispatcher("/WEB-INF/Listado.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		break;
    	case "categorias":
    		String categoria = request.getParameter("categoria");
    		List<EspectaculoDto> espectacs = port.obtenerEspectaculosPorCategoria(categoria).getList();
    		List<PaqueteDto> paquets = port.obtenerPaquetesPorCategoria(categoria).getList();
    		request.setAttribute("paquetes", paquets);
    		request.setAttribute("espectaculos", espectacs);
    		try {
				request.getRequestDispatcher("/WEB-INF/Listado.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		break;
    	case "usuarios":
    		List<ArtistaDto> artistas = port.getArtistas().getList();
    		List<EspectadorDto> espectadores = port.getEspectadores().getList();
    		
    		request.setAttribute("artistas", artistas);
    		request.setAttribute("espectadores", espectadores);
    		
    		try {
				request.getRequestDispatcher("/WEB-INF/Listado.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		break;
    	case "espectaculos":
    		try {
    			List<String> nplats = port.getNombrePlataformas().getList();
        		List<EspectaculoDto> resp = new LinkedList<EspectaculoDto>();
        		for (String p : nplats) {
        			List<EspectaculoDto> esps = port.obtenerEspectaculosAceptadosPorPlataforma(p).getList();
        			resp.addAll(esps);
        		}
        		request.setAttribute("espectaculos", resp);
				request.getRequestDispatcher("/WEB-INF/Mobile/ListadoMobile.jsp").forward(request, response);
			} catch (ServletException | IOException | PlataformaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		break;
    	case "funciones":
    		try {
    			List<String> nplats = port.getNombrePlataformas().getList();
        		List<FuncionDto> resf = new LinkedList<FuncionDto>();
        		for (String p : nplats) {
        			List<EspectaculoDto> esps = port.obtenerEspectaculosAceptadosPorPlataforma(p).getList();
        			for (EspectaculoDto e : esps) {
            			EspectaculoDto esp = port.obtenerDatosEspectaculo(p, e.getNombre());
            			resf.addAll(esp.getFuncionesAsociadas());
        			}
        		}
        		request.setAttribute("funciones", resf);
				request.getRequestDispatcher("/WEB-INF/Mobile/ListadoMobile.jsp").forward(request, response);
			} catch (ServletException | IOException | PlataformaNoExisteException_Exception | EspectaculoNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		break;
    	}
    	
    		
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipoGet = request.getParameter("tipoGet");
		switch(tipoGet) {
		case "busqueda":
			Busqueda(request,response);
			break;
		case "listado":
			Listado(request,response);
			break;
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
