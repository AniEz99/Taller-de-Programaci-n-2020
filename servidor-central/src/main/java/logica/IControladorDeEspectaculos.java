package logica;

import dtos.ArtistaDto;
import dtos.CategoriaDto;
import dtos.EspectaculoDto;
import dtos.FuncionDto;
import dtos.ImagenDto;
import dtos.PaqueteDto;
import dtos.PlataformaDto;
import dtos.EspectadorDto;
import dtos.RegistroDto;
import dtos.ValoracionDto;
import excepciones.YaExisteCategoriaException;
import excepciones.YaExisteRegistroException;
import excepciones.CategoriaNoExisteException;
import excepciones.EspectaculoNoAceptadoException;
import excepciones.EspectaculoNoExisteException;
import excepciones.EspectaculoYaExisteException;
import excepciones.EspectadorNoExisteException;
import excepciones.FaltanRegistroException;
import excepciones.FuncionNoExisteException;
import excepciones.FuncionYaExisteException;
import excepciones.MinEspMayorAMaxEspException;
import excepciones.PlataformaNoExisteException;
import excepciones.RegistroNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.ValoracionNoValidaException;

import java.util.List;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.LinkedList;

import dominio.Plataforma;
public interface IControladorDeEspectaculos {
	
	public void confirmarAltaEspectaculo(String nPlat, String nArt, String nombre, String descripcion, int duracionHoras, int duracionMinutos, int minEspectadores, int maxEspectadores, String url, float costo, Date fechaRegistro, ImagenDto imagen, LinkedList<CategoriaDto> categorias) throws EspectaculoYaExisteException, UsuarioNoExisteException, PlataformaNoExisteException, MinEspMayorAMaxEspException, CategoriaNoExisteException, YaExisteCategoriaException, Exception;
	public void cancelarAltaEspectaculo();
	public LinkedList<EspectaculoDto> obtenerEspectaculosPorPlataforma(String plataforma) throws PlataformaNoExisteException;
	public EspectaculoDto obtenerDatosEspectaculo(String plataforma, String espectaculo) throws PlataformaNoExisteException, EspectaculoNoExisteException;
	public LinkedList<EspectaculoDto> ingresarPlataforma(String urlPlataforma) throws PlataformaNoExisteException;
	public LinkedList<FuncionDto> seleccionarEspectaculoYRetornarFunciones(String nombreEspectaculo, String nombrePlataforma) throws EspectaculoNoExisteException, PlataformaNoExisteException;
	public FuncionDto seleccionarFuncion(String nombreFuncion, String nombreEspectaculo, String nombrePlataforma) throws PlataformaNoExisteException, EspectaculoNoExisteException;
	public LinkedList<ArtistaDto> obtenerArtistas() ;
	public void confirmarAltaFuncion(String nombreFunc, int hora, int min, Date fecha, LinkedList<String> artistas, EspectaculoDto espectaculo, PlataformaDto plataforma, String imagen) throws FuncionYaExisteException, PlataformaNoExisteException, PlataformaNoExisteException, EspectaculoNoExisteException;
	public boolean confirmarAltaFuncion2(String nombreFunc, int hora, int min, Date fecha, LinkedList<String> artistasInvitados, String nombreEspectaculo, String nickArtista, ImagenDto imagen) throws Exception;
	public void finalizarEspectaculo(String plat, String nomEsp) throws PlataformaNoExisteException, EspectaculoNoExisteException; 
	
	
	/* -------- Registro a Función de Espectáculo ------- */
	
	/*
	 * Retorna los Data de cada plataforma en el sistema
	 */
	public LinkedList<PlataformaDto> obtenerPlataformas();
	
	/*
	 * Retorna los espectaculos aceptados de la plataforma indicada
	 */
	LinkedList<EspectaculoDto> obtenerEspectaculosAceptadosPorPlataforma(String plataforma) throws PlataformaNoExisteException;
	
	/*
	 * Lista las funciones vigentes y que no esten llenas que posee el espectaculo 
	 */
	public LinkedList<FuncionDto> obtenerFuncionesVigentesDeEspectaculo(String nombrePlataforma, String nombreEspectaculo) throws PlataformaNoExisteException, EspectaculoNoExisteException;
	
	/*
	 * Lista a todos los espectadores no registrados en una funcion 
	 */
	public LinkedList<EspectadorDto> obtenerEspectadoresNoRegistradosEnFuncion(String nombreFuncion);
	
	/*
	 * Lista los Paquetes de Espectador que posean al Espectaculo y no sean usados
	 */
	public LinkedList<PaqueteDto> obtenerPaquetesEspectadorEspectaculo(String nickname) throws EspectadorNoExisteException;
	
	/*
	 * Lista a los RegistroDto de el espectador
	 * [Es necesario saber la data del registro para esta funcionalidad?
	 *  Si el espectador esta canjeando registros antiguos no le tendria que importar 
	 *  en que funcion fueron utilizados.
	 *  Tendría que ser automatico (?]
	 */
	public LinkedList<RegistroDto> obtenerRegistrosEspectador(String nickname) throws EspectadorNoExisteException;
	
