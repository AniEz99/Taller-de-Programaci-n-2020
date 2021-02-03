package webservices;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import dtos.ArtistaDto;
import dtos.EspectaculoDto;
import dtos.EspectadorDto;
import dtos.FuncionDto;
import dtos.ImagenDto;
import dtos.LogActividadDto;
import dtos.PaqueteDto;
import dtos.PlataformaDto;
import dtos.UsuarioDto;
import dtos.ValoracionDto;
import excepciones.AutenticacionException;
import excepciones.CamposIncompletosException;
import excepciones.CategoriaNoExisteException;
import excepciones.EspectaculoNoAceptadoException;
import excepciones.EspectaculoNoExisteException;
import excepciones.EspectaculoYaExisteException;
import excepciones.EspectadorNoExisteException;
import excepciones.FaltanRegistroException;
import excepciones.FuncionNoExisteException;
import excepciones.FuncionYaExisteException;
import excepciones.MinEspMayorAMaxEspException;
import excepciones.NoExistePaqueteException;
import excepciones.PaqueteYaExisteException;
import excepciones.PlataformaNoExisteException;
import excepciones.PlataformaYaExisteException;
import excepciones.RegistroNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.ValoracionNoValidaException;
import excepciones.YaExisteCategoriaException;
import excepciones.YaExisteRegistroException;
import logica.CargadorDeDatosDePrueba;
import logica.Fabrica;
import logica.Utils;
import logica.Utils.ArtistaDtoList;
import logica.Utils.CategoriaDtoList;
import logica.Utils.EspectaculoDtoList;
import logica.Utils.EspectadorDtoList;
import logica.Utils.FuncionDtoList;
import logica.Utils.LogActividadDtoList;
import logica.Utils.PaqueteDtoList;
import logica.Utils.PlataformaDtoList;
import logica.Utils.RegistroDtoList;
import logica.Utils.StringList;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {
	private Endpoint endpoint = null;
	
	public Publicador(){};
	
	@WebMethod(exclude = true)
	public void publicar() {
		/* Se cargan los datos*/
		try {
			new CargadorDeDatosDePrueba();
		} catch (Exception e) {
			System.out.print(e);
		}
		Utils.definirRutaImagenes(System.getProperty("user.dir")+"/webservices/");
		endpoint = Endpoint.publish("http://localhost:8090/publicador", this);
		System.out.print("Servidor central en ejecución");
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
	/*
	 * Implementacion de imagenes
	 */
	
	
	@WebMethod
    public byte[] getFile(@WebParam(name = "fileName") String name)
                    throws  IOException {
        byte[] byteArray = null;
        try {
                File f = new File(Utils.obtenerRutaImagenes() + "/image-storage/" + name);
                @SuppressWarnings("resource")
				FileInputStream streamer = new FileInputStream(f);
                byteArray = new byte[streamer.available()];
                streamer.read(byteArray);
        } catch (IOException e) {
                throw e;
        }
        return byteArray;
    }

	
	/*
	 * Implementación de operaciones de controlador de Usuarios
	 */
	
	@WebMethod
	public byte[] generarPdf (String nomPremio, String nomEspec, String nomFunc, String nomEsp, String fecha, String fechaFin) {
		return Fabrica.getControladorEspectaculos().generarPdf(nomPremio, nomEspec, nomFunc, nomEsp, fecha, fechaFin);
	}
	
	@WebMethod
	public void registrarEspectador(EspectadorDto usuario, ImagenDto imagen) throws UsuarioNoExisteException, Exception {
		 Fabrica.getControladorDeUsuarios().registrarEspectador(usuario, imagen);
	}
	
	@WebMethod
	public void registrarArtista(ArtistaDto artista, ImagenDto imagen) throws Exception {
		Fabrica.getControladorDeUsuarios().registrarArtista(artista, imagen);
	}
	
	@WebMethod
	public StringList obtenerNicknamesUsuarios() {
		StringList list = new StringList();
		list.setList(Fabrica.getControladorDeUsuarios().obtenerNicknamesUsuarios());
		return list;
	}
	
	@WebMethod
	public void editarArtista(ArtistaDto artista, ImagenDto imagen) throws UsuarioNoExisteException, Exception {
		Fabrica.getControladorDeUsuarios().editarArtista(artista, imagen);
	}
	
	@WebMethod	
	public void editarEspectador(EspectadorDto espectador, ImagenDto imagen) throws UsuarioNoExisteException, Exception {
		Fabrica.getControladorDeUsuarios().editarEspectador(espectador, imagen);
	}

	@WebMethod
	public UsuarioDto obtenerUsuario(String nickname) throws UsuarioNoExisteException {
		return Fabrica.getControladorDeUsuarios().obtenerUsuario(nickname);
	}
	
	@WebMethod
	public UsuarioDto login(String usuario, String contrasenia) throws Exception {
		return Fabrica.getControladorDeUsuarios().login(usuario, contrasenia);
	}
	
	@WebMethod
	public void seguir(String seguidor, String seguido) throws UsuarioNoExisteException, Exception {
		Fabrica.getControladorDeUsuarios().seguir(seguidor, seguido);
	}
	
	@WebMethod
	public void dejarDeSeguir(String seguidor, String seguido) throws UsuarioNoExisteException, Exception {
		Fabrica.getControladorDeUsuarios().dejarDeSeguir(seguidor, seguido);
	}
	
	@WebMethod
	public EspectaculoDtoList getEspectaculoAprobadosArtista(String nickname) throws UsuarioNoExisteException {
		EspectaculoDtoList list = new EspectaculoDtoList();
		list.setList(Fabrica.getControladorDeUsuarios().getEspectaculoAprobadosArtista(nickname));
		return list;
	}
	
	@WebMethod
	public StringList getNombreArtistas() {
		StringList list = new StringList();
		list.setList(Fabrica.getControladorDeUsuarios().getNombreArtistas());
		return list;
	}
	
	@WebMethod
	public ArtistaDtoList getArtistas() {
		ArtistaDtoList list = new ArtistaDtoList();
		list.setList(Fabrica.getControladorDeUsuarios().getArtistas());
		return list;
	}
	
	@WebMethod
	public EspectadorDtoList getEspectadores() {
		EspectadorDtoList list = new EspectadorDtoList();
		list.setList(Fabrica.getControladorDeUsuarios().getEspectadores());
		return list;
	}
	
	/*
	 * Implementacion de operaciones de controlador de Plataformas
	 */
	
	@WebMethod
	public void altaPlataforma(PlataformaDto infoPlataforma) throws PlataformaYaExisteException, CamposIncompletosException {
		Fabrica.getControladorPlataformas().altaPlataforma(infoPlataforma);
	}
	
	@WebMethod
	public StringList getNombrePlataformas() {
		StringList lista = new StringList();
		lista.setList(Fabrica.getControladorPlataformas().getNombrePlataformas());
		return lista;
	}
	
	/*
	 * Implementacion de operaciones de controlador de Paquetes
	 */
	
	@WebMethod
	public void crearPaquete(PaqueteDto infopaquete, ImagenDto imagen, String nArt) throws Exception {
		Fabrica.getControladorDePaquetes().crearPaquete(infopaquete, imagen, nArt);
	}
	
	/*
	 * Sirve para que el swing pueda crear un paquete sin artista (Ver bien casos de uso)
	 */
	@WebMethod
	public void crearPaqueteSinArtista(PaqueteDto infopaquete, ImagenDto imagen) throws Exception {
		Fabrica.getControladorDePaquetes().crearPaquete(infopaquete, imagen, null);
	}
	
	@WebMethod
	public EspectaculoDtoList obtenerEspectaculosDePaquete(String nombrePack) throws NoExistePaqueteException {
		EspectaculoDtoList list = new EspectaculoDtoList();
		list.setList(Fabrica.getControladorDePaquetes().obtenerEspectaculosDePaquete(nombrePack));
		return list;
	}
	
	@WebMethod
	public PaqueteDtoList obtenerPaquetes() {
		PaqueteDtoList list = new PaqueteDtoList();
		list.setList(Fabrica.getControladorDePaquetes().obtenerPaquetes());
		return list;
	}
	
	@WebMethod
	public PlataformaDtoList obtenerPlataformas() {
		PlataformaDtoList list = new PlataformaDtoList();
		list.setList(Fabrica.getControladorDePaquetes().obtenerPlataformas());
		return list;
	}
	
	@WebMethod
	public EspectaculoDtoList obtenerEspectaculosSinPaquete(String nombrePlat, String nombrePaq) throws PlataformaNoExisteException {
		EspectaculoDtoList list = new EspectaculoDtoList();
		list.setList(Fabrica.getControladorDePaquetes().obtenerEspectaculosSinPaquete(nombrePlat, nombrePaq));
		return list;
	}
	
	@WebMethod
	public void agregarEspectaculoAPaquete(String nombrePaq, String nombrePlat, String nombreEsp) throws PlataformaNoExisteException, EspectaculoNoExisteException, NoExistePaqueteException, PaqueteYaExisteException, EspectaculoYaExisteException {
		Fabrica.getControladorDePaquetes().agregarEspectaculoAPaquete(nombrePaq, nombrePlat, nombreEsp);
	}

	@WebMethod
	public PaqueteDtoList buscarPaquetes(String query) {
		PaqueteDtoList list = new PaqueteDtoList();
		list.setList(Fabrica.getControladorDePaquetes().buscarPaquetes(query));
		return list;
	}
	
	@WebMethod
	public PaqueteDto getPaquete(String nPaq) throws NoExistePaqueteException {
		return Fabrica.getControladorDePaquetes().getPaquete(nPaq);
	}

	@WebMethod
	public StringList getCategoriasDePaquete(String nombrePack) throws NoExistePaqueteException {
		StringList list = new StringList();
		list.setList(Fabrica.getControladorDePaquetes().getCategoriasDePaquete(nombrePack));
		return list;
	}
	
	@WebMethod
	public PaqueteDtoList obtenerPaquetesPorPlataforma(String nomPlat) {
		PaqueteDtoList list = new PaqueteDtoList();
		list.setList(Fabrica.getControladorDePaquetes().obtenerPaquetesPorPlataforma(nomPlat));
		return list;
	}
	
	@WebMethod
	public PaqueteDtoList obtenerPaquetesPorCategoria(String nomCat) {
		PaqueteDtoList list = new PaqueteDtoList();
		list.setList(Fabrica.getControladorDePaquetes().obtenerPaquetesPorCategoria(nomCat));
		return list;
	}

	@WebMethod
	public void comprarPaquete(String nomUsuario, String nomPaquete) throws EspectadorNoExisteException, PaqueteYaExisteException, NoExistePaqueteException {
		Fabrica.getControladorDePaquetes().comprarPaquete(nomUsuario, nomPaquete);
	}
	
	/*
	 * Implementacion de operaciones de controlador de Espectaculos
	 */

	@WebMethod
	public void confirmarAltaEspectaculo(String nPlat, String nArt, String nombre, String descripcion, int duracionHoras, int duracionMinutos, int minEspectadores, int maxEspectadores, String url, float costo, Date fechaRegistro, ImagenDto imagen, CategoriaDtoList categorias) throws EspectaculoYaExisteException, UsuarioNoExisteException, PlataformaNoExisteException, MinEspMayorAMaxEspException, CategoriaNoExisteException, YaExisteCategoriaException, Exception {
		Fabrica.getControladorEspectaculos().confirmarAltaEspectaculo(nPlat, nArt, nombre, descripcion, duracionHoras, duracionMinutos, minEspectadores, maxEspectadores, url, costo, fechaRegistro, imagen, categorias.getList());
	}
	
	@WebMethod
	public void cancelarAltaEspectaculo() {
		Fabrica.getControladorEspectaculos().cancelarAltaEspectaculo();
	}
	
	@WebMethod
	public EspectaculoDtoList obtenerEspectaculosPorPlataforma(String plataforma) throws PlataformaNoExisteException {
		EspectaculoDtoList list = new EspectaculoDtoList();
		list.setList(Fabrica.getControladorEspectaculos().obtenerEspectaculosPorPlataforma(plataforma));
		return list;
	}
	
	@WebMethod
	public EspectaculoDto obtenerDatosEspectaculo(String plataforma, String espectaculo) throws PlataformaNoExisteException, EspectaculoNoExisteException {
		return Fabrica.getControladorEspectaculos().obtenerDatosEspectaculo(plataforma, espectaculo);
	}
	
	@WebMethod
	public EspectaculoDtoList ingresarPlataforma(String urlPlataforma) throws PlataformaNoExisteException {
		EspectaculoDtoList list = new EspectaculoDtoList();
		list.setList(Fabrica.getControladorEspectaculos().ingresarPlataforma(urlPlataforma));
		return list;
	}
	
	@WebMethod
	public FuncionDtoList seleccionarEspectaculoYRetornarFunciones(String nombreEspectaculo, String nombrePlataforma) throws EspectaculoNoExisteException, PlataformaNoExisteException {
		FuncionDtoList list = new FuncionDtoList();
		list.setList(Fabrica.getControladorEspectaculos().seleccionarEspectaculoYRetornarFunciones(nombreEspectaculo, nombrePlataforma));
		return list;
	}
	
	@WebMethod
	public FuncionDto seleccionarFuncion(String nombreFuncion, String nombreEspectaculo, String nombrePlataforma) throws PlataformaNoExisteException, EspectaculoNoExisteException {
		return Fabrica.getControladorEspectaculos().seleccionarFuncion(nombreFuncion, nombreEspectaculo, nombrePlataforma);
	}
	
	@WebMethod
	public ArtistaDtoList obtenerArtistas() {
		ArtistaDtoList list = new ArtistaDtoList();
		list.setList(Fabrica.getControladorEspectaculos().obtenerArtistas());
		return list;
	}
	
	@WebMethod
	public void confirmarAltaFuncion(String nombreFunc, int hora, int min, Date fecha, StringList artistas, EspectaculoDto espectaculo, PlataformaDto plataforma, String imagen) throws FuncionYaExisteException, PlataformaNoExisteException, EspectaculoNoExisteException {
		Fabrica.getControladorEspectaculos().confirmarAltaFuncion(nombreFunc, hora, min, fecha, artistas.getList(), espectaculo, plataforma, imagen);
	}
	
	@WebMethod
	public boolean confirmarAltaFuncion2(String nombreFunc, int hora, int min, Date fecha, StringList artistasInvitados, String nombreEspectaculo, String nickArtista, ImagenDto imagen) throws Exception {
		return Fabrica.getControladorEspectaculos().confirmarAltaFuncion2(nombreFunc, hora, min, fecha, artistasInvitados.getList(), nombreEspectaculo, nickArtista, imagen);
	}
	
	@WebMethod
	public void finalizarEspectaculo(String plat, String nomEsp) throws PlataformaNoExisteException, EspectaculoNoExisteException {
		Fabrica.getControladorEspectaculos().finalizarEspectaculo(plat, nomEsp);
	}
	
	/*
	 * Uso el de arriba ya que ser repiten los nombres y hacen lo mismo
	 * 
	@WebMethod
	public PlataformaDtoList obtenerPlataformas() {
		return Fabrica.getControladorEspectaculos().obtenerPlataformas();
	}
	*/
	
	@WebMethod
	public EspectaculoDtoList obtenerEspectaculosAceptadosPorPlataforma(String plataforma) throws PlataformaNoExisteException {
		EspectaculoDtoList list = new EspectaculoDtoList();
		list.setList(Fabrica.getControladorEspectaculos().obtenerEspectaculosAceptadosPorPlataforma(plataforma));
		return list;
	}
	
	@WebMethod
	public FuncionDtoList obtenerFuncionesVigentesDeEspectaculo(String nombrePlataforma, String nombreEspectaculo) throws PlataformaNoExisteException, EspectaculoNoExisteException {
		FuncionDtoList list = new FuncionDtoList();
		list.setList(Fabrica.getControladorEspectaculos().obtenerFuncionesVigentesDeEspectaculo(nombrePlataforma, nombreEspectaculo));
		return list;
	}
	
	@WebMethod
	public EspectadorDtoList obtenerEspectadoresNoRegistradosEnFuncion(String nombreFuncion) {
		EspectadorDtoList list = new EspectadorDtoList();
		list.setList(Fabrica.getControladorEspectaculos().obtenerEspectadoresNoRegistradosEnFuncion(nombreFuncion));
		return list;
	}
	
	@WebMethod
	public PaqueteDtoList obtenerPaquetesEspectadorEspectaculo(String nickname) throws EspectadorNoExisteException {
		PaqueteDtoList list = new PaqueteDtoList();
		list.setList(Fabrica.getControladorEspectaculos().obtenerPaquetesEspectadorEspectaculo(nickname));
		return list;
	}
	
	@WebMethod
	public RegistroDtoList obtenerRegistrosEspectador(String nickname) throws EspectadorNoExisteException {
		RegistroDtoList list = new RegistroDtoList();
		list.setList(Fabrica.getControladorEspectaculos().obtenerRegistrosEspectador(nickname));
		return list;
	}
	
	@WebMethod
	public void altaRegistroConCanje(String nickname, String nombrePlat, String nombreEsp, String nombreFuncion, StringList nombreFuncionesRegistro) throws PlataformaNoExisteException, EspectaculoNoExisteException, FuncionNoExisteException, EspectadorNoExisteException, YaExisteRegistroException, RegistroNoExisteException, FaltanRegistroException {
		Fabrica.getControladorEspectaculos().altaRegistroConCanje(nickname, nombrePlat, nombreEsp, nombreFuncion, nombreFuncionesRegistro.getList());
	}
	
	@WebMethod
	public void altaRegistroPaquete(String nickname, String nombrePlat, String nombreEsp, String nombreFuncion, String nombrePaquete) throws PlataformaNoExisteException, EspectaculoNoExisteException, FuncionNoExisteException, EspectadorNoExisteException, YaExisteRegistroException {
		Fabrica.getControladorEspectaculos().altaRegistroPaquete(nickname, nombrePlat, nombreEsp, nombreFuncion, nombrePaquete);
	}
	
	@WebMethod
	public void altaRegistroComun(String nickname, String nombrePlat, String nombreEsp, String nombreFuncion) throws PlataformaNoExisteException, EspectaculoNoExisteException, FuncionNoExisteException, EspectadorNoExisteException, YaExisteRegistroException, EspectaculoNoAceptadoException {
		Fabrica.getControladorEspectaculos().altaRegistroComun(nickname, nombrePlat, nombreEsp, nombreFuncion);
	}
	
	@WebMethod
	public EspectaculoDtoList obtenerEspectaculosIngresados(String nomPlat) throws PlataformaNoExisteException {
		EspectaculoDtoList list = new EspectaculoDtoList();
		list.setList(Fabrica.getControladorEspectaculos().obtenerEspectaculosIngresados(nomPlat));
		return list;
	}
	
	@WebMethod
	public void aceptarEspectaculo(String nomPlat, String nomEsp) throws EspectaculoNoExisteException, PlataformaNoExisteException {
		Fabrica.getControladorEspectaculos().aceptarEspectaculo(nomPlat, nomEsp);
	}
	
	@WebMethod
	public void cancelarEspectaculo(String nomPlat, String nomEsp) throws EspectaculoNoExisteException, PlataformaNoExisteException {
		Fabrica.getControladorEspectaculos().cancelarEspectaculo(nomPlat, nomEsp);
	}
	
	@WebMethod
	public EspectaculoDtoList buscarEspectaculos(String query) {
		EspectaculoDtoList list = new EspectaculoDtoList();
		list.setList(Fabrica.getControladorEspectaculos().buscarEspectaculos(query));
		return list;
	}
	
	@WebMethod
	public void altaCategoria(String nombreCat) throws YaExisteCategoriaException {
		Fabrica.getControladorEspectaculos().altaCategoria(nombreCat);
	}
	
	@WebMethod
	public CategoriaDtoList listarCategorias() {
		CategoriaDtoList list = new CategoriaDtoList();
		list.setList(Fabrica.getControladorEspectaculos().listarCategorias());
		return list;
	}
	
	@WebMethod
	public EspectaculoDtoList obtenerEspectaculosPorCategoria(String nomCat) {
		EspectaculoDtoList list = new EspectaculoDtoList();
		list.setList(Fabrica.getControladorEspectaculos().obtenerEspectaculosPorCategoria(nomCat));
		return list;
	}
	
	@WebMethod
	public RegistroDtoList obtenerTodosRegistrosEspectador(String nickname) throws EspectadorNoExisteException {
		RegistroDtoList list = new RegistroDtoList();
		list.setList(Fabrica.getControladorEspectaculos().obtenerTodosRegistrosEspectador(nickname));
		return list;
	}
	
	@WebMethod
	public void marcarEspectaculoFavorito(String usuario, String plataforma, String espectaculo) throws PlataformaNoExisteException, EspectaculoNoExisteException, EspectadorNoExisteException {
		Fabrica.getControladorEspectaculos().marcarEspectaculoFavorito(usuario, plataforma, espectaculo);
	}
	
	@WebMethod
	public void desmarcarEspectaculoFavorito(String usuario, String plataforma, String espectaculo) throws PlataformaNoExisteException, EspectaculoNoExisteException, EspectadorNoExisteException {
		Fabrica.getControladorEspectaculos().desmarcarEspectaculoFavorito(usuario, plataforma, espectaculo);
	}
	
	@WebMethod
	public void valorarEspectaculo(String plataforma, String espectaculo, String usuario, ValoracionDto valoracion) throws PlataformaNoExisteException, EspectaculoNoExisteException, EspectadorNoExisteException, ValoracionNoValidaException {
		Fabrica.getControladorEspectaculos().valorarEspectaculo(plataforma, espectaculo, usuario, valoracion);
	}
	
	@WebMethod
	public void guardarPremios(String descPremio, int cantPremio, String nomEspectaculo, String nomPlataforma) throws PlataformaNoExisteException, EspectaculoNoExisteException {
		Fabrica.getControladorEspectaculos().guardarPremios(descPremio, cantPremio, nomEspectaculo, nomPlataforma);
	}
	
	@WebMethod
	public void hacerSorteoPremio(String nomEspectaculo, String nomPlataforma, String nomFuncion) throws PlataformaNoExisteException, EspectaculoNoExisteException, FuncionNoExisteException {
		Fabrica.getControladorEspectaculos().hacerSorteoPremio(nomEspectaculo, nomPlataforma, nomFuncion);
	}
	
	@WebMethod
	public void guardarUrlVideo(String urlVideo, String nomPlataforma, String nomEspectaculo) throws PlataformaNoExisteException, EspectaculoNoExisteException {
		Fabrica.getControladorEspectaculos().guardarUrlVideo(urlVideo, nomPlataforma, nomEspectaculo);
	}
	
	
	/*
	 * Implementacion de operaciones de controlador de Categorias
	 */
	
	@WebMethod
	public CategoriaDtoList getCategorias() {
		CategoriaDtoList list = new CategoriaDtoList();
		list.setList(Fabrica.getControladorDeCategorias().getCategorias());
		return list;
	}
	
	/*
	 * LOGS
	 * */
	
	@WebMethod
	public void loggearActividad(LogActividadDto log) {
		Fabrica.getLogger().loggearActividad(log);
	}
	
	@WebMethod
	public LogActividadDtoList getLogs(String llave) throws AutenticacionException {
		LogActividadDtoList list = new LogActividadDtoList();
		list.setList((LinkedList<LogActividadDto>) Fabrica.getLogger().obtenerLogsActividad(llave));
		return list;
	}
	
	@WebMethod
	public String generarUrlAccesoLogs() {
		return Fabrica.getLogger().generarLlaveDeAcceso();
	}
	
	public boolean nicknameOCorreoDisponible(String input) {
		return Fabrica.getControladorDeUsuarios().nicknameOCorreoDisponible(input);
	}
	
	
}
