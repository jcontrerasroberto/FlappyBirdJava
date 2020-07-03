/*
 * PROYECTO FINAL DE PROGRAMACIÓN ORIENTADA A OBJETOS
 * FLAPPY BIRD (MULTIPLAYER Y SINGLEPLAYER)
 * 
 * 2CM3
 * 
 * INTEGRANTES:
 * 
 * CONTRERAS BARRITA JOSÉ ROBERTO
 * CONTRERAS MENDEZ BRANDON
 * FONSECA RAMOS ANGEL GABRIEL
 * TOLEDO ESPINOSA CRISTINA ALINE
 * 
 * */
 
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
import javax.swing.JFrame;
import javax.swing.JDialog;
import java.util.HashMap;

public class OnlineGamePanel extends JPanel implements MouseListener{

	private Dimension windowSize;
	private Image imgfbname, imgclick, imgscore, losebird, imgover, playbtn, background, ground,tablerobg;
	private Bird bird;
	private boolean start, changeImg, pressed=false, end=false, endonline=false;
	private Timer movement, gravity, gameControl;
	private int bgxpos=0, score=0, maxscore;
	private ArrayList<Pipe> pipes = new ArrayList<>();
	private static final int separation = 150;
	private Area birdArea, pipeUpArea, pipeDownArea;
	private BoxArea baplay, bascore;
	private Pipe passed = null;
	private Font bitFont;
	private static String imgURL="../img/game/";
	private Player myplayer = null;
	private String nickname;
	private RMICon session;
	private HashMap<String, Player> players = new HashMap<String, Player>(); 

