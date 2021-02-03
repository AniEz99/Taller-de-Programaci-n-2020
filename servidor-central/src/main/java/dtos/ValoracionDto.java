package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ValoracionDto {
	private int estrellas;
	private String plataforma;
	private String espectaculo;
	
	public ValoracionDto() {}
	
	public ValoracionDto(int estrellas, String plataforma, String espectaculo) {
		this.estrellas = estrellas;
		this.plataforma = plataforma;
		this.espectaculo = espectaculo;
	}
	
	public int getEstrellas() {
		return estrellas;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getEspectaculo() {
		return espectaculo;
	}

	public void setEspectaculo(String espectaculo) {
		this.espectaculo = espectaculo;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
}
