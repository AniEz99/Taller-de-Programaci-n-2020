import java.awt.EventQueue;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import logica.Utils;
import webservices.Publicador;

public class Main extends JFrame {
	
	public Main() {
		setBounds(0,0,150, 150);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	
	public static void main(String args[]) {
		Publicador p = new Publicador();
		p.publicar(); 
		// Borra imagenes viejas
		
		try {
			String test = new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
				    .toURI()).getParentFile().getPath();
			Utils.crearCarpeta(test);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
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


}
