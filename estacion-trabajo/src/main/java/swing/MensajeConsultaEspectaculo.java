package swing;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import webservices.EspectaculoDto;
import webservices.FuncionDto;
import webservices.PaqueteDto;
import webservices.Publicador;
import webservices.PublicadorService;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MensajeConsultaEspectaculo extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();
	
	private JTextField eNombre;
	private JTextArea eDescripcion;
	private JTextField eHoras;
	private JTextField eMinutos;
	private JTextField eEspMin;
	private JTextField eEspMax;
	private JTextField eUrl;
	private JTextField eCosto;
	private JTextField eFechaReg;
	private JTextField eArtistaOrg;
	
	private DefaultListModel<String> demoListFunciones = new DefaultListModel<String>();
	private DefaultListModel<String> demoListPaquetes = new DefaultListModel<String>();
	
	private JList<String> listFunciones = new JList<String>(demoListFunciones);
	private JList<String> listPaquetes = new JList<String>(demoListPaquetes);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MensajeConsultaEspectaculo frame = new MensajeConsultaEspectaculo();
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
	public MensajeConsultaEspectaculo() {
		setClosable(true);
		getContentPane().setForeground(SystemColor.desktop);
		setTitle("Consulta de Espectaculo");
		setBounds(80, 50, 412, 466);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 73, 51, 66, 87, 0};
		gridBagLayout.rowHeights = new int[]{15, 15, 74, 15, 15, 15, 15, 15, 15, 15, 15, 15, 44, 40};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0};
		getContentPane().setLayout(gridBagLayout);;
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 1;
		getContentPane().add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		eNombre = new JTextField();
		eNombre.setEditable(false);
		GridBagConstraints gbc_eNombre = new GridBagConstraints();
		gbc_eNombre.gridwidth = 3;
		gbc_eNombre.insets = new Insets(0, 0, 5, 5);
		gbc_eNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_eNombre.gridx = 2;
		gbc_eNombre.gridy = 1;
		getContentPane().add(eNombre, gbc_eNombre);
		eNombre.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 1;
		gbc_lblNewLabel_1_2.gridy = 2;
		getContentPane().add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		eDescripcion = new JTextArea();
		eDescripcion.setEditable(false);
		scrollPane.setViewportView(eDescripcion);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Duracion:");
		GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1.gridx = 2;
		gbc_lblNewLabel_1_1_1.gridy = 3;
		getContentPane().add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Espectadores:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 3;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Horas:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 4;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		eHoras = new JTextField();
		eHoras.setEditable(false);
		GridBagConstraints gbc_eHoras = new GridBagConstraints();
		gbc_eHoras.insets = new Insets(0, 0, 5, 5);
		gbc_eHoras.fill = GridBagConstraints.HORIZONTAL;
		gbc_eHoras.gridx = 2;
		gbc_eHoras.gridy = 4;
		getContentPane().add(eHoras, gbc_eHoras);
		eHoras.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Minimos:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 3;
		gbc_lblNewLabel_5.gridy = 4;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		eEspMin = new JTextField();
		eEspMin.setEditable(false);
		GridBagConstraints gbc_eEspMin = new GridBagConstraints();
		gbc_eEspMin.insets = new Insets(0, 0, 5, 5);
		gbc_eEspMin.fill = GridBagConstraints.HORIZONTAL;
		gbc_eEspMin.gridx = 4;
		gbc_eEspMin.gridy = 4;
		getContentPane().add(eEspMin, gbc_eEspMin);
		eEspMin.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Minutos:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 5;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		eMinutos = new JTextField();
		eMinutos.setEditable(false);
		GridBagConstraints gbc_eMinutos = new GridBagConstraints();
		gbc_eMinutos.insets = new Insets(0, 0, 5, 5);
		gbc_eMinutos.fill = GridBagConstraints.HORIZONTAL;
		gbc_eMinutos.gridx = 2;
		gbc_eMinutos.gridy = 5;
		getContentPane().add(eMinutos, gbc_eMinutos);
		eMinutos.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Maximos:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 3;
		gbc_lblNewLabel_6.gridy = 5;
		getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		eEspMax = new JTextField();
		eEspMax.setEditable(false);
		GridBagConstraints gbc_eEspMax = new GridBagConstraints();
		gbc_eEspMax.insets = new Insets(0, 0, 5, 5);
		gbc_eEspMax.fill = GridBagConstraints.HORIZONTAL;
		gbc_eEspMax.gridx = 4;
		gbc_eEspMax.gridy = 5;
		getContentPane().add(eEspMax, gbc_eEspMax);
		eEspMax.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("URL:");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 7;
		getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		eUrl = new JTextField();
		eUrl.setEditable(false);
		GridBagConstraints gbc_eUrl = new GridBagConstraints();
		gbc_eUrl.gridwidth = 3;
		gbc_eUrl.anchor = GridBagConstraints.NORTH;
		gbc_eUrl.insets = new Insets(0, 0, 5, 5);
		gbc_eUrl.fill = GridBagConstraints.HORIZONTAL;
		gbc_eUrl.gridx = 2;
		gbc_eUrl.gridy = 7;
		getContentPane().add(eUrl, gbc_eUrl);
		eUrl.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Costo:");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 8;
		getContentPane().add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		eCosto = new JTextField();
		eCosto.setEditable(false);
		GridBagConstraints gbc_eCosto = new GridBagConstraints();
		gbc_eCosto.gridwidth = 3;
		gbc_eCosto.insets = new Insets(0, 0, 5, 5);
		gbc_eCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_eCosto.gridx = 2;
		gbc_eCosto.gridy = 8;
		getContentPane().add(eCosto, gbc_eCosto);
		eCosto.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Fecha registro:");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 9;
		getContentPane().add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		eFechaReg = new JTextField();
		eFechaReg.setEditable(false);
		GridBagConstraints gbc_eFechaReg = new GridBagConstraints();
		gbc_eFechaReg.gridwidth = 3;
		gbc_eFechaReg.insets = new Insets(0, 0, 5, 5);
		gbc_eFechaReg.fill = GridBagConstraints.HORIZONTAL;
		gbc_eFechaReg.gridx = 2;
		gbc_eFechaReg.gridy = 9;
		getContentPane().add(eFechaReg, gbc_eFechaReg);
		eFechaReg.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Artista organizador:");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 1;
		gbc_lblNewLabel_10.gridy = 10;
		getContentPane().add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		eArtistaOrg = new JTextField();
		eArtistaOrg.setEditable(false);
		GridBagConstraints gbc_eArtistaOrg = new GridBagConstraints();
		gbc_eArtistaOrg.gridwidth = 3;
		gbc_eArtistaOrg.insets = new Insets(0, 0, 5, 5);
		gbc_eArtistaOrg.fill = GridBagConstraints.HORIZONTAL;
		gbc_eArtistaOrg.gridx = 2;
		gbc_eArtistaOrg.gridy = 10;
		getContentPane().add(eArtistaOrg, gbc_eArtistaOrg);
		eArtistaOrg.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Funciones:");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 1;
		gbc_lblNewLabel_11.gridy = 11;
		getContentPane().add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Paquetes:");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 3;
		gbc_lblNewLabel_12.gridy = 11;
		getContentPane().add(lblNewLabel_12, gbc_lblNewLabel_12);
		
		
		listFunciones.setBorder(UIManager.getBorder("TextField.border"));
		GridBagConstraints gbc_listFunciones = new GridBagConstraints();
		gbc_listFunciones.gridwidth = 2;
		gbc_listFunciones.insets = new Insets(0, 0, 5, 5);
		gbc_listFunciones.fill = GridBagConstraints.BOTH;
		gbc_listFunciones.gridx = 1;
		gbc_listFunciones.gridy = 12;
		getContentPane().add(listFunciones, gbc_listFunciones);
		
	
		listPaquetes.setBorder(UIManager.getBorder("TextField.border"));
		GridBagConstraints gbc_listPaquetes = new GridBagConstraints();
		gbc_listPaquetes.gridwidth = 2;
		gbc_listPaquetes.insets = new Insets(0, 0, 5, 5);
		gbc_listPaquetes.fill = GridBagConstraints.BOTH;
		gbc_listPaquetes.gridx = 3;
		gbc_listPaquetes.gridy = 12;
		getContentPane().add(listPaquetes, gbc_listPaquetes);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 13;
		getContentPane().add(btnCerrar, gbc_btnNewButton);
	}
	
	public void cargarDatos(EspectaculoDto datos) {
		eNombre.setText(datos.getNombre());
		eHoras.setText(String.valueOf(datos.getDuracionHoras()));
		eDescripcion.setText(datos.getDescripcion());
		eMinutos.setText(String.valueOf(datos.getDuracionMinutos()));
		eEspMin.setText(String.valueOf(datos.getMinEspectadores()));
		eEspMax.setText(String.valueOf(datos.getMaxEspectadores()));
		eUrl.setText(datos.getUrl());
		eCosto.setText(String.valueOf(datos.getCosto()));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		eFechaReg.setText(dateFormat.format(datos.getFechaRegistro().toGregorianCalendar().getTime()));
		eArtistaOrg.setText(datos.getOrganizador().getNombre()+ " " + datos.getOrganizador().getApellido());
		for (FuncionDto func : datos.getFuncionesAsociadas())
			demoListFunciones.addElement(func.getNombre());
		for (PaqueteDto paq : datos.getPaquetesAsociados())
			demoListPaquetes.addElement(paq.getNombre());
	}
}
