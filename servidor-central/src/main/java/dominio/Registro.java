package dominio;
import java.util.Date;
import java.util.LinkedList;

import dtos.RegistroDto;

public class Registro {
	
	private Date fecha;
	private boolean esCanjeable;
	private float costo;
	
	private LinkedList<Registro> canjeados; // Los registros que son canjeados para obtener este
	private Paquete pack;
	private Espectador esp;
	private Funcion fun; // La identidad la define la funcion
	private int idReg;
	
	
	/*
	 * Asumo que la fecha es la del sistema
	 */
	public Registro(Funcion _fun, Espectador _esp, Paquete _pack, float _costo, boolean _esCanjeable, LinkedList<Registro> _canjeados, int _IdReg) {
		fun = _fun; 
		esp = _esp;
		pack = _pack;
		costo = _costo;
		esCanjeable = _esCanjeable;
		canjeados = _canjeados;
		idReg = _IdReg;
		fecha = new Date();
	}
	
	/*
	 * Retorna el datatype del registro
	 */
	public RegistroDto getData() {
		RegistroDto reg = new RegistroDto(fecha, esCanjeable, costo, fun.getNombre());
		reg.setNombreEspectador(esp.getNombre());
		return reg;
	}

	public String getNombreFuncion() {
		return fun.getNombre();
	}
	
	public void setFecha(Date fechaReg) {
		fecha = fechaReg;
	}
	
	public String getNombrePlataforma( ) {
		return fun.getNombrePlataforma();
	}
	
	public String getNombreEspectaculo() {
		return fun.getNombreEspectaculo();
	}

	public void deshabilitar() {
		esCanjeable = false;		
	}

	public boolean esCanjeable() {
		return esCanjeable;
	}

	public Object getId() {
		return idReg;
	}
	
	public LinkedList<Registro> getCanjeados() {
		return canjeados;
	}
	
	public Paquete getPaquete() {
		return pack;
	}
	
	public Espectador getEspectador() {
		return esp;
	}
}
