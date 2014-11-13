/**
 * 
 */
package dotsboxes.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 *
 */
public interface NodeRegister extends Remote {
	public void register (EventTransmitter transmitter) throws RemoteException;
}
