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
 
public class Player implements java.io.Serializable {

	private String nickname;
	private String ip;
	private int score;
	private boolean alive;

	public Player(){
		nickname="";
		ip="";
		score=0;
		alive = true;
	}
	
	public Player(String nickname, String ip, int score, boolean alive){
		this.nickname = nickname;
		this.ip = ip;
		this.score = score;
		this.alive = alive;
	}
	
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
