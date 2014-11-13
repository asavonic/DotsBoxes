/**
 * 
 */
package dotsboxes.rmi;

import java.rmi.Remote;

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
	public void register(EventTransmitter transmitter) 
	{
		m_Manager.accept_remote_transmitter(transmitter);
	}

	public ConnectionManager m_Manager;
}
