/**
 * 
 */
package dotsboxes.rmi;

import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 *
 */
public class Connection 
{
	Connection(InetAddress address, int port) throws RemoteException, NotBoundException 
	{
		Registry registry = LocateRegistry.getRegistry(address.getHostAddress(), port);
		NodeRegister remote_register = (NodeRegister)registry.lookup("NodeRegister");
		
		EventTransmitterImpl local_event_transmitter = new EventTransmitterImpl();
		m_LocalEventTransmitter = (EventTransmitter)UnicastRemoteObject.exportObject((Remote) local_event_transmitter, 0);
		
		remote_register.register(local_event_transmitter);
	}
	
	public EventTransmitter m_LocalEventTransmitter;
	public EventTransmitter m_RemoteEventTransmitter;
	public InetAddress address;
	public int port;
}
