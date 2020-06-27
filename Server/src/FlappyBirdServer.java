 
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;

public class FlappyBirdServer extends UnicastRemoteObject {
	
	private static final int PUERTO=9999;

	public FlappyBirdServer() throws RemoteException, AlreadyBoundException{
		startServer();
	}

	public void startServer() throws RemoteException, AlreadyBoundException{
		System.setProperty("java.rmi.server.hostname","192.168.137.87");
		ImplementRMI send = new ImplementRMI();
		Registry registry = LocateRegistry.createRegistry(PUERTO);
		registry.bind("OnlineMethods", (FlappyBirdOnline) send);
		System.out.println("Waiting for clients");
	}

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		new FlappyBirdServer();
	}
}
