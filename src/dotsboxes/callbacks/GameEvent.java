package dotsboxes.callbacks;

import dotsboxes.Debug;
import dotsboxes.Event;

public class GameEvent implements EventCallback
{
	public void new_event( Event event)
	{
		Debug.log("Recieved game event.");
	}
}
