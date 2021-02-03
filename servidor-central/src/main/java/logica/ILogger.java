package logica;

import java.util.List;

import dtos.LogActividadDto;
import excepciones.AutenticacionException;

public interface ILogger {

	public void loggearActividad(LogActividadDto dto);
	public List<LogActividadDto> obtenerLogsActividad(String llave) throws AutenticacionException;
	public String generarLlaveDeAcceso();
	
}
