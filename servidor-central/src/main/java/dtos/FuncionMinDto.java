package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class FuncionMinDto {
	public FuncionMinDto() {}
	
	public FuncionMinDto(String nombrePlat, String nombreEsp, String nombreFunc) {
		this.nombrePlat = nombrePlat;
		this.nombreEsp = nombreEsp;
		this.nombreFunc = nombreFunc;
	}
	
	private String nombrePlat, nombreEsp, nombreFunc;

	public String getNombrePlat() {
		return nombrePlat;
	}

	public void setNombrePlat(String nombrePlat) {
		this.nombrePlat = nombrePlat;
	}

	public String getNombreEsp() {
		return nombreEsp;
	}

	public void setNombreEsp(String nombreEsp) {
		this.nombreEsp = nombreEsp;
	}

	public String getNombreFunc() {
		return nombreFunc;
	}

	public void setNombreFunc(String nombreFunc) {
		this.nombreFunc = nombreFunc;
	}
}
