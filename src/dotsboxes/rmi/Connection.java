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

import dotsboxes.Debug;
import dotsboxes.Event;
import dotsboxes.PlayerDesc;
/**
 *
 *
 */
public class Connection 
{
	public Connection() throws RemoteException
	{
		EventTransmitterImpl local_event_transmitter = new EventTransmitterImpl();
		m_LocalEventTransmitter = (EventTransmitter)UnicastRemoteObject.exportObject((Remote) local_event_transmitter, 0);
	}
	
	public void Connect(InetAddress address, int port) throws RemoteException, NotBoundException 
	{
		Debug.log("Connection.Connect(): trying to connect");
		Registry registry = LocateRegistry.getRegistry(address.getHostAddress(), port);
		NodeRegister remote_register = (NodeRegister)registry.lookup("NodeRegister");
		
		remote_register.register(m_LocalEventTransmitter);
		Debug.log("Connection.Connect(): registration sent");
	}
	
	public void send_event(Event event) throws RemoteException 
	{
		m_RemoteEventTransmitter.transmit(event);
	}
	
	public EventTransmitter getLocalEventTransmitter() {
		return m_LocalEventTransmitter;
	}

	public void setLocalEventTransmitter(EventTransmitter localEventTransmitter) {
		m_LocalEventTransmitter = localEventTransmitter;
	}

	public EventTransmitter getRemoteEventTransmitter() {
		return m_RemoteEventTransmitter;
	}

	public void setRemoteEventTransmitter(EventTransmitter remoteEventTransmitter) {
		m_RemoteEventTransmitter = remoteEventTransmitter;
	}

	public PlayerDesc getRemotePlayerDesc() {
		return m_RemotePlayerDesc;
	}

	public void setRemotePlayerDesc(PlayerDesc remotePlayerDesc) {
		m_RemotePlayerDesc = remotePlayerDesc;
	}

	private EventTransmitter m_LocalEventTransmitter;
	private EventTransmitter m_RemoteEventTransmitter;
	private PlayerDesc m_RemotePlayerDesc; 
}
