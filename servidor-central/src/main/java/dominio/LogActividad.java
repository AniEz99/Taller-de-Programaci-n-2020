package dominio;

import dtos.LogActividadDto;

public class LogActividad {
	private String ipAddr;
	private String url;
	private String browser;
	private String opSis;
	
	public LogActividad(String ipAddr, String url, String browser, String opSis) {
		this.ipAddr = ipAddr;
		this.url = url;
		this.browser = browser;
		this.opSis = opSis;
	}
	
	public LogActividadDto getData() {
		return new LogActividadDto(ipAddr, url, browser, opSis);
	}
}
