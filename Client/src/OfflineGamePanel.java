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


/*
	Class OfflineGamePanel

	Es un panel donde se ejecuta el juego


*/

public class OfflineGamePanel extends JPanel implements MouseListener{

	//Declaración de variables usadas durante el juego

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

	//Constructor. Se cargan las imagenes usadas, se añade una nueva fuente y se ejecuta el metodo init()
	// Igual se declaran e inician los timers
	public OfflineGamePanel(Dimension windowSize) {
		this.windowSize = windowSize;
		try {
			background = ImageIO.read(new File(imgURL+"bg.png"));
			ground = ImageIO.read(new File(imgURL+"ground.png"));
			imgfbname = ImageIO.read(new File(imgURL+"imgfbname.png"));
			imgclick = ImageIO.read(new File(imgURL+"click.png"));
			imgscore = ImageIO.read(new File(imgURL+"scores.png"));
			losebird = ImageIO.read(new File(imgURL+"losebird.png"));
			imgover = ImageIO.read(new File(imgURL+"gameover.png"));
			playbtn = ImageIO.read(new File(imgURL+"playbtn.png"));
			//create the font to use. Specify the size!
			bitFont = Font.createFont(Font.TRUETYPE_FONT, new File("../fonts/8bit.ttf")).deriveFont(40f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(bitFont);
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Timer para el movimiento de las tuberias y el suelo
		movement = new Timer(30, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//Se modifica la posicion bgxpos que es la coordenada x de la imagen del suelo
				if(bgxpos <= -(int)windowSize.getWidth()) bgxpos=0;
				else bgxpos=bgxpos-4; 
	
				if(start && !end){
					//Si el juego ya inicio y aun no se muere ejecutamos un for que itera entre las tuberias
					Pipe temp = null; //Tuberia que ya no sirve
					for(Pipe p : pipes){
						p.setXPos(p.getXPos()-2); //Cambia la posición de la tuberia (Movimiento)
						if(detectCollision(p)){
							//Si se detecta que el pajaro choco se acaba el juego
							endGame();
							break;
						}
						if(passed!=p && detectPass(p)) {
							//Si pasa una tubería que no había pasado se aumenta el score
							score++;
							passed=p;
						}
						if(p.getXPos()<= -p.getWidth()) temp=p; /*
							Si ya pasamos una tubería y ya no esta a la vista, esta tubería ya no sirve
						*/
					}
					if(temp!=null){
						//Si tenemos una tubería que ya no sirve la borramos y generamos una nueva
						pipes.remove(pipes.indexOf(temp));
						generatePipe();
						temp = null;
					}
				}
				repaint(); //Se vuelve a pintar
			}
		});
		gravity = new Timer(25, new ActionListener(){
			//Timer que causa efecto de gravedad
			public void actionPerformed(ActionEvent ae){
			
				if(!pressed && start) bird.setYPos(bird.getYPos()+5); //Si no damos click y el juego comenzo hacemos que el pajaro caiga
				if(end && bird.getYPos() >= (windowSize.getHeight()-bird.getHeight()-75)) gravity.stop(); //Si el juego acaba detenemos el timer
				repaint(); //Se vuelve a pintar
			}
		});

		movement.start();
		gravity.start();
		

	}

