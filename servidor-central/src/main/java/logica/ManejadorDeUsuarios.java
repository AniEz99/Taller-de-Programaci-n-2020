package logica;

import dominio.Usuario;
import dtos.EspectadorDto;
import dtos.ImagenDto;
import dominio.Artista;
import dominio.Espectaculo;
import dominio.Espectador;
import dominio.Funcion;
import dominio.Paquete;
import dominio.Registro;
import excepciones.CorreoYaTomadoException;
import excepciones.EspectadorNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
 * Manejador de Usuarios (singleton)
 * Usuarios almacenados en Map, utiliza como key el nickname
 */
public class ManejadorDeUsuarios {
	private static ManejadorDeUsuarios instancia;
	private Map<String, Espectador> mapEspectadores;
	private Map<String, Artista> mapArtistas;
	
	/*
	 * Constructor privado
	 */
	private ManejadorDeUsuarios() {
		mapArtistas = new HashMap<String, Artista>();
		mapEspectadores = new HashMap<String, Espectador>();
	}
	
	/*
	 * Devuelve instancia única
	 */
	public static ManejadorDeUsuarios getInstance() {
		if (instancia == null)
			instancia = new ManejadorDeUsuarios();
		return instancia;
	}
	
	/*
	 * Agrega el nuevoUsuario a la coleccion de usuarios.
	 * Si ya existe un usuario con el mismo nick NO lo agrega y retorna false
	 * sino retorna true.
	 */
	public void addArtista(Artista nuevoArtista, ImagenDto imagen) throws Exception {
		String url = null;
		if (imagen.getName() != null) {
			url = Utils.guardarImagen(imagen, "artista_" + nuevoArtista.getNickname());
		} 
		if (existeCorreo(nuevoArtista.getCorreo())) {
			throw new CorreoYaTomadoException("Ya existe un usuario con el correo " + nuevoArtista.getCorreo() + ".");
		}
		if (mapEspectadores.containsKey(nuevoArtista.getNickname())) {
			throw  new UsuarioYaExisteException("Ya existe un usuario con el nickname " + nuevoArtista.getNickname() + ".");
		} else if (!(mapArtistas.putIfAbsent(nuevoArtista.getNickname(), nuevoArtista) == null)) {
			throw  new UsuarioYaExisteException("Ya existe un usuario con el nickname " + nuevoArtista.getNickname() + ".");
		}
		nuevoArtista.setUrlImagen(url);
		
	}
	/*
	 * SOLO USAR EN EL CARGADOR DE DATOS
	 */
	public void addArtista(Artista nuevoArtista) throws Exception {
		if (existeCorreo(nuevoArtista.getCorreo())) {
			throw new CorreoYaTomadoException("Ya existe un usuario con el correo " + nuevoArtista.getCorreo() + ".");
		}
		if (mapEspectadores.containsKey(nuevoArtista.getNickname())) {
			throw  new UsuarioYaExisteException("Ya existe un usuario con el nickname " + nuevoArtista.getNickname() + ".");
		} else if (!(mapArtistas.putIfAbsent(nuevoArtista.getNickname(), nuevoArtista) == null)) {
			throw  new UsuarioYaExisteException("Ya existe un usuario con el nickname " + nuevoArtista.getNickname() + ".");
		}
	}
	
	
	/*
	 * Agrega el nuevoEspectador a la coleccion de espectadores.
	 * Si ya existe un usuario con el mismo nick NO lo agrega y retorna false
	 * sino retorna true.
	 */
	public void addEspectador(Espectador nuevoEspectador, ImagenDto imagen) throws Exception {
		if (existeCorreo(nuevoEspectador.getCorreo())) {
			throw new CorreoYaTomadoException("Ya existe un usuario con el correo " + nuevoEspectador.getCorreo() + ".");
		}
		if (mapArtistas.containsKey(nuevoEspectador.getNickname())) {
			throw  new UsuarioYaExisteException("Ya existe un usuario con el nickname " + nuevoEspectador.getNickname() + ".");
		} else if (!(mapEspectadores.putIfAbsent(nuevoEspectador.getNickname(), nuevoEspectador) == null)) {
			throw new UsuarioYaExisteException("Ya existe un usuario con el nickname " + nuevoEspectador.getNickname() + ".");
		}
		if (imagen.getName() != null) {
			String url = Utils.guardarImagen(imagen, "espectador_" + nuevoEspectador.getNickname());
			nuevoEspectador.setUrlImagen(url);
		} 
	}
	/*
	 * Retorna al usuario con dicho nickname, si no lo encuentra
	 * retorna null
	 */
	public Espectador getEspectador(String nickname) throws EspectadorNoExisteException {
		if (!mapEspectadores.containsKey(nickname)) {
			throw new EspectadorNoExisteException("");
		} else {
			return mapEspectadores.get(nickname);
		}
	}
	
	public Artista getArtista(String nickname) {
		return mapArtistas.get(nickname);
	}	
	/*
	 * Retorna true si existe un usuario con dicho nickname
	 */
	public Usuario getUsuario(String nickname) throws UsuarioNoExisteException {
		Usuario usuario = mapArtistas.get(nickname);
		if (usuario == null) {
			usuario = mapEspectadores.get(nickname);
			if (usuario == null) {
				throw new UsuarioNoExisteException("No existe ningun usuario con el nickname " + nickname + ".");
			}
		}
		return usuario;
	}
	
