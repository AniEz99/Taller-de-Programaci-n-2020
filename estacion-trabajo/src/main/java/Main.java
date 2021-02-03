import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import swing.AceptarCancelarEspectaculo;
import swing.AgregarEspectaculoAPaquete;
import swing.AltaCategoria;
import swing.AltaEspectaculo;
import swing.AltaFuncionEspectaculo;
import swing.AltaPlataforma;
import swing.AltaUsuario;
import swing.ConsultaEspectaculo;
import swing.ConsultaFuncionEspectaculo;
import swing.ConsultaPaquete;
import swing.ConsultaUsuario;
import swing.CrearPaquete;
import swing.ModificarUsuario;
import swing.RegistroAFuncion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import webservices.Publicador;
import webservices.PublicadorService;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private FondoPanel contentPane;
	//private AltaFuncionEspectaculo altaFuncInternalFrame;

	PublicadorService service = new PublicadorService();
	Publicador port = service.getPublicadorPort();
	
	boolean DATOS_CARGADOS = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("coronatickets.uy");
		
		
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screen_width = (int)screenSize.getWidth();
		int screen_height = (int)screenSize.getHeight();
		
		// Tamaño de menu
		int main_width = 700;
		int main_height = 730;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(screen_width/2-main_width/2,screen_height/2-main_height/2,main_width, main_height);
		contentPane = new FondoPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
			
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 912, 22);
		setJMenuBar(menuBar);
		
		
		/*
		JButton btnNewButton = new JButton("Cargar Datos de Prueba");
		btnNewButton.addActionListener(new ActionListener() {
			
				public void actionPerformed(ActionEvent arg0) {
					if (!DATOS_CARGADOS) {
						try {
							CargadorDeDatosDePrueba datos = new CargadorDeDatosDePrueba();
							DATOS_CARGADOS = true;
						} catch (FuncionYaExisteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (EspectaculoYaExisteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (YaExisteRegistroException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (PaqueteYaExisteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						JOptionPane.showMessageDialog(rootPane, "Datos cargados.");
					}
					else {
						JOptionPane.showMessageDialog(rootPane, "Datos ya fueron cargados", "ERROR", 0);
					}			
				} 
		});
		menuBar.add(btnNewButton);
		
		*/
		
	
		
		JMenu mnNewMenu = new JMenu("Altas");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Alta de usuario");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmRegistroAFuncin = new JMenuItem("Registro a funcion");
		mnNewMenu.add(mntmRegistroAFuncin);
		
		JMenuItem mntmAltaDePlataforma = new JMenuItem("Alta de plataforma");
		mnNewMenu.add(mntmAltaDePlataforma);
		
		JMenuItem mntmAltaDeCategoria = new JMenuItem("Alta de categoria");
		mnNewMenu.add(mntmAltaDeCategoria);
		
		JMenuItem mntmAltaDeEspectaculo = new JMenuItem("Espectaculo");
		mnNewMenu.add(mntmAltaDeEspectaculo);
		
		JMenuItem mntmCrearPaquete = new JMenuItem("Crear paquete");
		mnNewMenu.add(mntmCrearPaquete);
		
		JMenuItem mntmAltaFuncionEspectaculo = new JMenuItem("Funcion de Espectaculo");
		mnNewMenu.add(mntmAltaFuncionEspectaculo);
		
		JMenuItem mntmAgregarEspectaculoA = new JMenuItem("Agregar Espectaculo a Paquete");
		mnNewMenu.add(mntmAgregarEspectaculoA);

		JMenu mnNewMenu_1 = new JMenu("Consultas");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmConsultaDeUsuario = new JMenuItem("Consulta de usuario");
		mnNewMenu_1.add(mntmConsultaDeUsuario);
		
		
		JMenuItem mntmConsultaDeEspectaculo = new JMenuItem("Espectaculo");
		mnNewMenu_1.add(mntmConsultaDeEspectaculo);
		
		JMenu mnNewMenu2 = new JMenu("Ediciones");
		menuBar.add(mnNewMenu2);
		
		JMenuItem mntmModificarUsuario = new JMenuItem("Modificar usuario");
		mnNewMenu2.add(mntmModificarUsuario);
		
		JMenuItem mntmAceptarrechazarEspectaculo = new JMenuItem("Aceptar/Rechazar Espectaculo");
		mnNewMenu2.add(mntmAceptarrechazarEspectaculo);
		
		JMenuItem mntmConsultaPaquete = new JMenuItem("Consulta Paquete");
		mnNewMenu_1.add(mntmConsultaPaquete);
		
		
		/*
		 * Consulta funcion espectaculo
		 */
		JMenuItem consultaFuncEspMenu = new JMenuItem("Consulta funcion espectaculo");
		consultaFuncEspMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				contentPane.repaint();
				ConsultaFuncionEspectaculo intFrame = new ConsultaFuncionEspectaculo();
				intFrame.setLocation(0,0);
				intFrame.setVisible(true);
				getContentPane().add(intFrame);
				contentPane.repaint();
			}
		});
		mnNewMenu_1.add(consultaFuncEspMenu);
		
		JMenu mnLogs = new JMenu("Registros");
		menuBar.add(mnLogs);
		
		JMenuItem mntmLogs = new JMenuItem("Registro de actividad");
		mnLogs.add(mntmLogs);
		
		/*
		 * Consulta Paquete
		 */
		mntmConsultaPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				contentPane.repaint();
				ConsultaPaquete intFrame = new ConsultaPaquete();
				intFrame.setLocation(0, 0);
				intFrame.setBounds(0, 0, 550, 550);
				intFrame.setVisible(true);
				getContentPane().add(intFrame);
			}
		});
		
		/*
		 * Alta usuario
		 */
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				contentPane.repaint();
				AltaUsuario intFrame = new AltaUsuario();
				intFrame.setLocation(188, 11);
				intFrame.setBounds(99, -7, 506, 745);
				intFrame.setVisible(true);
				getContentPane().add(intFrame);
			}
		});
		
		/*
		 * Consulta de usuario
		 */
		mntmConsultaDeUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				contentPane.repaint();
				ConsultaUsuario intFrame = new ConsultaUsuario(getContentPane());
				intFrame.setLocation(188, 11);
				intFrame.setBounds(99, -7, 500, 480);
				intFrame.setVisible(true);
				getContentPane().add(intFrame);
			}
		});
		
		
		/*
		 * Alta registro
		 */
		mntmRegistroAFuncin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				contentPane.repaint();
				RegistroAFuncion intFrame = new RegistroAFuncion();
				intFrame.setLocation(0, 0);
				intFrame.setVisible(true);
				getContentPane().add(intFrame);
			}
		});
		
		
		/*
		 * Alta Plataforma
		 */
		mntmAltaDePlataforma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				contentPane.repaint();
				AltaPlataforma intFrame = new AltaPlataforma();
				intFrame.setLocation(188, 11);
				intFrame.setBounds(48, 33, 570, 513);
				intFrame.setVisible(true);
				getContentPane().add(intFrame);
			}
		});
		
		/*
		 * Alta de Categoria
		 */
		mntmAltaDeCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				contentPane.repaint();
				AltaCategoria intFrame = new AltaCategoria();
				intFrame.setLocation(188, 11);
				intFrame.setBounds(48, 33, 320, 263);
				intFrame.setVisible(true);
				getContentPane().add(intFrame);
			}
		});
		
		//Alta de espectaculo 
		
		mntmAltaDeEspectaculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				contentPane.repaint();
				AltaEspectaculo intFrame = new AltaEspectaculo();
				intFrame.setLocation(0, 0);
				intFrame.setBounds(48, 33, 570, 650);
				intFrame.setVisible(true);
				getContentPane().add(intFrame);
			}
		});
		
		//Consulta de espectaculo
			mntmConsultaDeEspectaculo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getContentPane().removeAll();
					contentPane.repaint();
					ConsultaEspectaculo intFrame = new ConsultaEspectaculo();
					intFrame.setLocation(0, 0);
					intFrame.setBounds(10, 5, 600, 650);
					intFrame.setVisible(true);
					getContentPane().add(intFrame);
				}
			});

			//Crear paquete 
			
			mntmCrearPaquete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getContentPane().removeAll();
					contentPane.repaint();
					CrearPaquete intFrame = new CrearPaquete();
					intFrame.setLocation(0, 0);
					intFrame.setBounds(48, 33, 500, 553);
					intFrame.setVisible(true);
					getContentPane().add(intFrame);
				}
			});	
			
			//Modificar usuario
			mntmModificarUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getContentPane().removeAll();
					contentPane.repaint();
					ModificarUsuario intFrame = new ModificarUsuario();
					intFrame.setLocation(0, 0);
					intFrame.setBounds(48, 0, 500, 694);
					intFrame.setVisible(true);
					getContentPane().add(intFrame);
				}
			});	

		
		
			//Alta de Funcion de Espectaculo
			
			mntmAltaFuncionEspectaculo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getContentPane().removeAll();
					contentPane.repaint();
					AltaFuncionEspectaculo intFrame = new AltaFuncionEspectaculo();
					intFrame.setLocation(0, 0);
					intFrame.setBounds(48, 33, 570, 563);
					intFrame.setVisible(true);
					getContentPane().add(intFrame);
				}
			});
			
			/*
			 * Agregar Espectaculo a Paquete
			 */
			mntmAgregarEspectaculoA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getContentPane().removeAll();
					contentPane.repaint();
					AgregarEspectaculoAPaquete intFrame = new AgregarEspectaculoAPaquete();
					intFrame.setLocation(188, 11);
					intFrame.setBounds(99, -7, 500, 380);
					intFrame.setVisible(true);
					getContentPane().add(intFrame);
				}
			});
			
			
			/*
			 * Aceptar Rechazar Espectaculo
			 */
			mntmAceptarrechazarEspectaculo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getContentPane().removeAll();
					contentPane.repaint();
					AceptarCancelarEspectaculo intFrame = new AceptarCancelarEspectaculo();
					intFrame.setLocation(188, 11);
					intFrame.setBounds(99, -7, 500, 380);
					intFrame.setVisible(true);
					getContentPane().add(intFrame);
				}
			});
			
			//logs
			
			mntmLogs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String url = port.generarUrlAccesoLogs();
					StringSelection stringSelection = new StringSelection(url);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					JOptionPane.showMessageDialog(rootPane, "Puede acceder a los registros de actividad usando la url que copiamos a su portapapeles.", "Exito", 1);
				}
			});
			
		
	}
}

