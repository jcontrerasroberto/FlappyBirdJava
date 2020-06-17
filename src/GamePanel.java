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
	//private Pipe pipe, downpipe;

	public GamePanel(Dimension windowSize) {
		try {
			this.windowSize = windowSize;
			start = false;
			changeImg = false;
			background = ImageIO.read(new File("../img/bg.png"));
			imgfbname = ImageIO.read(new File("../img/imgfbname.png"));
			bird = new Bird(this.windowSize);
			//pipe = new Pipe(windowSize, 300, 300);
			//downpipe = new Pipe(windowSize, 400, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		movement = new Timer(50, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(bgxpos <= -(int)windowSize.getWidth()) bgxpos=0;
				else bgxpos=bgxpos-4;
				if(start){
					/*pipe.setXPos(pipe.getXPos(true)-2);
					pipe.setXPos(pipe.getXPos(false)-2);
					if(pipe.getXPos(true)<=100){
						downpipe.setXPos(downpipe.getXPos(true)-2);
						downpipe.setXPos(downpipe.getXPos(false)-2);
					}*/
					
				}
				repaint();
			}
		});
		movement.start();

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
		/*g2.drawImage(pipe.getImage(true), pipe.getXPos(true) , pipe.getYPos(true), pipe.getWidth(true), pipe.getHeight(true), this);
		g2.drawImage(pipe.getImage(false), pipe.getXPos(false) , pipe.getYPos(false), pipe.getWidth(false), pipe.getHeight(false), this);
		g2.drawImage(downpipe.getImage(true), downpipe.getXPos(true) , downpipe.getYPos(true), downpipe.getWidth(true), downpipe.getHeight(true), this);
		g2.drawImage(downpipe.getImage(false), downpipe.getXPos(false) , downpipe.getYPos(false), downpipe.getWidth(false), downpipe.getHeight(false), this);
		System.out.println("x="+pipe.getXPos(true));*/
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == ke.VK_UP){
			changeImg=!changeImg;
			if(!start) start=!start;
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
	public void keyReleased(KeyEvent ke) {
		if(ke.getKeyCode() == ke.VK_UP){
			changeImg=!changeImg;
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent ke) {

	}
}
