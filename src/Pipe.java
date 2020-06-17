import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Pipe {
	
	private Image uppipeimg, downpipeimg;
	private BoxArea uppipebox, downpipebox;
	private Dimension windowSize;
	private static int width = 80;

	public Pipe(Dimension windowSize, int heightup, int heightdown){
		try {
			uppipeimg = ImageIO.read(new File("../img/uppipe.png"));
			downpipeimg = ImageIO.read(new File("../img/downpipe.png"));
			this.windowSize = windowSize;
			uppipebox = new BoxArea(width, heightup, this.windowSize.width+5, 0);
			downpipebox = new BoxArea(width, heightdown, this.windowSize.width+5, this.windowSize.height-heightdown);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Image getImage(boolean type){
		if(type) return uppipeimg;
		else return downpipeimg;
			
	}

	public int getWidth(boolean type){
		if(type) return uppipebox.getWidth();
		else return downpipebox.getWidth();
		
	}

	public int getHeight(boolean type){
		if(type) return uppipebox.getHeight();
		else return downpipebox.getHeight();
	}

	public int getXPos(boolean type){
		if(type) return uppipebox.getXPos();
		else return downpipebox.getXPos();
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