	public LinkedList<Artista> obtenerArtistas() {
		return new LinkedList<Artista>(mapArtistas.values());
	}
	
	public LinkedList<Espectador> obtenerEspectadores() {
		return new LinkedList<Espectador>(mapEspectadores.values());
	}
	
	public LinkedList<EspectadorDto> getEspectadoresNoRegistradosEnFuncion(String nombreFuncion) {
		LinkedList<EspectadorDto> resultado = new LinkedList<EspectadorDto>();
		for (Map.Entry<String, Espectador> entry : mapEspectadores.entrySet()) {
			if (!entry.getValue().estaRegistradoA(nombreFuncion)) // Si espectador no está registrado a funcion
				resultado.add(entry.getValue().getDataEspectador());
		}
		return resultado;
	}
	
	public LinkedList<Usuario> obtenerUsuarios() {
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>(mapArtistas.values());
		usuarios.addAll(new LinkedList<Usuario>(mapEspectadores.values()));
		return usuarios;
	}
	
	public void editarArtista(Artista artista, ImagenDto imagen) throws Exception {
		Artista artistaOriginal = mapArtistas.get(artista.getNickname());
		if (artistaOriginal == null) {
			throw new UsuarioNoExisteException("No existe ningun usuario con el nickname " + artista.getNickname() + ".");
		}
		/*
		espectaculos = new LinkedList<Espectaculo>();
		funciones = new LinkedList<Funcion>();
		seguidores = new LinkedList<String>();
		paquetes = new LinkedList<Paquete>();
		seguidos = new LinkedList<String>();*/
		
		
		String url = artistaOriginal.getUrlImagen();
		
		LinkedList<Espectaculo> espectaculos = artistaOriginal.getEspectaculos();
		LinkedList<Funcion> funciones = artistaOriginal.getFunciones();
		LinkedList<String> seguidores = artistaOriginal.getSeguidores();
		LinkedList<String> seguidos = artistaOriginal.getSeguidos();
		LinkedList<Paquete> paquetes = artistaOriginal.getPaquetes();
		
		mapArtistas.put(artista.getNickname(), artista);
		if (imagen.getName() != null) {
			url = Utils.guardarImagen(imagen, "espectador_" + artista.getNickname());
		}
		
		artista.setUrlImagen(url);
		artista.setEspectaculos(espectaculos);
		artista.setFunciones(funciones);
		artista.setSeguidores(seguidores);
		artista.setSeguidos(seguidos);
		artista.setPaquetes(paquetes);
	}

	public void editarEspectador(Espectador espectador, ImagenDto imagen) throws Exception {
		Espectador espectadorOriginal = mapEspectadores.get(espectador.getNickname());
		if (espectadorOriginal == null) {
			throw new UsuarioNoExisteException("No existe ningun usuario con el nickname " + espectador.getNickname() + ".");
		}
		
		LinkedList<Registro> registros = espectadorOriginal.obtenerRegistros();
		LinkedList<Paquete> paquetes = espectadorOriginal.obtenerPaquetes();
		Map<String, Date> comprasPaquetes = espectadorOriginal.obtenerComprasPaquetes();
		LinkedList<String> seguidores = espectadorOriginal.getSeguidores();
		LinkedList<String> seguidos = espectadorOriginal.getSeguidos();
		
		String url = espectadorOriginal.getUrlImagen();
		mapEspectadores.put(espectador.getNickname(), espectador);
		if (imagen.getName() != null) {
			url = Utils.guardarImagen(imagen, "espectador_" + espectador.getNickname());
		}
		
		espectador.setUrlImagen(url);
		espectador.setRegistros(registros);
		espectador.setPaquetes(paquetes);
		espectador.setComprasPaquete(comprasPaquetes);
		espectador.setSeguidores(seguidores);
		espectador.setSeguidos(seguidos);
	}
	
	private Boolean existeCorreo(String correo) {
		return (mapEspectadores.entrySet().stream().filter(p -> p.getValue().getCorreo().equals(correo)).count() > 0)
				|| 
				(mapArtistas.entrySet().stream().filter(p -> p.getValue().getCorreo().equals(correo)).count() > 0);
	}
	
	public Usuario getUsuarioPorNickOCorreo(String input) throws UsuarioNoExisteException {
		Usuario usuario = mapArtistas.get(input);
		if (usuario == null) {
			usuario = mapEspectadores.get(input);
			if (usuario == null) {
				for (Artista c : mapArtistas.values()) {
					if (c.getCorreo().equals(input)) {
						usuario = c;
						return usuario;
					}
				}
				for (Espectador c : mapEspectadores.values()) {
					if (c.getCorreo().equals(input)) {
						usuario = c;
						return usuario;
					}
				}
				throw new UsuarioNoExisteException("No existe ningun usuario con el nickname o correo " + input + ".");
			}
		}
		return usuario;
	}
	
}
