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
import dotsboxes.players.PlayerDesc;
import dotsboxes.rmi.exceptions.ConnectionAlreadyEstablished;
import dotsboxes.utils.Debug;

/**
 *
 *
 */
public final class ConnectionManager {
	public ConnectionManager(int port) throws AccessException, RemoteException, AlreadyBoundException
	{
		Init(port);
	}
	
	public static void Init(int port) throws AccessException, RemoteException, AlreadyBoundException
	{
		Debug.log("ConnectionManager init...");
		m_ActiveConnections = new LinkedList<Connection>();
		register = new NodeRegisterImpl();
		
		NodeRegister stub = (NodeRegister)UnicastRemoteObject.exportObject(register, 0);
		m_Port = port;
		registry = LocateRegistry.createRegistry(m_Port);
  		registry.bind("NodeRegister", (Remote) stub);
  		Debug.log("ConnectionManager init done. Server running on port " + m_Port );
	}
	
	public static Connection connect(PlayerDesc remote_player_desc) throws RemoteException, NotBoundException, ConnectionAlreadyEstablished
	{
		Connection new_connection = new Connection();
		new_connection.Connect(remote_player_desc);
		return new_connection;
	}
	
	public static Connection getConnection(PlayerDesc player)
	{
		for ( Connection connection : m_ActiveConnections) {
			if ( connection.getRemotePlayerDesc() == player ) {
				return connection;
			}
		}
		
		return null;
	}
	
	public static List<Connection> m_ActiveConnections;
	private static int m_Port;
	
	private static NodeRegisterImpl register;
	private static Registry registry;
	
	public static void accept_new_connection(Connection new_connection) throws ConnectionAlreadyEstablished
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
