package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class LogActividadDto {
	private String ipAddr;
	private String url;
	private String browser;
	private String opSis;
	
	public LogActividadDto() {
		
	}
	
	public LogActividadDto(String ipAddr, String url, String browser, String opSis) {
		this.ipAddr = ipAddr;
		this.url = url;
		this.browser = browser;
		this.opSis = opSis;
	}
	
	public String getIpAddr() {
		return ipAddr;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getBrowser() {
		return browser;
	}
	
	public String getOs() {
		return opSis;
	}
}
