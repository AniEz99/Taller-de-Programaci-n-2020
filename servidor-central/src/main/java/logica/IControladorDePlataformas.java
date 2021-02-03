package logica;
import java.util.LinkedList;
import java.util.List;

import dtos.PlataformaDto;
import excepciones.CamposIncompletosException;
import excepciones.PlataformaYaExisteException;

/*
 * Interfaz de controlador de plataforma
 */
public interface IControladorDePlataformas {
	public void altaPlataforma(PlataformaDto infoPlataforma) throws PlataformaYaExisteException, CamposIncompletosException;
	public LinkedList<String> getNombrePlataformas();
}
