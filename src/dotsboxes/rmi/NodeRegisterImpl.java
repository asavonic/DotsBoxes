/**
 * 
 */
package dotsboxes.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import dotsboxes.players.PlayerDesc;
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
	public EventTransmitter register(EventTransmitter remote_transmitter, PlayerDesc remote_player ) throws RemoteException, ConnectionAlreadyEstablished
	{
		Connection connection = new Connection();
		connection.setRemoteEventTransmitter(remote_transmitter);
		connection.setRemotePlayerDesc(remote_player);
		
		ConnectionManager.accept_new_connection(connection);
		
		return connection.getLocalEventTransmitter();
	}
}
