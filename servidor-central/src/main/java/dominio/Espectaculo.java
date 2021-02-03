package dominio;
import dtos.ArtistaDto;
import dtos.CategoriaDto;
import dtos.EspectaculoDto;
import dtos.FuncionDto;
import dtos.PaqueteDto;
import dtos.ValoracionDto;
import excepciones.FuncionNoExisteException;
import excepciones.FuncionYaExisteException;
import excepciones.PaqueteYaExisteException;
import excepciones.YaExisteCategoriaException;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Espectaculo {
	
	public enum Estado {
		INGRESADO,
		RECHAZADO,
		ACEPTADO,
		FINALIZADO
	}
	
	private String nombre;
	private String descripcion;
	private int duracionHoras;
	private int duracionMinutos;
	private int minEspectadores;
	private int maxEspectadores;
	private String url;
	private float costo;
	private Date fechaRegistro;
	private Date fechaFinalizado;
	private String descPremio;
	private int cantPremio;
	private String urlVideo;
	
	private Artista organizador;
	private Plataforma plat;
	private Map<String, Paquete> paquetes;
	private Map<String, Funcion> funciones;
	private LinkedList<Categoria> categorias;
	
	private Estado estado;
	
	private String urlImagen;
	
	
	private int cantFavoritos;
	
	private List<Valoracion> valoraciones;
	
	public Espectaculo() {};
	
	public Espectaculo(Plataforma platform, Artista art, String nom, String desc, int durHor, int durMin,
			int minEsp, int maxEsp, String URL, float cost, Date fechaReg, Estado est) {
		plat = platform;
		organizador = art;
		nombre = nom;
		descripcion = desc;
		duracionHoras = durHor;
		duracionMinutos = durMin;
		minEspectadores = minEsp;
		maxEspectadores = maxEsp;
		url = URL;
		costo = cost;
		fechaRegistro = fechaReg;
		fechaFinalizado = null;
		paquetes = new HashMap<String, Paquete>();
		funciones = new HashMap<String, Funcion>();
		categorias = new LinkedList<Categoria>();
		estado = est;
		urlImagen = "https://blogmedia.evbstatic.com/wp-content/uploads/wpmulti/sites/8/2016/09/06105011/ticket-giveaways.jpg";
		cantFavoritos = 0;
		valoraciones = new LinkedList<Valoracion>();
		descPremio = "";
		cantPremio = 0;
		urlVideo = "";
	}
	
	public void setUrlVideo(String url) {
		urlVideo = url;
	}
	
	public void setUrlImagen(String url) {
		urlImagen = url;
	}
	
	public String getUrlImagen() {
		return urlImagen;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getDuracionHoras() {
		return duracionHoras;
	}
	
	public int getDuracionMinutos() {
		return duracionMinutos;
	}
	
	
	public int getMinEspectadores() {
		return minEspectadores;
	}
	
	public int getMaxEspectadores() {
		return maxEspectadores;
	}
	
	public String getURL() {
		return url;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	
	
	public EspectaculoDto getData() {
		
		LinkedList<FuncionDto> lfun = new LinkedList<FuncionDto>();
		LinkedList<PaqueteDto> lpack = new LinkedList<PaqueteDto>();
		LinkedList<CategoriaDto> lcat = new LinkedList<CategoriaDto>();
		
		for (Map.Entry<String, Funcion> f :funciones.entrySet()) {
			FuncionDto fres = f.getValue().getData();
			lfun.add(fres);
		}
		for (Map.Entry<String, Paquete> p :paquetes.entrySet()) {
			PaqueteDto pres = p.getValue().getData();
			lpack.add(pres);
		}
		for (Categoria c : categorias) {
			lcat.add(c.getData());
		}
		
		ArtistaDto aux = (ArtistaDto) organizador.getData();
		
		LinkedList<ValoracionDto> vals = new LinkedList<ValoracionDto>();
		valoraciones.forEach(v -> vals.add(v.getData()));
		
		EspectaculoDto res = new EspectaculoDto(nombre, descripcion, duracionHoras, duracionMinutos, minEspectadores, maxEspectadores, url,
				costo, fechaRegistro, fechaFinalizado, aux, lfun, lpack, lcat, estado, plat.getNombre(), urlImagen, cantFavoritos, vals);
		res.setCantPremio(cantPremio);
		res.setPremio(descPremio);
		res.setUrlVideo(urlVideo);
		return res;
	}

	public Map<String, Funcion> getFunciones() {
		return funciones;
	}

	public Funcion getFuncion(String nombreFuncion) throws FuncionNoExisteException {
		if (!funciones.containsKey(nombreFuncion)) {
			throw new FuncionNoExisteException("La funcion no existe");
		} else {
			return funciones.get(nombreFuncion);
		}
	}
	
	public boolean existeFuncion(String nombreFuncion) {
		return funciones.containsKey(nombreFuncion);
	}
	
	public String getPlataforma() {
		return plat.getNombre();
	}
	
	
	public void addFuncion(Funcion func) throws FuncionYaExisteException {
		if (funciones.containsKey(func.getNombre())) {
			throw new FuncionYaExisteException("La funcion "+func.getNombre()+" ya existe");
		}
		funciones.putIfAbsent(func.getNombre(), func);
	}
	
	public void addPaquete(String nombrePaq, Paquete paquete) throws PaqueteYaExisteException {
		if (paquetes.containsKey(nombrePaq))
			throw new PaqueteYaExisteException("El paquete ya posee al espectaculo");
		else {
			paquetes.put(nombrePaq, paquete);
		}
	}

	/*
	 * Retorna true si tiene el paquete con nombte nombrePaq
	 */
	public boolean tienePaquete(String nombrePaq) {
		return paquetes.containsKey(nombrePaq);
	}
	
	/*
	 * Retorna el estado del espectaculo
	 */
	public Estado getEstado() {
		return estado;
	}
	
	/*
	 * Setea el estado del espectaculo
	 */
	public void setEstado(Estado _estado) {
		if (_estado.equals(Estado.FINALIZADO))
			this.setFechaFinalizado(new Date());
		estado = _estado;
	}
	
	public void addCategoria(Categoria cat) throws YaExisteCategoriaException {
		for (Categoria c : categorias) {
			if (c.getNombre().equals(cat.getNombre())) {
				throw new YaExisteCategoriaException("");
			}
		}
		categorias.add(cat);
	}
	
	public LinkedList<String> getCategoriasNombre() {
		LinkedList<String> cats = new LinkedList<String>();
		for (Categoria cat : categorias) {
			cats.add(cat.getNombre());
		}
		return cats;
	}
	
	/*
	 * Retorna las categorias
	 */
	public LinkedList<Categoria> getCategorias() {
		return categorias;
	}
	
	/*
	 * Retorna true si la desc o el nombre poseen el string
	 */
	public boolean contieneTira(String tira) {
		if (nombre.contains(tira) || descripcion.contains(tira))
			return true;
		return false;
	}
	
	public void incCantidadFavoritos() {
		cantFavoritos++;
	}
	
	public void decCantidadFavoritos() {
		cantFavoritos--;
	}
	
	public void addValoracion(Valoracion val) {
		valoraciones.add(val);
	}
	
	public void setPremio(String descripcion) {
		descPremio = descripcion;
	}	
	
	public void setCantPremio(int cant) {
		cantPremio = cant;
	}
	
	public String getPremio() {
		return descPremio;
	}	
	
	public int getCantPremio() {
		return cantPremio;
	}

	public Date getFechaFinalizado() {
		return fechaFinalizado;
	}

	public void setFechaFinalizado(Date fechaFinalizado) {
		this.fechaFinalizado = fechaFinalizado;
	}
	
}	
