package dotsboxes.events;

public enum EventType 
{
	Generic,	
	
	GUI_game_Turn,
	GUI_back_to_Menu,
	GUI_to_the_Game,
	GUI_game_exit,
	GUI_mark_set,
	GUI_edge_set,
	game_Turn,
	game_Start,
	game_Start_GUI_Request,
	
	
	ConnectionHandshake,
	ConnectionPing,
	ConnectionClose,
	
	NewGameRequest,
	
	gui_New_Game_Request,
	gui_New_Game_Accept,
	remote_New_Game_Request,
	remote_New_Game_Accept,
	
	internal_Current_Player_Change
}
