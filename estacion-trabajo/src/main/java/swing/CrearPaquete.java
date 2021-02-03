package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import com.toedter.calendar.JCalendar;

import webservices.ImagenDto;
import webservices.PaqueteDto;
import webservices.Publicador;
import webservices.PublicadorService;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearPaquete extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();
	
	private JTextField txtNombre;
	private JTextField txtDescuento;
	private File imagen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPaquete frame = new CrearPaquete();
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
	public CrearPaquete() {
		setClosable(true);
		setTitle("Crear paquete");
		setBounds(100, 100, 500, 558);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 11, 144, 14);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(164, 8, 310, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(10, 36, 144, 14);
		getContentPane().add(lblDescripcion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(164, 36, 312, 47);
		getContentPane().add(scrollPane);
		
		JTextArea txtDescripcion = new JTextArea();
		txtDescripcion.setLineWrap(true);
		scrollPane.setViewportView(txtDescripcion);
		
		JCalendar calendarInicio = new JCalendar();
		calendarInicio.setBounds(164, 94, 310, 153);
		getContentPane().add(calendarInicio);
		
		JLabel lblInicio = new JLabel("Fecha entrada en vigencia:");
		lblInicio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInicio.setBounds(10, 94, 144, 14);
		getContentPane().add(lblInicio);
		
		JCalendar calendarFin = new JCalendar();
		calendarFin.setBounds(164, 258, 310, 153);
		getContentPane().add(calendarFin);
		
		JLabel lblFin = new JLabel("Fecha fin de vigencia:");
		lblFin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFin.setBounds(10, 258, 144, 14);
		getContentPane().add(lblFin);
		
		JLabel lblDescuento = new JLabel("Descuento(%):");
		lblDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescuento.setBounds(10, 425, 144, 14);
		getContentPane().add(lblDescuento);
		
		txtDescuento = new JTextField();
		txtDescuento.setBounds(164, 422, 310, 20);
		getContentPane().add(txtDescuento);
		txtDescuento.setColumns(10);
		
		JLabel lblSeleccionarImgen = new JLabel("Seleccionar Imágen:");
		lblSeleccionarImgen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionarImgen.setBounds(30, 464, 124, 15);
		getContentPane().add(lblSeleccionarImgen);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			/*
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(CrearPaquete.this);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       imagen = chooser.getSelectedFile();
			    } else {
			    	System.out.append("Open command cancelled by user." + "\n");
			    }	
			}*/
			
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(CrearPaquete.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.append("Opening: " + fc.getSelectedFile().getName() + "." + "\n");
					imagen = fc.getSelectedFile();
			    } else {
			    	System.out.append("Open command cancelled by user." + "\n");
			    }		
			}
		});
		

		
		btnSeleccionar.setBounds(164, 454, 124, 25);
		getContentPane().add(btnSeleccionar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombre = txtNombre.getText();
				String descripcion = txtDescripcion.getText();
				if (nombre.equals("") || descripcion.equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Hay datos requeridos sin ingresar.", "Error", 0);
					return;
				}
				Date inicio = calendarInicio.getDate();
				Date fin = calendarFin.getDate();
				
				GregorianCalendar idateGC = new GregorianCalendar();
				idateGC.setTime(inicio);
				GregorianCalendar fdateGC = new GregorianCalendar();
				fdateGC.setTime(fin);
				GregorianCalendar rdateGC = new GregorianCalendar();
				rdateGC.setTime(new Date());
	    		XMLGregorianCalendar xmlbdateINI = null;
	    		XMLGregorianCalendar xmlbdateFIN = null;
	    		XMLGregorianCalendar xmlbdateREG = null;
				try {
					xmlbdateINI = DatatypeFactory.newInstance().newXMLGregorianCalendar(idateGC);
					xmlbdateFIN = DatatypeFactory.newInstance().newXMLGregorianCalendar(fdateGC);
					xmlbdateREG = DatatypeFactory.newInstance().newXMLGregorianCalendar(rdateGC);
				} catch (DatatypeConfigurationException e2) {
					e2.printStackTrace();
				}
				
				
				float descuento;
				try {
					descuento = Float.parseFloat(txtDescuento.getText());					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(rootPane, "El descuento debe ser un n�mero.", "Error", 0);
					return;
				}
				PaqueteDto paquete = new PaqueteDto();
				paquete.setNombre(nombre);
				paquete.setDescripcion(descripcion);
				paquete.setFechaInicio(xmlbdateINI);
				paquete.setFechaFin(xmlbdateFIN);
				paquete.setFechaRegistro(xmlbdateREG);
				paquete.setDescuento(descuento);
				
				ImagenDto img =  new ImagenDto();
				try {
					img.setContent(Files.readAllBytes(imagen.toPath()));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				img.setName(imagen.getName());
				try {
					port.crearPaqueteSinArtista(paquete, img);
					JOptionPane.showMessageDialog(rootPane, "Paquete creado exitosamente.", "Exito", 1);
					dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(rootPane, e1.getMessage(), "Error", 0);
				}
			}
		});
		btnAceptar.setBounds(128, 491, 89, 23);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(260, 491, 89, 23);
		getContentPane().add(btnCancelar);
		
		

	}
}
