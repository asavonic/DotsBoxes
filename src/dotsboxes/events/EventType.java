package dotsboxes.events;

public enum EventType 
{
//Events for GUI:
	GUI_game_Turn,              //game turn for GUI.
	
	GUI_back_to_Menu,           // internal GUI event.
	//GUI_to_the_Game,            // internal GUI event to .
	GUI_game_exit,              // player press button "Exit" from Menu.
	
	GUI_current_player_changed, // event for GUI that pass to Field current player desc.
	
	GUI_New_Game_Request,       // player press button "Create game" and set width, height, number of local and remote players.
	
	GUI_New_Game_Accept,        // player press button "Join to the game" and set number of local players.
	
	GUI_game_Start,             // event for GUI that tell "Game inited and you can show Field!".
	
	GUI_game_over,              // event for GUI that tell "All edges and square busy and win player number ...!".

	
// Game mechanic events:
	
	game_Turn,                  // this event contained necessary in info about what player do with field on him turn.
	
	game_Start,                 // if we host then send this event to all remote players 
								// else we get it from host and set game property.	
	
	local_Current_Player_Change,  // this event emits when player changed and new player is local player.
	
	turn_unlock,                // if now current player have got turn we send turn unlock for GameSession. 
	
	show_history,               // event that send to GUI all game turns or reverted game turns round.
	
	
// For remote connection events:
	
	remote_New_Game_Request,	// this event we get when remote player create new game and want remote players.
								// or we send this event all known IP when player want game with remote players.
	
	remote_New_Game_Accept,		// this event we get when remote player accept on our request.
								// or we send this event when player press button "Join to the game".
	
//Other events:
	
	sleep_event,                 // event that tell EventManager " Go to sleep a little.".
	
	Generic						 // special type for EventManager. Don't use.
}
