package logica;


import java.util.LinkedList;
import java.util.List;
import java.util.List;

import dominio.Artista;
import dominio.Espectador;
import dominio.Usuario;
import dtos.ArtistaDto;
import dtos.EspectaculoDto;
import dtos.EspectadorDto;
import dtos.ImagenDto;
import dtos.UsuarioDto;
import excepciones.AutenticacionException;
import excepciones.UsuarioNoExisteException;

public class ControladorDeUsuarios implements IControladorDeUsuarios{
	
	public ControladorDeUsuarios() {}
	

	@Override
	public LinkedList<String> obtenerNicknamesUsuarios() {
		LinkedList<Usuario> usuarios =  ManejadorDeUsuarios.getInstance().obtenerUsuarios();
		LinkedList<String> nicknames = new LinkedList<String>();
		for (int i = 0; i < usuarios.size(); i++) {
			nicknames.add(usuarios.get(i).getNickname());
		}
		return nicknames;
	}

	@Override
	public void editarArtista(ArtistaDto art, ImagenDto imagen) throws Exception {
		Artista artista = new Artista(
				art.getNickname(),
				art.getNombre(),
				art.getApellido(),
				art.getCorreo(),
				art.getFechaNacimiento(),
				art.getDescripcion(),
				art.getBiografia(),
				art.getSitioWeb(),
				art.getContrasenia());
		ManejadorDeUsuarios.getInstance().editarArtista(artista, imagen);
	}

	@Override
	public void editarEspectador(EspectadorDto esp, ImagenDto imagen) throws Exception {
		Espectador espectador = new Espectador(
				esp.getNickname(),
				esp.getNombre(),
				esp.getApellido(),
				esp.getCorreo(),
				esp.getFechaNacimiento(),
				esp.getContrasenia());
		ManejadorDeUsuarios.getInstance().editarEspectador(espectador, imagen);
	}

	@Override
	public void registrarEspectador(EspectadorDto usuario, ImagenDto imagen) throws Exception {
		Espectador espectador = new Espectador(
				usuario.getNickname(),
				usuario.getNombre(),
				usuario.getApellido(),
				usuario.getCorreo(),
				usuario.getFechaNacimiento(),
				usuario.getContrasenia());
		ManejadorDeUsuarios.getInstance().addEspectador(espectador, imagen);
	}

	@Override
	public void registrarArtista(ArtistaDto usuario, ImagenDto imagen) throws Exception {
		Artista artista = new Artista(
				usuario.getNickname(),
				usuario.getNombre(),
				usuario.getApellido(),
				usuario.getCorreo(),
				usuario.getFechaNacimiento(),
				usuario.getDescripcion(),
				usuario.getBiografia(),
				usuario.getSitioWeb(),
				usuario.getContrasenia());
		ManejadorDeUsuarios.getInstance().addArtista(artista, imagen);
	}

	@Override
	public UsuarioDto obtenerUsuario(String nickname) throws UsuarioNoExisteException {
		return ManejadorDeUsuarios.getInstance().getUsuario(nickname).getData();		
	}



	@Override
	public UsuarioDto login(String keyUser, String contrasenia) throws Exception {
		Usuario usu = ManejadorDeUsuarios.getInstance().getUsuarioPorNickOCorreo(keyUser);
		if (usu.getContrasenia().equals(contrasenia)) {
			return usu.getData();
		} else {
			throw new AutenticacionException("La contrase�a ingresada es incorrecta!");
		}
	}


	@Override
	public void seguir(String seguidor, String seguido) throws Exception {
		ManejadorDeUsuarios manejador = ManejadorDeUsuarios.getInstance();
		Usuario usuarioSeguidor = manejador.getUsuario(seguidor);
		Usuario usuarioSeguido = manejador.getUsuario(seguido);	
		usuarioSeguidor.addSeguido(seguido);
		usuarioSeguido.addSeguidor(seguidor);
		Artista art;
		Espectador esp;
		try {
			 art = (Artista) usuarioSeguidor;
			 manejador.editarArtista(art, new ImagenDto());
		} catch (ClassCastException e1) {
			esp = (Espectador) usuarioSeguidor;
			manejador.editarEspectador(esp, new ImagenDto());
		}
		try {
			 art = (Artista) usuarioSeguido;
			 manejador.editarArtista(art, new ImagenDto());
		} catch (ClassCastException e1) {
			esp = (Espectador) usuarioSeguido;
			manejador.editarEspectador(esp, new ImagenDto());
		}
	}


	@Override
	public void dejarDeSeguir(String seguidor, String seguido) throws Exception {
		ManejadorDeUsuarios manejador = ManejadorDeUsuarios.getInstance();
		Usuario usuarioSeguidor = manejador.getUsuario(seguidor);
		Usuario usuarioSeguido = manejador.getUsuario(seguido);	
		usuarioSeguidor.removeSeguido(seguido);
		usuarioSeguido.removeSeguidor(seguidor);
		Artista art;
		Espectador esp;
		try {
			 art = (Artista) usuarioSeguidor;
			 manejador.editarArtista(art, new ImagenDto());
		} catch (ClassCastException e1) {
			esp = (Espectador) usuarioSeguidor;
			manejador.editarEspectador(esp, new ImagenDto());
		}
		try {
			 art = (Artista) usuarioSeguido;
			 manejador.editarArtista(art, new ImagenDto());
		} catch (ClassCastException e1) {
			esp = (Espectador) usuarioSeguido;
			manejador.editarEspectador(esp, new ImagenDto());
		}
	}
	@Override
	public LinkedList<EspectaculoDto> getEspectaculoAprobadosArtista(String nickname) throws UsuarioNoExisteException{
		ManejadorDeUsuarios manejadorUsuarios = ManejadorDeUsuarios.getInstance();
		Artista artistaLogeado = (Artista) manejadorUsuarios.getUsuario(nickname);
		return artistaLogeado.getEspectaculosAprobados();
	}
	@Override
	public LinkedList<String> getNombreArtistas(){
		ManejadorDeUsuarios manejadorUsuarios = ManejadorDeUsuarios.getInstance();
		LinkedList<Artista> artistasIngresados = manejadorUsuarios.obtenerArtistas();
		LinkedList<String> nombreArtistas = new LinkedList<String>();
		for (int i = 0; i < artistasIngresados.size(); i++) {
			nombreArtistas.add(artistasIngresados.get(i).getNickname());
		}
		return nombreArtistas;
	}
	
	@Override
	public LinkedList<ArtistaDto> getArtistas() {
		LinkedList<ArtistaDto> artistas = new LinkedList<ArtistaDto>();
		ManejadorDeUsuarios manejadorU = ManejadorDeUsuarios.getInstance();
		LinkedList<Artista> listArt = manejadorU.obtenerArtistas();
		listArt.forEach(art -> artistas.add((ArtistaDto) art.getData()));
		return artistas;
	}
	
	@Override
	public LinkedList<EspectadorDto> getEspectadores() {
		LinkedList<EspectadorDto> espectadores = new LinkedList<EspectadorDto>();
		ManejadorDeUsuarios manejadorU = ManejadorDeUsuarios.getInstance();
		LinkedList<Espectador> listEsp = manejadorU.obtenerEspectadores();
		listEsp.forEach(esp -> espectadores.add((EspectadorDto) esp.getData()));
		return espectadores;
	}

	@Override
	public boolean nicknameOCorreoDisponible(String input) {
		try {
			ManejadorDeUsuarios.getInstance().getUsuarioPorNickOCorreo(input);
			return false;
		} catch (UsuarioNoExisteException e) {
			return true;
		}
	}
	
}

