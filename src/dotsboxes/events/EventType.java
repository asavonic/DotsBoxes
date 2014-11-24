package dotsboxes.events;

public enum EventType 
{
	Generic,	
	
	GUI_game_Turn,
	game_Turn,
	game_Start,
	game_Start_GUI_Request,
	
	ConnectionHandshake,
	ConnectionPing,
	ConnectionClose,
	
	NewGameRequest,
	
	remote_New_Game_Request,
	remote_New_Game_Accept
}
