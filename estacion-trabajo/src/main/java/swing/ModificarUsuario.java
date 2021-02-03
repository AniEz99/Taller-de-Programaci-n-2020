package swing;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
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
import webservices.UsuarioDto;
import webservices.UsuarioNoExisteException_Exception;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificarUsuario extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();
	
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCorreo;
	private JTextArea txtDescripcion;
	private JTextArea txtBio;
	private JTextField txtSitio;
	private Boolean esArtista;
	private JPasswordField txtPassword;
	private File imagen = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarUsuario frame = new ModificarUsuario();
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
	public ModificarUsuario() {
		setClosable(true);
		setTitle("Modificar usuario");
		setBounds(100, 100, 500, 637);
		getContentPane().setLayout(null);
		
		List<String> nicknames = port.obtenerNicknamesUsuarios().getList();
		nicknames.add(0, "Seleccione un usuario...");
		String[] comboList = nicknames.toArray(new String[0]);
		JComboBox<String> cmbUsuarios = new JComboBox<String>(comboList);
		
		cmbUsuarios.setBounds(10, 11, 464, 22);
		getContentPane().add(cmbUsuarios);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNickname.setBounds(10, 45, 129, 14);
		getContentPane().add(lblNickname);
		
		txtNickname = new JTextField();
		txtNickname.setEditable(false);
		txtNickname.setColumns(10);
		txtNickname.setBounds(149, 44, 325, 20);
		getContentPane().add(txtNickname);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 76, 129, 14);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(149, 75, 325, 20);
		getContentPane().add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(10, 107, 129, 14);
		getContentPane().add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(149, 106, 325, 20);
		getContentPane().add(txtApellido);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorreo.setBounds(10, 138, 129, 14);
		getContentPane().add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setEditable(false);
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(149, 137, 325, 20);
		getContentPane().add(txtCorreo);
		
		JLabel lblFecha = new JLabel("Fecha de nacimiento:");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(10, 200, 129, 14);
		getContentPane().add(lblFecha);
		
		JLabel lblDesc = new JLabel("Descripcion:");
		lblDesc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesc.setBounds(10, 344, 129, 14);
		getContentPane().add(lblDesc);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(149, 343, 325, 44);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(txtDescripcion);
		
		JLabel lblBio = new JLabel("Biograf\u00EDa");
		lblBio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBio.setBounds(10, 399, 129, 14);
		getContentPane().add(lblBio);
		
		txtBio = new JTextArea();
		txtBio.setLineWrap(true);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setBounds(149, 398, 325, 76);
		getContentPane().add(scrollPane1);
		scrollPane1.setViewportView(txtBio);
		
		JLabel lblSitio = new JLabel("Sitio web:");
		lblSitio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSitio.setBounds(10, 486, 129, 14);
		getContentPane().add(lblSitio);
		
		txtSitio = new JTextField();
		txtSitio.setColumns(10);
		txtSitio.setBounds(149, 485, 325, 20);
		getContentPane().add(txtSitio);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(149, 202, 325, 130);
		getContentPane().add(calendar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(121, 570, 89, 23);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(256, 570, 89, 23);
		getContentPane().add(btnCancelar);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(149, 168, 325, 20);
		getContentPane().add(txtPassword);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(13, 175, 126, 14);
		getContentPane().add(lblPassword);
		
		JButton btnNuevaImagen = new JButton("Nueva imagen");
		btnNuevaImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(ModificarUsuario.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.append("Opening: " + fc.getSelectedFile().getName() + "." + "\n");
					imagen = fc.getSelectedFile();
			    } else {
			    	System.out.append("Open command cancelled by user." + "\n");
			    }		
				
				
			}
		});
		btnNuevaImagen.setBounds(83, 517, 127, 25);
		getContentPane().add(btnNuevaImagen);

		txtNickname.setVisible(false);
		txtNombre.setVisible(false);
		txtApellido.setVisible(false);
		calendar.setVisible(false);
		txtCorreo.setVisible(false);
		txtPassword.setVisible(false);
		lblNickname.setVisible(false);
		lblNombre.setVisible(false);
		lblApellido.setVisible(false);
		lblFecha.setVisible(false);
		lblCorreo.setVisible(false);
		lblPassword.setVisible(false);
		scrollPane1.setVisible(false);
		scrollPane.setVisible(false);
		txtSitio.setVisible(false);
		lblDesc.setVisible(false);
		lblBio.setVisible(false);
		lblSitio.setVisible(false);
		
		cmbUsuarios.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 2 && "Seleccione un usuario...".contentEquals(e.getItem().toString())) {
					cmbUsuarios.removeItem("Seleccione un usuario...");
					txtNickname.setVisible(true);
					txtNombre.setVisible(true);
					txtApellido.setVisible(true);
					txtCorreo.setVisible(true);
					txtPassword.setVisible(true);
					calendar.setVisible(true);
					lblNickname.setVisible(true);
					lblNombre.setVisible(true);
					lblApellido.setVisible(true);
					lblFecha.setVisible(true);
					lblCorreo.setVisible(true);
					lblPassword.setVisible(true);
				}
				if (e.getStateChange() == 1) {
					try {
						UsuarioDto usuario = port.obtenerUsuario(e.getItem().toString());
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
						try
						{
							EspectadorDto espectador = (EspectadorDto) usuario;
							esArtista = false;
							txtNickname.setText(espectador.getNickname());
							txtNombre.setText(espectador.getNombre());
							txtApellido.setText(espectador.getApellido());
							calendar.setDate(espectador.getFechaNacimiento().toGregorianCalendar().getTime());
							txtCorreo.setText(espectador.getCorreo());
							txtPassword.setText(espectador.getContrasenia());
							scrollPane.setVisible(false);
							scrollPane1.setVisible(false);
							lblDesc.setVisible(false);
							lblBio.setVisible(false);
							lblSitio.setVisible(false);
							txtSitio.setVisible(false);
						} catch (Exception ex){
							esArtista = true;
							ArtistaDto artista = (ArtistaDto) usuario;
							txtNickname.setText(artista.getNickname());
							txtNombre.setText(artista.getNombre());
							txtApellido.setText(artista.getApellido());
							calendar.setDate(artista.getFechaNacimiento().toGregorianCalendar().getTime());
							txtCorreo.setText(artista.getCorreo());
							txtPassword.setText(artista.getContrasenia());
							scrollPane.setVisible(true);
							txtDescripcion.setText(artista.getDescripcion());
							scrollPane1.setVisible(true);
							txtBio.setText(artista.getBiografia());
							txtSitio.setVisible(true);
							txtSitio.setText(artista.getSitioWeb());
							lblDesc.setVisible(true);
							lblBio.setVisible(true);
							lblSitio.setVisible(true);
						}
					} catch (UsuarioNoExisteException_Exception ex) {
						//tirar error!
					}
				}
			}
		});
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nickname = txtNickname.getText();
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String correo = txtCorreo.getText();
				
				Date fechaNacimiento = calendar.getDate();
				
				GregorianCalendar bdateGC = new GregorianCalendar();
	    		bdateGC.setTime(fechaNacimiento);
	    		XMLGregorianCalendar xmlbdateGC = null;
				try {
					xmlbdateGC = DatatypeFactory.newInstance().newXMLGregorianCalendar(bdateGC);
				} catch (DatatypeConfigurationException e2) {
					e2.printStackTrace();
				}
				
			
				String contrasenia = txtPassword.getText();
				if (nickname.equals("") || nombre.equals("") || apellido.equals("") || correo.equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Hay datos requeridos sin ingresar.", "ERROR", 0);
					return;
				}
				if (!esArtista) {
					EspectadorDto dto = new EspectadorDto();
					dto.setNickname(nickname);
					dto.setNombre(nombre);
					dto.setApellido(apellido);
					dto.setCorreo(correo);
					dto.setFechaNacimiento(xmlbdateGC);
					dto.setContrasenia(contrasenia);
					ImagenDto img = new ImagenDto();
					if (imagen != null) {
						try {
							img.setContent(Files.readAllBytes(imagen.toPath()));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						img.setName(imagen.getName());
					}
					try {
						port.editarEspectador(dto, img);
						JOptionPane.showMessageDialog(rootPane, "Usuario modificado exitosamente.", "Exito", 1);
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
					dto.setFechaNacimiento(xmlbdateGC);
					dto.setDescripcion(descripcion);
					dto.setBiografia(bio);
					dto.setSitioWeb(sitioWeb);
					dto.setContrasenia(contrasenia);
					ImagenDto img = new ImagenDto();
					if (imagen != null) {
						try {
							img.setContent(Files.readAllBytes(imagen.toPath()));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						img.setName(imagen.getName());
					}
					try {
						port.editarArtista(dto, img);	
						JOptionPane.showMessageDialog(rootPane, "Usuario modificado exitosamente.", "Exito", 1);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERROR", 0);
					}
				}
			}
		});

	}
}
