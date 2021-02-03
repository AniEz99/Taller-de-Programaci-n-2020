package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.toedter.calendar.JCalendar;

import webservices.ArtistaDto;
import webservices.EspectadorDto;
import webservices.ImagenDto;
import webservices.Publicador;
import webservices.PublicadorService;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaUsuario extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCorreo;
	private JTextField txtSitio;
	private JTextArea txtDescripcion;
	private JTextArea txtBio;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private File imagen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaUsuario frame = new AltaUsuario();
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
	public AltaUsuario() {
		PublicadorService service = new PublicadorService();
		Publicador port = service.getPublicadorPort();
		
		setTitle("Alta Usuario");
		setClosable(true);
		setBounds(100, 100, 506, 687);
		getContentPane().setLayout(null);		
		
		JLabel lblNewLabel = new JLabel("Nickname:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 37, 139, 14);
		getContentPane().add(lblNewLabel);
		
		txtNickname = new JTextField();
		txtNickname.setBounds(159, 31, 321, 20);
		getContentPane().add(txtNickname);
		txtNickname.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 68, 139, 14);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(159, 62, 321, 20);
		getContentPane().add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(10, 99, 139, 14);
		getContentPane().add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(159, 93, 321, 20);
		getContentPane().add(txtApellido);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorreo.setBounds(10, 130, 139, 14);
		getContentPane().add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(159, 124, 321, 20);
		getContentPane().add(txtCorreo);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDeNacimiento.setBounds(10, 158, 139, 14);
		getContentPane().add(lblFechaDeNacimiento);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(159, 155, 321, 150);
		calendar.setMinSelectableDate(new Date(0, 0, 1));
		getContentPane().add(calendar);
		
		JRadioButton radioArtista = new JRadioButton("Artista");
		radioArtista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (radioArtista.isSelected()) {
					txtDescripcion.setEnabled(true);
					txtBio.setEnabled(true);
					txtSitio.setEnabled(true);
				} else {
					txtDescripcion.setEnabled(false);
					txtBio.setEnabled(false);
					txtSitio.setEnabled(false);
				}
			}
		});
		radioArtista.setBounds(270, 379, 76, 23);
		getContentPane().add(radioArtista);
		
		JRadioButton radioEspectador = new JRadioButton("Espectador");
		radioEspectador.setSelected(true);
		radioEspectador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (radioArtista.isSelected()) {
					txtDescripcion.setEnabled(true);
					txtBio.setEnabled(true);
					txtSitio.setEnabled(true);
				} else {
					txtDescripcion.setEnabled(false);
					txtDescripcion.setText("");
					txtBio.setEnabled(false);
					txtBio.setText("");
					txtSitio.setEnabled(false);
					txtSitio.setText("");
				}
			}
		});
		radioEspectador.setBounds(159, 379, 91, 23);
		getContentPane().add(radioEspectador);
		
		ButtonGroup radioButtons = new ButtonGroup();
		radioButtons.add(radioEspectador);
		radioButtons.add(radioArtista);
		
		JLabel lblDescripcin = new JLabel("Descripcion:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setBounds(10, 418, 139, 14);
		getContentPane().add(lblDescripcin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(159, 412, 321, 44);
		getContentPane().add(scrollPane);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setEnabled(false);
		scrollPane.setViewportView(txtDescripcion);
		
		JLabel lblBiografa = new JLabel("Biograf\u00EDa:");
		lblBiografa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBiografa.setBounds(10, 473, 139, 14);
		getContentPane().add(lblBiografa);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(159, 467, 321, 68);
		getContentPane().add(scrollPane2);
		
		txtBio = new JTextArea();
		txtBio.setLineWrap(true);
		txtBio.setEnabled(false);
		scrollPane2.setViewportView(txtBio);
		
		JLabel lblSitioWeb = new JLabel("Sitio web:");
		lblSitioWeb.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSitioWeb.setBounds(10, 552, 139, 14);
		getContentPane().add(lblSitioWeb);
		
		txtSitio = new JTextField();
		txtSitio.setEnabled(false);
		txtSitio.setColumns(10);
		txtSitio.setBounds(159, 546, 321, 20);
		getContentPane().add(txtSitio);
		
		JButton btnSubmit = new JButton("Aceptar");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nickname = txtNickname.getText();
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String correo = txtCorreo.getText();
				Date fechaNacimiento = calendar.getDate();
				String contrasenia = passwordField.getText();
				String contrasenia2 = passwordField_1.getText();
				if (nickname.equals("") || nombre.equals("") || apellido.equals("") || correo.equals("") || contrasenia.equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Hay datos requeridos sin ingresar.", "ERROR", 0);
					return;
				}
				if (!contrasenia.equals(contrasenia2)) {
					JOptionPane.showMessageDialog(rootPane, "La confirmacion de la contrase�a no coincide.", "ERROR", 0);
					return;
				}
				if (radioEspectador.isSelected()) {
					EspectadorDto dto = new EspectadorDto();
					dto.setNickname(nickname);
					dto.setNombre(nombre);
					dto.setApellido(apellido);
					dto.setCorreo(correo);
					// Formateador de tiempo (el central no maneja DATE sino que XMLGregorianCalendar)
		    		GregorianCalendar bdateGC = new GregorianCalendar();
		    		bdateGC.setTime(fechaNacimiento);
		    		XMLGregorianCalendar xmlbdateGC = null;
					try {
						xmlbdateGC = DatatypeFactory.newInstance().newXMLGregorianCalendar(bdateGC);
					} catch (DatatypeConfigurationException e2) {
						e2.printStackTrace();
					}
					dto.setFechaNacimiento(xmlbdateGC);
					
					dto.setContrasenia(contrasenia);
					dto.setUrlImagen(null);
							
					ImagenDto img = new ImagenDto();
					if (imagen != null) {
						try {
							img.setContent(Files.readAllBytes(imagen.toPath()));
							img.setName(imagen.getName());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					try {
						port.registrarEspectador(dto, img);
						JOptionPane.showMessageDialog(rootPane, "Usuario agregado exitosamente.", "Exito", 1);
						dispose();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERROR", 0);
					}
				} else {
					String descripcion = txtDescripcion.getText();
					if (descripcion.equals("")) {
						JOptionPane.showMessageDialog(rootPane, "Hay datos requeridos sin ingresar.", "ERROR", 0);
						return;
					}
					String bio = txtBio.getText();
					String sitioWeb = txtSitio.getText();
					ArtistaDto dto = new ArtistaDto();
					dto.setNickname(nickname);
					dto.setNombre(nombre);
					dto.setApellido(apellido);
					dto.setCorreo(correo);
					// Formateador de tiempo (el central no maneja DATE sino que XMLGregorianCalendar)
		    		GregorianCalendar bdateGC = new GregorianCalendar();
		    		bdateGC.setTime(fechaNacimiento);
		    		XMLGregorianCalendar xmlbdateGC = null;
					try {
						xmlbdateGC = DatatypeFactory.newInstance().newXMLGregorianCalendar(bdateGC);
					} catch (DatatypeConfigurationException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					dto.setFechaNacimiento(xmlbdateGC);
					dto.setDescripcion(descripcion);
					dto.setBiografia(bio);
					dto.setSitioWeb(sitioWeb);
					dto.setContrasenia("");
					dto.setUrlImagen(null);
					
					ImagenDto img = new ImagenDto();
					if (imagen != null) {
						try {
							img.setContent(Files.readAllBytes(imagen.toPath()));
							img.setName(imagen.getName());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
						
					try {
						port.registrarArtista(dto, img);	
						JOptionPane.showMessageDialog(rootPane, "Usuario agregado exitosamente.", "Exito", 1);
						dispose();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERROR", 0);
					}
				}
			}
		});
		btnSubmit.setBounds(105, 620, 118, 23);
		getContentPane().add(btnSubmit);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(270, 620, 103, 23);
		getContentPane().add(btnCancelar);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasea.setBounds(10, 322, 139, 14);
		getContentPane().add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(159, 316, 321, 20);
		getContentPane().add(passwordField);
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar contrase\u00F1a:");
		lblConfirmarContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmarContrasea.setBounds(10, 353, 139, 14);
		getContentPane().add(lblConfirmarContrasea);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(159, 347, 321, 20);
		getContentPane().add(passwordField_1);
		
		JLabel lblSeleccionarFoto = new JLabel("Seleccionar foto:");
		lblSeleccionarFoto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarFoto.setBounds(46, 593, 103, 15);
		getContentPane().add(lblSeleccionarFoto);
		
		/*
		 * Boton para abrir foto
		 */
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(AltaUsuario.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.append("Opening: " + fc.getSelectedFile().getName() + "." + "\n");
					imagen = fc.getSelectedFile();
			    } else {
			    	System.out.append("Open command cancelled by user." + "\n");
			    }		
			}
		});
		btnSeleccionar.setBounds(159, 583, 118, 25);
		getContentPane().add(btnSeleccionar);
		
	}
}
