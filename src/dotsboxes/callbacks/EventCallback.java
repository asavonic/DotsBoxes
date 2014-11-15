package dotsboxes.callbacks;

import dotsboxes.events.Event;

public interface EventCallback 
{
	//public void new_game_event( Event event);
	//public void new_connect_event( Event event);
	abstract public void HandleEvent( Event event);
}