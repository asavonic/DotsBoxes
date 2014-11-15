package dotsboxes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dotsboxes.rmi.ConnectionManager;

public class DotsBoxes {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		SessionManager smanager = new SessionManager();
		
		try {
			ConnectionManager conn_manager = new ConnectionManager();
			
			PlayerDesc remote_player = new PlayerDesc();
			remote_player.setInetAdress(InetAddress.getLocalHost());
			remote_player.setPort(10000);
			
			conn_manager.CreateConnection(remote_player);
			
		} catch (RemoteException | AlreadyBoundException | NotBoundException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			Debug.log("Exception: host not found");
		}
		
		System.out.println("Hello World");
		smanager.Delete();
		System.exit(0);
	}

}
