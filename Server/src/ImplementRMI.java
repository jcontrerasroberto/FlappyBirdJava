

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;

/*
 * CLASE ImplementRMI
 * 
 * Esta clase es la implementación de los métodos RMI declarados en la interfaz FlappyBirdOnline
 * 
 * */

public class ImplementRMI extends UnicastRemoteObject implements FlappyBirdOnline{
	
	private HashMap<String, Player> players = new HashMap<String, Player>(); //HashMap donde se guardan los jugadores de una partida
	private int numberOfPlayers =0; //Numero de jugadores en partida
	
	//Constructor de la clase. Solo indica cuando se crea
	public ImplementRMI() throws RemoteException, AlreadyBoundException{
		System.out.println("Creación de la implementación");
	}
	
	/* 
	 * public boolean getPermission(String nickname)
	 * 
	 * Método que sirve para que el cliente pida permiso para entrar a la partida enviando como parametro el nickname. 
	 * Retorna true si se agrega el jugador a la partida 
	 * Retorna false si hay un error añadiendo al jugador
	 * 
	 * */
	@Override
	public boolean getPermission(String nickname){
		String ip = "";
		try{
			ip = getClientHost().toString(); //Se obtiene la IP del cliente
			System.out.println("Request from "+ip);
			return addPlayer(nickname, ip); //llamada al método addPlayer enviandole el nickname y la IP
		}catch(Exception e){
			System.out.println("Unable to get ip");
			return false;
		}
	}
	
	/*
	 * private boolean addPlayer(String nickname, String ip)
	 * 
	 * Añade un jugador players si el nickname no esta en uso
	 * Cuando se une un jugador aumenta el numero de jugadores
	 * 
	 * */ 
	private boolean addPlayer(String nickname, String ip){
		if(!players.containsKey(nickname)){ //Revisa si el nickname no esta en uso
			players.put(nickname, new Player(nickname, ip, 0, true)); //Se añade al jugador a la partida
			System.out.println(nickname);
			numberOfPlayers++; //Aumento número de jugadores
			return true;
		}else return false;
	}
	
	/*
	 * public Player getPlayer(String nickname)
	 * 
	 * Le da al cliente un jugador que corresponda a su nickname para obtener los nuevos datos de este
	 * 
	 * */
	@Override
	public Player getPlayer(String nickname){
		if(players.containsKey(nickname)) return players.get(nickname); //Si el nickname existe en la partida devuelve los datos del jugador
		else return null; //Si no existe, retorna null
	}
	
	/*
	 * public void setPlayer(Player p)
	 * 
	 * Actualiza los datos del jugador del parametro recibido
	 * 
	 * */
	@Override
	public void setPlayer(Player p){
		if(players.containsKey(p.getNickname())) players.put(p.getNickname(), p); //Si existe el jugador p modifica sus valores
	}
	
	/*
	 * public HashMap<String, Player> getPlayers()
	 * 
	 * Le retorna al cliente el hashmap de los jugadores
	 * 
	 * */
	@Override
	public HashMap<String, Player> getPlayers(){
		return players;
	}

	/*
	 * public boolean keepGaming()
	 * 
	 * Esta función es para ver si aun existen jugadores vivos, sí no para decirle al cliente que el juego acabó 
	 * Retorna true si hay minimo un jugador vivo, false si todos estan muertos
	 * 
	 * */
	@Override
	public boolean keepGaming(){
		int alive=0;
		for(Player p : players.values()){
			if(p.getAlive()) alive++; //Su el jugador sigue vivo aumenta el contador
		}
		return alive>0; 
	}
	
	/* 
	 * public Player getWinner()
	 * 
	 * Cuando el juego acaba, busca al jugador con el mayor score y enviarselo al cliente
	 * 
	 * */
	@Override
	public Player getWinner(){
		if(!keepGaming()){
			Player temp = null; //Aquí se guarda al jugador con mayor score
			for(Player p : players.values()){
				if(temp==null) temp=p;
				if(p.getScore()>temp.getScore()) temp=p; //Si un nuevo jugador tiene más puntos que el pasado se convierte en el ganador temporal
			}
			System.out.println("El ganador es "+temp.getNickname());
			return temp;
		}else return null;
	}

	/*
	 * public boolean saveOnlineGame(Player winner)
	 * 
	 * Método para guardar los datos de la partida en la base de datos
	 * 
	 * */
	@Override
	public boolean saveOnlineGame(Player winner){
		DBProcess db = new DBProcess();
		try {
			boolean ok = db.insertGame(winner.getNickname(), winner.getScore(), numberOfPlayers); //Se insertan los datos
			db.close(); //Se cierra la conexión
			numberOfPlayers=0;
			return ok;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * public void exitGame(Player p)
	 * 
	 * Método para remover a un jugador del juego. 
	 * 
	 * */
	@Override
	public void exitGame(Player p){
		players.remove(p.getNickname()); //Quita a un jugador de la martida
		System.out.println("Salió "+p.getNickname());
	}
}
