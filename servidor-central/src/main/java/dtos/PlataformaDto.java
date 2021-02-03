package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlataformaDto {
	
	public PlataformaDto() {}
	
	private String nombre;
	private String descripcion;
	private String url;

	
	public PlataformaDto(String nombre_, String descripcion_, String URL_) {
		nombre = nombre_;
		descripcion = descripcion_;
		url = URL_;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getURL() {
		return url;
	}
	
	/*
	 * Esta operacion sirve para usar los comboBox en la gui
	 */
	@Override
	public String toString() {
		return nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
