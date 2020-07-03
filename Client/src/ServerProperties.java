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
 
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;

public class ServerProperties {
	private Properties serverprop = new Properties();
	private InputStream read;

	public ServerProperties(){
		
		try {
			read = new FileInputStream("../.config/server.properties");
			serverprop.load(read);
		} catch (Exception e) {
			System.out.println("Error opening file");
		}
		
	}

	public String getIP(){
		return serverprop.getProperty("server.ip");
	}

	public void setIP(String newIP){
		serverprop.setProperty("server.ip", newIP);
	}

	public void saveProp(){
		try{
			serverprop.store(new FileOutputStream("../.config/server.properties"), null);
		}catch(Exception e){
			System.out.println("Error saving server properties");
		}		
	}
}
