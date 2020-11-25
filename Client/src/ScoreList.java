


 
import java.util.ArrayList;
import java.io.File;
import java.io.*;
import java.io.Serializable;
import java.util.*;

/*
	Class ScoreList

	Esta clase ayuda a manejar un archivo serializado el cual contiene los datos de todos los puntajes obtenidos en el modo offline


*/

public class ScoreList {

	private ArrayList<Score> scores = new ArrayList<>();
	private static final String configURL="../.config/"; //Path donde esta el archivo
	private File bdfile = null;
	private String filename;

	//Constructor. Ejecutamos el método deserialization()
	public ScoreList(){
		filename=configURL+"scores.ser"; //Path con el nombre del archivo incluido
		deserialization();
	}
	

	public void addScore(Score s){
		scores.add(s); //Añadimos un puntaje al ArrayList
	}
	
	//Getter para el arraylist de puntajes
	public ArrayList<Score> getScores(){
		return scores; 
	}
	
	//public void saveScores(). Ejecutamos el metodo serialization
	public void saveScores(){
		serialization();
	}
	
	/*
		private void deserialization()

		Abrimos el archivo y obtenemos lo que contiene que es un ArrayList de Scores
	*/
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
	
	/*
		private void serialization()

		Escribimos los nuevos valores en el archivo en forma binaria
	*/
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
