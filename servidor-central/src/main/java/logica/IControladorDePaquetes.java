package logica;

import java.util.LinkedList;
import java.util.List;

import dtos.EspectaculoDto;
import dtos.ImagenDto;
import dtos.PaqueteDto;
import dtos.PlataformaDto;
import excepciones.EspectaculoNoExisteException;
import excepciones.EspectaculoYaExisteException;
import excepciones.EspectadorNoExisteException;
import excepciones.NoExistePaqueteException;
import excepciones.PaqueteYaExisteException;
import excepciones.PlataformaNoExisteException;

public interface IControladorDePaquetes {

	public void crearPaquete(PaqueteDto infopaquete, ImagenDto imagen, String nArt) throws Exception;
	
	public LinkedList<EspectaculoDto> obtenerEspectaculosDePaquete(String nombrePack) throws NoExistePaqueteException;
	
	/*
	 * Lista los paquetes del Sistema
	 */
	public LinkedList<PaqueteDto> obtenerPaquetes();
	
	/*
	 * Lista las plataformas del Sistema
	 */
	public LinkedList<PlataformaDto> obtenerPlataformas();
	
	/*
	 *	Lista los espectaculos de una plataforma que no pertenecen al paquete
	 */
	public LinkedList<EspectaculoDto> obtenerEspectaculosSinPaquete(String nombrePlat, String nombrePaq) throws PlataformaNoExisteException;
	
	/*
	 * Agrega el espectaculo de la plataforma al paquete
	 */
	void agregarEspectaculoAPaquete(String nombrePaq, String nombrePlat, String nombreEsp) throws PlataformaNoExisteException, EspectaculoNoExisteException, NoExistePaqueteException, PaqueteYaExisteException, EspectaculoYaExisteException;

	/*
	 * Retorna la lista de paquetes que posean dicha query
	 */
	public LinkedList<PaqueteDto> buscarPaquetes(String query);
	
	// Retorna paquete con nombre nPaq
	public PaqueteDto getPaquete(String nPaq) throws NoExistePaqueteException;

	public LinkedList<String> getCategoriasDePaquete(String nombrePack) throws NoExistePaqueteException;
	
	/*
	 * Retorna los paquetes con algun espectaculo en la plataforma
	 */
	public LinkedList<PaqueteDto> obtenerPaquetesPorPlataforma(String nomPlat);
	
	/*
	 * Retorna los paquetes con la categoria
	 */
	public LinkedList<PaqueteDto> obtenerPaquetesPorCategoria(String nomCat);

	public void comprarPaquete(String nomUsuario, String nomPaquete) throws EspectadorNoExisteException, PaqueteYaExisteException, NoExistePaqueteException;
}

	