/**
 * 
 */
package dotsboxes.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import dotsboxes.rmi.exceptions.ConnectionAlreadyEstablished;
import dotsboxes.utils.Debug;

/**
 *
 *
 */
public class NodeRegisterImpl implements NodeRegister 
{
	public NodeRegisterImpl(ConnectionManager manager) 
	{
		m_Manager = manager;
	}

	@Override
	public EventTransmitter register(EventTransmitter remote_transmitter) throws RemoteException, ConnectionAlreadyEstablished
	{
		Connection connection = new Connection();
		connection.setRemoteEventTransmitter(remote_transmitter);
		
		m_Manager.accept_new_connection(connection);
		
		return connection.getLocalEventTransmitter();
	}

	public ConnectionManager m_Manager;
}
