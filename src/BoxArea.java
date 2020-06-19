
import java.awt.Rectangle;

public class BoxArea {
	
	private int width, height, xpos, ypos;
	Rectangle r;
	
	public BoxArea(){
		width=0;
		height=0;
		xpos=0;
		ypos=0;
		r = new Rectangle(ypos, xpos, width, height);
	}
	
	public BoxArea(int width, int height, int xpos, int ypos){
		r = new Rectangle(xpos, ypos, width, height);
	}

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

