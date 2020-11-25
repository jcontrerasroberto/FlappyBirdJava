
 
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

/*
	Class Bird

	Esta clase define las propiedades del pajaro. Su boxarea, su tamaño y la skin


*/

public class Bird {

	private ArrayList<Image> birdsprites = new ArrayList<>();
	private BoxArea birdbox;
	private Dimension windowSize;
	//Ancho y alto por default
	private static final int width=53; 
	private static final int height=38;
	private static String imgURL="../img/skins/";
	//Skin por default
	private String skin="yellow/";

	/*
		Constructor
		Cargamos los sprites del pajaro
		Calculamos su posición de acuerdo al tamaño de la ventana
		Creamos el boxarea con esos valores
	*/
	public Bird(Dimension windowSize){
		try {
			skin = setSkin(); //Obtenemos el nombre de la skin actual
			this.windowSize = windowSize;
			birdsprites.add(ImageIO.read(new File(imgURL+skin+"bird.png")));
			birdsprites.add(ImageIO.read(new File(imgURL+skin+"birdup.png")));
			int xpos =(int) ((this.windowSize.getWidth() - width) / 2);
			int ypos =(int) (this.windowSize.getHeight() / 2) - height - 100;
			birdbox = new BoxArea(width, height, xpos, ypos);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/*
		public Image getImage(boolean toup)

		Dependiendo que recibamos, devuelve la imagen (normal o inclinada)
	*/
	public Image getImage(boolean toup){
		if(!toup){
			return this.birdsprites.get(0);
		}else{
			return this.birdsprites.get(1);
		}
		
	}

	/*
		Getter y setter para cada propiedad del boxArea
	*/

	public Rectangle getBoxArea(){
		return birdbox.getBoxArea();
	}

	public int getWidth(){
		return birdbox.getWidth();
	}

	public int getHeight(){
		return birdbox.getHeight();
	}

	public int getXPos(){
		return birdbox.getXPos();
	}

	public int getYPos(){
		return birdbox.getYPos();
	}

	public void setXPos(int xpos){
		birdbox.setXPos(xpos);
	}

	public void setYPos(int ypos){
		birdbox.setYPos(ypos);
	}
	

	/*
		public String setSkin()
		Colocamos el valor de la skin a la actual escogida por el usuario. Este valor se encuentra en el archivo
		user.properties

	*/	
	public String setSkin(){
		UserProperties up = new UserProperties();
		return up.getSkin()+"/";
	}
}
