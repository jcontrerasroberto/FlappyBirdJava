import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;

public class ImplementRMI extends UnicastRemoteObject implements FlappyBirdOnline{
	
	private HashMap<String, Player> players = new HashMap<String, Player>();
	
	public ImplementRMI() throws RemoteException, AlreadyBoundException{
		System.out.println("Creación de la implementación");
	}
	
	@Override
	public boolean getPermission(String nickname){
		String ip = "";
		try{
			ip = getClientHost().toString();
		}catch(Exception e){
			System.out.println("Unable to get ip");
		}
		return addPlayer(nickname, ip);
	}
	
	
	private boolean addPlayer(String nickname, String ip){
		if(!players.containsKey(nickname)){
			players.put(nickname, new Player(nickname, ip, 0, true));
			return true;
		}else return false;
	}
	
	@Override
	public Player getPlayer(String nickname){
		return players.get(nickname);
	}
	
	@Override
	public void setPlayer(Player p){
		if(players.containsKey(p.getNickname())) players.put(p.getNickname(), p);
	}
	
	@Override
	public HashMap<String, Player> getPlayers(){
		return players;
	}

	@Override
	public boolean keepGaming(){
		int alive=0;
		for(Player p : players.values()){
			if(p.getAlive()) alive++;
		}
		return alive>0;
	}
	
	@Override
	public Player getWinner(){
		if(!keepGaming()){
			Player temp = null;
			for(Player p : players.values()){
				if(temp==null && p.getScore()>0) temp=p;
				if(p.getScore()>temp.getScore() && p.getScore()>0) temp=p;
			}
			return temp;
		}else return null;
	}
}
