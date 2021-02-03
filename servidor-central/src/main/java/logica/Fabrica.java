package logica;

public class Fabrica {

	public static IControladorDeUsuarios getControladorDeUsuarios() {
		return new ControladorDeUsuarios();
	}
	
	public static IControladorDePlataformas getControladorPlataformas() {
		return new ControladorDePlataformas();
	}
	
	public static IControladorDeEspectaculos getControladorEspectaculos() {
		return new ControladorDeEspectaculos();
	}
	
	public static IControladorDePaquetes getControladorDePaquetes() {
		return new ControladorDePaquetes();
	}
	
	public static IControladorDeCategorias getControladorDeCategorias() {
		return new ControladorDeCategorias();
	}
	
	public static ILogger getLogger() {
		return new Logger();
	}
}
