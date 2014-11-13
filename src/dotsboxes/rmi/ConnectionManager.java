/**
 * 
 */
package dotsboxes.rmi;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import dotsboxes.Debug;
import dotsboxes.Event;
import dotsboxes.EventType;
import dotsboxes.PlayerDesc;

/**
 *
 *
 */
public class ConnectionManager {
	public ConnectionManager() throws AccessException, RemoteException, AlreadyBoundException
	{
		register = new NodeRegisterImpl(this);
		
		NodeRegister stub = (NodeRegister)UnicastRemoteObject.exportObject(register, 0);
		m_Port = 10000;
		registry = LocateRegistry.createRegistry(m_Port);
  		registry.bind("NodeRegister", (Remote) stub);
  		Debug.log("ConnectionManager init done. Server running on port " + m_Port );
	}
	
	public void Delete()
	{
		Debug.log("ConnectionManager: deleted.");
	}
	
	public void CreateConnection(PlayerDesc remote_player_desc) throws RemoteException, NotBoundException
	{
		Connection new_connection = new Connection();
		m_ActiveConnections = new LinkedList<Connection>();
		m_PendingConnections = new LinkedList<Connection>();
		m_PendingTransmittors = new LinkedList<EventTransmitter>();
		
		new_connection.Connect(remote_player_desc.getInetAdress(), remote_player_desc.getPort());
		m_PendingConnections.add(new_connection);
	}
	
	public List<Connection> m_ActiveConnections;
	public List<Connection> m_PendingConnections;
	public List<EventTransmitter> m_PendingTransmittors;
	private int m_Port;
	
	private NodeRegisterImpl register;
	private Registry registry;
	
	public void accept_remote_transmitter(EventTransmitter remote_transmitter)
	{
		m_PendingTransmittors.add(remote_transmitter);
		Debug.log("Add pending transmitter");
		try {
			remote_transmitter.transmit(new Event(EventType.ConnectionHandshake));
		} catch (RemoteException e) {
			m_PendingTransmittors.remove(remote_transmitter);
			Debug.log("Remove pending transmitter");
			e.printStackTrace();
		}
		
	}
}
