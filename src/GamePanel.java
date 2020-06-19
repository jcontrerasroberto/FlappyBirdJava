import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.Rectangle;

public class GamePanel extends JPanel implements KeyListener, MouseListener{

	private Image background, ground;
	private Dimension windowSize;
	private Image imgfbname;
	private Bird bird;
	private boolean start, changeImg, pressed=false;
	private Timer movement, gravity;
	private int bgxpos=0;
	private ArrayList<Pipe> pipes = new ArrayList<>();
	private static final int separation = 150;
	private Area birdArea, pipeUpArea, pipeDownArea;

	public GamePanel(Dimension windowSize) {
		try {
			this.windowSize = windowSize;
			start = false;
			changeImg = false;
			background = ImageIO.read(new File("../img/bg.png"));
			ground = ImageIO.read(new File("../img/ground.png"));
			imgfbname = ImageIO.read(new File("../img/imgfbname.png"));
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		movement = new Timer(30, new ActionListener(){
			public void actionPerformed(ActionEvent ae){

				if(bgxpos <= -(int)windowSize.getWidth()) bgxpos=0;
				else bgxpos=bgxpos-4;
	
				if(start){

					Pipe temp = null;
					
					for(Pipe p : pipes){
						if(p.getXPos()<= -p.getWidth()){
							temp=p;
						}
						if(detectCollision(p)){
							System.out.println("Chocaste");
							System.exit(1);

						}
					}
					if(temp!=null){
						pipes.remove(pipes.indexOf(temp));
						int tempheight = getNewHeight();
						pipes.add(new Pipe(windowSize, tempheight, windowSize.height-tempheight-140, pipes.get(pipes.size()-1).getXPos()+pipes.get(pipes.size()-1).getWidth()+separation));
						temp = null;
					}
					
				}
				repaint();
			}
		});
		movement.start();
		gravity = new Timer(25, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
				if(!pressed && start){
					bird.setYPos(bird.getYPos()+5);
				}
				repaint();
			}
		});
		gravity.start();

	}

	public void init(){
		bird = new Bird(windowSize);
		birdArea = new Area(bird.getBoxArea());
		for(int i=0; i<5; i++){
			int temp = getNewHeight();
			if(i==0) pipes.add(new Pipe(windowSize, temp, windowSize.height-temp-140, windowSize.width+20));
			else pipes.add(new Pipe(windowSize, temp, windowSize.height-temp-140, pipes.get(i-1).getXPos()+pipes.get(i-1).getWidth()+separation));			
		}
	}

	public boolean detectCollision(Pipe p){
		birdArea = new Area(bird.getBoxArea());
		p.setXPos(p.getXPos()-2);
		pipeUpArea = new Area(p.getBoxArea(true));
   		pipeUpArea.intersect(birdArea);
   		pipeDownArea = new Area(p.getBoxArea(false));
   		pipeDownArea.intersect(birdArea);
		if(!pipeUpArea.isEmpty() || !pipeDownArea.isEmpty()) return true;
		else return false;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(background, 0, 0,(int)(windowSize.getWidth()),(int) windowSize.getHeight(), this);

		if(!start) g2.drawImage(imgfbname, 25, 50, 300, 70, this);
		
		
		if(changeImg){
			g2.drawImage(bird.getImage(changeImg), bird.getXPos() , bird.getYPos(), bird.getWidth()+5, bird.getHeight()+5, this);
		}else{
			g2.drawImage(bird.getImage(changeImg), bird.getXPos() , bird.getYPos(), bird.getWidth(), bird.getHeight(), this);
		}	
		g2.draw(birdArea);
		for(Pipe p : pipes){
			g2.drawImage(p.getImage(true), p.getXPos() , p.getYPos(true), p.getWidth(), p.getHeight(true), this);
			g2.drawImage(p.getImage(false), p.getXPos() , p.getYPos(false), p.getWidth(), p.getHeight(false), this);
		}
		g2.drawImage(ground, bgxpos, (int)(windowSize.getHeight())-60,(int)(windowSize.getWidth() * 2),50, this);
	}

	public int getNewHeight(){
		return (int)(Math.random()*(400-200+1)+200); 
	}

	public void goUp(){
		if(!start) start=!start;
		changeImg=!changeImg;
		pressed=false;
		bird.setYPos(bird.getYPos()-60);
		repaint();
	}

	public void goUpEnd(){
		if(!start) start=!start;
		changeImg=!changeImg;
		pressed=true;
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == ke.VK_UP){
			goUpEnd();
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		if(ke.getKeyCode() == ke.VK_UP){
			goUp();			
		}
	}

    public void mousePressed(MouseEvent e) {
		goUpEnd();
	}

    public void mouseReleased(MouseEvent e) {
		goUp();
	}

    public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void keyTyped(KeyEvent ke) {}
}
