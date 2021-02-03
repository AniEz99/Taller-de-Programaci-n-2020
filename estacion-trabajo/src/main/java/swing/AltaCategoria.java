package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import webservices.Publicador;
import webservices.PublicadorService;
import webservices.YaExisteCategoriaException_Exception;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AltaCategoria extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaCategoria frame = new AltaCategoria();
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
	public AltaCategoria() {
		setBounds(100, 100, 358, 257);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese el Nombre de la Categoria:");
		lblNewLabel.setBounds(37, 42, 295, 39);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(37, 110, 229, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(37, 174, 111, 23);
		getContentPane().add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreCat = textField.getText();
				if (nombreCat.equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Debe ingresar un nombre para la categoria", "ERROR", 0);
					return;
				}
				try {
					port.altaCategoria(nombreCat);
					JOptionPane.showMessageDialog(rootPane, "Categoria creada exitosamente.", "Exito", 1);
					dispose();
				} catch (YaExisteCategoriaException_Exception e1) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, e1.getMessage());
				}
				
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(158, 174, 111, 23);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}
}
