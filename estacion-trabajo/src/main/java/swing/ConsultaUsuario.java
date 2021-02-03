package swing;

import java.awt.Container;
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.SwingConstants;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionListener;

import webservices.ArtistaDto;
import webservices.EspectaculoMinDto;
import webservices.EspectadorDto;
import webservices.FuncionMinDto;
import webservices.Publicador;
import webservices.PublicadorService;
import webservices.StringList;
import webservices.UsuarioDto;
import webservices.UsuarioNoExisteException_Exception;

import javax.swing.event.ListSelectionEvent;

public class ConsultaUsuario extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCorreo;
	private JTextField txtFecha;
	private JTextArea txtDescripcion;
	private JTextArea txtBio;
	private JTextField txtSitio;
	private JList<String> listEspectaculos;
	private Map<String, String> mapaEspectaculos;
	private Map<String, String[]> mapaFunciones;
	private Container parent;
	private JInternalFrame intEspectaculo;
	private JInternalFrame intFuncion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaUsuario frame = new ConsultaUsuario(null);
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
	public ConsultaUsuario(Container p) {
		parent = p;
		
		intEspectaculo = new ConsultaEspectaculo();
		intEspectaculo.setLocation(0, 0);
		intEspectaculo.setBounds(48, 5, 571, 532);
		intEspectaculo.setVisible(false);
		parent.add(intEspectaculo);
		
		intFuncion = new ConsultaFuncionEspectaculo();
		intFuncion.setLocation(0,0);
		intFuncion.setBounds(48, 33, 453, 368);
		intFuncion.setVisible(false);
		parent.add(intFuncion);
		
		setClosable(true);
		setTitle("Consulta de usuario");
		setBounds(100, 100, 500, 480);
		getContentPane().setLayout(null);
		
		PublicadorService service = new PublicadorService();
		Publicador port = service.getPublicadorPort();
		
		StringList slNicknames = port.obtenerNicknamesUsuarios();
		List<String> nicknames = slNicknames.getList();
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
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(149, 75, 325, 20);
		getContentPane().add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(10, 107, 129, 14);
		getContentPane().add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setEditable(false);
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
		lblFecha.setBounds(10, 166, 129, 14);
		getContentPane().add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(149, 165, 325, 20);
		getContentPane().add(txtFecha);
		
		JLabel lblDesc = new JLabel("Descripcion:");
		lblDesc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesc.setBounds(10, 194, 129, 14);
		getContentPane().add(lblDesc);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setEditable(false);
		txtDescripcion.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(149, 193, 325, 44);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(txtDescripcion);
		
		JLabel lblBio = new JLabel("Biograf\u00EDa");
		lblBio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBio.setBounds(10, 249, 129, 14);
		getContentPane().add(lblBio);
		
		txtBio = new JTextArea();
		txtBio.setEditable(false);
		txtBio.setLineWrap(true);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setBounds(149, 248, 325, 76);
		getContentPane().add(scrollPane1);
		scrollPane1.setViewportView(txtBio);
		
		JLabel lblSitio = new JLabel("Sitio web:");
		lblSitio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSitio.setBounds(10, 336, 129, 14);
		getContentPane().add(lblSitio);
		
		txtSitio = new JTextField();
		txtSitio.setEditable(false);
		txtSitio.setColumns(10);
		txtSitio.setBounds(149, 335, 325, 20);
		getContentPane().add(txtSitio);
		
		JLabel lblEspectaculos = new JLabel("Espectaculos:");
		lblEspectaculos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEspectaculos.setBounds(14, 361, 125, 14);
		getContentPane().add(lblEspectaculos);

		txtNickname.setVisible(false);
		txtNombre.setVisible(false);
		txtApellido.setVisible(false);
		txtFecha.setVisible(false);
		txtCorreo.setVisible(false);
		lblNickname.setVisible(false);
		lblNombre.setVisible(false);
		lblApellido.setVisible(false);
		lblFecha.setVisible(false);
		lblCorreo.setVisible(false);
		scrollPane1.setVisible(false);
		scrollPane.setVisible(false);
		txtSitio.setVisible(false);
		lblDesc.setVisible(false);
		lblBio.setVisible(false);
		lblSitio.setVisible(false);
		lblEspectaculos.setVisible(false);
		
		listEspectaculos = new JList<String>();
		
		JScrollPane scrollPaneEsp = new JScrollPane();
		scrollPaneEsp.setBounds(149, 366, 325, 70);
		getContentPane().add(scrollPaneEsp);
		scrollPaneEsp.setViewportView(listEspectaculos);
		scrollPaneEsp.setVisible(false);
		
		JList<String> listaFunciones = new JList<String>();
		
		JScrollPane scrollPaneFun = new JScrollPane();
		scrollPaneFun.setBounds(149, 195, 325, 100);
		getContentPane().add(scrollPaneFun);
		scrollPaneFun.setViewportView(listaFunciones);
		scrollPaneFun.setVisible(false);
		
		JLabel lblFunciones = new JLabel("Funciones:"); 
		lblFunciones.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFunciones.setBounds(10, 197, 129, 14);
		getContentPane().add(lblFunciones);
		
		lblFunciones.setVisible(false);
		
		
		listEspectaculos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getFirstIndex() != listEspectaculos.getSelectedIndex()) {return;}
				String selected = (String) listEspectaculos.getSelectedValue();
				intEspectaculo.setVisible(true);
				intEspectaculo.transferFocus();
				listEspectaculos.clearSelection();
				((ConsultaEspectaculo) intEspectaculo).setPlataforma(mapaEspectaculos.get(selected));
				((ConsultaEspectaculo) intEspectaculo).setEspectaculo(selected);
			}
		});
		
		listaFunciones.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getFirstIndex() != listaFunciones.getSelectedIndex()) {return;}
				String selected = (String) listaFunciones.getSelectedValue();
				intFuncion.setVisible(true);
				intFuncion.transferFocus();
				listaFunciones.clearSelection();
				((ConsultaFuncionEspectaculo) intFuncion).plataformaComboBox.setSelectedItem(mapaFunciones.get(selected)[1]);
				((ConsultaFuncionEspectaculo) intFuncion).espectaculoComboBox.setSelectedItem(mapaFunciones.get(selected)[0]);
				((ConsultaFuncionEspectaculo) intFuncion).funcionComboBox.setSelectedItem(selected);
			}
		});
		
		cmbUsuarios.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 2 && "Seleccione un usuario...".contentEquals(e.getItem().toString())) {
					cmbUsuarios.removeItem("Seleccione un usuario...");
					txtNickname.setVisible(true);
					txtNombre.setVisible(true);
					txtApellido.setVisible(true);
					txtFecha.setVisible(true);
					txtCorreo.setVisible(true);
					lblNickname.setVisible(true);
					lblNombre.setVisible(true);
					lblApellido.setVisible(true);
					lblFecha.setVisible(true);
					lblCorreo.setVisible(true);
				}
				if (e.getStateChange() == 1) {
					try {
						UsuarioDto usuario = port.obtenerUsuario(e.getItem().toString());
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
						try
						{
							EspectadorDto espectador = (EspectadorDto) usuario;
							txtNickname.setText(espectador.getNickname());
							txtNombre.setText(espectador.getNombre());
							txtApellido.setText(espectador.getApellido());
							txtFecha.setText(dateFormat.format(espectador.getFechaNacimiento().toGregorianCalendar().getTime()));
							txtCorreo.setText(espectador.getCorreo());
							scrollPane.setVisible(false);
							scrollPane1.setVisible(false);
							lblDesc.setVisible(false);
							lblBio.setVisible(false);
							lblSitio.setVisible(false);
							txtSitio.setVisible(false);
							lblEspectaculos.setVisible(false);
							scrollPaneEsp.setVisible(false);
							scrollPaneFun.setVisible(true);
							List<FuncionMinDto> funciones = espectador.getFunciones();
							List<String> titulos = new LinkedList<String>();
							mapaFunciones = new HashMap<String, String[]>();
							funciones.forEach(f -> {
								titulos.add(f.getNombreFunc());
								mapaFunciones.put(f.getNombreFunc(), new String[]{ f.getNombreEsp(), f.getNombrePlat() });
							});
							listaFunciones.setListData(titulos.toArray(new String[0]));
							lblFunciones.setVisible(true);
						} catch (Exception ex){
							ArtistaDto artista = (ArtistaDto) usuario;
							txtNickname.setText(artista.getNickname());
							txtNombre.setText(artista.getNombre());
							txtApellido.setText(artista.getApellido());
							txtFecha.setText(dateFormat.format(artista.getFechaNacimiento().toGregorianCalendar().getTime()));
							txtCorreo.setText(artista.getCorreo());
							scrollPane.setVisible(true);
							txtDescripcion.setText(artista.getDescripcion());
							scrollPane1.setVisible(true);
							txtBio.setText(artista.getBiografia());
							txtSitio.setVisible(true);
							txtSitio.setText(artista.getSitioWeb());
							lblDesc.setVisible(true);
							lblBio.setVisible(true);
							lblSitio.setVisible(true);
							lblEspectaculos.setVisible(true);
							scrollPaneEsp.setVisible(true);
							List<EspectaculoMinDto> espectaculos = artista.getEspectaculos();
							List<String> titulos = new LinkedList<String>();
							mapaEspectaculos = new HashMap<String, String>();
							espectaculos.forEach(esp -> {
								titulos.add(esp.getNomEsp());
								mapaEspectaculos.put(esp.getNomEsp(), esp.getNomPlat());
							});
							listEspectaculos.setListData(titulos.toArray(new String[0]));
							scrollPaneFun.setVisible(false);
							lblFunciones.setVisible(false);
						}
					} catch (UsuarioNoExisteException_Exception ex) {
						//tirar error!
					}
				}
			}
		});
		

	}
	
}
