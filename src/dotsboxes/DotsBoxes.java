package dotsboxes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dotsboxes.players.PlayerDesc;
import dotsboxes.rmi.ConnectionManager;
import dotsboxes.rmi.exceptions.ConnectionAlreadyEstablished;
import dotsboxes.utils.Debug;

public class DotsBoxes {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int port = Integer.parseInt(args[0]);
		Configuration.setPort(port);
		Configuration.setKnownPlayersFilepath( "known_players.conf" );
		SessionManager smanager = new SessionManager();
		EventManager.Init();
		try {
			ConnectionManager conn_manager = new ConnectionManager(port);
			
			PlayerDesc remote_player = new PlayerDesc();
			remote_player.setInetAdress(InetAddress.getLocalHost());
			remote_player.setPort(10000);
			
			conn_manager.connect(remote_player);
			
		} catch (RemoteException | AlreadyBoundException | NotBoundException | ConnectionAlreadyEstablished e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			Debug.log("Exception: host not found");
		}
		
		System.out.println("Hello World");
		smanager.Delete();
		System.exit(0);
	}

}
