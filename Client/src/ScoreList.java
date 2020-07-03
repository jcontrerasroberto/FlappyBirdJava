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
 
import java.util.ArrayList;
import java.io.File;
import java.io.*;
import java.io.Serializable;
import java.util.*;

public class ScoreList {

	private ArrayList<Score> scores = new ArrayList<>();
	private static final String configURL="../.config/"; 
	private File bdfile = null;
	private String filename;

	public ScoreList(){
		filename=configURL+"scores.ser";
		deserialization();
	}
	
	public void addScore(Score s){
		scores.add(s);
	}
	
	public ArrayList<Score> getScores(){
		return scores;
	}
	
	public void printScores(){
		for(Score s: scores){
			System.out.println(s.getNickname()+": "+s.getScore());
		}
	}
	
	public void saveScores(){
		serialization();
	}
	
	private void deserialization(){
		try{
			FileInputStream file = new FileInputStream(filename); 
			ObjectInputStream in = new ObjectInputStream(file);
			scores = (ArrayList<Score>)in.readObject(); 
			in.close(); 
			file.close();
		}catch(Exception e){
			System.out.println("Error");
		}	
	}
	
	private void serialization(){
		try
        {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
            out.writeObject(scores);
            out.close(); 
            file.close(); 
            System.out.println("Object has been serialized"); 
        } 
        catch(Exception ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
	}

}
