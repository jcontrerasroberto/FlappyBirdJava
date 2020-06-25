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
