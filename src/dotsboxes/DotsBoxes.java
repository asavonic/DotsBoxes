package dotsboxes;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
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
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
		System.out.println("Hello World");
		smanager.Delete();
	}

}