	public OnlineGamePanel(Dimension windowSize, String nickname) {
		this.windowSize = windowSize;
		try {
			this.nickname = nickname;
			session = new RMICon();
			myplayer = session.getPlayer(nickname);
			if(myplayer==null) System.out.println("Error, null player");
			background = ImageIO.read(new File(imgURL+"bg.png"));
			ground = ImageIO.read(new File(imgURL+"ground.png"));
			imgfbname = ImageIO.read(new File(imgURL+"imgfbname.png"));
			imgclick = ImageIO.read(new File(imgURL+"click.png"));
			imgscore = ImageIO.read(new File(imgURL+"scores.png"));
			losebird = ImageIO.read(new File(imgURL+"losebird.png"));
			imgover = ImageIO.read(new File(imgURL+"gameover.png"));
			playbtn = ImageIO.read(new File(imgURL+"playbtn.png"));
			tablerobg = ImageIO.read(new File(imgURL+"tablerobg.png"));
			//create the font to use. Specify the size!
			bitFont = Font.createFont(Font.TRUETYPE_FONT, new File("../fonts/8bit.ttf")).deriveFont(40f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(bitFont);
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		movement = new Timer(30, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
	
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
							passed=p;
							try{
								myplayer.setScore(score);
								session.setPlayer(myplayer);
							}
							catch(Exception exc){
							}
							
						}
						if(p.getXPos()<= -p.getWidth()) temp=p;
					}
					if(temp!=null){
						pipes.remove(pipes.indexOf(temp));
						generatePipe();
						temp = null;
					}
				}
				repaint();
			}
		});
		gravity = new Timer(30, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
			
				if(!pressed && start) bird.setYPos(bird.getYPos()+5);
				if(end && bird.getYPos() >= (windowSize.getHeight()-bird.getHeight()-75)) gravity.stop();
				repaint();
			}
		});
		gameControl = new Timer(100, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					if(!session.keepGaming() && end){
						endonline=true;
						endOnlineGame();
						System.out.println("gameControl");
						gameControl.stop();
					}
					players =  session.getPlayers();
					repaint();
				}
				catch(Exception e){
					System.out.println("Error obteniendo players");	
				}
			}
		});
		
		movement.start();
		gravity.start();
		gameControl.start();
	}

	public void init(){
		pipes.clear();
		end=false;
		start = false;
		changeImg = false;
		pressed=false;
		changeImg=false;
		score=0;
		bird = new Bird(windowSize);
		birdArea = new Area(bird.getBoxArea());
		baplay = new BoxArea(150, 100, 25 , (int)windowSize.getHeight() - 400);
		bascore = new BoxArea(150, 100, 175 , (int)windowSize.getHeight() - 400);
		for(int i=0; i<5; i++){
			generatePipe();
		}
		try{
			players =  session.getPlayers();
		} catch(Exception e) {}
	}
	
	public void generatePipe(){
		int temp = getNewHeight();
		if(pipes.size()==0) pipes.add(new Pipe(windowSize, temp, windowSize.height-temp-140, windowSize.width+70));
		else pipes.add(new Pipe(windowSize, temp, windowSize.height-temp-140, pipes.get(pipes.size()-1).getXPos()+pipes.get(pipes.size()-1).getWidth()+separation));
	}
		
	public boolean detectCollision(Pipe p){
		birdArea = new Area(bird.getBoxArea());
		return birdArea.intersects(p.getBoxArea(true)) || birdArea.intersects(p.getBoxArea(false)) || (bird.getYPos() >= (windowSize.getHeight()-bird.getHeight()-75)); 
	}
	
	public boolean detectPass(Pipe p){
		return bird.getXPos() > p.getXPos()+p.getWidth();
	}
	
	public void endOnlineGame(){
		try{
			Player winner = session.getWinner();
			if(winner.getNickname().equals(myplayer.getNickname())) {
				JOptionPane.showMessageDialog(null, "Felicidades, has ganado!");
				session.saveOnlineGame(myplayer);
			}
			else JOptionPane.showMessageDialog(null, "El ganador fue "+winner.getNickname());
			session.exitGame(myplayer);
			JDialog parent = (JDialog) this.getTopLevelAncestor();
			parent.dispose();
		}
		catch(Exception e){
			System.out.println("Error obteniendo al ganador");
		}
	}	

	public int getNewHeight(){
		return (int)(Math.random()*(400-200+1)+200); 
	}

	public void goUp(){
		if(!end){
			if(!start) start=!start;
			if(bird.getYPos()>10) bird.setYPos(bird.getYPos()-60);
			changeImg=!changeImg;
			pressed=false;
			repaint();
		}else pressed=false;		
	}

	public void goUpEnd(){
		if(!end){
			if(!start) start=!start;
			changeImg=!changeImg;
			pressed=true;
			repaint();
		}else pressed=false;	
	}
	
	public void endGame(){
		end=true;
		try{
			myplayer.setAlive(false);
			session.setPlayer(myplayer);
		}catch(Exception exc){
		}
		movement.stop();
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
		}else{
			g2.drawImage(losebird, bird.getXPos() , bird.getYPos(), bird.getHeight()+15, bird.getWidth()+25, this);
			g2.drawImage(imgover, 25, 70, 300, 125, this);
		}	
		
		g2.drawImage(ground, 0, (int)(windowSize.getHeight())-60,(int)(windowSize.getWidth() * 2),50, this);
	
		if(!start) {
			g2.drawImage(imgfbname, 25, 50, 300, 70, this);
			g2.drawImage(imgclick, (int)windowSize.getWidth()/2 - 100, (int)windowSize.getHeight()/2 - 100, 200, 130, this);
		}else{
			g2.setColor(Color.WHITE);
			g2.setFont(bitFont);
			g2.drawString(""+score, 30, 70);
		}
		g2.setColor(Color.WHITE);
		g2.fillRect(350, 0, 300, 750);
		int yposs = 100;
		Font bitfs = bitFont.deriveFont(bitFont.getSize() * 0.3F);
		g2.setFont(bitfs);
		//g2.drawImage(tablerobg, 375, 15, 250, 650, this);
		
		for(Player p : players.values()){
			g2.setColor(Color.RED);
			//g2.fillRect(375, yposs-40, 250, 50);
			g2.drawImage(tablerobg, 375, yposs-40, 250, 50, this);
			g2.setColor(Color.BLACK);
			g2.drawString(p.getNickname()+": "+p.getScore(), 400, yposs-10);
			yposs=yposs+75;
		}
	}
	
	public void mousePressed(MouseEvent e) {
		if(!end) goUpEnd();
	}

    public void mouseReleased(MouseEvent e) {
		if(!end) goUp();
	}

    public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
}
