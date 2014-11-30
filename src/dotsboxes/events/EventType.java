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
	GUI_game_Start,
	
	playersList,
	
	turn_unlock,
	
	gui_New_Game_Request,
	remote_New_Game_Request,
	remote_New_Game_Accept,
	
	internal_Current_Player_Change
}
