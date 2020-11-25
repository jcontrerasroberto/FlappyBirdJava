

import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;

 /*
 * CLASE ServerProperties
 * 
 * Esta clase sirve para leer el archivo server.properties ubicado en la carpeta .config el cual contiene una propiedad
 * que es la IP del servidor para una fácil modificación sin necesidad de módificar el código
 * 
 * */

public class ServerProperties {
	private Properties serverprop = new Properties(); //Objeto properties para el manejo del archivo
	private InputStream read; //Sirve para leer el archivo

	//Constructor que se encarga de abrir el archivo y cargarlo al objeto Properties
	public ServerProperties(){
		
		try {
			read = new FileInputStream("../.config/server.properties"); //Se abre el archivo
			serverprop.load(read); //Cargamos las propiedades del archivo
		} catch (Exception e) {
			System.out.println("Error opening file");
		}
		
	}

	//Getter que sirve para obtener el valor de la propiedad server.ip del archivo properties
	public String getIP(){
		return serverprop.getProperty("server.ip");
	}
}
