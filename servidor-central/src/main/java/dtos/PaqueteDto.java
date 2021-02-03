package dtos;

import java.util.Date;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class PaqueteDto {
	
	public PaqueteDto() {}
	
	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin; 
	private Date fechaRegistro;
	private float descuento;
	
	private LinkedList<String> idEspectaculos;
	private LinkedList<CategoriaDto> categorias;
	private LinkedList<String> plataformas;
	
	private String urlImagen;
	
	
	
	public PaqueteDto(String nom, String descr, Date fechaIni, Date fechaF, Date fechaReg, float desc, LinkedList<String> idEsp, LinkedList<CategoriaDto> cats, LinkedList<String> plats, String urlImagen) {
		nombre = nom;
		descripcion = descr;
		fechaInicio = fechaIni;
		fechaFin = fechaF;
		fechaRegistro = fechaReg;
		descuento = desc;
		idEspectaculos = idEsp;
		categorias = cats;
		this.urlImagen = urlImagen;
		plataformas = plats;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public Date getFechaFin() {
		return fechaFin;
	}
	
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public LinkedList<String> getNomEspectaculos() {
		return idEspectaculos;
	}
	
	public LinkedList<CategoriaDto> getCategorias() {
		return categorias;
	}
	
	public LinkedList<String> getPlataformas() {
		return plataformas;
	}
	
	/*
	 * No poner los espectaculoDto aca pq espectaculoDto ya tiene plataformaDto, tremendo loop sino
	public LinkedList<EspectaculoDto> getListaEspectaculos() {
		return listaEspectaculos;
	}
	*/
	
	/*
	 * Esta operacion sirve para usar los comboBox en la gui
	 */
	@Override
	public String toString() {
		return nombre;
	}
	
	public String getUrlImagen() {
		return urlImagen;
	}

	public LinkedList<String> getIdEspectaculos() {
		return idEspectaculos;
	}

	public void setIdEspectaculos(LinkedList<String> idEspectaculos) {
		this.idEspectaculos = idEspectaculos;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public void setCategorias(LinkedList<CategoriaDto> categorias) {
		this.categorias = categorias;
	}

	public void setPlataformas(LinkedList<String> plataformas) {
		this.plataformas = plataformas;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
}
