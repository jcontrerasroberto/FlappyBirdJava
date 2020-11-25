
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

/*
	Class MenuPanel

	Esta clase crea dibuja en un JPanel el cual se usará para el Menu del juego

*/

public class MenuPanel extends JPanel {

	private Image bg, gamename; //Imagenes
	private Dimension ws; //Tamaño de la ventana

	//Constructos. Cargamos las imagenes que se colocaran en el JPanel
	public MenuPanel(Dimension windowSize){
		this.ws = windowSize;
		try {
			bg = ImageIO.read(new File("../img/menu/bg.png"));
			gamename = ImageIO.read(new File("../img/menu/imgfbname.png"));
		} catch (Exception e) {
			//TODO: handle exception
		}
	}

	//public void paintComponent(Graphics g). Dibujamos las imagenes (Fondo y el nombre del juego)
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(bg, 0, 0, (int) ws.getWidth(),(int) ws.getHeight(), this); //Se dibuja el fondo del tamaño de la pantalla
		g2.drawImage(gamename, (int) ws.getWidth()/2 - 250/2,50, 250,100, this);
	}
}
