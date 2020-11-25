
 
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;

 /*
 * CLASE ServerProperties
 * 
 * Esta clase sirve para leer y editar el archivo user.properties ubicado en la carpeta .config el cual contiene 
 	la configuración actual del usuario. Skin, máximo puntaje
 * 
 * */

public class UserProperties {
	
	private Properties userprop = new Properties();
	private InputStream read;

	//Constructor que se encarga de abrir el archivo y cargarlo al objeto Properties
	public UserProperties(){
		
		try {
			read = new FileInputStream("../.config/user.properties");
			userprop.load(read);
		} catch (Exception e) {
			System.out.println("Error opening file");
		}
		
	}

	//Getter y setters para cada propiedad del archivo
	public String getSkin(){
		return userprop.getProperty("user.skin");
	}

	public void setSkin(String newSkin){
		userprop.setProperty("user.skin", newSkin);
	}

	public String getMaxScore(){
		return userprop.getProperty("user.maxscore");
	}

	public void setMaxScore(String score){
		userprop.setProperty("user.maxscore", score);
	}

	public String getMaxNick(){
		return userprop.getProperty("user.maxnick");
	}

	public void setMaxNick(String nick){
		userprop.setProperty("user.maxnick", nick);
	}

	//Método que sirve para guardar el archivo con los nuevos valores
	public void saveProp(){
		try{
			userprop.store(new FileOutputStream("../.config/user.properties"), null);
		}catch(Exception e){
			System.out.println("Error saving the properties");
		}		
	}
}
