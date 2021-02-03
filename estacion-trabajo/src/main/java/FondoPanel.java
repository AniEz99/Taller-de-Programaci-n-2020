
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FondoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image imagen;
	@Override
	public void paint(Graphics g) {
		ImageIcon aux = new ImageIcon(getClass().getResource("/fondo.jpg"));
		imagen = aux.getImage();
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paint(g);
	}

}
