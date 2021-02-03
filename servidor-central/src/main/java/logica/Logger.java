package logica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

import dominio.LogActividad;
import dtos.LogActividadDto;
import excepciones.AutenticacionException;

public class Logger implements ILogger {
	
	private static List<LogActividad> logsActividad = new ArrayList<LogActividad>();
	private static String llaveDeAcceso;
	private static long timestampDeAcceso;
	
	private final long ttl = 3*60*1000; //3 minutos
	private final long maxLogs = 10000;
	private final String baseUrl = "/servidor-web/Logs?llave=";
	

	@Override
	public void loggearActividad(LogActividadDto dto) {
		LogActividad log = new LogActividad(dto.getIpAddr(), dto.getUrl(), dto.getBrowser(), dto.getOs());
		logsActividad.add(log);
		if (logsActividad.size() > maxLogs) {
			logsActividad.remove(0);
		}
	}

	@Override
	public List<LogActividadDto> obtenerLogsActividad(String llave) throws AutenticacionException {
		if (!llave.equals(llaveDeAcceso) || new Date().getTime() - timestampDeAcceso > ttl) {
			throw new AutenticacionException("Acceso denegado");
		}
		return (List<LogActividadDto>) logsActividad.stream().map(l -> l.getData()).collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public String generarLlaveDeAcceso() {
	    llaveDeAcceso = randomString();
	    timestampDeAcceso = new Date().getTime();
	    Properties prop = new Properties();
	    InputStream is = null;
	    try {
	        is = new FileInputStream("/home/vagrant/config.properties");
	        prop.load(is);
	        return prop.getProperty("baseUrlLogs") + llaveDeAcceso;
	    } catch (IOException ex) {
	    	System.out.println("No anda bien");
	    	return "http://localhost:8080/servidor-web/Logs?llave=" + llaveDeAcceso;
	    }
	    

	    
	}
	
	private String randomString() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	 
	    return generatedString;
	}

}
