/**
 * 
 */
package dotsboxes.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import dotsboxes.events.Event;

/**
 *
 *
 */
public interface EventTransmitter extends Remote {
	public void transmit(Event event) throws RemoteException;
}
