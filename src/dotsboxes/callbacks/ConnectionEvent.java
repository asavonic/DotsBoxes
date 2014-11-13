package dotsboxes.callbacks;

import dotsboxes.Debug;
import dotsboxes.Event;

public class ConnectionEvent implements EventCallback
{
	public void new_event( Event event)
	{
		Debug.log("Recieved connection event.");
	}
}
