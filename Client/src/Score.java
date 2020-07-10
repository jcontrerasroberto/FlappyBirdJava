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

/*
	Class Score

	Esta clase define las propiedades de un puntaje.


*/

public class Score implements Serializable{
	private String nickname;
	private int score;

	//Constructor que crea un nuevo score con los datos recibidos: jugador y puntos

	public Score(String n, int s){
		this.nickname = n;
		this.score = s;
	}

	//Métodos getters para cada propiedad
	public String getNickname(){
		return nickname;
	}

	public int getScore(){
		return score;
	}

}
