package dtos;

import java.util.Date;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


import dominio.Espectaculo;
import dominio.Espectaculo.Estado;
@XmlAccessorType(XmlAccessType.FIELD)
public class EspectaculoDto {
	
	public EspectaculoDto() {}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LinkedList<FuncionDto> getFuncionesAsociadas() {
		return funcionesAsociadas;
	}

	public void setFuncionesAsociadas(LinkedList<FuncionDto> funcionesAsociadas) {
		this.funcionesAsociadas = funcionesAsociadas;
	}

	public LinkedList<PaqueteDto> getPaquetesAsociados() {
		return paquetesAsociados;
	}

	public void setPaquetesAsociados(LinkedList<PaqueteDto> paquetesAsociados) {
		this.paquetesAsociados = paquetesAsociados;
	}

	public LinkedList<CategoriaDto> getCategoriasAsociadas() {
		return categoriasAsociadas;
	}

	public void setCategoriasAsociadas(LinkedList<CategoriaDto> categoriasAsociadas) {
		this.categoriasAsociadas = categoriasAsociadas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setDuracionHoras(int duracionHoras) {
		this.duracionHoras = duracionHoras;
	}

	public void setDuracionMinutos(int duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}

	public void setMinEspectadores(int minEspectadores) {
		this.minEspectadores = minEspectadores;
	}

	public void setMaxEspectadores(int maxEspectadores) {
		this.maxEspectadores = maxEspectadores;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setOrganizador(ArtistaDto organizador) {
		this.organizador = organizador;
	}

	public void setNombrePlataforma(String nombrePlataforma) {
		this.nombrePlataforma = nombrePlataforma;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
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
	private Estado estado;
	private String descPremio;
	private int cantPremio;
	
	private ArtistaDto organizador;
	private LinkedList<FuncionDto> funcionesAsociadas;
	private LinkedList<PaqueteDto> paquetesAsociados;
	private LinkedList<CategoriaDto> categoriasAsociadas;
	private String nombrePlataforma;
	private String urlImagen;
	
	private int cantFavoritos;
	
	private LinkedList<ValoracionDto> valoraciones;
	
	private String urlVideo;
	
	public EspectaculoDto(String nom, String desc, int durHor, int durMin, int minEsp, int maxEsp, String URL, float cost, Date fechaReg, Date fechaFin,
					ArtistaDto art, LinkedList<FuncionDto> funciones, LinkedList<PaqueteDto> paquetes, LinkedList<CategoriaDto> categorias, Estado est,
					String nombrePlat, String urlImagen, int cantFavoritos, LinkedList<ValoracionDto> valoraciones) {
		
		nombre = nom;
		descripcion = desc;
		duracionHoras = durHor;
		duracionMinutos = durMin;
		minEspectadores = minEsp;
		maxEspectadores = maxEsp;
		url = URL;
		costo = cost;
		fechaRegistro = fechaReg;
		setFechaFinalizado(fechaFin);
		organizador = art;
		funcionesAsociadas = funciones;
		paquetesAsociados = paquetes;
		categoriasAsociadas = categorias;
		estado = est;
		nombrePlataforma = nombrePlat;
		this.urlImagen = urlImagen;
		this.cantFavoritos = cantFavoritos;
		this.valoraciones = valoraciones;
		descPremio = "";
		cantPremio = 0;
		urlVideo = "";
	}
	
	public EspectaculoDto(Espectaculo espect) {
		
		// falta get duracion
		nombre = espect.getNombre();
		descripcion = espect.getDescripcion();
		duracionHoras = espect.getDuracionHoras();
		duracionMinutos = espect.getDuracionMinutos();
		minEspectadores = espect.getMinEspectadores();
		maxEspectadores = espect.getMaxEspectadores();
		url = espect.getURL();
		costo = espect.getCosto();
		fechaRegistro = espect.getFechaRegistro();
		funcionesAsociadas = new LinkedList<FuncionDto>();
		paquetesAsociados = new LinkedList<PaqueteDto>();
		estado = espect.getEstado();
		this.urlImagen = espect.getUrlImagen();
		nombrePlataforma = espect.getPlataforma();
		urlVideo = "";
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getUrlImagen() {
		return urlImagen;
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
	
	public Estado getEstado() {
		return estado;
	}

	/*
	 * Esta operacion sirve para usar los comboBox en la gui
	 */
	@Override
	public String toString() {
		return nombre;
	}
	
	public ArtistaDto getOrganizador() {
		return organizador;
	}
	public LinkedList<FuncionDto> getFunciones(){
		return funcionesAsociadas;
	}
	
	public LinkedList<PaqueteDto> getPaquetes() {
		return paquetesAsociados;
	}
	
	public LinkedList<CategoriaDto> getCategorias() {
		return categoriasAsociadas;
	}
	
	public String getNombrePlataforma() {
		return nombrePlataforma;
	}
	
	public int getCantidadFavoritos() {
		return cantFavoritos;
	}
	
	public LinkedList<ValoracionDto> getValoraciones() {
		return valoraciones;
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
	
	public String getUrlVideo() {
		return urlVideo;
	}
	
	public void setUrlVideo(String url) {
		urlVideo = url;
	}

	public Date getFechaFinalizado() {
		return fechaFinalizado;
	}

	public void setFechaFinalizado(Date fechaFinalizado) {
		this.fechaFinalizado = fechaFinalizado;
	}
	
}
