package dotsboxes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dotsboxes.players.PlayerDesc;
import dotsboxes.rmi.ConnectionManager;
import dotsboxes.rmi.exceptions.ConnectionAlreadyEstablished;
import dotsboxes.utils.Configuration;
import dotsboxes.utils.Debug;

public class DotsBoxes {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println("Hello World");
		int port = Integer.parseInt(args[0]);
		Configuration.setPort(port);
		Configuration.setKnownPlayersFilepath( args[1] );
		SessionManager smanager = new SessionManager();
		EventManager.Init();
		try {
			ConnectionManager.Init(port);
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
			
		smanager.Delete();
		System.exit(0);
	}

}
