package Screens;


public interface ScreenSwitcher {
	public static final int MENU_SCREEN = 0;
	public static final int PRE_GAME_SCREEN = 1;
	public static final int GAME_SCREEN = 2;
	public static final int INSTRUCTION_SCREEN = 3;
	
	public void switchScreen(int i);
}
