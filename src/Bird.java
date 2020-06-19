import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

public class Bird {

	private ArrayList<Image> birdsprites = new ArrayList<>();
	private BoxArea birdbox;
	private Dimension windowSize;

	public Bird(Dimension windowSize){
		try {
			this.windowSize = windowSize;
			birdsprites.add(ImageIO.read(new File("../img/bird.png")));
			birdsprites.add(ImageIO.read(new File("../img/birdupok.png")));
			int width=53; 
			int height=38;
			int xpos =(int) ((this.windowSize.getWidth() - width) / 2);
			int ypos =(int) (this.windowSize.getHeight() / 2) - height;
			birdbox = new BoxArea(width, height, xpos, ypos);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Image getImage(boolean toup){
		if(!toup){
			return this.birdsprites.get(0);
		}else{
			return this.birdsprites.get(1);
		}
		
	}

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
}
