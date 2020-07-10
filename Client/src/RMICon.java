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
 
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.HashMap;

 /*
 * CLASE RMICon
 * 
 * Obtiene la implementación del server y permite ejecutar los méttodos remotos.
 * Ayuda a controlar las conexiones RMI
 * 
 * */

public class RMICon {
	
	private FlappyBirdOnline inter;
	private static int serverPort = 9999;
	
	//Constructor. Crea un registro del puerto y la dirección del server especificada en ServerProperties
	public RMICon(){
		
		ServerProperties sp = new ServerProperties();
		String serverAddres = sp.getIP();
		try {
			Registry registry = LocateRegistry.getRegistry(serverAddres, serverPort);
			inter = (FlappyBirdOnline) (registry.lookup("OnlineMethods")); //Obtenemos la implementación del servidor
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// A partir de aquí, cada método solo ejecuta el método remoto correspondiente
	
	
	public boolean getPermission(String nickname) throws RemoteException{
		return inter.getPermission(nickname);
	}
	
	public Player getPlayer(String nickname) throws RemoteException{
		return inter.getPlayer(nickname);
	}
	
	public void setPlayer(Player p) throws RemoteException{
		inter.setPlayer(p);
	}
	
	public HashMap<String, Player> getPlayers() throws RemoteException{
		return inter.getPlayers();
	}

	public boolean keepGaming() throws RemoteException{
		return inter.keepGaming();
	}
	
	public Player getWinner() throws RemoteException{
		return inter.getWinner();
	}
	
	public boolean saveOnlineGame(Player winner) throws RemoteException{
		return inter.saveOnlineGame(winner);
	}
	
	public void exitGame(Player p) throws RemoteException{
		inter.exitGame(p);
	}
}
