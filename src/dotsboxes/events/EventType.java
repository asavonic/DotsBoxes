package dotsboxes.events;

public enum EventType 
{
	Generic,	
	
	GUI_game_Turn,
	GUI_back_to_Menu,
	GUI_to_the_Game,
	game_Turn,
	game_Start,
	game_Start_GUI_Request,
	
	
	ConnectionHandshake,
	ConnectionPing,
	ConnectionClose,
	
	NewGameRequest,
	
	gui_New_Game_Request,
	remote_New_Game_Request,
	remote_New_Game_Accept
}
