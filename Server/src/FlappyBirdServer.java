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

import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.net.InetAddress;

 /*
 * CLASE FlappyBirdServer
 * 
 * Esta clase contiene el método main para iniciar el servidor. 
 * 
 * */
 
public class FlappyBirdServer extends UnicastRemoteObject {
	
	private static final int PUERTO=9999; //Declaración del puerto a usar. En este caso el 9999

	//Constructor de la clase que simplemente ejecuta el método startServer
	public FlappyBirdServer() throws RemoteException, AlreadyBoundException{
		startServer();
	}

	/*
	 * public void startServer()
	 * 
	 * Con ayuda de la clase ServerProperties obtiene la ip del servidor y modifica la propiedad 
	 * 		"java.rmi.server.hostname" del sistema para un buen funcionamiento
	 * Crea un nuevo objeto de ImplementRMI el cual se "enviara" al cliente
	 * 
	 * */
	public void startServer() throws RemoteException, AlreadyBoundException{
		ServerProperties sp = new ServerProperties();
		System.setProperty("java.rmi.server.hostname",sp.getIP()); //IP of server
		System.out.println(System.getProperty("java.rmi.server.hostname"));
		ImplementRMI send = new ImplementRMI();
		Registry registry = LocateRegistry.createRegistry(PUERTO); //Creación del registro en el puerto especificado
		registry.bind("OnlineMethods", (FlappyBirdOnline) send); //Se añade el skeleton
		System.out.println("Waiting for clients");
	}
	
	//Método main que crea un nuevo FlappyBirdServer
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		new FlappyBirdServer();
	}
}
