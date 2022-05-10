package Screens;


public interface ScreenSwitcher {
	public static final int MENU_SCREEN = 0;
	public static final int GAME_SCREEN = 1;
	
	public void switchScreen(int i);
}
