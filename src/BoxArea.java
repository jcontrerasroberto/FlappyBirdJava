public class BoxArea {
	
	private int width, height, xpos, ypos;
	
	public BoxArea(){
		width=0;
		height=0;
		xpos=0;
		ypos=0;
	}
	
	public BoxArea(int width, int height, int xpos, int ypos){
		this.width=width;
		this.height=height;
		this.xpos=xpos;
		this.ypos=ypos;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getXPos(){
		return xpos;
	}
	
	public int getYPos(){
		return ypos;
	}
	
	public void setXPos(int xpos){
		this.xpos = xpos;
	}

	public void setYPos(int ypos){
		this.ypos = ypos;
	}
}

