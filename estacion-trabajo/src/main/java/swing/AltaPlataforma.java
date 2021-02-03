package swing;

import java.awt.EventQueue;


import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import webservices.CamposIncompletosException_Exception;
import webservices.PlataformaDto;
import webservices.PlataformaYaExisteException_Exception;
import webservices.Publicador;
import webservices.PublicadorService;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;

public class AltaPlataforma extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaPlataforma frame = new AltaPlataforma();
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
	public AltaPlataforma() {
		setClosable(true);
		setTitle("Alta Plataforma");
		setBounds(100, 100, 600, 500);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 130, 30, 190, 0};
		gridBagLayout.rowHeights = new int[]{33, 33, 33, 33, 71, 37, 5, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.fill = GridBagConstraints.BOTH;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		getContentPane().add(lblNombre, gbc_lblNombre);
		
		JTextField txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.fill = GridBagConstraints.BOTH;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 1;
		getContentPane().add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblUrl = new JLabel("Url:");
		GridBagConstraints gbc_lblUrl = new GridBagConstraints();
		gbc_lblUrl.fill = GridBagConstraints.BOTH;
		gbc_lblUrl.insets = new Insets(0, 0, 5, 5);
		gbc_lblUrl.gridx = 1;
		gbc_lblUrl.gridy = 2;
		getContentPane().add(lblUrl, gbc_lblUrl);
		
		JTextField textUrl = new JTextField();
		GridBagConstraints gbc_textUrl = new GridBagConstraints();
		gbc_textUrl.fill = GridBagConstraints.BOTH;
		gbc_textUrl.insets = new Insets(0, 0, 5, 5);
		gbc_textUrl.gridx = 3;
		gbc_textUrl.gridy = 2;
		getContentPane().add(textUrl, gbc_textUrl);
		
		JLabel lblDescripcin = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.fill = GridBagConstraints.BOTH;
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 1;
		gbc_lblDescripcin.gridy = 3;
		getContentPane().add(lblDescripcin, gbc_lblDescripcin);
		
		JTextArea txtDescripcion = new JTextArea();
		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.gridwidth = 3;
		gbc_txtDescripcion.fill = GridBagConstraints.BOTH;
		gbc_txtDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescripcion.gridx = 1;
		gbc_txtDescripcion.gridy = 4;
		getContentPane().add(txtDescripcion, gbc_txtDescripcion);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 5;
		getContentPane().add(panel, gbc_panel);
		
		JButton btnConfirmar = new JButton("Confirmar");
		panel.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
		
		/*
		 * Cancelar caso de uso
		 */
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		/*
		 * Confirmar caso de uso
		 */
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlataformaDto nuevaPlatInfo = new PlataformaDto();
				nuevaPlatInfo.setNombre(txtNombre.getText());
				nuevaPlatInfo.setUrl(textUrl.getText());
				nuevaPlatInfo.setDescripcion(txtDescripcion.getText());
				
				try {
					port.altaPlataforma(nuevaPlatInfo);
					JOptionPane.showMessageDialog(rootPane, "Plataforma creada exitosamente.", "Exito", 1);
					dispose();
				} catch (PlataformaYaExisteException_Exception ex) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				} catch (CamposIncompletosException_Exception ex)	{
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}
			}
		});
		
		
		
		
		

	}

}
