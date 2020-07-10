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

 /*
 * CLASE BoxArea
 * Esta clase crea un box area el cual define los limites de los objetos. Es simplemente un rectángulo.
 * */
 
import java.awt.Rectangle;

public class BoxArea {
	
	Rectangle r;
	
	//Constructor sin parametros. Crea un rectángulo con todas las propiedades en 0
	public BoxArea(){
		r = new Rectangle(0, 0, 0, 0);
	}
	
	//Constructor con parametros. Crea un rectángulo con los valores recibidos
	public BoxArea(int width, int height, int xpos, int ypos){
		r = new Rectangle(xpos, ypos, width, height);
	}

	//Métodos getter y setter para cada el rectángulo y cada uno de sus atributos

	public Rectangle getBoxArea(){
		return r;
	}
	
	public int getWidth(){
		return (int)r.getWidth();
	}
	
	public int getHeight(){
		return (int)r.getHeight();
	}
	
	public int getXPos(){
		return (int)r.getX();
	}
	
	public int getYPos(){
		return (int)r.getY();
	}
	
	public void setXPos(int xpos){
		r.setLocation(xpos, getYPos());
	}

	public void setYPos(int ypos){
		r.setLocation(getXPos(), ypos);
	}
}

