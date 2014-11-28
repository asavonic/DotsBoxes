package dotsboxes.events;

public enum EventType 
{
	Generic,	
	
	GUI_game_Turn,
	GUI_back_to_Menu,
	GUI_to_the_Game,
	GUI_game_exit,
	game_Turn,
	game_Start,
	game_Start_GUI_Request,
	
	
	ConnectionHandshake,
	ConnectionPing,
	ConnectionClose,
	
	NewGameRequest
}
