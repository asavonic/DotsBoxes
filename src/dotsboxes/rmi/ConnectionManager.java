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

import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.events.SuppStructs.PlayerDesc;
import dotsboxes.rmi.exceptions.ConnectionAlreadyEstablished;
import dotsboxes.utils.Debug;

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
	
	public void connect(PlayerDesc remote_player_desc) throws RemoteException, NotBoundException, ConnectionAlreadyEstablished
	{
		Connection new_connection = new Connection();
		m_ActiveConnections = new LinkedList<Connection>();
		
		new_connection.Connect(remote_player_desc.getInetAdress(), remote_player_desc.getPort());
	}
	
	public List<Connection> m_ActiveConnections;
	private int m_Port;
	
	private NodeRegisterImpl register;
	private Registry registry;
	
	public void accept_new_connection(Connection new_connection) throws ConnectionAlreadyEstablished
	{
		for ( int i = 0; i < m_ActiveConnections.size(); i++) {
			if (m_ActiveConnections.get(i).getRemoteEventTransmitter() == new_connection.getRemoteEventTransmitter()) {
				throw new ConnectionAlreadyEstablished();
			}
		}
		
		m_ActiveConnections.add(new_connection);
		
		Debug.log("ConnectionManager: new connection accepted");
	}
}
