import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Bird {

	private ArrayList<Image> birdsprites = new ArrayList<>();
	private int width, height, xpos, ypos;
	private Dimension windowSize;

	public Bird(Dimension windowSize){
		try {
			birdsprites.add(ImageIO.read(new File("../img/bird.png")));
			birdsprites.add(ImageIO.read(new File("../img/birdup.png")));
			width=80; height=55;
			this.windowSize = windowSize;
			xpos =(int) (this.windowSize.getWidth() - width) / 2;
			ypos =(int) (this.windowSize.getHeight() / 2) - height;
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

	public int getWidth(){
		return this.width;
	}

	public int getHeight(){
		return this.height;
	}

	public int getXPos(){
		return this.xpos;
	}

	public int getYPos(){
		return this.ypos;
	}

	public void setXPos(int xpos){
		this.xpos = xpos;
	}

	public void setYPos(int ypos){
		this.ypos = ypos;
	}
}
