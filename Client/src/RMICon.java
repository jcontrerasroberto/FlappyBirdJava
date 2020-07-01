import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.HashMap;

public class RMICon {
	
	private FlappyBirdOnline inter;
	private static int serverPort = 9999;
	
	public RMICon(){
		
		ServerProperties sp = new ServerProperties();
		String serverAddres = sp.getIP();
		try {
			Registry registry = LocateRegistry.getRegistry(serverAddres, serverPort);
			inter = (FlappyBirdOnline) (registry.lookup("OnlineMethods"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
