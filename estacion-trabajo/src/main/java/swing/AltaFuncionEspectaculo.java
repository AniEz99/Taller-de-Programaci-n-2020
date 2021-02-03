package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;

import webservices.ArtistaDto;
import webservices.EspectaculoDto;
import webservices.Exception_Exception;
import webservices.ImagenDto;
import webservices.PlataformaDto;
import webservices.PlataformaNoExisteException_Exception;
import webservices.Publicador;
import webservices.PublicadorService;
import webservices.StringList;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class AltaFuncionEspectaculo extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();
	/**
	 * Launch the application.
	 */
	
	// Booleanos para desactivar los item listeners de los combobox cuando les agrego elementos
	boolean espectaculosListo = false;
	boolean funcionesListo = false;
	boolean espectadoresListo = false;
	boolean registrosListo = false;
	
	List<EspectaculoDto> listaEspc = new LinkedList<EspectaculoDto>();
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private File imagen = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaFuncionEspectaculo frame = new AltaFuncionEspectaculo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AltaFuncionEspectaculo() {
		setTitle("Alta Funcion de Espectaculo");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		setSize(602, 557);
		setLocation(10, 33);
		getContentPane().setLayout(null);
		
		//Boton confirmar
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(157, 490, 105, 23);
		getContentPane().add(btnConfirmar);
		
		//Boton cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(300, 490, 105, 23);
		getContentPane().add(btnCancelar);
		
		//Calendario
		JCalendar calendar = new JCalendar();
		calendar.setBounds(35, 108, 227, 129);
		getContentPane().add(calendar);
		calendar.setMinSelectableDate(new Date());
		calendar.setEnabled(false);
			
		

		// Plataformas
		JPanel panelPlataformas = new JPanel();
		panelPlataformas.setBounds(74, 0, 227, 54);
		getContentPane().add(panelPlataformas);
		panelPlataformas.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPlataformas = new JLabel("Plataforma:");
		lblPlataformas.setBorder(new EmptyBorder(10,10,10,10));
		panelPlataformas.add(lblPlataformas, BorderLayout.NORTH);
		
		JComboBox<String> comboBoxPlataformas = new JComboBox<String>();
		comboBoxPlataformas.setLightWeightPopupEnabled(false);
		panelPlataformas.add(comboBoxPlataformas, BorderLayout.CENTER);
		

		// Espectaculos
		JPanel panelEspectaculos = new JPanel();
		panelEspectaculos.setBounds(336, 0, 169, 54);
		getContentPane().add(panelEspectaculos);
		panelEspectaculos.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEspectaculos = new JLabel("Espectaculo:");
		lblEspectaculos.setEnabled(false);
		lblEspectaculos.setBorder(new EmptyBorder(10,10,10,10));
		panelEspectaculos.add(lblEspectaculos, BorderLayout.NORTH);
		
		JComboBox<String> comboBoxEspectaculos = new JComboBox<String>();
		comboBoxEspectaculos.setLightWeightPopupEnabled(false);
		comboBoxEspectaculos.setEnabled(false);
		panelEspectaculos.add(comboBoxEspectaculos, BorderLayout.CENTER);
		
		/*
		 * Cargar Plataformas
		 */
		
		List<PlataformaDto> listaPlat = port.obtenerPlataformas().getList();
		for (PlataformaDto item : listaPlat) {
			comboBoxPlataformas.addItem(item.getNombre()); // Agrego los elementos al comboBox
		}
		comboBoxPlataformas.setSelectedIndex(-1);
		
		textField = new JTextField();
		textField.setBounds(318, 138, 169, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEnabled(false);
		
		JLabel lblNewLabel = new JLabel("Nombre Funcion:");
		lblNewLabel.setBounds(355, 109, 96, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hora de comienzo:");
		lblNewLabel_1.setBounds(355, 169, 150, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(336, 194, 48, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEnabled(false);
		
		// Lista artistas
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> listaArtistas = new JList<String>();
		listaArtistas.setEnabled(false);
		listaArtistas.setBounds(157, 278, 234, 102);
		getContentPane().add(listaArtistas);
		
		
		JLabel lblNewLabel_2 = new JLabel("Selecionar Artistas:");
		lblNewLabel_2.setBounds(50, 306, 153, 23);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(":");
		lblNewLabel_3.setBounds(400, 197, 75, 14);
		getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(417, 194, 48, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setEnabled(false);
		
		JScrollPane scroll = new JScrollPane(listaArtistas);
		scroll.setBounds(213, 277, 192, 102);
		getContentPane().add(scroll);
		
		JLabel lblSeleccionarImgen = new JLabel("Seleccionar Imagen:");
		lblSeleccionarImgen.setBounds(50, 428, 139, 15);
		getContentPane().add(lblSeleccionarImgen);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(AltaFuncionEspectaculo.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.append("Opening: " + fc.getSelectedFile().getName() + "." + "\n");
					imagen = fc.getSelectedFile();
			    } else {
			    	System.out.append("Open command cancelled by user." + "\n");
			    }		
			}
		});
		btnSeleccionar.setBounds(213, 423, 192, 25);
		getContentPane().add(btnSeleccionar);
		
		
		
		
		
		/*
		 * Cuando se selecciona una Plataforma
		 */
		comboBoxPlataformas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED && comboBoxPlataformas.getSelectedIndex() != -1) {
					try {
						listaEspc = port.obtenerEspectaculosPorPlataforma(comboBoxPlataformas.getSelectedItem().toString()).getList();
					} catch (PlataformaNoExisteException_Exception ex) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, ex.getMessage());
						dispose();
					}
					
					espectaculosListo = false;
					funcionesListo = false;
					espectadoresListo = false;
					
					comboBoxEspectaculos.removeAllItems();
					for (EspectaculoDto item : listaEspc) {
						comboBoxEspectaculos.addItem(item.getNombre()); // Agrego los elementos al comboBox
					}
					comboBoxEspectaculos.setSelectedIndex(-1);
					
					// Enable
					for (Component comp : panelEspectaculos.getComponents()) {
						comp.setEnabled(true);
					}
					espectaculosListo = true;
					
				}
			}
		});
		
		/*
		 * Cuando se selecciona un espectaculo
		 */
		comboBoxEspectaculos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED && comboBoxEspectaculos.getSelectedIndex() != -1 && espectaculosListo) {					
					calendar.setEnabled(true);
					textField.setEnabled(true);
					textField_1.setEnabled(true);
					textField_2.setEnabled(true);
					listaArtistas.setEnabled(true);
					List<ArtistaDto> artistas = port.obtenerArtistas().getList();
					for (int i = 0; i < artistas.size(); i++) {
						model.addElement(artistas.get(i).getNickname());
					}
					listaArtistas.setModel(model);
				}
			}
		});
				
				
				//Cuando confirmo				
				btnConfirmar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						/* Coso horrible de fecha */
						GregorianCalendar bdateGC = new GregorianCalendar();
			    		bdateGC.setTime(calendar.getDate());
			    		XMLGregorianCalendar xmlfechaAlta = null;
						try {
							xmlfechaAlta = DatatypeFactory.newInstance().newXMLGregorianCalendar(bdateGC);
						} catch (DatatypeConfigurationException e2) {
							e2.printStackTrace();
						}	
						String nombreFunc = textField.getText();
						String horaF = textField_1.getText();
						String minF = textField_2.getText();
						if (nombreFunc.equals("") || horaF.equals("") || minF.equals("")) {
							JOptionPane.showMessageDialog(rootPane, "Hay datos requeridos sin ingresar.", "ERROR", 0);
							return;
						}
						int horaFunc = Integer.parseInt(horaF);
						int minFunc = Integer.parseInt(minF);
						if (horaFunc>23 || horaFunc<0 || minFunc>59 || minFunc<0) {
							JOptionPane.showMessageDialog(rootPane, "No existe esa hora", "ERROR", 0);
							return;
						}
						
						
						
						List<String> artistas = listaArtistas.getSelectedValuesList();
						StringList artistasList = new StringList();
						artistas.forEach(art -> artistasList.getList().add(art));
						
						
						/*
						if (artistas.size() == 0) {
							JOptionPane.showMessageDialog(rootPane, "No selecciono ningun artista", "ERROR", 0);
							return;
						}*/
						EspectaculoDto especSel = listaEspc.stream().filter(esp -> esp.getNombre().equals(comboBoxEspectaculos.getSelectedItem().toString()))
														   .findAny()
														   .orElse(null);
						ImagenDto img = new ImagenDto();
						try {
							img.setContent(Files.readAllBytes(imagen.toPath()));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						img.setName(imagen.getName());
						
						
						try {
							port.confirmarAltaFuncion2(nombreFunc, horaFunc, minFunc, xmlfechaAlta, artistasList, especSel.getNombre(), especSel.getOrganizador().getNickname(), img);
							JOptionPane.showMessageDialog(rootPane, "Funcion creada exitosamente.", "Exito", 1);
							dispose();
						} catch (Exception_Exception e1) {
							JFrame frame = new JFrame();
							JOptionPane.showMessageDialog(frame, e1.getMessage());
						}
						
					}
				});
				
				//Cuando cancelo
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
	}
}
