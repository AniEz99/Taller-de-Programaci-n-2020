package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class EspectaculoMinDto {
	private String nomEsp, nomPlat, estado;
		
	public String getNomEsp() {
		return nomEsp;
	}

	public void setNomEsp(String nomEsp) {
		this.nomEsp = nomEsp;
	}

	public String getNomPlat() {
		return nomPlat;
	}

	public void setNomPlat(String nomPlat) {
		this.nomPlat = nomPlat;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public EspectaculoMinDto() {
		nomEsp = "";
		nomPlat = "";
		estado = "";
	}
	
	public EspectaculoMinDto(String nomEsp, String nomPlat, String estado) {
		this.nomEsp = nomEsp;
		this.nomPlat = nomPlat;
		this.estado = estado;
	}
	
	
	
	
}
