package dotsboxes;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;

import dotsboxes.players.PlayerDesc;
import dotsboxes.rmi.ConnectionManager;
import dotsboxes.rmi.FixedPortRMISocketFactory;
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
			RMISocketFactory.setSocketFactory(new FixedPortRMISocketFactory());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Hello World");
			int port = Integer.parseInt(args[0]);
			Configuration.setPort(port);
			Configuration.setKnownPlayersFilepath( args[1] );
			Configuration.setAddress( InetAddress.getByName("127.0.0.1") );
			
			ConnectionManager.Init(port);
			EventManager.Init();
			
			SessionManager smanager = new SessionManager();
			EventManager.ProcessEvents();
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
