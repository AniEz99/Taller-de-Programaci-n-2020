package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.xml.datatype.XMLGregorianCalendar;

import webservices.Publicador;
import webservices.PublicadorService;
import webservices.UsuarioDto;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;

public class MensajeConsultaFunciones extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();
	private JTextField tNombre;
	private JTextField tFechaInicio;
	private JTextField tFechaRegistro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MensajeConsultaFunciones frame = new MensajeConsultaFunciones(null, null, null, null);
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
	public MensajeConsultaFunciones(String nom, XMLGregorianCalendar fechaInicio, XMLGregorianCalendar fechaRegistro, List<UsuarioDto> invitados) {
		setClosable(true);
		setTitle("Consulta de Funcion");
		setBounds(100, 100, 485, 320);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 271, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		
		JLabel lNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lNombre = new GridBagConstraints();
		gbc_lNombre.anchor = GridBagConstraints.EAST;
		gbc_lNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lNombre.gridx = 1;
		gbc_lNombre.gridy = 1;
		getContentPane().add(lNombre, gbc_lNombre);
		
		tNombre = new JTextField();
		tNombre.setEditable(false);
		GridBagConstraints gbc_tNombre = new GridBagConstraints();
		gbc_tNombre.insets = new Insets(0, 0, 5, 5);
		gbc_tNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tNombre.gridx = 2;
		gbc_tNombre.gridy = 1;
		getContentPane().add(tNombre, gbc_tNombre);
		tNombre.setColumns(10);
		
		JLabel lFechaInicio = new JLabel("Fecha inicio:");
		GridBagConstraints gbc_lFechaInicio = new GridBagConstraints();
		gbc_lFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lFechaInicio.anchor = GridBagConstraints.EAST;
		gbc_lFechaInicio.gridx = 1;
		gbc_lFechaInicio.gridy = 2;
		getContentPane().add(lFechaInicio, gbc_lFechaInicio);
		
		tFechaInicio = new JTextField();
		tFechaInicio.setEditable(false);
		GridBagConstraints gbc_tFechaInicio = new GridBagConstraints();
		gbc_tFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_tFechaInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFechaInicio.gridx = 2;
		gbc_tFechaInicio.gridy = 2;
		getContentPane().add(tFechaInicio, gbc_tFechaInicio);
		tFechaInicio.setColumns(10);
		
		JLabel lFechaRegistro = new JLabel("Fecha registro:");
		GridBagConstraints gbc_lFechaRegistro = new GridBagConstraints();
		gbc_lFechaRegistro.anchor = GridBagConstraints.EAST;
		gbc_lFechaRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lFechaRegistro.gridx = 1;
		gbc_lFechaRegistro.gridy = 3;
		getContentPane().add(lFechaRegistro, gbc_lFechaRegistro);
		
		tFechaRegistro = new JTextField();
		tFechaRegistro.setEditable(false);
		GridBagConstraints gbc_tFechaRegistro = new GridBagConstraints();
		gbc_tFechaRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_tFechaRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFechaRegistro.gridx = 2;
		gbc_tFechaRegistro.gridy = 3;
		getContentPane().add(tFechaRegistro, gbc_tFechaRegistro);
		tFechaRegistro.setColumns(10);
		
		JLabel lInvitados = new JLabel("Artistas invitados:");
		GridBagConstraints gbc_lInvitados = new GridBagConstraints();
		gbc_lInvitados.insets = new Insets(0, 0, 5, 5);
		gbc_lInvitados.gridx = 1;
		gbc_lInvitados.gridy = 5;
		getContentPane().add(lInvitados, gbc_lInvitados);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 5;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		JList<String> lArtistasInvitados = new JList<String>();
		lArtistasInvitados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(lArtistasInvitados);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		tNombre.setText(nom);
		tFechaInicio.setText(dateFormat.format(fechaInicio.toGregorianCalendar().getTime()).toString());
		tFechaRegistro.setText(dateFormat.format(fechaRegistro.toGregorianCalendar().getTime()).toString());
		List<String> aux = new LinkedList<String>();
		for (UsuarioDto it : invitados) {
			aux.add(it.getNickname());
		}
		lArtistasInvitados.setListData(aux.toArray(new String[0]));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 6;
		getContentPane().add(btnCancelar, gbc_btnNewButton);
	}

}
