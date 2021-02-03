package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

import webservices.CategoriaDto;
import webservices.CategoriaDtoList;
import webservices.ImagenDto;
import webservices.Publicador;
import webservices.PublicadorService;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JSpinner;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.swing.SpinnerNumberModel;
import javax.swing.JList;

public class AltaEspectaculo extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField tNombre;
	private JTextField tUrl;
	private File imagen = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaEspectaculo frame = new AltaEspectaculo();
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
	public AltaEspectaculo() {
		
		PublicadorService service = new PublicadorService();
		Publicador port = service.getPublicadorPort();
		
		setClosable(true);
		setTitle("Alta Espectaculo");
		setBounds(100, 100, 575, 743);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{181, 85, 67, 122, 0, 55, 0};
		gridBagLayout.rowHeights = new int[]{12, 0, 0, 0, 47, 28, 0, 0, 0, 0, 0, 145, 42, 37, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Plataforma:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JComboBox<String> plataformas = new JComboBox<String>();
		GridBagConstraints gbc_plataformas = new GridBagConstraints();
		gbc_plataformas.gridwidth = 3;
		gbc_plataformas.insets = new Insets(0, 0, 5, 5);
		gbc_plataformas.fill = GridBagConstraints.HORIZONTAL;
		gbc_plataformas.gridx = 1;
		gbc_plataformas.gridy = 1;
		getContentPane().add(plataformas, gbc_plataformas);
		
		JLabel lblArtistaOrganizador = new JLabel("Artista Organizador:");
		GridBagConstraints gbc_lblArtistaOrganizador = new GridBagConstraints();
		gbc_lblArtistaOrganizador.anchor = GridBagConstraints.EAST;
		gbc_lblArtistaOrganizador.insets = new Insets(0, 0, 5, 5);
		gbc_lblArtistaOrganizador.gridx = 0;
		gbc_lblArtistaOrganizador.gridy = 2;
		getContentPane().add(lblArtistaOrganizador, gbc_lblArtistaOrganizador);
		
		final JComboBox<String> artistas = new JComboBox<String>();
		GridBagConstraints gbc_artistas = new GridBagConstraints();
		gbc_artistas.gridwidth = 3;
		gbc_artistas.insets = new Insets(0, 0, 5, 5);
		gbc_artistas.fill = GridBagConstraints.HORIZONTAL;
		gbc_artistas.gridx = 1;
		gbc_artistas.gridy = 2;
		getContentPane().add(artistas, gbc_artistas);
		
		JLabel lblNombreEspectaculo = new JLabel("Nombre Espectaculo:");
		GridBagConstraints gbc_lblNombreEspectaculo = new GridBagConstraints();
		gbc_lblNombreEspectaculo.anchor = GridBagConstraints.EAST;
		gbc_lblNombreEspectaculo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreEspectaculo.gridx = 0;
		gbc_lblNombreEspectaculo.gridy = 3;
		getContentPane().add(lblNombreEspectaculo, gbc_lblNombreEspectaculo);
		
		tNombre = new JTextField();
		tNombre.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_tNombre = new GridBagConstraints();
		gbc_tNombre.gridwidth = 3;
		gbc_tNombre.insets = new Insets(0, 0, 5, 5);
		gbc_tNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tNombre.gridx = 1;
		gbc_tNombre.gridy = 3;
		getContentPane().add(tNombre, gbc_tNombre);
		tNombre.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 0;
		gbc_lblDescripcin.gridy = 4;
		getContentPane().add(lblDescripcin, gbc_lblDescripcin);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		JTextPane tDescripcion = new JTextPane();
		scrollPane.setViewportView(tDescripcion);
		
		JLabel lblNewLabel_1 = new JLabel("Duracion:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 5;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JSpinner tHoras = new JSpinner();
		tHoras.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_tHoras = new GridBagConstraints();
		gbc_tHoras.fill = GridBagConstraints.HORIZONTAL;
		gbc_tHoras.insets = new Insets(0, 0, 5, 5);
		gbc_tHoras.gridx = 1;
		gbc_tHoras.gridy = 5;
		getContentPane().add(tHoras, gbc_tHoras);
		
		JSpinner tMinutos = new JSpinner();
		tMinutos.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		GridBagConstraints gbc_tMinutos = new GridBagConstraints();
		gbc_tMinutos.fill = GridBagConstraints.HORIZONTAL;
		gbc_tMinutos.insets = new Insets(0, 0, 5, 5);
		gbc_tMinutos.gridx = 2;
		gbc_tMinutos.gridy = 5;
		getContentPane().add(tMinutos, gbc_tMinutos);
		
		JLabel lblNewLabel_7 = new JLabel("    Horas / Minutos");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 3;
		gbc_lblNewLabel_7.gridy = 5;
		getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		JLabel lblNewLabel_2 = new JLabel("Espectadores Minimos:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 6;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JSpinner tMinEsp = new JSpinner();
		tMinEsp.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_tMinEsp = new GridBagConstraints();
		gbc_tMinEsp.fill = GridBagConstraints.HORIZONTAL;
		gbc_tMinEsp.insets = new Insets(0, 0, 5, 5);
		gbc_tMinEsp.gridx = 1;
		gbc_tMinEsp.gridy = 6;
		getContentPane().add(tMinEsp, gbc_tMinEsp);
		
		JLabel lblNewLabel_3 = new JLabel("Espectadores Maximos:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 7;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JSpinner tMaxEsp = new JSpinner();
		tMaxEsp.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_tMaxEsp = new GridBagConstraints();
		gbc_tMaxEsp.fill = GridBagConstraints.HORIZONTAL;
		gbc_tMaxEsp.insets = new Insets(0, 0, 5, 5);
		gbc_tMaxEsp.gridx = 1;
		gbc_tMaxEsp.gridy = 7;
		getContentPane().add(tMaxEsp, gbc_tMaxEsp);
		
		JLabel lblNewLabel_4 = new JLabel("URL:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 8;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		tUrl = new JTextField();
		tUrl.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_tUrl = new GridBagConstraints();
		gbc_tUrl.gridwidth = 3;
		gbc_tUrl.insets = new Insets(0, 0, 5, 5);
		gbc_tUrl.fill = GridBagConstraints.HORIZONTAL;
		gbc_tUrl.gridx = 1;
		gbc_tUrl.gridy = 8;
		getContentPane().add(tUrl, gbc_tUrl);
		tUrl.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Costo:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 9;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JSpinner tCosto = new JSpinner();
		tCosto.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		GridBagConstraints gbc_tCosto = new GridBagConstraints();
		gbc_tCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_tCosto.insets = new Insets(0, 0, 5, 5);
		gbc_tCosto.gridx = 1;
		gbc_tCosto.gridy = 9;
		getContentPane().add(tCosto, gbc_tCosto);
		
		JLabel lblNewLabel_6 = new JLabel("Fecha de alta:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 11;
		getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JCalendar calendar = new JCalendar();
		GridBagConstraints gbc_calendar = new GridBagConstraints();
		gbc_calendar.gridwidth = 3;
		gbc_calendar.insets = new Insets(0, 0, 5, 5);
		gbc_calendar.fill = GridBagConstraints.BOTH;
		gbc_calendar.gridx = 1;
		gbc_calendar.gridy = 11;
		calendar.setMinSelectableDate(new Date());
		getContentPane().add(calendar, gbc_calendar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		List<String> nPlataformas = port.getNombrePlataformas().getList();
		for (String it : nPlataformas) {
			plataformas.addItem(it);;
		}
		plataformas.setSelectedIndex(-1);
		port.obtenerArtistas().getList().forEach(a -> artistas.addItem(a.getNickname()));
		
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(AltaEspectaculo.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.append("Opening: " + fc.getSelectedFile().getName() + "." + "\n");
					imagen = fc.getSelectedFile();
			    } else {
			    	System.out.append("Open command cancelled by user." + "\n");
			    }		
			}
			
		});
		GridBagConstraints gbc_btnSeleccionar = new GridBagConstraints();
		gbc_btnSeleccionar.anchor = GridBagConstraints.EAST;
		gbc_btnSeleccionar.insets = new Insets(0, 0, 5, 5);
		gbc_btnSeleccionar.gridx = 3;
		gbc_btnSeleccionar.gridy = 12;
		getContentPane().add(btnSeleccionar, gbc_btnSeleccionar);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
		gbc_lblCategoria.fill = GridBagConstraints.VERTICAL;
		gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoria.gridx = 1;
		gbc_lblCategoria.gridy = 13;
		getContentPane().add(lblCategoria, gbc_lblCategoria);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 13;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> list = new JList<String>();
		scrollPane_1.setViewportView(list);
		
		List<CategoriaDto> lisCat = port.listarCategorias().getList();
		for (CategoriaDto cat : lisCat) {
			model.addElement(cat.getNombre());
		}
		list.setModel(model);
		
		
		artistas.setSelectedIndex(-1);
		btnConfirmar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tNombre.getText().equals("") || tDescripcion.getText().equals("") || 
						(tHoras.getValue().equals(0) && tMinutos.getValue().equals(0)) ||
						tMinEsp.getValue().equals(0) || tMaxEsp.getValue().equals(0) || tUrl.getText().equals("")
						|| (artistas.getSelectedIndex() == -1) || (plataformas.getSelectedIndex() == -1)) {
					JOptionPane.showMessageDialog(rootPane, "Complete todos los campos. ");
				}
				else {
					if (((int) tMinEsp.getValue()) > ((int) tMaxEsp.getValue())) {
						JOptionPane.showMessageDialog(rootPane, "minimo de espectadores > maximo de espectadores");
					}
					else {
						String nombre = tNombre.getText();
						String descripcion = tDescripcion.getText();
						int duracionHoras = (int) tHoras.getValue();
						int duracionMinutos = (int) tMinutos.getValue();
						int espectadoresMinimos = (int) tMinEsp.getValue();
						int espectadoresMaximos = (int) tMaxEsp.getValue();
						String URL = tUrl.getText();
						float costo = (float) tCosto.getValue();
						
						/* Coso horrible de fecha */
						GregorianCalendar bdateGC = new GregorianCalendar();
			    		bdateGC.setTime(new Date());
			    		XMLGregorianCalendar xmlfechaAlta = null;
						try {
							xmlfechaAlta = DatatypeFactory.newInstance().newXMLGregorianCalendar(bdateGC);
						} catch (DatatypeConfigurationException e2) {
							e2.printStackTrace();
						}	
						
						
						String plat = (String) plataformas.getSelectedItem();
						String art = (String) artistas.getSelectedItem();
						if (espectadoresMinimos > espectadoresMaximos) {
							JOptionPane.showMessageDialog(rootPane, "minimo de espectadores > maximo de espectadores");
						}
						
						CategoriaDtoList listCategorias = new CategoriaDtoList();
						lisCat.stream().filter(cat -> list.getSelectedValuesList().contains(cat.getNombre()))
									   .forEach(cat -> listCategorias.getList().add(cat) );
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
							port.confirmarAltaEspectaculo(plat, art, nombre, descripcion, duracionHoras, duracionMinutos, espectadoresMinimos,
									espectadoresMaximos, URL, costo, xmlfechaAlta, img, listCategorias);
							JOptionPane.showMessageDialog(rootPane, "Espectaculo creado con exito. ");
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(rootPane, "ERROR " + ex.toString());
						}
					}
				}
			}
		});
		
		JLabel lblSeleccionarImgen = new JLabel("Seleccionar Imágen:");
		GridBagConstraints gbc_lblSeleccionarImgen = new GridBagConstraints();
		gbc_lblSeleccionarImgen.gridwidth = 2;
		gbc_lblSeleccionarImgen.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionarImgen.gridx = 1;
		gbc_lblSeleccionarImgen.gridy = 12;
		getContentPane().add(lblSeleccionarImgen, gbc_lblSeleccionarImgen);
		
	
		
	
		
		
		
		GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
		gbc_btnConfirmar.anchor = GridBagConstraints.EAST;
		gbc_btnConfirmar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfirmar.gridx = 3;
		gbc_btnConfirmar.gridy = 14;
		getContentPane().add(btnConfirmar, gbc_btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.anchor = GridBagConstraints.WEST;
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 14;
		getContentPane().add(btnCancelar, gbc_btnCancelar);

	}

}
