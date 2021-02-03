package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.HashSet;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import webservices.CategoriaDto;
import webservices.EspectaculoDto;
import webservices.NoExistePaqueteException_Exception;
import webservices.PaqueteDto;
import webservices.Publicador;
import webservices.PublicadorService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class ConsultaPaquete extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();

	List<PaqueteDto> listaPaquetes;
	List<EspectaculoDto> listEsp;

	
	boolean CARGARGANDO_ESPECTACULOS = false; // cargargando.
	
	public JComboBox<String> comboBoxPaquetes;
	private JTextField txtFechaInicio;
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaPaquete frame = new ConsultaPaquete();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultaPaquete() {
		setTitle("Consulta de Paquete");
		setClosable(true);
		setBounds(0, 0, 484, 517);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 43, 182, 0};
		gridBagLayout.rowHeights = new int[]{59, 36, 41, 87, 0, 0, 0, 0, 0, 32, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		/*
		 * Lista Paquetes
		 */
		JLabel lblListaPaquetes = new JLabel("Selecione paquete: ");
		GridBagConstraints gbc_lblListaPaquetes = new GridBagConstraints();
		gbc_lblListaPaquetes.anchor = GridBagConstraints.WEST;
		gbc_lblListaPaquetes.insets = new Insets(0, 0, 5, 5);
		gbc_lblListaPaquetes.fill = GridBagConstraints.VERTICAL;
		gbc_lblListaPaquetes.gridx = 1;
		gbc_lblListaPaquetes.gridy = 0;
		getContentPane().add(lblListaPaquetes, gbc_lblListaPaquetes);
		
		comboBoxPaquetes = new JComboBox<String>();
		comboBoxPaquetes.setLightWeightPopupEnabled(false);
		GridBagConstraints gbc_comboBoxPaquetes = new GridBagConstraints();
		gbc_comboBoxPaquetes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPaquetes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPaquetes.gridx = 2;
		gbc_comboBoxPaquetes.gridy = 0;
		getContentPane().add(comboBoxPaquetes, gbc_comboBoxPaquetes);
		// Agrego paquetes
		listaPaquetes = port.obtenerPaquetes().getList();
		for (PaqueteDto paq : listaPaquetes)
			comboBoxPaquetes.addItem(paq.getNombre());
		comboBoxPaquetes.setSelectedIndex(-1);
		
		/*
		 * Titulo
		 */
		JLabel lblInformacionPaquete = new JLabel("Informacion Paquete:");
		GridBagConstraints gbc_lblInformacionPaquete = new GridBagConstraints();
		gbc_lblInformacionPaquete.anchor = GridBagConstraints.WEST;
		gbc_lblInformacionPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_lblInformacionPaquete.gridx = 1;
		gbc_lblInformacionPaquete.gridy = 1;
		getContentPane().add(lblInformacionPaquete, gbc_lblInformacionPaquete);
		
		/*
		 * Nombre
		 */
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 2;
		getContentPane().add(lblNombre, gbc_lblNombre);
		
		JTextField textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 2;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		
		/*
		 * Descripción
		 */
		JLabel lblDescripcion = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 3;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);
		
		JTextPane textPaneDescripcion = new JTextPane();
		textPaneDescripcion.setEditable(false);
		GridBagConstraints gbc_textPaneDescripcion = new GridBagConstraints();
		gbc_textPaneDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textPaneDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textPaneDescripcion.gridx = 2;
		gbc_textPaneDescripcion.gridy = 3;
		getContentPane().add(textPaneDescripcion, gbc_textPaneDescripcion);
		
		/*
		 * Fecha Inicio
		 */
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		GridBagConstraints gbc_lblFechaInicio = new GridBagConstraints();
		gbc_lblFechaInicio.anchor = GridBagConstraints.WEST;
		gbc_lblFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaInicio.gridx = 1;
		gbc_lblFechaInicio.gridy = 4;
		getContentPane().add(lblFechaInicio, gbc_lblFechaInicio);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setEditable(false);
		GridBagConstraints gbc_txtFechaInicio = new GridBagConstraints();
		gbc_txtFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_txtFechaInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFechaInicio.gridx = 2;
		gbc_txtFechaInicio.gridy = 4;
		getContentPane().add(txtFechaInicio, gbc_txtFechaInicio);
		txtFechaInicio.setColumns(10);
		
		/*
		 * Fecha Vencimiento
		 */
		JLabel lblFechaVencimiento = new JLabel("Fecha Vencimiento:");
		GridBagConstraints gbc_lblFechaVencimiento = new GridBagConstraints();
		gbc_lblFechaVencimiento.anchor = GridBagConstraints.WEST;
		gbc_lblFechaVencimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaVencimiento.gridx = 1;
		gbc_lblFechaVencimiento.gridy = 5;
		getContentPane().add(lblFechaVencimiento, gbc_lblFechaVencimiento);
		
		JTextField textFieldFechaVencimiento = new JTextField();
		textFieldFechaVencimiento.setEditable(false);
		GridBagConstraints gbc_textFieldFechaVencimiento = new GridBagConstraints();
		gbc_textFieldFechaVencimiento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechaVencimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaVencimiento.gridx = 2;
		gbc_textFieldFechaVencimiento.gridy = 5;
		getContentPane().add(textFieldFechaVencimiento, gbc_textFieldFechaVencimiento);
		
		
		/*
		 * Fecha Registro
		 */
		JLabel lblFechaRegistro = new JLabel("Fecha Registro:");
		GridBagConstraints gbc_lblFechaRegistro = new GridBagConstraints();
		gbc_lblFechaRegistro.anchor = GridBagConstraints.WEST;
		gbc_lblFechaRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaRegistro.gridx = 1;
		gbc_lblFechaRegistro.gridy = 6;
		getContentPane().add(lblFechaRegistro, gbc_lblFechaRegistro);
		
		JTextField textFieldFechaRegistro = new JTextField();
		textFieldFechaRegistro.setEditable(false);
		GridBagConstraints gbc_textFieldFechaRegistro = new GridBagConstraints();
		gbc_textFieldFechaRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechaRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaRegistro.gridx = 2;
		gbc_textFieldFechaRegistro.gridy = 6;
		getContentPane().add(textFieldFechaRegistro, gbc_textFieldFechaRegistro);
		
		/*
		 * Descuento
		 */
		JLabel lblDescuento = new JLabel("Descuento:");
		GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
		gbc_lblDescuento.anchor = GridBagConstraints.WEST;
		gbc_lblDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuento.gridx = 1;
		gbc_lblDescuento.gridy = 7;
		getContentPane().add(lblDescuento, gbc_lblDescuento);
		
		JTextField textFieldDescuento = new JTextField();
		textFieldDescuento.setEditable(false);
		GridBagConstraints gbc_textFieldDescuento = new GridBagConstraints();
		gbc_textFieldDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescuento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescuento.gridx = 2;
		gbc_textFieldDescuento.gridy = 7;
		getContentPane().add(textFieldDescuento, gbc_textFieldDescuento);
		JLabel lblEspectaculos = new JLabel("Espectaculos:");
		GridBagConstraints gbc_lblEspectaculos = new GridBagConstraints();
		gbc_lblEspectaculos.anchor = GridBagConstraints.WEST;
		gbc_lblEspectaculos.insets = new Insets(0, 0, 5, 5);
		gbc_lblEspectaculos.gridx = 1;
		gbc_lblEspectaculos.gridy = 8;
		getContentPane().add(lblEspectaculos, gbc_lblEspectaculos);
		
		JComboBox<String> comboBoxEspectaculos = new JComboBox<String>();
		comboBoxEspectaculos.setEnabled(false);
		comboBoxEspectaculos.setLightWeightPopupEnabled(false);
		GridBagConstraints gbc_comboBoxEspectaculos = new GridBagConstraints();
		gbc_comboBoxEspectaculos.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEspectaculos.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEspectaculos.gridx = 2;
		gbc_comboBoxEspectaculos.gridy = 8;
		getContentPane().add(comboBoxEspectaculos, gbc_comboBoxEspectaculos);
		
		JButton btnVerEspectaculo = new JButton("Ver Espectaculo");
		btnVerEspectaculo.setEnabled(false);
		GridBagConstraints gbc_btnVerEspectaculo = new GridBagConstraints();
		gbc_btnVerEspectaculo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnVerEspectaculo.anchor = GridBagConstraints.NORTH;
		gbc_btnVerEspectaculo.insets = new Insets(0, 0, 5, 5);
		gbc_btnVerEspectaculo.gridx = 2;
		gbc_btnVerEspectaculo.gridy = 9;
		getContentPane().add(btnVerEspectaculo, gbc_btnVerEspectaculo);
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Categorias:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 10;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 10;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		JList<String> lCategorias = new JList<String>();
		scrollPane.setViewportView(lCategorias);
		
		GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
		gbc_btnCerrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCerrar.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnCerrar.gridx = 2;
		gbc_btnCerrar.gridy = 11;
		getContentPane().add(btnCerrar, gbc_btnCerrar);
		
		/*
		 * Cuando se selecciona un paquete
		 */
		comboBoxPaquetes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED && comboBoxPaquetes.getSelectedIndex() != -1) {
					/*
					 * Cargar datos básicos
					 */
					
					
					PaqueteDto paqSel = listaPaquetes.stream()
							.filter(paq -> paq.getNombre().equals(comboBoxPaquetes.getSelectedItem().toString()))
							.findAny()
							.orElse(null);
							
					textFieldNombre.setText(paqSel.getNombre());
					textPaneDescripcion.setText(paqSel.getDescripcion());
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
					txtFechaInicio.setText(dateFormat.format(paqSel.getFechaInicio().toGregorianCalendar().getTime()));
					textFieldFechaVencimiento.setText(dateFormat.format(paqSel.getFechaFin().toGregorianCalendar().getTime()));
					textFieldFechaRegistro.setText(dateFormat.format(paqSel.getFechaRegistro().toGregorianCalendar().getTime()));
					textFieldDescuento.setText(String.valueOf(paqSel.getDescuento() + "%"));
					/*
					 * Cargar Espectaculos
					 */
					
					//
					CARGARGANDO_ESPECTACULOS = false; 
					comboBoxEspectaculos.removeAllItems();
					lCategorias.removeAll();
					
					paqSel = listaPaquetes.stream()
							.filter(paq -> paq.getNombre().equals(comboBoxPaquetes.getSelectedItem().toString()))
							.findAny()
							.orElse(null);
					
					HashSet<String> categorias = new HashSet<String>(); //Sin elementos repetidos
					
					try {
						listEsp = port.obtenerEspectaculosDePaquete(paqSel.getNombre()).getList();
						for (EspectaculoDto esp : listEsp) {
							comboBoxEspectaculos.addItem(esp.getNombre());
							// Para cada Espectaculo del paquete obtengo sus categorias
							List<CategoriaDto> aux = esp.getCategoriasAsociadas();
							for (CategoriaDto c : aux) {
								categorias.add(c.getNombre());
							}
						}
						lCategorias.setListData(categorias.toArray(new String[0]));
						comboBoxEspectaculos.setSelectedIndex(-1);
						comboBoxEspectaculos.setEnabled(true);
						CARGARGANDO_ESPECTACULOS = true;
						btnVerEspectaculo.setEnabled(false);
					} catch (NoExistePaqueteException_Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		/*
		 * Cuando selecciono un espectaculo
		 */
		comboBoxEspectaculos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED && comboBoxEspectaculos.getSelectedIndex() != -1 && CARGARGANDO_ESPECTACULOS) {
					btnVerEspectaculo.setEnabled(true);
				}
			}
		});
		
		/*
		 * Cuando selecciono ver espectaculo
		 */
		btnVerEspectaculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MensajeConsultaEspectaculo MCesp = new MensajeConsultaEspectaculo();
				
				
				EspectaculoDto espSel = listEsp.stream()
						.filter(esp -> esp.getNombre().equals(comboBoxEspectaculos.getSelectedItem().toString()))
						.findAny()
						.orElse(null);
				MCesp.cargarDatos(espSel);
				getLayeredPane().add(MCesp);
				MCesp.setVisible(true);
			}
		});
	}
}
