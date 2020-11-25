
 
 /*
 * CLASE Player
 * 
 * Define a un jugador, contiene metodos getters y setters para cada atributo
 * Un jugador tiene 4 atributos, su nombre o nickname, la ip de dónde se conecta, el puntaje que lleva y su estado (vivo o muerto)
 * 
 * */


public class Player implements java.io.Serializable {

	private String nickname;
	private String ip;
	private int score;
	private boolean alive;


	// Constructor por default que inicializa los valores por default
	public Player(){
		nickname="";
		ip="";
		score=0;
		alive = true;
	}
	
	//Constructor que recibe los valores que se le asignaran a cada atributo
	public Player(String nickname, String ip, int score, boolean alive){
		this.nickname = nickname;
		this.ip = ip;
		this.score = score;
		this.alive = alive;
	}
	
	/*	Métodos getters */
	
	public String getNickname(){
		return nickname;
	}
	
	public String getIP(){
		return ip;
	}
	
	public int getScore(){
		return score;
	}
	
	public boolean getAlive(){
		return alive;
	}
	
	/*	Métodos setters */
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	
	public void setIP(String ip){
		this.ip = ip;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public void setAlive(boolean alive){
		this.alive = alive;
	}
	
}
