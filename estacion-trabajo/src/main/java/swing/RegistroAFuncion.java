package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import webservices.EspectaculoDto;
import webservices.EspectaculoNoAceptadoException_Exception;
import webservices.EspectaculoNoExisteException_Exception;
import webservices.EspectadorDto;
import webservices.EspectadorNoExisteException_Exception;
import webservices.FaltanRegistroException_Exception;
import webservices.FuncionDto;
import webservices.FuncionNoExisteException_Exception;
import webservices.PaqueteDto;
import webservices.PlataformaDto;
import webservices.PlataformaNoExisteException_Exception;
import webservices.Publicador;
import webservices.PublicadorService;
import webservices.RegistroDto;
import webservices.RegistroNoExisteException_Exception;
import webservices.StringList;
import webservices.YaExisteRegistroException_Exception;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ButtonGroup;

import java.awt.Insets;
import java.awt.Component;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.Panel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class RegistroAFuncion extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();

	// Para que los seleccionadores de registro compartan una misma lista tengo que 
	// transformarla en una parte de la interfaz?
	// En esta lista se guardara obtenerRegistrosEspectador(String nickname);
	public List<RegistroDto> listaReg = new LinkedList<RegistroDto>();
	
	List<PlataformaDto> listaPlat = null;
	List<EspectaculoDto> listaEspc = null;
	
	/**
	 * Launch the application.
	 */
	
	// Booleanos para desactivar los item listeners de los combobox cuando les agrego elementos
	boolean espectaculosListo = false;
	boolean funcionesListo = false;
	boolean espectadoresListo = false;
	boolean registrosListo = false;
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroAFuncion frame = new RegistroAFuncion();
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
	public RegistroAFuncion() {
		setClosable(true);
		setTitle("Registro Funcion de Espectaculo");
		setBounds(100, 100, 600, 500);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 160, 30, 160, 0};
		gridBagLayout.rowHeights = new int[]{90, 90, 130, 114, 5, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		
		// Plataformas
		JPanel panelPlataformas = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		getContentPane().add(panelPlataformas, gbc_panel);
		panelPlataformas.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPlataformas = new JLabel("Plataforma:");
		lblPlataformas.setBorder(new EmptyBorder(10,10,10,10));
		panelPlataformas.add(lblPlataformas, BorderLayout.NORTH);
		
		JComboBox<String> comboBoxPlataformas = new JComboBox<String>();
		comboBoxPlataformas.setLightWeightPopupEnabled(false);
		panelPlataformas.add(comboBoxPlataformas, BorderLayout.CENTER);
		
		
		// Espectaculos
		JPanel panelEspectaculos = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 0;
		getContentPane().add(panelEspectaculos, gbc_panel_1);
		panelEspectaculos.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEspectaculos = new JLabel("Espectaculo:");
		lblEspectaculos.setEnabled(false);
		lblEspectaculos.setBorder(new EmptyBorder(10,10,10,10));
		panelEspectaculos.add(lblEspectaculos, BorderLayout.NORTH);
		
		JComboBox<String> comboBoxEspectaculos = new JComboBox<String>();
		comboBoxEspectaculos.setLightWeightPopupEnabled(false);
		comboBoxEspectaculos.setEnabled(false);
		panelEspectaculos.add(comboBoxEspectaculos, BorderLayout.CENTER);
		
		
		//Funciones
		JPanel panelFunciones = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		getContentPane().add(panelFunciones, gbc_panel_2);
		panelFunciones.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFunciones = new JLabel("Funcion:");
		lblFunciones.setEnabled(false);
		lblFunciones.setBorder(new EmptyBorder(10,10,10,10));
		panelFunciones.add(lblFunciones, BorderLayout.NORTH);
		
		JComboBox<String> comboBoxFunciones = new JComboBox<String>();
		comboBoxFunciones.setLightWeightPopupEnabled(false);
		comboBoxFunciones.setEnabled(false);
		panelFunciones.add(comboBoxFunciones, BorderLayout.CENTER);
		
		
		//Espectadores
		JPanel panelEspectadores = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.anchor = GridBagConstraints.NORTH;
		gbc_panel_3.gridx = 3;
		gbc_panel_3.gridy = 1;
		getContentPane().add(panelEspectadores, gbc_panel_3);
		panelEspectadores.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEspectadores = new JLabel("Espectador:");
		lblEspectadores.setEnabled(false);
		lblEspectadores.setBorder(new EmptyBorder(10,10,10,10));
		panelEspectadores.add(lblEspectadores, BorderLayout.NORTH);
		
		JComboBox<String> comboBoxEspectadores = new JComboBox<String>();
		comboBoxEspectadores.setLightWeightPopupEnabled(false);
		comboBoxEspectadores.setEnabled(false);
		panelEspectadores.add(comboBoxEspectadores, BorderLayout.CENTER);
		
		
		//Tipo de compra
		JPanel panelTipoCompra = new JPanel();
		GridBagConstraints gbc_panelCompra = new GridBagConstraints();
		gbc_panelCompra.insets = new Insets(0, 0, 5, 5);
		gbc_panelCompra.fill = GridBagConstraints.BOTH;
		gbc_panelCompra.gridx = 1;
		gbc_panelCompra.gridy = 2;
		getContentPane().add(panelTipoCompra, gbc_panelCompra);
		panelTipoCompra.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTipoCompra = new JLabel("Tipo de compra");
		lblTipoCompra.setEnabled(false);
		lblTipoCompra.setBorder(new EmptyBorder(10,10,10,10));
		panelTipoCompra.add(lblTipoCompra, BorderLayout.NORTH);
		
		JPanel panelTipoCompra2 = new JPanel();
		panelTipoCompra2.setEnabled(false);
		panelTipoCompra.add(panelTipoCompra2, BorderLayout.CENTER);
		panelTipoCompra2.setLayout(null);
		
		ButtonGroup grupoTipoCompra = new ButtonGroup();
		
		JRadioButton buttonTipoCompraEstandar = new JRadioButton("Compra Estandar");
		buttonTipoCompraEstandar.setEnabled(false);
				
		buttonTipoCompraEstandar.setSelected(true);
		buttonTipoCompraEstandar.setHorizontalAlignment(SwingConstants.LEFT);
		buttonTipoCompraEstandar.setBounds(0, 0, 157, 23);
		panelTipoCompra2.add(buttonTipoCompraEstandar);
		grupoTipoCompra.add(buttonTipoCompraEstandar);
		
		JRadioButton buttonTipoCompraRegistros = new JRadioButton("Canje de registros");
		buttonTipoCompraRegistros.setEnabled(false);
		buttonTipoCompraRegistros.setHorizontalAlignment(SwingConstants.LEFT);
		buttonTipoCompraRegistros.setBounds(0, 48, 157, 23);
		panelTipoCompra2.add(buttonTipoCompraRegistros);
		grupoTipoCompra.add(buttonTipoCompraRegistros);
		
		JRadioButton buttonTipoCompraPaquete = new JRadioButton("Paquete de descuento");
		buttonTipoCompraPaquete.setEnabled(false);
		
		buttonTipoCompraPaquete.setHorizontalAlignment(SwingConstants.LEFT);
		buttonTipoCompraPaquete.setBounds(0, 24, 157, 23);
		panelTipoCompra2.add(buttonTipoCompraPaquete);
		grupoTipoCompra.add(buttonTipoCompraPaquete);
		
		
		// Panel Dinamico tipo compra
		JPanel panelListaTipoCompra = new JPanel();
		GridBagConstraints gbc_panelListaTipoCompra  = new GridBagConstraints();
		gbc_panelListaTipoCompra .insets = new Insets(0, 0, 5, 5);
		gbc_panelListaTipoCompra .fill = GridBagConstraints.BOTH;
		gbc_panelListaTipoCompra .gridx = 3;
		gbc_panelListaTipoCompra .gridy = 2;
		getContentPane().add(panelListaTipoCompra, gbc_panelListaTipoCompra );
		panelListaTipoCompra.setLayout(new BorderLayout(0, 0));
		
		
		// Panel dinamico cambio de registro		
		Panel panelDinamicoCanjeRegistro = new Panel();
		GridBagLayout gbl_panelDinamico = new GridBagLayout();
		gbl_panelDinamico.columnWidths = new int[]{0, 0};
		gbl_panelDinamico.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelDinamico.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelDinamico.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDinamicoCanjeRegistro.setLayout(gbl_panelDinamico);
		
		JLabel LabelElegirRegistros = new JLabel("Elegir registros");
		LabelElegirRegistros.setBorder(new EmptyBorder(10,10,10,10));
		GridBagConstraints gbc_lblElegirRegistros = new GridBagConstraints();
		gbc_lblElegirRegistros.insets = new Insets(0, 0, 5, 0);
		gbc_lblElegirRegistros.gridx = 0;
		gbc_lblElegirRegistros.gridy = 0;
		panelDinamicoCanjeRegistro.add(LabelElegirRegistros, gbc_lblElegirRegistros);
		
		JComboBox<String> comboBoxReg1 = new JComboBox<String>();
		comboBoxReg1.setLightWeightPopupEnabled(false);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		panelDinamicoCanjeRegistro.add(comboBoxReg1, gbc_comboBox);
		
		JComboBox<String> comboBoxReg2 = new JComboBox<String>();
		comboBoxReg2.setLightWeightPopupEnabled(false);
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 0;
		gbc_comboBox_1.gridy = 2;
		panelDinamicoCanjeRegistro.add(comboBoxReg2, gbc_comboBox_1);
		
		JComboBox<String> comboBoxReg3 = new JComboBox<String>();
		comboBoxReg3.setLightWeightPopupEnabled(false);
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 0;
		gbc_comboBox_2.gridy = 3;
		panelDinamicoCanjeRegistro.add(comboBoxReg3, gbc_comboBox_2);
		
		
		// Panel dinamico elegir paquete		
		Panel panelDinamicoPaquete = new Panel();
		GridBagLayout gbl_panelDinamicoPaquete = new GridBagLayout();
		gbl_panelDinamicoPaquete.columnWidths = new int[]{0, 0};
		gbl_panelDinamicoPaquete.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelDinamicoPaquete.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelDinamicoPaquete.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDinamicoPaquete.setLayout(gbl_panelDinamicoPaquete);

		JLabel LabelPaquete = new JLabel("Elegir paquete");
		LabelPaquete.setBorder(new EmptyBorder(10,10,10,10));
		GridBagConstraints gbc_LabelPaquete = new GridBagConstraints();
		gbc_LabelPaquete.insets = new Insets(0, 0, 5, 0);
		gbc_LabelPaquete.gridx = 0;
		gbc_LabelPaquete.gridy = 0;
		panelDinamicoPaquete.add(LabelPaquete, gbc_LabelPaquete);
		
		JComboBox<String> comboBoxPaquete = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxPaquete = new GridBagConstraints();
		gbc_comboBoxPaquete.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxPaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPaquete.gridx = 0;
		gbc_comboBoxPaquete.gridy = 1;
		comboBoxPaquete.setLightWeightPopupEnabled(false);
		panelDinamicoPaquete.add(comboBoxPaquete, gbc_comboBoxPaquete);
		
		
		// Panel de Botones
		JPanel panelBotones = new JPanel();
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBotones.anchor = GridBagConstraints.SOUTH;
		gbc_panelBotones.insets = new Insets(0, 0, 5, 5);
		gbc_panelBotones.gridx = 3;
		gbc_panelBotones.gridy = 3;
		getContentPane().add(panelBotones, gbc_panelBotones);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setEnabled(false);
		panelBotones.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(true);
		panelBotones.add(btnCancelar);
		
			
		/*
		 * Cargar Plataformas
		 */
		
		listaPlat = port.obtenerPlataformas().getList();
		for (PlataformaDto item : listaPlat) {
			comboBoxPlataformas.addItem(item.getNombre()); // Agrego los elementos al comboBox
		}
		comboBoxPlataformas.setSelectedIndex(-1);
		
		/*
		 * Cuando se selecciona una Plataforma
		 */
		comboBoxPlataformas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED && comboBoxPlataformas.getSelectedIndex() != -1) {
					
					
					try {
						listaEspc = port.obtenerEspectaculosAceptadosPorPlataforma(comboBoxPlataformas.getSelectedItem().toString()).getList();
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
					
					// Disable
					for (Component comp : panelFunciones.getComponents()) {
						comp.setEnabled(false);
					}
					for (Component comp : panelEspectadores.getComponents()) {
						comp.setEnabled(false);
					}
					for (Component comp : panelTipoCompra.getComponents()) {
						comp.setEnabled(false);
					}
					for (Component comp : panelTipoCompra2.getComponents()) {
						comp.setEnabled(false);
					}
					btnConfirmar.setEnabled(false);
					panelListaTipoCompra.removeAll();
					panelListaTipoCompra.repaint();
					panelListaTipoCompra.revalidate();
					buttonTipoCompraEstandar.setSelected(true);
					
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
					
					List<FuncionDto> listaFunc = new LinkedList<FuncionDto>();
										
					try {
						listaFunc = port.obtenerFuncionesVigentesDeEspectaculo(comboBoxPlataformas.getSelectedItem().toString(),
																			  comboBoxEspectaculos.getSelectedItem().toString()).getList();
					} catch (PlataformaNoExisteException_Exception | EspectaculoNoExisteException_Exception ex) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, ex.getMessage());
						dispose();
					}
					
					espectaculosListo = true;
					funcionesListo = false;
					espectadoresListo = false;
					
					comboBoxFunciones.removeAllItems();
					for (FuncionDto item : listaFunc) {
						comboBoxFunciones.addItem(item.getNombre()); // Agrego los elementos al comboBox
					}
					comboBoxFunciones.setSelectedIndex(-1);
					
					
					// Disable
					
					for (Component comp : panelEspectadores.getComponents()) {
						comp.setEnabled(false);
					}
					for (Component comp : panelTipoCompra.getComponents()) {
						comp.setEnabled(false);
					}
					for (Component comp : panelTipoCompra2.getComponents()) {
						comp.setEnabled(false);
					}
					btnConfirmar.setEnabled(false);
					panelListaTipoCompra.removeAll();
					panelListaTipoCompra.repaint();
					panelListaTipoCompra.revalidate();
					buttonTipoCompraEstandar.setSelected(true);					
					// Enable
					for (Component comp : panelFunciones.getComponents()) {
						comp.setEnabled(true);
					}
					funcionesListo = true;
				}
			}
		});
		
		/*
		 * Cuando se selecciona una funcion
		 */
		comboBoxFunciones.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED && comboBoxFunciones.getSelectedIndex() != -1 && funcionesListo) {
					
					List<EspectadorDto> listaEspc = port.obtenerEspectadoresNoRegistradosEnFuncion(comboBoxFunciones.getSelectedItem().toString()).getList();
					
					espectaculosListo = true;
					funcionesListo = true;
					espectadoresListo = false;
					
					comboBoxEspectadores.removeAllItems();
					for (EspectadorDto item : listaEspc) {
						comboBoxEspectadores.addItem(item.getNickname()); // Agrego los elementos al comboBox
					}
					comboBoxEspectadores.setSelectedIndex(-1);
					
					// Disable
					for (Component comp : panelTipoCompra.getComponents()) {
						comp.setEnabled(false);
					}
					for (Component comp : panelTipoCompra2.getComponents()) {
						comp.setEnabled(false);
					}
					btnConfirmar.setEnabled(false);
					panelListaTipoCompra.removeAll();
					panelListaTipoCompra.repaint();
					panelListaTipoCompra.revalidate();
					buttonTipoCompraEstandar.setSelected(true);
					
					// Enable
					for (Component comp : panelEspectadores.getComponents()) {
						comp.setEnabled(true);
					}
					espectadoresListo = true;
					
					
				}
			}
		});
		
		/*
		 * Cuando se selecciona un espectador
		 */
		comboBoxEspectadores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED && comboBoxEspectadores.getSelectedIndex() != -1 && espectadoresListo) {				
					
					espectaculosListo = true;
					funcionesListo = true;
					espectadoresListo = true;
					
					//Disable
					panelListaTipoCompra.removeAll();
					panelListaTipoCompra.repaint();
					panelListaTipoCompra.revalidate();
					buttonTipoCompraEstandar.setSelected(true);
					// Enable
					for (Component comp : panelTipoCompra.getComponents()) {
						comp.setEnabled(true);
					}
					for (Component comp : panelTipoCompra2.getComponents()) {
						comp.setEnabled(true);
					}
					btnConfirmar.setEnabled(true); // Tipo de compra estadar por default
					
				}
			}
		});
		
		/*
		 * Cuando se selecciona tipo de compra estandar
		 */
		buttonTipoCompraEstandar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				espectaculosListo = true;
				funcionesListo = true;
				espectadoresListo = true;
				
				panelListaTipoCompra.removeAll();
				panelListaTipoCompra.repaint();
				panelListaTipoCompra.revalidate();
				btnConfirmar.setEnabled(true);
			}
		});
		/*
		 * Cuando se selecciona tipo de compra canje registros
		 */
		buttonTipoCompraRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				espectaculosListo = true;
				funcionesListo = true;
				espectadoresListo = true;
				
				panelListaTipoCompra.removeAll();
				panelListaTipoCompra.add(panelDinamicoCanjeRegistro);
				panelListaTipoCompra.repaint();
				panelListaTipoCompra.revalidate();
				
				
				List<RegistroDto> listaReg = new LinkedList<RegistroDto>();
				try {
					listaReg = port.obtenerRegistrosEspectador(comboBoxEspectadores.getSelectedItem().toString()).getList();
				} catch (EspectadorNoExisteException_Exception e) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, e.getMessage(), "ERROR", 0);
					dispose();
				}
				
				// Elimino los Item listeners
				for( ItemListener com : comboBoxReg1.getItemListeners() ) {
					comboBoxReg1.removeItemListener( com );
			    }
				for( ItemListener com : comboBoxReg2.getItemListeners() ) {
					comboBoxReg2.removeItemListener( com );
			    }
				for( ItemListener com : comboBoxReg3.getItemListeners() ) {
					comboBoxReg3.removeItemListener( com );
			    }
				
				comboBoxReg1.removeAllItems();
				comboBoxReg2.removeAllItems();
				comboBoxReg3.removeAllItems();
				
				for (RegistroDto item : listaReg) {
					comboBoxReg1.addItem(item.getNombreFuncion()); // Agrego los elementos al comboBox
					comboBoxReg2.addItem(item.getNombreFuncion());
					comboBoxReg3.addItem(item.getNombreFuncion());
				}
				comboBoxReg1.setSelectedIndex(-1);
				comboBoxReg2.setSelectedIndex(-1);
				comboBoxReg3.setSelectedIndex(-1);
				
				//Disable
				btnConfirmar.setEnabled(false);
				
				// Agrego los listeners despues de cargados los datos
				
				/*
				 * Cuando selecciono el registro 1
				 */
				comboBoxReg1.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent arg0) {
						if (arg0.getStateChange() == ItemEvent.DESELECTED && comboBoxReg1.getSelectedIndex() != -1) {
							comboBoxReg2.addItem(arg0.getItem().toString());
							comboBoxReg3.addItem(arg0.getItem().toString());			
						} else if (arg0.getStateChange() == ItemEvent.SELECTED && comboBoxReg1.getSelectedIndex() != -1) {				
							comboBoxReg2.removeItem(arg0.getItem());
							comboBoxReg3.removeItem(arg0.getItem());	
						}
						
						if (comboBoxReg1.getSelectedIndex() != -1 &&
							comboBoxReg2.getSelectedIndex() != -1 &&
							comboBoxReg3.getSelectedIndex() != -1 ){
							btnConfirmar.setEnabled(true);
						}
					}
				});
				/*
				 * Cuando selecciono el registro 2
				 */
				comboBoxReg2.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent arg0) {
						if (arg0.getStateChange() == ItemEvent.DESELECTED && comboBoxReg2.getSelectedIndex() != -1) {
							comboBoxReg1.addItem(arg0.getItem().toString());
							comboBoxReg3.addItem(arg0.getItem().toString());			
						} else if (arg0.getStateChange() == ItemEvent.SELECTED && comboBoxReg2.getSelectedIndex() != -1) {				
							comboBoxReg1.removeItem(arg0.getItem());
							comboBoxReg3.removeItem(arg0.getItem());	
						}
						
						if (comboBoxReg1.getSelectedIndex() != -1 &&
							comboBoxReg2.getSelectedIndex() != -1 &&
							comboBoxReg3.getSelectedIndex() != -1 ){
							btnConfirmar.setEnabled(true);
						}
					}
				});
				/*
				 * Cuando selecciono el registro 3
				 */
				comboBoxReg3.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent arg0) {
						if (arg0.getStateChange() == ItemEvent.DESELECTED && comboBoxReg3.getSelectedIndex() != -1) {
							comboBoxReg1.addItem(arg0.getItem().toString());
							comboBoxReg2.addItem(arg0.getItem().toString());			
						} else if (arg0.getStateChange() == ItemEvent.SELECTED && comboBoxReg3.getSelectedIndex() != -1) {				
							comboBoxReg1.removeItem(arg0.getItem());
							comboBoxReg2.removeItem(arg0.getItem());	
						}
						
						if (comboBoxReg1.getSelectedIndex() != -1 &&
							comboBoxReg2.getSelectedIndex() != -1 &&
							comboBoxReg3.getSelectedIndex() != -1 ){
							btnConfirmar.setEnabled(true);
						}
					}
				});
				
			}
		});
		
		
		/*
		 * Cuando selecciono tipo de compra con paquetes
		 */
		buttonTipoCompraPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelListaTipoCompra.removeAll();
				panelListaTipoCompra.add(panelDinamicoPaquete);
				panelListaTipoCompra.repaint();
				panelListaTipoCompra.revalidate();
				
				List<PaqueteDto> listaPack = new LinkedList<PaqueteDto>();
				try {
					listaPack = port.obtenerPaquetesEspectadorEspectaculo(comboBoxEspectadores.getSelectedItem().toString()).getList();
				} catch (EspectadorNoExisteException_Exception e) {
					
				}
				
				comboBoxPaquete.removeAllItems();
				for (PaqueteDto elem : listaPack) {
					comboBoxPaquete.addItem(elem.getNombre());
				}
				
				//Disable
				btnConfirmar.setEnabled(false);
				comboBoxPaquete.setSelectedIndex(-1);
				
			}	
		});
		
		/*
		 * Cuando selecciono un paquete
		 */
		comboBoxPaquete.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED && comboBoxPaquete.getSelectedIndex() != -1) {				
					btnConfirmar.setEnabled(true);
				}
			}
		});
		
		/*
		 * Cuando quiero cancelar caso de uso
		 */
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		

		/*
		 * Cuando confirmo caso de uso
		 */
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * Compra estandar
				 */
				if (buttonTipoCompraEstandar.isSelected()) {
					try {
						port.altaRegistroComun(comboBoxEspectadores.getSelectedItem().toString(),
											  comboBoxPlataformas.getSelectedItem().toString(),
											  comboBoxEspectaculos.getSelectedItem().toString(), 
											  comboBoxFunciones.getSelectedItem().toString() );
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "Compra exitosa");
						dispose();
					} catch (PlataformaNoExisteException_Exception | EspectaculoNoExisteException_Exception | FuncionNoExisteException_Exception
							| EspectadorNoExisteException_Exception | YaExisteRegistroException_Exception | EspectaculoNoAceptadoException_Exception ex) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, ex.getMessage());
						dispose();
					}
				}
				/*
				 * Compra con canje de registros
				 */
				else if (buttonTipoCompraRegistros.isSelected()) {
					StringList listReg = new StringList();
					listReg.getList().add(comboBoxReg1.getSelectedItem().toString());
					listReg.getList().add(comboBoxReg2.getSelectedItem().toString());
					listReg.getList().add(comboBoxReg3.getSelectedItem().toString());
					
					try {
						port.altaRegistroConCanje(comboBoxEspectadores.getSelectedItem().toString(),
												 comboBoxPlataformas.getSelectedItem().toString(),
												 comboBoxEspectaculos.getSelectedItem().toString(),
												 comboBoxFunciones.getSelectedItem().toString(),
												 listReg);
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "Compra exitosa");
						dispose();
					} catch (PlataformaNoExisteException_Exception | EspectaculoNoExisteException_Exception | FuncionNoExisteException_Exception
							| EspectadorNoExisteException_Exception | YaExisteRegistroException_Exception | RegistroNoExisteException_Exception | FaltanRegistroException_Exception ex) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, ex.getMessage());
						dispose();
					}

				}
				
				
				
	
			}
		});
	
		
		
				

	}

	
}

