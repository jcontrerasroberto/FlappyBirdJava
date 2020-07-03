/*
 * PROYECTO FINAL DE PROGRAMACIÓN ORIENTADA A OBJETOS
 * FLAPPY BIRD (MULTIPLAYER Y SINGLEPLAYER)
 * 
 * 2CM3
 * 
 * INTEGRANTES:
 * 
 * CONTRERAS BARRITA JOSÉ ROBERTO
 * CONTRERAS MENDEZ BRANDON
 * FONSECA RAMOS ANGEL GABRIEL
 * TOLEDO ESPINOSA CRISTINA ALINE
 * 
 * */
 
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

public class MenuPanel extends JPanel {

	private Image bg, gamename;
	private Dimension ws;

	public MenuPanel(Dimension windowSize){
		this.ws = windowSize;
		try {
			bg = ImageIO.read(new File("../img/menu/bg.png"));
			gamename = ImageIO.read(new File("../img/menu/imgfbname.png"));
		} catch (Exception e) {
			//TODO: handle exception
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(bg, 0, 0, (int) ws.getWidth(),(int) ws.getHeight(), this);
		g2.drawImage(gamename, (int) ws.getWidth()/2 - 250/2,50, 250,100, this);
	}
}
