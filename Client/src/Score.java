
 
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
