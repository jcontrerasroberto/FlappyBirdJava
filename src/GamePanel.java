import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	private Timer bgmovement;
	private int bgxpos=0;

	public GamePanel(Dimension windowSize) {
		try {
			this.windowSize = windowSize;
			start = false;
			changeImg = false;
			background = ImageIO.read(new File("../img/bg.png"));
			imgfbname = ImageIO.read(new File("../img/imgfbname.png"));
			bird = new Bird(this.windowSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bgmovement = new Timer(100, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(bgxpos <= -(int)windowSize.getWidth()){
					bgxpos=0;
				}else{
					bgxpos=bgxpos-8;
				}
				
				repaint();
			}
		});
		bgmovement.start();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(background, bgxpos, 0,(int)(windowSize.getWidth() * 2),(int) windowSize.getHeight(), this);
		if(!start){
			g2.drawImage(imgfbname, 25, 50, 300, 70, this);
		}
		g2.drawImage(bird.getImage(changeImg), bird.getXPos() , bird.getYPos(), bird.getWidth(), bird.getHeight(), this);
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
