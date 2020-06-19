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

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {

	private Image background;
	private Dimension windowSize;
	private Image imgfbname;
	private Bird bird;
	private boolean start, changeImg;
	private Timer movement;
	private int bgxpos=0;
	private ArrayList<Pipe> pipes = new ArrayList<>();
	private static final int separation = 150;

	public GamePanel(Dimension windowSize) {
		try {
			this.windowSize = windowSize;
			start = false;
			changeImg = false;
			background = ImageIO.read(new File("../img/bg.png"));
			imgfbname = ImageIO.read(new File("../img/imgfbname.png"));
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		movement = new Timer(50, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(bgxpos <= -(int)windowSize.getWidth()) bgxpos=0;
				else bgxpos=bgxpos-4;
				Pipe temp = null;	
				if(start){
					for(Pipe p : pipes){
						p.setXPos(p.getXPos()-2);
						if(p.getXPos()<= -p.getWidth()){
							temp=p;
						}
					}
					if(temp!=null){
						pipes.remove(pipes.indexOf(temp));
						int tempheight = getNewHeight();
						pipes.add(new Pipe(windowSize, tempheight, windowSize.height-tempheight-100, pipes.get(pipes.size()-1).getXPos()+pipes.get(pipes.size()-1).getWidth()+separation));
						temp = null;
					}
					
				}
				repaint();
			}
		});
		movement.start();

	}

	public void init(){
		bird = new Bird(windowSize);
		for(int i=0; i<5; i++){
			int temp = getNewHeight();
			if(i==0) pipes.add(new Pipe(windowSize, temp, windowSize.height-temp-100, windowSize.width+20));
			else pipes.add(new Pipe(windowSize, temp, windowSize.height-temp-100, pipes.get(i-1).getXPos()+pipes.get(i-1).getWidth()+separation));			
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(background, bgxpos, 0,(int)(windowSize.getWidth() * 2),(int) windowSize.getHeight(), this);
	
		if(!start) g2.drawImage(imgfbname, 25, 50, 300, 70, this);
		
		Rectangle2D r = new Rectangle2D.Float(bird.getXPos() , bird.getYPos(), bird.getWidth(), bird.getHeight());
		g2.drawImage(bird.getImage(changeImg), bird.getXPos() , bird.getYPos(), bird.getWidth(), bird.getHeight(), this);
		g2.draw(r);
		for(Pipe p : pipes){
			g2.drawImage(p.getImage(true), p.getXPos() , p.getYPos(true), p.getWidth(), p.getHeight(true), this);
			g2.drawImage(p.getImage(false), p.getXPos() , p.getYPos(false), p.getWidth(), p.getHeight(false), this);
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == ke.VK_UP){
			if(!start) start=!start;
			changeImg=!changeImg;
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		if(ke.getKeyCode() == ke.VK_UP){
			if(!start) start=!start;
			changeImg=!changeImg;
			bird.setYPos(bird.getYPos()-15);
			repaint();
		}
		if(ke.getKeyCode() == ke.VK_DOWN){
			if(!start) start=!start;
			bird.setYPos(bird.getYPos()+15);
			repaint();
		}	
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		
	}

	public int getNewHeight(){
		return (int)(Math.random()*(500-200+1)+200); 
	}
}
