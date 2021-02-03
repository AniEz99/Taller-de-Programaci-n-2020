package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import webservices.EspectaculoDto;
import webservices.EspectaculoNoExisteException_Exception;
import webservices.EspectaculoYaExisteException_Exception;
import webservices.NoExistePaqueteException_Exception;
import webservices.PaqueteDto;
import webservices.PaqueteYaExisteException_Exception;
import webservices.PlataformaDto;
import webservices.PlataformaNoExisteException_Exception;
import webservices.Publicador;
import webservices.PublicadorService;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.awt.event.ItemEvent;

public class AgregarEspectaculoAPaquete extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();
	private boolean CARGA_DE_DATOS = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarEspectaculoAPaquete frame = new AgregarEspectaculoAPaquete();
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
	public AgregarEspectaculoAPaquete() {
		setBounds(100, 100, 450, 295);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 116, 116, 0, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		/*
		 * Paquete
		 */
		JLabel lblPaquete = new JLabel("Paquete:");
		GridBagConstraints gbc_lblPaquete = new GridBagConstraints();
		gbc_lblPaquete.fill = GridBagConstraints.VERTICAL;
		gbc_lblPaquete.anchor = GridBagConstraints.WEST;
		gbc_lblPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaquete.gridx = 1;
		gbc_lblPaquete.gridy = 1;
		getContentPane().add(lblPaquete, gbc_lblPaquete);
		
		final JComboBox<String> comboBoxPaquete = new JComboBox<String>();
		comboBoxPaquete.setLightWeightPopupEnabled(false);
		GridBagConstraints gbc_comboBoxPaquete = new GridBagConstraints();
		gbc_comboBoxPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPaquete.gridx = 2;
		gbc_comboBoxPaquete.gridy = 1;
		getContentPane().add(comboBoxPaquete, gbc_comboBoxPaquete);
		
		/*
		 * Plataforma
		 */
		JLabel lblPlataforma = new JLabel("Plataforma:");
		GridBagConstraints gbc_lblPlataforma = new GridBagConstraints();
		gbc_lblPlataforma.fill = GridBagConstraints.VERTICAL;
		gbc_lblPlataforma.anchor = GridBagConstraints.WEST;
		gbc_lblPlataforma.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlataforma.gridx = 1;
		gbc_lblPlataforma.gridy = 2;
		getContentPane().add(lblPlataforma, gbc_lblPlataforma);
		
		final JComboBox<String> comboBoxPlataforma = new JComboBox<String>();
		comboBoxPlataforma.setEnabled(false);
		comboBoxPlataforma.setLightWeightPopupEnabled(false);
		GridBagConstraints gbc_comboBoxPlataforma = new GridBagConstraints();
		gbc_comboBoxPlataforma.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPlataforma.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPlataforma.gridx = 2;
		gbc_comboBoxPlataforma.gridy = 2;
		getContentPane().add(comboBoxPlataforma, gbc_comboBoxPlataforma);
		
		/*
		 * Espectaculo
		 */
		JLabel lblEspectaculo = new JLabel("Espectaculo:");
		GridBagConstraints gbc_lblEspectaculo = new GridBagConstraints();
		gbc_lblEspectaculo.fill = GridBagConstraints.VERTICAL;
		gbc_lblEspectaculo.anchor = GridBagConstraints.WEST;
		gbc_lblEspectaculo.insets = new Insets(0, 0, 5, 5);
		gbc_lblEspectaculo.gridx = 1;
		gbc_lblEspectaculo.gridy = 3;
		getContentPane().add(lblEspectaculo, gbc_lblEspectaculo);
		
		final JComboBox<String> comboBoxEspectaculo = new JComboBox<String>();
		comboBoxEspectaculo.setEnabled(false);
		comboBoxEspectaculo.setLightWeightPopupEnabled(false);
		GridBagConstraints gbc_comboBoxEspectaculo = new GridBagConstraints();
		gbc_comboBoxEspectaculo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEspectaculo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEspectaculo.gridx = 2;
		gbc_comboBoxEspectaculo.gridy = 3;
		getContentPane().add(comboBoxEspectaculo, gbc_comboBoxEspectaculo);
		
		/*
		 * Botones
		 */
		JPanel panelBtn = new JPanel();
		GridBagConstraints gbc_panelBtn = new GridBagConstraints();
		gbc_panelBtn.anchor = GridBagConstraints.SOUTHEAST;
		gbc_panelBtn.insets = new Insets(0, 0, 5, 5);
		gbc_panelBtn.gridx = 2;
		gbc_panelBtn.gridy = 4;
		getContentPane().add(panelBtn, gbc_panelBtn);
		
		JButton btnCancelar = new JButton("Cancelar");
		panelBtn.add(btnCancelar);
		
		final JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setEnabled(false);
		panelBtn.add(btnConfirmar);
		
		/*
		 * Carga de Paquetes
		 */
		
		//Disable
		comboBoxPaquete.setEnabled(false);
		comboBoxPlataforma.setEnabled(false);
		comboBoxEspectaculo.setEnabled(false);
		btnConfirmar.setEnabled(false);
		//Carga datos
		CARGA_DE_DATOS = true;
		List<PaqueteDto> listaPaquetes = port.obtenerPaquetes().getList();
		for (PaqueteDto paq : listaPaquetes) {
			comboBoxPaquete.addItem(paq.getNombre());
		}
		comboBoxPaquete.setSelectedIndex(-1);
		CARGA_DE_DATOS = false;
		//Enable
		comboBoxPaquete.setEnabled(true);
		
		/*
		 * Cuando se selecciona un paquete
		 */
		comboBoxPaquete.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED && !CARGA_DE_DATOS) {
					//Disable
					comboBoxPlataforma.setEnabled(false);
					comboBoxEspectaculo.setEnabled(false);
					btnConfirmar.setEnabled(false);
					//Carga datos
					CARGA_DE_DATOS = true;
					comboBoxPlataforma.removeAllItems();
					List<PlataformaDto> listaPlataformas = port.obtenerPlataformas().getList();
					for (PlataformaDto plat : listaPlataformas) {
						comboBoxPlataforma.addItem(plat.getNombre());
					}
					comboBoxPlataforma.setSelectedIndex(-1);
					CARGA_DE_DATOS = false;
					//Enable
					comboBoxPlataforma.setEnabled(true);
				}
			}
		});
		
		/*
		 * Cuando se selecciona una platafoma
		 */
		comboBoxPlataforma.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED && !CARGA_DE_DATOS) {
					//Disable
					comboBoxEspectaculo.setEnabled(false);
					btnConfirmar.setEnabled(false);
					//Carga datos
					CARGA_DE_DATOS = true;
					comboBoxEspectaculo.removeAllItems();
					List<EspectaculoDto> listaEspectaculos = null;
					try {
						listaEspectaculos = port.obtenerEspectaculosSinPaquete(comboBoxPlataforma.getSelectedItem().toString(), 
																			   comboBoxPaquete.getSelectedItem().toString()).getList();
					} catch (PlataformaNoExisteException_Exception ex) {
						JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERROR", 0);
					}
					for (EspectaculoDto espc : listaEspectaculos) {
						comboBoxEspectaculo.addItem(espc.getNombre());
					}
					comboBoxEspectaculo.setSelectedIndex(-1);
					CARGA_DE_DATOS = false;
					//Enable
					comboBoxEspectaculo.setEnabled(true);
				}
			}
		});
		
		/*
		 * Cuando se selecciona un espectaculo
		 */
		comboBoxEspectaculo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED && !CARGA_DE_DATOS) {
					//Enable
					btnConfirmar.setEnabled(true);
				}
			}
		});
		
		/*
		 * Cuando se confirma
		 */
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombrePaq = comboBoxPaquete.getSelectedItem().toString();
				String nombrePlat = comboBoxPlataforma.getSelectedItem().toString();
				String nombreEsp = comboBoxEspectaculo.getSelectedItem().toString();
				try {
					port.agregarEspectaculoAPaquete(nombrePaq, nombrePlat, nombreEsp);
				} catch (PlataformaNoExisteException_Exception | EspectaculoNoExisteException_Exception | NoExistePaqueteException_Exception | PaqueteYaExisteException_Exception | EspectaculoYaExisteException_Exception ex) {
					JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERROR", 0);
				}
				dispose();
			}
		});
		
		/*
		 * Cuando cancela
		 */
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

	}

}