	/*
	 * Se realiza una alta de registro del espectador a la funcion y se canjean los registros 
	 * idReg1..idReg3. El nuevo registro tendra asociados a dichos registros (y viceversa?).
	 * Los registros quedan deshabilitados para reeuso
	 * Retorna true si se realiza correctamente, sino false
	 */
	public void altaRegistroConCanje(String nickname, String nombrePlat, String nombreEsp, String nombreFuncion, LinkedList<String> nombreFuncionesRegistro) throws PlataformaNoExisteException, EspectaculoNoExisteException, FuncionNoExisteException, EspectadorNoExisteException, YaExisteRegistroException, RegistroNoExisteException, FaltanRegistroException;
	
	/*
	 *  Se realiza una alta de registro del espectador a la funcion y se canjean el paquete. No se permitirá
	 *  canjear el espectaculo de este paquete devuelta.
	 *  Si la operación es exitosa retorna true, sino false
	 */
	public void altaRegistroPaquete(String nickname, String nombrePlat, String nombreEsp, String nombreFuncion, String nombrePaquete) throws PlataformaNoExisteException, EspectaculoNoExisteException, FuncionNoExisteException, EspectadorNoExisteException, YaExisteRegistroException;
	
	/*
	 * Se realiza un alta de registro del espectador a la función.
	 * Si la operación es exitosa retorna true, sino false.
	 */
	public void altaRegistroComun(String nickname, String nombrePlat, String nombreEsp, String nombreFuncion) throws PlataformaNoExisteException, EspectaculoNoExisteException, FuncionNoExisteException, EspectadorNoExisteException, YaExisteRegistroException, FuncionNoExisteException, EspectadorNoExisteException, YaExisteRegistroException, EspectaculoNoAceptadoException;
	
	
	/* ------------ FIN Registro a funcion -------------- */
	

	/* ---------- Aceptar/Rechazar espectaculo ---------- */
	
	/*
	 * Retorna los Data de cada plataforma en el sistema
	 */
	//public LinkedList<PlataformaDto> obtenerPlataformas();
	
	/*
	 * Se retorna una lista de todos los espectaculos de la plataforma que esten
	 * en estado ingresado
	 */
	public LinkedList<EspectaculoDto> obtenerEspectaculosIngresados(String nomPlat) throws PlataformaNoExisteException;
	
	/*
	 * Se acepta al espectaculo de la plataforma indicada
	 */
	public void aceptarEspectaculo(String nomPlat, String nomEsp) throws EspectaculoNoExisteException, PlataformaNoExisteException;
	
	/*
	 * Se cancela al espectaculo de la plataforma indicada
	 */
	public void cancelarEspectaculo(String nomPlat, String nomEsp) throws EspectaculoNoExisteException, PlataformaNoExisteException;
	
	/* -------- Fin Aceptar/Rechazar espectaculo -------- */
	
	
	/* ------------ Busqueda de espectáculos ------------ */
	
	/*
	 * Retorna una lista de EspectaculoDto con espectaculos que tengan en el nombre
	 * o descripción ese fragmento de query
	 */
	public LinkedList<EspectaculoDto> buscarEspectaculos(String query);
	
	/* ---------- Fin Busqueda de espectáculos ---------- */
	 
	
	public void altaCategoria(String nombreCat) throws YaExisteCategoriaException;
	
	/*
	 * Se listan las categorías del sistema
	 */
	LinkedList<CategoriaDto> listarCategorias();
	
	/*
	 * Retorna una lista de los espectáculos del sistema que tienen dicha categoria
	 */
	public LinkedList<EspectaculoDto> obtenerEspectaculosPorCategoria(String nomCat);
	
	LinkedList<RegistroDto> obtenerTodosRegistrosEspectador(String nickname) throws EspectadorNoExisteException;
	
	public void marcarEspectaculoFavorito(String usuario, String plataforma, String espectaculo) throws PlataformaNoExisteException, EspectaculoNoExisteException, EspectadorNoExisteException;
	public void desmarcarEspectaculoFavorito(String usuario, String plataforma, String espectaculo) throws PlataformaNoExisteException, EspectaculoNoExisteException, EspectadorNoExisteException;
	
	public void valorarEspectaculo(String plataforma, String espectaculo, String usuario, ValoracionDto valoracion) throws PlataformaNoExisteException, EspectaculoNoExisteException, EspectadorNoExisteException, ValoracionNoValidaException;
	
	public void guardarPremios(String descPremio, int cantPremio, String nomEspectaculo, String nomPlataforma) throws PlataformaNoExisteException, EspectaculoNoExisteException;
	
	public void hacerSorteoPremio(String nomEspectaculo, String nomPlataforma, String nomFuncion) throws PlataformaNoExisteException, EspectaculoNoExisteException, FuncionNoExisteException;
	
	public void guardarUrlVideo(String urlVideo, String nomPlataforma, String nomEspectaculo) throws PlataformaNoExisteException, EspectaculoNoExisteException;
	
	public byte[] generarPdf(String nomPremio, String nomEspec, String nomFunc, String nomEsp, String fecha,
			String fechaFin);
	
}
