package Instructions;

import java.awt.Color;
import java.util.ArrayList;

import Players.Player;
//import Players.PlayerHUD;
import Screens.*;
import Tiles.TileManager;
import Weapons.Bullet;
import Weapons.Shotgun;
import Weapons.Sniper;
import Weapons.Submachine;
import processing.core.PApplet;
import processing.core.PImage;

public class World extends Screen {
	
	public final static String fileSeparator = System.getProperty("file.separator");
	public final static String lineSeparator = System.getProperty("line.separator");
	public final static String userDir = System.getProperty("user.dir");
	private PApplet p;
	
//put walls around the map to add borders
	private int tileGrid[][] = {{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},


{1, 2, 4, 4, 3, 2, 0, 4, 0, 3, 1, 1, 2, 3, 4, 0, 2, 1, 4, 0, 4, 3, 1, 1, 0, 0, 0, 3, 0, 2, 0, 4, 3, 2, 4, 2, 0, 1, 1, 3, 3, 0, 0, 1, 3, 2, 1, 0, 0, 2, 5, 7, 10, 9, 8, 8, 10, 10, 7, 9, 9, 8, 10, 8, 8, 9, 8, 7, 10, 5, 5, 6, 7, 5, 10, 5, 8, 8, 9, 5, 8, 7, 10, 9, 9, 7, 7, 10, 6, 8, 9, 7, 6, 5, 5, 10, 10, 8, 9, 6},


{2, 2, 3, 0, 1, 0, 3, 0, 4, 2, 1, 4, 1, 0, 3, 0, 2, 1, 0, 2, 3, 0, 3, 0, 1, 4, 2, 3, 0, 4, 2, 1, 0, 2, 2, 2, 0, 0, 4, 1, 1, 1, 2, 3, 4, 2, 0, 0, 4, 1, 6, 8, 9, 8, 7, 7, 9, 5, 6, 8, 10, 7, 9, 7, 8, 9, 10, 10, 8, 9, 5, 5, 6, 7, 8, 10, 10, 9, 9, 8, 5, 7, 8, 9, 10, 8, 7, 7, 6, 5, 5, 6, 7, 10, 8, 9, 5, 7, 5, 10},


{3, 2, 2, 0, 1, 4, 0, 3, 1, 2, 2, 0, 0, 4, 4, 4, 3, 2, 2, 1, 2, 0, 4, 2, 2, 0, 0, 4, 1, 0, 1, 0, 2, 1, 3, 4, 1, 0, 0, 2, 2, 0, 3, 3, 3, 0, 4, 2, 1, 3, 7, 6, 8, 9, 8, 8, 10, 6, 7, 10, 7, 8, 9, 7, 6, 10, 10, 9, 5, 6, 6, 7, 8, 10, 9, 5, 6, 6, 10, 7, 8, 8, 9, 6, 10, 5, 6, 6, 7, 7, 8, 7, 9, 10, 5, 9, 10, 10, 8, 5},


{4, 1, 1, 2, 2, 0, 0, 3, 4, 0, 3, 2, 3, 1, 0, 0, 0, 3, 4, 1, 2, 0, 1, 4, 3, 0, 2, 2, 0, 1, 1, 1, 2, 4, 0, 2, 0, 3, 2, 3, 4, 4, 1, 0, 0, 1, 1, 2, 3, 4, 8, 9, 10, 10, 7, 6, 6, 9, 5, 10, 7, 8, 8, 9, 5, 5, 6, 8, 9, 8, 7, 7, 5, 7, 6, 10, 8, 8, 9, 10, 5, 7, 8, 10, 9, 8, 5, 6, 9, 10, 6, 7, 5, 8, 8, 9, 7, 5, 9, 10},




};
	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
//	private PlayerHUD hud = new PlayerHUD();
	TileManager tM = new TileManager(16, 5, tileGrid);
	

	int maxScreenCol = 16;
	int maxScreenRow = 9;
	public int screenWidth = maxScreenCol * tM.getTileSize();
	public int screenHeight = maxScreenRow * tM.getTileSize();
	
	private PImage[] playerImage = new PImage[8];
	private PImage[] tileImage = new PImage[14]; //should be 17 once the remaining tile sprites for traps are made
	private final int maxWorldCol = 100;
	private final int maxWorldRow = 100;
	private final int worldWidth = maxWorldCol * tM.getTileSize();
	private final int worldHeight = maxWorldRow * tM.getTileSize();
	
	Player player;
	
	public World(PApplet p) {
		this.p = p;
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		
//		frameRate(999);

		playerImage[0] = p.loadImage("Assets" + fileSeparator + "BlueAvatar" + fileSeparator + "Backwards1.png");
		playerImage[1] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Forward1.png");
		playerImage[2] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Backwards2.png");
		playerImage[3] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Forward2.png");
		playerImage[4] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Left1.png");
		playerImage[5] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Right1.png");
		playerImage[6] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Left2.png");
		playerImage[7] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Right2.png");

		player =  new Player(screenWidth/2 - tM.getTileSize()/2, screenHeight/2 - tM.getTileSize()/2, tM.getTileSize() * 20, tM.getTileSize() *2, p, playerImage);
		player.setWeapon(new Sniper());
		player.setWeapon(new Shotgun());

		tileImage[0] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "redbrick1.png");
		tileImage[1] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "redbrick2.png");
		tileImage[2] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "redbrick3.png");
		tileImage[3] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "redbrick4.png");
		tileImage[4] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "redbrick5.png");
		
		
		tileImage[5] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "redbrickwall.png");
		tileImage[6] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonewall1.png");
		tileImage[7] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonewall2.png");
	
		tileImage[8] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonebrick1.png");
		tileImage[9] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonebrick2.png");
		tileImage[10] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonebrick3.png");
		tileImage[11] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonebrick4.png");
		tileImage[12] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonebrick5.png");
		tileImage[13] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonebrick6.png");
			
		
		
		tM.setTiles(tileImage);
		
	}
	
	
	public void draw() { 
		System.out.println(p.frameRate);
		
		
		p.background(220,220,220);  
//		fill(255);
		p.textAlign(p.CENTER);

		
		tM.draw(p, player);
		player.draw(p);
		
		p.push();
		for(Bullet b : bullets)
		{
			p.fill(0, 255, 0);
			b.draw(p);
		}
		p.pop();
		

		player.draw(p);
		if(player.getWeapon().getAmmo() == 0)
		{
			player.getWeapon().reload();
		}
		
		
	
//		hud.draw(this, screenWidth, screenHeight, player, new Player(screenWidth-screenWidth/10 - tM.getTileSize()/2, 3*screenHeight/4 - tM.getTileSize()/2, 0, tM.getTileSize() * 20, this, playerImage));
	
		
		
//		noFill();
//		rect(player.getRectangle().x, player.getRectangle().y, player.getRectangle().width, player.getRectangle().height);
	}
			 
	public	void keyPressed() {
			  final int k = p.keyCode;
			  player.setDirection(k, true);
			  player.avatar.setDirection(k, true);
			}
			 
	public void keyReleased() {
			  player.setDirection(p.keyCode, false);
			}


		
	public void mousePressed() {
		
		
		for(Bullet b : player.shoot(p.mouseX, p.mouseY))
		{
			bullets.add(b);
		}
	}
	
}

