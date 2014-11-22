/**
 * 
 */
package dotsboxes.rmi;

import dotsboxes.events.Event;
import dotsboxes.utils.Debug;
import dotsboxes.EventManager;

/**
 *
 *
 */
public class EventTransmitterImpl implements EventTransmitter {

	@Override
	public void transmit(Event event) {
		Debug.log("Event recieved");
		EventManager.NewEvent(event);
	}

}
