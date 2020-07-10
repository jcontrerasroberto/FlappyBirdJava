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
 
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

/*
	Class Pipe

	Esta clase define las propiedades de un PAR de tuberias (cada ranura). Sus boxareas, sus tamaños y las imagenes que usan


*/

public class Pipe {
	
	private Image uppipeimg, downpipeimg;
	private BoxArea uppipebox, downpipebox;
	private Dimension windowSize;
	private static int width = 80; //Ancho por default
	private static String imgURL="../img/game/"; //Ruta donde se encuentran las imagenes

	/*
		Constructor. Recibe el tamaño del frame, la altura de cada tubería y la posición x dónde se colocaran
	*/

	public Pipe(Dimension windowSize, int heightup, int heightdown, int xpos){
		try {
			//Cargamos las imagenes,uno para la tubería superior y otra para la inferior
			uppipeimg = ImageIO.read(new File(imgURL+"uppipe.png"));
			downpipeimg = ImageIO.read(new File(imgURL+"downpipe.png"));
			this.windowSize = windowSize;
			//Creamos sus box area con los valores recibidos
			uppipebox = new BoxArea(width, heightup, xpos, 0);
			downpipebox = new BoxArea(width, heightdown, xpos, this.windowSize.height-heightdown);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Métodos getter y setter para cada propiedad de las tuberías. Los getters reciben un boolean que dependiendo el valor
	//   devuelve la propiedad de la tubería superior o de la inferior
	
	public Image getImage(boolean type){
		if(type) return uppipeimg;
		else return downpipeimg;
			
	}

	public Rectangle getBoxArea(boolean type){
		if(type) return uppipebox.getBoxArea();
		else return downpipebox.getBoxArea();
	}

	public int getWidth(){
		return width; //Ambas tuberías tienen el mismo ancho
		
	}

	public int getHeight(boolean type){
		if(type) return uppipebox.getHeight();
		else return downpipebox.getHeight();
	}

	public int getXPos(){
		return uppipebox.getXPos(); //Ambas tuberias tienen la misma coordenada x
	}

	public int getYPos(boolean type){
		if(type) return uppipebox.getYPos();
		else return downpipebox.getYPos();
	}

	public void setXPos(int xpos){
		uppipebox.setXPos(xpos);
		downpipebox.setXPos(xpos);
	}

	/*public void setYPos(int ypos){
		uppipebox.setYPos(ypos);
	}*/
	
}
