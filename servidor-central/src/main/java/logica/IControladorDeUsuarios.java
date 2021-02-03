package logica;

import java.util.LinkedList;
import java.util.List;

import dtos.ArtistaDto;
import dtos.EspectaculoDto;
import dtos.EspectadorDto;
import dtos.ImagenDto;
import dtos.UsuarioDto;
import excepciones.UsuarioNoExisteException;

public interface IControladorDeUsuarios {
	public void registrarEspectador(EspectadorDto usuario, ImagenDto imagen) throws Exception;
	public void registrarArtista(ArtistaDto artista, ImagenDto imagen) throws Exception;
	public LinkedList<String> obtenerNicknamesUsuarios();
	public void editarArtista(ArtistaDto artista, ImagenDto imagen) throws UsuarioNoExisteException, Exception;
	public void editarEspectador(EspectadorDto espectador, ImagenDto imagen) throws UsuarioNoExisteException, Exception;
	public UsuarioDto obtenerUsuario(String nickname) throws UsuarioNoExisteException;
	public UsuarioDto login(String usuario, String contrasenia) throws Exception;
	public void seguir(String seguidor, String seguido) throws UsuarioNoExisteException, Exception;
	public void dejarDeSeguir(String seguidor, String seguido) throws UsuarioNoExisteException, Exception;
	public LinkedList<EspectaculoDto> getEspectaculoAprobadosArtista(String nickname) throws UsuarioNoExisteException;
	public LinkedList<String> getNombreArtistas();
	/*
	 * Retorna lista de artistas dto
	 */
	public LinkedList<ArtistaDto> getArtistas();
	/*
	 * Retorna lista de espectadores dto
	 */
	public LinkedList<EspectadorDto> getEspectadores();
	
	public boolean nicknameOCorreoDisponible(String input);
}
