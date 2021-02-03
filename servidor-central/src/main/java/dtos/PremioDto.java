package dtos;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PremioDto {
	
	private String nombrePremio;
	private String espAsociado;
	private String funcAsociada;
	private Date fechaPremio;
	private String platAsociada;
	
	public PremioDto(String plat, String nombre, String esp, String func, Date fecha) {
		nombrePremio = nombre;
		espAsociado = esp;
		funcAsociada = func;
		fechaPremio = fecha;
		platAsociada = plat;
	}
	
	
	public String getPremio() {
		return nombrePremio;
	}
	
	public String getEspectaculo() {
		return espAsociado;
	}
	
	public String getFuncion() {
		return funcAsociada;
	}
	
	public Date getFecha() {
		return fechaPremio;
	}


	public String getNombrePremio() {
		return nombrePremio;
	}


	public void setNombrePremio(String nombrePremio) {
		this.nombrePremio = nombrePremio;
	}


	public String getEspAsociado() {
		return espAsociado;
	}


	public void setEspAsociado(String espAsociado) {
		this.espAsociado = espAsociado;
	}


	public String getFuncAsociada() {
		return funcAsociada;
	}


	public void setFuncAsociada(String funcAsociada) {
		this.funcAsociada = funcAsociada;
	}


	public Date getFechaPremio() {
		return fechaPremio;
	}


	public void setFechaPremio(Date fechaPremio) {
		this.fechaPremio = fechaPremio;
	}


	public String getPlatAsociada() {
		return platAsociada;
	}


	public void setPlatAsociada(String platAsociada) {
		this.platAsociada = platAsociada;
	}
}
