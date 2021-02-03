package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextField;

import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import webservices.CategoriaDto;
import webservices.EspectaculoDto;
import webservices.Estado;
import webservices.FuncionDto;
import webservices.PaqueteDto;
import webservices.Publicador;
import webservices.PublicadorService;

import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class ConsultaEspectaculo extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();
	
	private JTextField eNombre;
	private JTextField eHoras;
	private JTextField eMinutos;
	private JTextField eEspMin;
	private JTextField eEspMax;
	private JTextField eUrl;
	private JTextField eCosto;
	private JTextField eFechaReg;
	private JTextField eArtistaOrg;
	private JComboBox<String> plataformas;
	private JComboBox<String> espectaculos;
	boolean ESPECTACULOS_CARGADOS = false;
	boolean PAQUETES_CARGADOS = false;
	boolean FUNCIONES_CARGADAS = false;
	private JTextField eEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaEspectaculo frame = new ConsultaEspectaculo();
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
	public ConsultaEspectaculo() {
		setClosable(true);
		getContentPane().setForeground(SystemColor.desktop);
		setTitle("Consulta de Espectaculo");
		setBounds(100, 100, 605, 596);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{66, 70, 74, 134, 121, 93, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 74, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 61, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		
		JLabel lblNewLabel = new JLabel("Plataforma:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		plataformas = new JComboBox<String>();
		GridBagConstraints gbc_plataformas = new GridBagConstraints();
		gbc_plataformas.gridwidth = 3;
		gbc_plataformas.insets = new Insets(0, 0, 5, 5);
		gbc_plataformas.fill = GridBagConstraints.HORIZONTAL;
		gbc_plataformas.gridx = 2;
		gbc_plataformas.gridy = 1;
		getContentPane().add(plataformas, gbc_plataformas);
		
		JLabel lblNewLabel_1 = new JLabel("Espectaculos:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		espectaculos = new JComboBox<String>();
		GridBagConstraints gbc_espectaculos = new GridBagConstraints();
		gbc_espectaculos.gridwidth = 3;
		gbc_espectaculos.insets = new Insets(0, 0, 5, 5);
		gbc_espectaculos.fill = GridBagConstraints.HORIZONTAL;
		gbc_espectaculos.gridx = 2;
		gbc_espectaculos.gridy = 2;
		getContentPane().add(espectaculos, gbc_espectaculos);
		espectaculos.setEnabled(false);;
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 3;
		getContentPane().add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		eNombre = new JTextField();
		eNombre.setEditable(false);
		GridBagConstraints gbc_eNombre = new GridBagConstraints();
		gbc_eNombre.gridwidth = 3;
		gbc_eNombre.insets = new Insets(0, 0, 5, 5);
		gbc_eNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_eNombre.gridx = 2;
		gbc_eNombre.gridy = 3;
		getContentPane().add(eNombre, gbc_eNombre);
		eNombre.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 1;
		gbc_lblNewLabel_1_2.gridy = 4;
		getContentPane().add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 4;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		JTextArea eDescripcion = new JTextArea();
		eDescripcion.setEditable(false);
		scrollPane.setViewportView(eDescripcion);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Duracion:");
		GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1.gridx = 2;
		gbc_lblNewLabel_1_1_1.gridy = 5;
		getContentPane().add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Espectadores:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 5;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Horas:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 6;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		eHoras = new JTextField();
		eHoras.setEditable(false);
		GridBagConstraints gbc_eHoras = new GridBagConstraints();
		gbc_eHoras.insets = new Insets(0, 0, 5, 5);
		gbc_eHoras.fill = GridBagConstraints.HORIZONTAL;
		gbc_eHoras.gridx = 2;
		gbc_eHoras.gridy = 6;
		getContentPane().add(eHoras, gbc_eHoras);
		eHoras.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Minimos:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 3;
		gbc_lblNewLabel_5.gridy = 6;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		eEspMin = new JTextField();
		eEspMin.setEditable(false);
		GridBagConstraints gbc_eEspMin = new GridBagConstraints();
		gbc_eEspMin.insets = new Insets(0, 0, 5, 5);
		gbc_eEspMin.fill = GridBagConstraints.HORIZONTAL;
		gbc_eEspMin.gridx = 4;
		gbc_eEspMin.gridy = 6;
		getContentPane().add(eEspMin, gbc_eEspMin);
		eEspMin.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Minutos:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 7;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		eMinutos = new JTextField();
		eMinutos.setEditable(false);
		GridBagConstraints gbc_eMinutos = new GridBagConstraints();
		gbc_eMinutos.insets = new Insets(0, 0, 5, 5);
		gbc_eMinutos.fill = GridBagConstraints.HORIZONTAL;
		gbc_eMinutos.gridx = 2;
		gbc_eMinutos.gridy = 7;
		getContentPane().add(eMinutos, gbc_eMinutos);
		eMinutos.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Maximos:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 3;
		gbc_lblNewLabel_6.gridy = 7;
		getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		eEspMax = new JTextField();
		eEspMax.setEditable(false);
		GridBagConstraints gbc_eEspMax = new GridBagConstraints();
		gbc_eEspMax.insets = new Insets(0, 0, 5, 5);
		gbc_eEspMax.fill = GridBagConstraints.HORIZONTAL;
		gbc_eEspMax.gridx = 4;
		gbc_eEspMax.gridy = 7;
		getContentPane().add(eEspMax, gbc_eEspMax);
		eEspMax.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Estado:");
		GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
		gbc_lblNewLabel_14.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_14.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_14.gridx = 1;
		gbc_lblNewLabel_14.gridy = 8;
		getContentPane().add(lblNewLabel_14, gbc_lblNewLabel_14);
		
		eEstado = new JTextField();
		eEstado.setEditable(false);
		GridBagConstraints gbc_eEstado = new GridBagConstraints();
		gbc_eEstado.gridwidth = 3;
		gbc_eEstado.insets = new Insets(0, 0, 5, 5);
		gbc_eEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_eEstado.gridx = 2;
		gbc_eEstado.gridy = 8;
		getContentPane().add(eEstado, gbc_eEstado);
		eEstado.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("URL:");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 9;
		getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		eUrl = new JTextField();
		eUrl.setEditable(false);
		GridBagConstraints gbc_eUrl = new GridBagConstraints();
		gbc_eUrl.gridwidth = 3;
		gbc_eUrl.anchor = GridBagConstraints.NORTH;
		gbc_eUrl.insets = new Insets(0, 0, 5, 5);
		gbc_eUrl.fill = GridBagConstraints.HORIZONTAL;
		gbc_eUrl.gridx = 2;
		gbc_eUrl.gridy = 9;
		getContentPane().add(eUrl, gbc_eUrl);
		eUrl.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Costo:");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 10;
		getContentPane().add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		eCosto = new JTextField();
		eCosto.setEditable(false);
		GridBagConstraints gbc_eCosto = new GridBagConstraints();
		gbc_eCosto.gridwidth = 2;
		gbc_eCosto.insets = new Insets(0, 0, 5, 5);
		gbc_eCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_eCosto.gridx = 2;
		gbc_eCosto.gridy = 10;
		getContentPane().add(eCosto, gbc_eCosto);
		eCosto.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Fecha registro:");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 11;
		getContentPane().add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		eFechaReg = new JTextField();
		eFechaReg.setEditable(false);
		GridBagConstraints gbc_eFechaReg = new GridBagConstraints();
		gbc_eFechaReg.gridwidth = 3;
		gbc_eFechaReg.insets = new Insets(0, 0, 5, 5);
		gbc_eFechaReg.fill = GridBagConstraints.HORIZONTAL;
		gbc_eFechaReg.gridx = 2;
		gbc_eFechaReg.gridy = 11;
		getContentPane().add(eFechaReg, gbc_eFechaReg);
		eFechaReg.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Artista organizador:");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 1;
		gbc_lblNewLabel_10.gridy = 12;
		getContentPane().add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		eArtistaOrg = new JTextField();
		eArtistaOrg.setEditable(false);
		GridBagConstraints gbc_eArtistaOrg = new GridBagConstraints();
		gbc_eArtistaOrg.gridwidth = 3;
		gbc_eArtistaOrg.insets = new Insets(0, 0, 5, 5);
		gbc_eArtistaOrg.fill = GridBagConstraints.HORIZONTAL;
		gbc_eArtistaOrg.gridx = 2;
		gbc_eArtistaOrg.gridy = 12;
		getContentPane().add(eArtistaOrg, gbc_eArtistaOrg);
		eArtistaOrg.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Funciones:");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 3;
		gbc_lblNewLabel_11.gridy = 13;
		getContentPane().add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		JComboBox<String> funciones = new JComboBox<String>();
		GridBagConstraints gbc_funciones = new GridBagConstraints();
		gbc_funciones.gridwidth = 3;
		gbc_funciones.insets = new Insets(0, 0, 5, 5);
		gbc_funciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_funciones.gridx = 2;
		gbc_funciones.gridy = 14;
		getContentPane().add(funciones, gbc_funciones);
		funciones.setEnabled(false);
		
		JLabel lblNewLabel_12 = new JLabel("Paquetes:");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 3;
		gbc_lblNewLabel_12.gridy = 15;
		getContentPane().add(lblNewLabel_12, gbc_lblNewLabel_12);
		
		JComboBox<String> paquetes = new JComboBox<String>();
		GridBagConstraints gbc_paquetes = new GridBagConstraints();
		gbc_paquetes.gridwidth = 3;
		gbc_paquetes.insets = new Insets(0, 0, 5, 5);
		gbc_paquetes.fill = GridBagConstraints.HORIZONTAL;
		gbc_paquetes.gridx = 2;
		gbc_paquetes.gridy = 16;
		getContentPane().add(paquetes, gbc_paquetes);
		paquetes.setEnabled(false);
		
		List<String> nPlat = port.getNombrePlataformas().getList();
		for (String it : nPlat) {
			plataformas.addItem(it);
		}
		plataformas.setSelectedIndex(-1);
		
		JLabel lblNewLabel_13 = new JLabel("Categorias:");
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridx = 3;
		gbc_lblNewLabel_13.gridy = 17;
		getContentPane().add(lblNewLabel_13, gbc_lblNewLabel_13);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 18;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		JList<String> lCategorias = new JList<String>();
		lCategorias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(lCategorias);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 21;
		getContentPane().add(panel_1, gbc_panel_1);
		//Cambio de plataforma
		plataformas.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ESPECTACULOS_CARGADOS = false;
					espectaculos.setEnabled(false);
					espectaculos.removeAllItems();
					funciones.setEnabled(false);
					funciones.removeAllItems();
					paquetes.setEnabled(false);
					paquetes.removeAllItems();
					List<EspectaculoDto> nEsp = new LinkedList<EspectaculoDto>();
					try {
						nEsp = port.obtenerEspectaculosPorPlataforma((String) e.getItem()).getList();
						for (EspectaculoDto it : nEsp) {
							espectaculos.addItem(it.getNombre());
						}
						//limpio panel
						eNombre.setText(null);
						eDescripcion.setText(null);
						eHoras.setText(null);
						eMinutos.setText(null);
						eEspMin.setText(null);
						eEspMax.setText(null);
						eUrl.setText(null);
						eCosto.setText(null);
						eFechaReg.setText(null);
						eArtistaOrg.setText(null);
						eEstado.setText(null);
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(rootPane, "ERROR " + ex.toString());
					}
					espectaculos.setEnabled(true);
					ESPECTACULOS_CARGADOS = true;
					espectaculos.setSelectedIndex(-1);
				}
			}
		});
		
	//Cambio de espectaculo
	espectaculos.addItemListener(new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent ee) {
			if (ee.getStateChange() == ItemEvent.SELECTED && espectaculos.getSelectedIndex() != -1 && ESPECTACULOS_CARGADOS) {
				PAQUETES_CARGADOS = false;
				FUNCIONES_CARGADAS = false;
				funciones.setEnabled(false);
				funciones.removeAllItems();
				paquetes.setEnabled(false);
				paquetes.removeAllItems();
				String nplat = (String)plataformas.getSelectedItem();
				String nesp = (String)espectaculos.getSelectedItem();
				/*
				String nplat = (String)e.getItem();
				String nesp = (String)ee.getItem();
				*/
				try {
					EspectaculoDto aux = port.obtenerDatosEspectaculo(nplat, nesp);
					eNombre.setText(aux.getNombre());
					eDescripcion.setText(aux.getDescripcion());
					eHoras.setText(Integer.toString(aux.getDuracionHoras()));
					eMinutos.setText(Integer.toString(aux.getDuracionMinutos()));
					eEspMin.setText(Integer.toString(aux.getMinEspectadores()));
					eEspMax.setText(Integer.toString(aux.getMaxEspectadores()));
					eUrl.setText(aux.getUrl());
					eCosto.setText(Float.toString(aux.getCosto()));
					Estado e = aux.getEstado();
					switch(e) {
					  case INGRESADO:
						  eEstado.setText("INGRESADO");
					    break;
					  case RECHAZADO:
						  eEstado.setText("RECHAZADO");
					    break;
					  case ACEPTADO:
						  eEstado.setText("ACEPTADO");
						  break;
					  case FINALIZADO:
						  eEstado.setText("FINALIZADO");
						  break;
					}
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
					eFechaReg.setText(dateFormat.format(aux.getFechaRegistro().toGregorianCalendar().getTime()).toString());
					eArtistaOrg.setText(aux.getOrganizador().getNombre());
					List<CategoriaDto> categoriasEspectaculo = aux.getCategoriasAsociadas();
					LinkedList<String> nombreCategorias = new LinkedList<String>();
					for (CategoriaDto c : categoriasEspectaculo) {
						nombreCategorias.add(c.getNombre());
					}
					lCategorias.setListData(nombreCategorias.toArray(new String[0]));
					List<FuncionDto> funcionesEspectaculo = aux.getFuncionesAsociadas();
					for (FuncionDto f : funcionesEspectaculo) {
						funciones.addItem(f.getNombre());
					}
					funciones.setSelectedIndex(-1);
					FUNCIONES_CARGADAS = true;
					funciones.setEnabled(true);
					//Cambio de funcion
					funciones.addItemListener(new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent ef) {
							if (ef.getStateChange() == ItemEvent.SELECTED && funciones.getSelectedIndex() != -1 && FUNCIONES_CARGADAS) {
								for (FuncionDto f : funcionesEspectaculo) {
									if (f.getNombre().equals((String)funciones.getSelectedItem())) {
										MensajeConsultaFunciones res = new MensajeConsultaFunciones(f.getNombre(), f.getFechaInicio(), f.getFechaRegistro(), f.getInvitados());
										res.setLocation(188, 11);
										res.setBounds(50, 100, 485, 320);
										res.setVisible(true);
										getLayeredPane().add(res);
									}
								}
								funciones.setSelectedIndex(-1);
							}
						}
					});
					List<PaqueteDto> paquetesEspectaculo = aux.getPaquetesAsociados();
					for (PaqueteDto p : paquetesEspectaculo) {
						paquetes.addItem(p.getNombre());
					}
					paquetes.setSelectedIndex(-1);
					PAQUETES_CARGADOS = true;
					paquetes.setEnabled(true);
					//Cambio de paquete
					paquetes.addItemListener(new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent ep) {
							if (ep.getStateChange() == ItemEvent.SELECTED && paquetes.getSelectedIndex() != -1 && PAQUETES_CARGADOS) {
								for (PaqueteDto p : paquetesEspectaculo) {
									if (p.getNombre().equals((String)paquetes.getSelectedItem())) {
										ConsultaPaquete r = new ConsultaPaquete();
										r.comboBoxPaquetes.addItem(p.getNombre());
										r.comboBoxPaquetes.setSelectedItem(p.getNombre());
										r.setLocation(188, 11);
										r.setBounds(30, 15, 500, 550);
										r.setVisible(true);
										getLayeredPane().add(r);
									}
								}
								paquetes.setSelectedIndex(-1);
							}
						}
					});
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(rootPane, "ERROR " + ex.toString());
				}
			}
		}
	});
	}
	
	public void setPlataforma(String plataforma) {
		plataformas.setSelectedItem(plataforma);
		
	}
	
	public void setEspectaculo(String espectaculo) {
		espectaculos.setSelectedItem(espectaculo);
	}
}
