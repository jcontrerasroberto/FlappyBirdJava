import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;

public class OfflineGamePanel extends JPanel implements MouseListener{

	private Dimension windowSize;
	private Image imgfbname, imgclick, imgscore, losebird, imgover, playbtn, background, ground;
	private Bird bird;
	private boolean start, changeImg, pressed=false, end=false;
	private Timer movement, gravity;
	private int bgxpos=0, score=0, maxscore;
	private ArrayList<Pipe> pipes = new ArrayList<>();
	private static final int separation = 150;
	private Area birdArea, pipeUpArea, pipeDownArea;
	private BoxArea baplay, bascore;
	private Pipe passed = null;
	private Font bitFont;
	private static String imgURL="../img/game/";

	public OfflineGamePanel(Dimension windowSize) {
		this.windowSize = windowSize;
		start = false;
		changeImg = false;
		try {
			background = ImageIO.read(new File(imgURL+"bg.png"));
			ground = ImageIO.read(new File(imgURL+"ground.png"));
			imgfbname = ImageIO.read(new File(imgURL+"imgfbname.png"));
			imgclick = ImageIO.read(new File(imgURL+"click.png"));
			imgscore = ImageIO.read(new File(imgURL+"scores.png"));
			losebird = ImageIO.read(new File(imgURL+"losebird.png"));
			imgover = ImageIO.read(new File(imgURL+"gameover.png"));
			playbtn = ImageIO.read(new File(imgURL+"playbtn.png"));
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		movement = new Timer(30, new ActionListener(){
			public void actionPerformed(ActionEvent ae){

				if(bgxpos <= -(int)windowSize.getWidth()) bgxpos=0;
				else bgxpos=bgxpos-4;
	
				if(start && !end){

					Pipe temp = null;
					
					for(Pipe p : pipes){
						p.setXPos(p.getXPos()-2);
						if(detectCollision(p)){
							endGame();
							break;
						}
						if(passed!=p && detectPass(p)) {
							score++;
							passed = p;
						}	
						if(p.getXPos()<= -p.getWidth()){
							temp=p;
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
		gravity = new Timer(25, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
			
				if(!pressed && start){
					bird.setYPos(bird.getYPos()+5);
				}
				if(end && bird.getYPos() >= (windowSize.getHeight()-bird.getHeight()-75)) gravity.stop();
				repaint();
			}
		});
		
		movement.start();
		gravity.start();

	}

	public void init(){
		bird = new Bird(windowSize);
		birdArea = new Area(bird.getBoxArea());
		baplay = new BoxArea(150, 100, 25 , (int)windowSize.getHeight() - 400);
		bascore = new BoxArea(150, 100, 175 , (int)windowSize.getHeight() - 400);
		for(int i=0; i<5; i++){
			int temp = getNewHeight();
			if(i==0) pipes.add(new Pipe(windowSize, temp, windowSize.height-temp-140, windowSize.width+70));
			else pipes.add(new Pipe(windowSize, temp, windowSize.height-temp-140, pipes.get(i-1).getXPos()+pipes.get(i-1).getWidth()+separation));			
		}
		try {
			//create the font to use. Specify the size!
			bitFont = Font.createFont(Font.TRUETYPE_FONT, new File("../fonts/8bit.ttf")).deriveFont(40f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(bitFont);
		} catch (Exception e) {
			e.printStackTrace();
		}
		maxscore=getMaxScore(); 
	}
	
	public void reset(){
		pipes.clear();
		start = false;
		end = false;
		init();
		movement.start();
		gravity.start();
		pressed=false;
		changeImg=false;
		score=0;
		repaint();
	}	

	public boolean detectCollision(Pipe p){
		birdArea = new Area(bird.getBoxArea());
		return birdArea.intersects(p.getBoxArea(true)) || birdArea.intersects(p.getBoxArea(false)) || (bird.getYPos() >= (windowSize.getHeight()-bird.getHeight()-75)); 
	}
	
	public boolean detectPass(Pipe p){
		return bird.getXPos() > p.getXPos()+p.getWidth();
	}	

	public int getNewHeight(){
		return (int)(Math.random()*(400-200+1)+200); 
	}

	public void goUp(){
		if(!end){
			if(!start) start=!start;
			changeImg=!changeImg;
			pressed=false;
			if(bird.getYPos()>10) bird.setYPos(bird.getYPos()-60);
			repaint();
		}else{
			pressed=false;
		}		
		
	}

	public void goUpEnd(){
		if(!end){
			if(!start) start=!start;
			changeImg=!changeImg;
			pressed=true;
			repaint();
		}else{
			pressed=false;
		}			
	}

    public void mousePressed(MouseEvent e) {
		if(!end) goUpEnd();
	}

    public void mouseReleased(MouseEvent e) {
		if(!end) goUp();
		if(end && baplay.getBoxArea().contains(e.getX(), e.getY())) reset();
		if(end && bascore.getBoxArea().contains(e.getX(), e.getY())) new ScoresTable();
	}

    public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	
	public int getMaxScore(){
		UserProperties up = new UserProperties();
		return Integer.parseInt(up.getMaxScore());
	}
	
	public void setMaxScore(){
		JOptionPane.showMessageDialog(null, "You've break the record!");
		String nickname = JOptionPane.showInputDialog("Write your nickname to save the record");
		UserProperties up = new UserProperties();
		up.setMaxScore(""+score);
		if(nickname==null || nickname.equals("")) nickname="Unknown";
		up.setMaxNick(nickname);
		up.saveProp();
		saveScore(nickname);
	}
	
	public void saveScore(){
		String nickname = JOptionPane.showInputDialog("Write your nickname to save your score");
		if(nickname==null || nickname.equals("")) nickname="Unknown";
		ScoreList sl = new ScoreList();
		sl.addScore(new Score(nickname, score));
		sl.saveScores();
		sl.printScores();
	}
	
	public void saveScore(String nickname){
		ScoreList sl = new ScoreList();
		sl.addScore(new Score(nickname, score));
		sl.saveScores();
		sl.printScores();
	}

	public void endGame(){
		end=true;
		movement.stop();
		if(score>maxscore) setMaxScore();
		else if(score>0) saveScore();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(background, 0, 0,(int)(windowSize.getWidth() * 2),(int) windowSize.getHeight(), this);
			
		for(Pipe p : pipes){
			g2.drawImage(p.getImage(true), p.getXPos() , p.getYPos(true), p.getWidth(), p.getHeight(true), this);
			g2.drawImage(p.getImage(false), p.getXPos() , p.getYPos(false), p.getWidth(), p.getHeight(false), this);
		}
		
		if(!end){
			if(changeImg){
				g2.drawImage(bird.getImage(changeImg), bird.getXPos() , bird.getYPos(), bird.getWidth()+5, bird.getHeight()+5, this);
			}else{
				g2.drawImage(bird.getImage(changeImg), bird.getXPos() , bird.getYPos(), bird.getWidth(), bird.getHeight(), this);
			}
			if(start){
				g2.setColor(Color.BLUE);
				Font bitfs = bitFont.deriveFont(bitFont.getSize() * 0.3F);
				g2.setFont(bitfs);
				g2.drawString("MaxScore: "+maxscore, 30, (int)windowSize.getHeight()-100);
			}
		}else{
			g2.drawImage(losebird, bird.getXPos() , bird.getYPos(), bird.getHeight()+15, bird.getWidth()+25, this);
			g2.drawImage(imgover, 25, 70, 300, 125, this);
			g2.drawImage(playbtn, 25 , (int)windowSize.getHeight() - 400, 150, 100, this);
			g2.drawImage(imgscore, 175 , (int)windowSize.getHeight() - 400, 150, 100, this);
		}	
		
		g2.drawImage(ground, bgxpos, (int)(windowSize.getHeight())-60,(int)(windowSize.getWidth() * 2),50, this);
	
		if(!start) {
			g2.drawImage(imgfbname, 25, 50, 300, 70, this);
			g2.drawImage(imgclick, (int)windowSize.getWidth()/2 - 100, (int)windowSize.getHeight()/2 - 100, 200, 130, this);
		}else{
			g2.setColor(Color.WHITE);
			g2.setFont(bitFont);
			g2.drawString(""+score, 30, 70);
		}
	}
}