	/*
		public void init()

		Inicializa las variables del juego, crea BoxArea de los botones
	*/
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
		maxscore=getMaxScore();
		//Al inicio generamos 5 pares de tuberias
		for(int i=0; i<5; i++){
			generatePipe();
		}
	}
	
	/*
		public void reset()

		Método que sirve para volver a jugar despues de que se muere. Reinicia todo
	*/
	public void reset(){
		init();
		movement.start();
		gravity.start();
		repaint();
	}	

	/*
		public void generatePipe()

		Añade un nuevo par de tuberias
	*/
	public void generatePipe(){
		int temp = getNewHeight(); //Creamos una nueva altura de la tubería superios. La altura de ka inferior se calcula
		if(pipes.size()==0) pipes.add(new Pipe(windowSize, temp, windowSize.height-temp-140, windowSize.width+70)); //Si no hay tuberías se crea la principal que sirve como referencia
		//Si ya eciste una tubería anterior se crea otra a una distancia determinada de separación respecto a la anterior
		else pipes.add(new Pipe(windowSize, temp, windowSize.height-temp-140, pipes.get(pipes.size()-1).getXPos()+pipes.get(pipes.size()-1).getWidth()+separation));
	}
	
	/*
		public boolean detectCollision(Pipe p)

		Revisa si el pajaro choca contra la tubería o el suelo

		Retorna true si intersecta el boxArea del pajaro con la de lastuberías o el suelo
	*/	
	public boolean detectCollision(Pipe p){
		birdArea = new Area(bird.getBoxArea());
		return birdArea.intersects(p.getBoxArea(true)) || birdArea.intersects(p.getBoxArea(false)) || (bird.getYPos() >= (windowSize.getHeight()-bird.getHeight()-75)); 
	}
	
	/*
		public boolean detectPass(Pipe p)

		Retorna true si el pajaro esta adelante de la tubería lo que significa que la paso exitosamente
	*/
	public boolean detectPass(Pipe p){
		return bird.getXPos() > p.getXPos()+p.getWidth();
	}	

	// Crea un numero aleatorio para el tamaño de las tuberias
	public int getNewHeight(){
		return (int)(Math.random()*(400-200+1)+200); 
	}

	/*
		public void goUp()

		Se ejecuta cuando se da click
	*/
	public void goUp(){
		if(!end){
			if(!start) start=!start; //Inicia el juego si aun no inicia
			if(bird.getYPos()>10) bird.setYPos(bird.getYPos()-60); //Efecto de que el pajaro vuela más alto
			changeImg=!changeImg; //Cambiamos el sprite del pajaro
			pressed=false; //Indicamos que se dejo de presionar el mouse	
			repaint(); //Se vuelve a pintar
		}else pressed=false; //Indicamos que se dejo de presionar el mouse	
	}

	/*
		public void goUpEnd()

		Se ejecuta cuando se da presiona el mouse
	*/
	public void goUpEnd(){
		if(!end){
			if(!start) start=!start;//Inicia el juego si aun no inicia
			changeImg=!changeImg;//Cambiamos el sprite del pajaro
			pressed=true;//Indicamos que se presionó el mouse	
			repaint();//Se vuelve a pintar
		}else pressed=false;//Indicamos que se dejo de presionar el mouse	
	}
	
	/*
		public int getMaxScore()

		De las propiedades de usuario obtenemos el puntaje máximo actual
	*/
	public int getMaxScore(){
		UserProperties up = new UserProperties();
		return Integer.parseInt(up.getMaxScore());
	}
	
	/*
		public void setMaxScore()

		Se ejecuta si se supero el puntaje máximo
	*/
	public void setMaxScore(){
		JOptionPane.showMessageDialog(null, "You've break the record!"); //Aviso
		String nickname = JOptionPane.showInputDialog("Write your nickname to save the record"); //Se pide el nickname para guardar la información
		if(nickname==null || nickname.equals("")) nickname="Unknown";
		UserProperties up = new UserProperties(); //Se modifica el archivo user.properties
		up.setMaxScore(""+score);
		up.setMaxNick(nickname);
		up.saveProp();
		saveScore(nickname); //Se ejecuta saveScore con un nickname dado
	}
	
	/*
		public void saveScore()

		Se ejecuta cuando no se rompe el record pero el usuario quiere guardar su puntaje
	*/
	public void saveScore(){
		String nickname = JOptionPane.showInputDialog("Write your nickname to save your score");
		if(nickname!=null && !nickname.equals("")) saveScore(nickname); //Se ejecuta saveScore con un nickname dado
	}
	
	/*
		public void saveScore(String nickname)

		Guarda el puntaje en ScoreList
	*/
	public void saveScore(String nickname){
		ScoreList sl = new ScoreList();
		sl.addScore(new Score(nickname, score)); //Lo añadimos al scorelist
		sl.saveScores(); //Guardamos
	}

	/*
		public void endGame()

		Detiene el juego y se pide el nickname para guardar el score
	*/
	public void endGame(){
		end=true;
		movement.stop();
		if(score>maxscore) setMaxScore(); //Si se rompe el score maximo
		else if(score>0) saveScore(); //Si no se rompe el máximo score pero tiene puntaje mayor que 0
	}
	
	/*
		public void paintComponent(Graphics g)

		Se dibujan los elementos en pantalla
	*/
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//El fondo
		g2.drawImage(background, 0, 0,(int)(windowSize.getWidth() * 2),(int) windowSize.getHeight(), this);
			
		for(Pipe p : pipes){
			//Se dibuja cada tubería
			g2.drawImage(p.getImage(true), p.getXPos() , p.getYPos(true), p.getWidth(), p.getHeight(true), this);
			g2.drawImage(p.getImage(false), p.getXPos() , p.getYPos(false), p.getWidth(), p.getHeight(false), this);
		}
		
		if(!end){
			//Si eljuego no acaba
			if(changeImg){
				//Se dubuja el pajaro en posición normal
				g2.drawImage(bird.getImage(changeImg), bird.getXPos() , bird.getYPos(), bird.getWidth()+5, bird.getHeight()+5, this);
			}else{
				//Se dubuja el pajaro inclinado
				g2.drawImage(bird.getImage(changeImg), bird.getXPos() , bird.getYPos(), bird.getWidth(), bird.getHeight(), this);
			}
			if(start){
				//Si ya inicio se dibuja el puntaje máximo al fondo de la pantalla
				g2.setColor(Color.BLUE);
				Font bitfs = bitFont.deriveFont(bitFont.getSize() * 0.3F);
				g2.setFont(bitfs);
				g2.drawString("MaxScore: "+maxscore, 30, (int)windowSize.getHeight()-100);
			}
		}else{
			//Si eljuego acabo se dibujan los botones y el anuncio de que perdiste
			g2.drawImage(losebird, bird.getXPos() , bird.getYPos(), bird.getHeight()+15, bird.getWidth()+25, this);
			g2.drawImage(imgover, 25, 70, 300, 125, this);
			g2.drawImage(playbtn, 25 , (int)windowSize.getHeight() - 400, 150, 100, this);
			g2.drawImage(imgscore, 175 , (int)windowSize.getHeight() - 400, 150, 100, this);
		}	
		
		//Se dibuja elsuelo que esta en constante movimiento
		g2.drawImage(ground, bgxpos, (int)(windowSize.getHeight())-60,(int)(windowSize.getWidth() * 2),50, this);
	
		if(!start) {
			//Si eljuego aun no inicia se dibuja el nombre del juego y el icono de clicl
			g2.drawImage(imgfbname, 25, 50, 300, 70, this);
			g2.drawImage(imgclick, (int)windowSize.getWidth()/2 - 100, (int)windowSize.getHeight()/2 - 100, 200, 130, this);
		}else{
			//Si el juego ya inició se dibuja el score actual en la parte superior
			g2.setColor(Color.WHITE);
			g2.setFont(bitFont);
			g2.drawString(""+score, 30, 70);
		}
	}
	
	/* EVENTOS DEL MOUSE */

	public void mousePressed(MouseEvent e) {
		if(!end) goUpEnd();
	}

    public void mouseReleased(MouseEvent e) {
		if(!end) goUp(); 
		if(end && baplay.getBoxArea().contains(e.getX(), e.getY())) reset(); //Si se da click en el boton play se reinicia el juego
		if(end && bascore.getBoxArea().contains(e.getX(), e.getY())) new ScoresTable(); //Si se da click en el boton scores se muestran todos los puntajes
	}

    public void mouseEntered(MouseEvent e) {} //Ignore
	public void mouseExited(MouseEvent e) {} //Ignore
	public void mouseClicked(MouseEvent e) {} //Ignore
}
