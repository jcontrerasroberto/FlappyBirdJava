import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface FlappyBirdOnline extends Remote{
	public boolean getPermission(String nickname) throws RemoteException;
	public Player getPlayer(String nickname) throws RemoteException;
	public void setPlayer(Player p) throws RemoteException;
	public HashMap<String, Player> getPlayers() throws RemoteException;
	public boolean keepGaming() throws RemoteException;
	public Player getWinner() throws RemoteException;
	public void exitGame(Player p) throws RemoteException;
	public boolean saveOnlineGame(Player winner) throws RemoteException;
}
