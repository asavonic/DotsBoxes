/**
 * 
 */
package dotsboxes.rmi;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dotsboxes.Debug;

/**
 *
 *
 */
public class ConnectionManager {
	public ConnectionManager() throws AccessException, RemoteException, AlreadyBoundException
	{
		register = new NodeRegisterImpl(this);
		
		NodeRegister stub = (NodeRegister)UnicastRemoteObject.exportObject(register, 0);
		int port = 12345;
		
		registry = LocateRegistry.createRegistry(port);
  		registry.bind("ClientRegister", (Remote) stub);
  		Debug.log("ConnectionManager init done. Server running on port " + port );
	}
	
	public List<Connection> m_ActiveConnections;
	public List<EventTransmitter> m_PendingTransmittors;
	
	private NodeRegisterImpl register;
	private Registry registry;
	
	public void accept_remote_transmitter(EventTransmitter remote_transmitter) 
	{
		m_PendingTransmittors.add(remote_transmitter);
		Debug.log("Add pending transmitter");
	}
}
