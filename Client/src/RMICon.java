import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class RMICon {
	
	private FlappyBirdOnline inter;
	
	public RMICon(){
		String serverAddres = "192.168.42.150";
		int serverPort = 9999;

		try {
			Registry registry = LocateRegistry.getRegistry(serverAddres, serverPort);
			inter = (FlappyBirdOnline) (registry.lookup("OnlineMethods"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try{
			inter.getPermission("My IP");
		}catch(Exception e){
		}
		
	}
	
	public static void main(String[] args) {
		new RMICon();
	}
}
