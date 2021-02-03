package dtos;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistroDto {

	private Date fecha;
	private boolean esCanjeable;
	private float costo;
	private String nomEspectador;
	private String nombreFuncion; // Para poder hacer un display amigable con el usuario en el caso de seleccionar registros
	
	public RegistroDto(Date fecha_, boolean esCanjeable_, float costo_, String nombreFuncion_) { 
		fecha = fecha_;
		esCanjeable = esCanjeable_;
		costo = costo_;
		nombreFuncion = nombreFuncion_;
		nomEspectador = "";
	}
	
	public RegistroDto() {}
	
	public Date getFecha() {
		return fecha;
	}
	
	public boolean esCanjeable() {
		return esCanjeable;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public String nombreFuncion() {
		return nombreFuncion;
	}

	
	/*
	 * Esta operacion sirve para usar los comboBox en la gui
	 */
	@Override
	public String toString() {
		return nombreFuncion;
	}

	public boolean isEsCanjeable() {
		return esCanjeable;
	}

	public void setEsCanjeable(boolean esCanjeable) {
		this.esCanjeable = esCanjeable;
	}

	public String getNombreFuncion() {
		return nombreFuncion;
	}

	public void setNombreFuncion(String nombreFuncion) {
		this.nombreFuncion = nombreFuncion;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public void setNombreEspectador(String nombre) {
		nomEspectador = nombre;
	}
	
	public String getNombreEspectador() {
		return nomEspectador;
	}
}
