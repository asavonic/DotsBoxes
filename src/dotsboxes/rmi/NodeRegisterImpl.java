/**
 * 
 */
package dotsboxes.rmi;

import dotsboxes.Debug;

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
		Debug.log("NodeRegisterImpl.register(): pushing new remote transmitter");
		m_Manager.accept_remote_transmitter(transmitter);
	}

	public ConnectionManager m_Manager;
}
