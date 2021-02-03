package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import webservices.EspectaculoDto;
import webservices.EspectaculoNoExisteException_Exception;
import webservices.PlataformaDto;
import webservices.PlataformaNoExisteException_Exception;
import webservices.Publicador;
import webservices.PublicadorService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AceptarCancelarEspectaculo extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	public boolean CARGA_DE_DATOS = true;

	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AceptarCancelarEspectaculo frame = new AceptarCancelarEspectaculo();
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
	public AceptarCancelarEspectaculo() {
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 97, 172, 34, 0};
		gridBagLayout.rowHeights = new int[]{31, 0, 0, 0, 14, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		/*
		 * Plataforma
		 */
		JLabel lblPlataforma = new JLabel("Plataforma:");
		GridBagConstraints gbc_lblPlataforma = new GridBagConstraints();
		gbc_lblPlataforma.anchor = GridBagConstraints.WEST;
		gbc_lblPlataforma.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlataforma.gridx = 1;
		gbc_lblPlataforma.gridy = 1;
		getContentPane().add(lblPlataforma, gbc_lblPlataforma);
		
		final JComboBox<String> comboBoxPlataforma = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxPlataforma = new GridBagConstraints();
		gbc_comboBoxPlataforma.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPlataforma.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPlataforma.gridx = 2;
		gbc_comboBoxPlataforma.gridy = 1;
		getContentPane().add(comboBoxPlataforma, gbc_comboBoxPlataforma);
		
		/*
		 * Espectaculo
		 */
		JLabel lblEspectaculo = new JLabel("Espectaculo:");
		GridBagConstraints gbc_lblEspectaculo = new GridBagConstraints();
		gbc_lblEspectaculo.anchor = GridBagConstraints.WEST;
		gbc_lblEspectaculo.insets = new Insets(0, 0, 5, 5);
		gbc_lblEspectaculo.gridx = 1;
		gbc_lblEspectaculo.gridy = 2;
		getContentPane().add(lblEspectaculo, gbc_lblEspectaculo);
		
		final JComboBox<String> comboBoxEspectaculo = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxEspectaculo = new GridBagConstraints();
		gbc_comboBoxEspectaculo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEspectaculo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEspectaculo.gridx = 2;
		gbc_comboBoxEspectaculo.gridy = 2;
		getContentPane().add(comboBoxEspectaculo, gbc_comboBoxEspectaculo);
		
		/*
		 * Botones
		 */
		JPanel panelBotones = new JPanel();
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.anchor = GridBagConstraints.SOUTHEAST;
		gbc_panelBotones.insets = new Insets(0, 0, 5, 5);
		gbc_panelBotones.gridx = 2;
		gbc_panelBotones.gridy = 3;
		getContentPane().add(panelBotones, gbc_panelBotones);
		
		final JButton btnAceptar = new JButton("Aceptar");
		panelBotones.add(btnAceptar);
		
		final JButton btnRechazar = new JButton("Rechazar");
		panelBotones.add(btnRechazar);
		
		JButton btnCerrar = new JButton("Cerrar");
		panelBotones.add(btnCerrar);
		
		/*
		 * Carga de plataformas
		 */
		// Disable
		comboBoxPlataforma.setEnabled(false);
		comboBoxEspectaculo.setEnabled(false);
		btnAceptar.setEnabled(false);
		btnRechazar.setEnabled(false);
		// Carga de datos
		CARGA_DE_DATOS = true;
		List<PlataformaDto> listaPlataformas = port.obtenerPlataformas().getList();
		for (PlataformaDto plat : listaPlataformas) {
			comboBoxPlataforma.addItem(plat.getNombre());
		}
		comboBoxPlataforma.setSelectedIndex(-1);
		CARGA_DE_DATOS = false;
		// Enable
		comboBoxPlataforma.setEnabled(true);
		
		/*
		 * Cuando se selecciona Plataforma
		 */
		comboBoxPlataforma.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED && !CARGA_DE_DATOS) {
					// Disable
					comboBoxEspectaculo.setEnabled(false);
					btnAceptar.setEnabled(false);
					btnRechazar.setEnabled(false);
					// Carga de datos
					CARGA_DE_DATOS = true;
					comboBoxEspectaculo.removeAllItems();
					List<EspectaculoDto> listaEspectaculos = null;
					try {
						listaEspectaculos = port.obtenerEspectaculosIngresados(comboBoxPlataforma.getSelectedItem().toString()).getList();
					} catch (PlataformaNoExisteException_Exception e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage(), "ERROR", 0);
					}
					for (EspectaculoDto esp : listaEspectaculos) {
						comboBoxEspectaculo.addItem(esp.getNombre());
					}
					comboBoxEspectaculo.setSelectedIndex(-1);
					CARGA_DE_DATOS = false;
					// Enable
					comboBoxEspectaculo.setEnabled(true);
				}
			}
		});
		
		/*
		 * Cuando se selecciona Espectaculo
		 */
		comboBoxEspectaculo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED && !CARGA_DE_DATOS) {
					// Enable
					btnAceptar.setEnabled(true);
					btnRechazar.setEnabled(true);
				}
			}
		});
		
		/*
		 * Cuando acepto un espectaculo
		 */
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!CARGA_DE_DATOS) {
					try {
						port.aceptarEspectaculo(comboBoxPlataforma.getSelectedItem().toString(), comboBoxEspectaculo.getSelectedItem().toString());
						JOptionPane.showMessageDialog(rootPane, "Espectaculo aceptado", "Exito", 1);
					} catch (EspectaculoNoExisteException_Exception | PlataformaNoExisteException_Exception e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage(), "ERROR", 0);
					}
					// Cerrar
					dispose();
				}
			}
		});
		
		/*
		 * Cuando cancelo un espectaculo
		 */
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!CARGA_DE_DATOS) {
					try {
						
						port.cancelarEspectaculo(comboBoxPlataforma.getSelectedItem().toString(), comboBoxEspectaculo.getSelectedItem().toString());
						JOptionPane.showMessageDialog(rootPane, "Espectaculo cancelado", "Exito", 1);
					} catch (EspectaculoNoExisteException_Exception | PlataformaNoExisteException_Exception e) {
						JOptionPane.showMessageDialog(rootPane, e.getMessage(), "ERROR", 0);
					}
					// Cerrar
					dispose();
				}
			}
		});
		
		/*
		 * Cuando cierro
		 */
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		
	}

}
