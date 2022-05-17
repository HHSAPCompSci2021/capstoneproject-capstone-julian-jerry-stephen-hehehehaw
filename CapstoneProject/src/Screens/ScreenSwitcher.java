package Screens;


public interface ScreenSwitcher {
	public static final int MENU_SCREEN = 0;
	public static final int GAMEMODE_SELECTION_SCREEN = 1;
	public static final int WEAPON_SELECTION_SCREEN = 2;
	public static final int GAME_SCREEN = 3;
	public static final int INSTRUCTION_SCREEN = 4;
	public static final int DEATH_SCREEN = 5;

//	public static final int AVATAR_SELECTION_SCREEN = 6;
	
	public void switchScreen(int i);
}
