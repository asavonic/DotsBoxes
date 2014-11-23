package dotsboxes.events;

public enum EventType 
{
	Generic,	
	
	GUI_game_Turn,
	game_Turn,
	game_Start,
	
	ConnectionHandshake,
	ConnectionPing,
	ConnectionClose,
	
	NewGameRequest
}
