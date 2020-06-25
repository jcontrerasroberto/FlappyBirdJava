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
			if (scores==null)
			{
				System.out.println("NULL");
			}
			System.out.println(scores.size());
		}catch(Exception e){
			System.out.println("Error");
		}	
	}
	
	private void serialization(){
		try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
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
