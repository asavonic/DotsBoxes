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
	public NodeRegisterImpl() 
	{
	}

	@Override
	public EventTransmitter register(EventTransmitter remote_transmitter) throws RemoteException, ConnectionAlreadyEstablished
	{
		Connection connection = new Connection();
		connection.setRemoteEventTransmitter(remote_transmitter);
		
		ConnectionManager.accept_new_connection(connection);
		
		return connection.getLocalEventTransmitter();
	}
}
