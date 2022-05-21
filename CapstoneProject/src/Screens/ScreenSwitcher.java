package Screens;

/**
 * Represents the ability to switch screens
 * @author Stephen
 */
public interface ScreenSwitcher {
	/** Integer representing a MenuScreen */
	public static final int MENU_SCREEN = 0;
	/** Integer representing a GamemodeSelectionScreen */
	public static final int GAMEMODE_SELECTION_SCREEN = 1;
	/** Integer representing a WeaponSelectionScreen */
	public static final int WEAPON_SELECTION_SCREEN = 2;
	/** Integer representing a GameScreen */
	public static final int GAME_SCREEN = 3;
	/** Integer representing a Instructions screen */
	public static final int INSTRUCTION_SCREEN = 4;
	/** Integer representing a DeathScreen */
	public static final int DEATH_SCREEN = 5;
	
	/**
	 * Switches the screen to the given screen (as specified in the ScreenSwitcher class)
	 * @param i - the screen to switch to
	 */
	public void switchScreen(int i);
}
