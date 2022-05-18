package FireBaseStuff;

/**
 * 
 *  The class you store in the database must fit 3 simple constraints:

	The class must have a default constructor that takes no arguments.
	The class must define public getters for the properties to be assigned. Properties without a public getter will be set to their default value when an instance is deserialized.
	The class can only have fields that are:
		- primitive data types
		- ArrayLists (not arrays)
		- Objects of classes that follow these same rules.

	Classes from the Java library will often not fit these requirements, so you may need to make simpler classes
	yourself.



	I recommend that you *only* use this class for database posts. Don't use this class for storing
	real data in your program. Just create these objects at the moment you want to put something in the
	database, and when you read from the database, quickly turn these objects into some other form that
	is more useful.


 * 
 * 
 * @author john_shelby
 *
 */
public class PlayerData {
	
	public float worldX, worldY, screenX, screenY;
	public boolean player2;
	public int weapon; //0-3
	public double health;
	public double speed;
	public boolean east, west, north, south;
	
	
	public boolean gameDecision; 
	//get this from the screen class that holds it, if both are the same then execute the game, else do nothing
//	public boolean emote;
	//need to check for bullet collisions with player2, store the damage dealt in here
	//need to check for picking up a powerup and remove that tile, doesnt have to be done in here
	
	// One thing that is interesting is that the Firebase database cannot store arrays.
	// So, if you want to use a library class that uses arrays (the Color class is one such example), then
	// you need to store the data a different, simpler way yourself.
	// Note that ArrayLists *can* be stored.
	
	public PlayerData() {
		
	}
	
	public double getX() {
		return worldX;
	}

	public double getY() {
		return worldY;
	}

	
}
