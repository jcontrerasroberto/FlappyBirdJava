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
 
import java.io.*;

public class Score implements Serializable{
	private String nickname;
	private int score;

	public Score(String n, int s){
		this.nickname = n;
		this.score = s;
	}

	public String getNickname(){
		return nickname;
	}

	public int getScore(){
		return score;
	}

}
