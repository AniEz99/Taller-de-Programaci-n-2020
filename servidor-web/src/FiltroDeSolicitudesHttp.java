import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import webservices.LogActividadDto;
import webservices.UsuarioDto;
import webservices.UsuarioNoExisteException_Exception;
import webservices.YaExisteCategoriaException_Exception;

@WebFilter("/*")
public class FiltroDeSolicitudesHttp  implements Filter{
	
	public void init(FilterConfig arg0) throws ServletException {}  

	@Override
	public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String ip = req.getRemoteAddr();
		StringBuffer requestURL = req.getRequestURL();
		if (req.getQueryString() != null) {
		    requestURL.append("?").append(req.getQueryString());
		}
		String completeURL = requestURL.toString();
		String browser = extractBrowserFromUserAgent(req.getHeader("User-Agent"));
		String os = extractOSFromUserAgent(req.getHeader("User-Agent"));
		
		webservices.PublicadorService service = new webservices.PublicadorService();
		webservices.Publicador port = service.getPublicadorPort();
		HttpSession session = req.getSession();
		
		Cookie[] aux = req.getCookies();
		if (session.getAttribute("usuarioLogueado") == null) {
			if (aux != null) {
				for (int i=0; i < aux.length; i++) {
					if (aux[i].getName().equals("rememberUser")) {
						try {
							UsuarioDto user = port.obtenerUsuario(aux[i].getValue());
							session.setAttribute("usuarioLogueado", user);
						} catch (UsuarioNoExisteException_Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		LogActividadDto log = new LogActividadDto();
		log.setUrl(completeURL);
		log.setBrowser(browser);
		log.setOpSis(os);
		log.setIpAddr(ip);
		
		port.loggearActividad(log);
		
		chain.doFilter(request, res);
	}
	
	private String extractBrowserFromUserAgent(String userAgent) {
		if (userAgent.contains("Chrome") && !userAgent.contains("Edge"))
		{
			return("Chrome");
		} else if (userAgent.contains("Firefox")) {
			return "Firefox";
		} else if (userAgent.contains("Edge")) {
			return "Edge";
		} else if (userAgent.contains("Safari") && !userAgent.contains("Chrome")) {
			return "Safari";
		} else if (userAgent.contains("Windows")) {
			return "Internet Explorer";
		} else {
			return "Navegador desconocido";
		}
	}

	private String extractOSFromUserAgent(String userAgent) {
		if (userAgent.contains("Windows"))
		{
			return("Windows");
		} else if (userAgent.contains("Linux"))
		{
			return("Linux");
		} else if (userAgent.contains("Mac OS"))
		{
			return("iOS");
		} else {
			return "Sistema operativo desconocido";
		}
	}

}
