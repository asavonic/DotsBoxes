/**
 * 
 */
package dotsboxes.rmi;

import dotsboxes.Debug;
import dotsboxes.Event;

/**
 *
 *
 */
public class EventTransmitterImpl implements EventTransmitter {

	@Override
	public void transmit(Event event) {
		Debug.log("Event recieved");
	}

}
