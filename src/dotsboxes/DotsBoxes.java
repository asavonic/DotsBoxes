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
		try {
			System.out.println("Hello World");
			int port = Integer.parseInt(args[0]);
			Configuration.setPort(port);
			Configuration.setKnownPlayersFilepath( args[1] );
			
			ConnectionManager.Init(port);
			EventManager.Init();
			
			SessionManager smanager = new SessionManager();
			smanager.Run();
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}

}
