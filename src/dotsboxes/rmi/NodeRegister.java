/**
 * 
 */
package dotsboxes.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import dotsboxes.rmi.exceptions.ConnectionAlreadyEstablished;

/**
 *
 *
 */
public interface NodeRegister extends Remote {
	public EventTransmitter register (EventTransmitter transmitter) throws RemoteException, ConnectionAlreadyEstablished;
}
