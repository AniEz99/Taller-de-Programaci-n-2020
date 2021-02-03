package swing;

import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;

import webservices.ArtistaDto;
import webservices.EspectaculoDto;
import webservices.EspectaculoNoExisteException_Exception;
import webservices.FuncionDto;
import webservices.PlataformaNoExisteException_Exception;
import webservices.Publicador;
import webservices.PublicadorService;
import webservices.UsuarioDto;

public class ConsultaFuncionEspectaculo extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();
	
	public JComboBox<String> plataformaComboBox;
	public JComboBox<String> espectaculoComboBox;
	public JComboBox<String> funcionComboBox;
	private JComboBox<String> artistasInvitadosComboBox;
	private JTextField nombreTextField;
	private JTextField fechaiTextField;
	private JTextField fecharTextField;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaFuncionEspectaculo frame = new ConsultaFuncionEspectaculo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public ConsultaFuncionEspectaculo(){
		setClosable(true);
		setTitle("Consultar funcion de espectaculo");
		setBounds(100, 100, 621, 353);
		getContentPane().setLayout(null);
		
		JLabel seleccionarPlataformaLabel = new JLabel("Seleccionar plataforma");
		seleccionarPlataformaLabel.setBounds(6, 40, 163, 16);
		getContentPane().add(seleccionarPlataformaLabel);
		
		plataformaComboBox = new JComboBox<String>();
		plataformaComboBox.addItem("SeleccionarPlataforma");
		plataformaComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent eve) {
				if (eve.getStateChange() == ItemEvent.SELECTED) {
					if(!plataformaComboBox.getSelectedItem().toString().equals("Seleccionar plataforma")) {
						espectaculoComboBox.removeAllItems();
						funcionComboBox.removeAllItems();
						espectaculoComboBox.addItem("Seleccionar espectaculo");
						cargarEspectaculos();
					}
				}
			}
		});
		plataformaComboBox.setBounds(181, 36, 169, 27);
		getContentPane().add(plataformaComboBox);
		cargarPlataformas();
		
		
		JLabel seleccionarEspectaculoLabel = new JLabel("Seleccionar espectaculo");
		seleccionarEspectaculoLabel.setBounds(6, 68, 163, 16);
		getContentPane().add(seleccionarEspectaculoLabel);
		
		espectaculoComboBox = new JComboBox<String>();
		espectaculoComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent eve) {
				if (eve.getStateChange() == ItemEvent.SELECTED) {
					if(!espectaculoComboBox.getSelectedItem().toString().equals("Seleccionar espectaculo")) {
						funcionComboBox.removeAllItems();
						funcionComboBox.addItem("Seleccionar funcion");
						cargarFunciones();
					}
				}
			}
		});
		espectaculoComboBox.setBounds(181, 64, 169, 27);
		getContentPane().add(espectaculoComboBox);

		
		JLabel seleccionarFuncionLabel = new JLabel("Seleccionar funcion");
		seleccionarFuncionLabel.setBounds(6, 96, 163, 16);
		getContentPane().add(seleccionarFuncionLabel);
		
		funcionComboBox = new JComboBox<String>();
		funcionComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent eve) {
				if (eve.getStateChange() == ItemEvent.SELECTED) {
					if(!funcionComboBox.getSelectedItem().toString().equals("Seleccionar funcion")) {
						artistasInvitadosComboBox.removeAllItems();
						mostrarDatosFuncion();
					}
				}
			}
		});

		funcionComboBox.setBounds(181, 92, 169, 27);
		getContentPane().add(funcionComboBox);
		

		
		nombreTextField = new JTextField();
		nombreTextField.setBounds(181, 157, 270, 26);
		getContentPane().add(nombreTextField);
		nombreTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Datos de la funcion");
		lblNewLabel.setBounds(191, 131, 137, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(6, 162, 61, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio:");
		lblFechaDeInicio.setBounds(6, 190, 103, 16);
		getContentPane().add(lblFechaDeInicio);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha de registro:");
		lblNewLabel_2.setBounds(6, 218, 117, 16);
		getContentPane().add(lblNewLabel_2);
		
		fechaiTextField = new JTextField();
		fechaiTextField.setBounds(181, 185, 147, 26);
		getContentPane().add(fechaiTextField);
		fechaiTextField.setColumns(10);
		
		fecharTextField = new JTextField();
		fecharTextField.setBounds(181, 213, 147, 26);
		getContentPane().add(fecharTextField);
		fecharTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Artistas invitados:");
		lblNewLabel_3.setBounds(6, 246, 117, 16);
		getContentPane().add(lblNewLabel_3);
		
		artistasInvitadosComboBox = new JComboBox<String>();
		artistasInvitadosComboBox.setBounds(181, 242, 147, 27);
		getContentPane().add(artistasInvitadosComboBox);
	}

	public void cargarPlataformas() {	
		List<String> nombrePlat =  port.getNombrePlataformas().getList();
		for(String plataforma : nombrePlat) {
			plataformaComboBox.addItem(plataforma);
		}
	}
	
	public void cargarEspectaculos() {
		String aux = plataformaComboBox.getSelectedItem().toString();
		if(plataformaComboBox.getSelectedIndex() != 0) {
			List<EspectaculoDto> datosEspectaculos;
			try {
				datosEspectaculos = port.ingresarPlataforma(aux).getList();
				for(EspectaculoDto espectaculo : datosEspectaculos) {
					espectaculoComboBox.addItem(espectaculo.getNombre());
				}
			} catch (PlataformaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	public void cargarFunciones() {
		String auxEspectaculo = espectaculoComboBox.getSelectedItem().toString();
		String plataformaSeleccionada = plataformaComboBox.getSelectedItem().toString();
		try {
			List<FuncionDto> funcionesDelEspectaculo = port.seleccionarEspectaculoYRetornarFunciones(auxEspectaculo, plataformaSeleccionada).getList();
			
			for(FuncionDto funcion : funcionesDelEspectaculo) {
				funcionComboBox.addItem(funcion.getNombre());
			}
		} catch (PlataformaNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EspectaculoNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostrarDatosFuncion() {
		String auxEspectaculo = (String) espectaculoComboBox.getSelectedItem();
		String auxFuncion = (String) funcionComboBox.getSelectedItem();
		String auxPlataforma = (String) plataformaComboBox.getSelectedItem();
		FuncionDto datosFuncionSeleccionada;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			datosFuncionSeleccionada = port.seleccionarFuncion(auxFuncion, auxEspectaculo, auxPlataforma);
			nombreTextField.setText(datosFuncionSeleccionada.getNombre());
			String fechai = dateFormat.format(datosFuncionSeleccionada.getFechaInicio().toGregorianCalendar().getTime());
			fechaiTextField.setText(fechai);
			String fechar = dateFormat.format(datosFuncionSeleccionada.getFechaRegistro().toGregorianCalendar().getTime());
			fecharTextField.setText(fechar);
			for(UsuarioDto art : datosFuncionSeleccionada.getInvitados()) {
				ArtistaDto aux = (ArtistaDto) art;
				artistasInvitadosComboBox.addItem(aux.getNombre() + " " + aux.getApellido());
			}
		} catch (PlataformaNoExisteException_Exception | EspectaculoNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
