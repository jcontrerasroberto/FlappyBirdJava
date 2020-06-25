import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

public class Pipe {
	
	private Image uppipeimg, downpipeimg;
	private BoxArea uppipebox, downpipebox;
	private Dimension windowSize;
	private static int width = 80;
	private static String imgURL="../img/game/";

	public Pipe(Dimension windowSize, int heightup, int heightdown, int xpos){
		try {
			uppipeimg = ImageIO.read(new File(imgURL+"uppipe.png"));
			downpipeimg = ImageIO.read(new File(imgURL+"downpipe.png"));
			this.windowSize = windowSize;
			uppipebox = new BoxArea(width, heightup, xpos, 0);
			downpipebox = new BoxArea(width, heightdown, xpos, this.windowSize.height-heightdown);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Image getImage(boolean type){
		if(type) return uppipeimg;
		else return downpipeimg;
			
	}

	public Rectangle getBoxArea(boolean type){
		if(type) return uppipebox.getBoxArea();
		else return downpipebox.getBoxArea();
	}

	public int getWidth(){
		return width;
		
	}

	public int getHeight(boolean type){
		if(type) return uppipebox.getHeight();
		else return downpipebox.getHeight();
	}

	public int getXPos(){
		return uppipebox.getXPos();
	}

	public int getYPos(boolean type){
		if(type) return uppipebox.getYPos();
		else return downpipebox.getYPos();
	}

	public void setXPos(int xpos){
		uppipebox.setXPos(xpos);
		downpipebox.setXPos(xpos);
	}

	public void setYPos(int ypos){
		uppipebox.setYPos(ypos);
	}

	
}
