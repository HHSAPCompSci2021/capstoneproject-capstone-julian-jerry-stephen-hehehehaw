package FireBase;


import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JOptionPane;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import processing.core.PApplet;


/**
 * 
 * For this to work, you must first create a "realtime" database:
 * https://firebase.google.com/
 * 
 * And use the "service accounts" menu to generate a JSON key for access.
 * 
 * Check here for more code samples:
 * https://firebase.google.com/docs/database/admin/save-data
 * 
 * 
 * 
 * @author john_shelby
 *
 */
public class DrawingSurface extends PApplet {

	
	// Window stuff
	private static final int DRAWING_WIDTH = 800, DRAWING_HEIGHT = 600;
	private static final Rectangle WINDOW_BOUNDS = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
	private ArrayList<Integer> keysDown;
	
	
	
	// Drawing stuff 
	private Player me;
	private ArrayList<Player> players;
	
	
	
	// Database stuff
	private DatabaseReference roomRef;  // This is the database entry for the whole room
	private DatabaseReference myUserRef;  // This is the database entry for just our user's data. This allows us to more easily update ourselves.
	
	private boolean currentlySending;  // These field allows us to limit database writes by only sending data once we've received confirmation the previous data went through.
	
	
	
	
	
	

	public DrawingSurface(DatabaseReference roomRef) {
	
		keysDown = new ArrayList<Integer>();
		players = new ArrayList<Player>();
		
		this.roomRef = roomRef;
		currentlySending = false;
	}

	public void setup() {
		// The Player uses the PApplet in its constructor, so we're initializing a bunch of stuff in setup() instead of the constructor
		// so the PApplet is definitely ready to go.
		
		String username = JOptionPane.showInputDialog("Give yourself a username:");
		
		myUserRef = roomRef.child("users").push();
		me = new Player(myUserRef.getKey(), username, DRAWING_WIDTH/2, DRAWING_HEIGHT/2, this);   
		myUserRef.setValueAsync(me.getDataObject());
		
		roomRef.child("users").addChildEventListener(new UserChangeListener());
		
		Runtime.getRuntime().addShutdownHook(new Thread()  // This code runs when the program exits.
	    {
	      public void run()
	      {
	    	  if (players.size() == 0)
					roomRef.removeValueAsync();
				else
					myUserRef.removeValueAsync();
	      }
	    });
	}
	
	public void draw() {
		background(255);
		
		push();
		scale((float)width/DRAWING_WIDTH, (float)height/DRAWING_HEIGHT);
		
		
		for (int i = 0; i < players.size(); i++) {
			players.get(i).draw(this);
		}
		me.draw(this);
		
		pop();
		

		if (keysDown.contains(KeyEvent.VK_UP))
			me.move(0, -5, WINDOW_BOUNDS);
		if (keysDown.contains(KeyEvent.VK_DOWN))
			me.move(0, 5, WINDOW_BOUNDS);
		if (keysDown.contains(KeyEvent.VK_LEFT))
			me.move(-5, 0, WINDOW_BOUNDS);
		if (keysDown.contains(KeyEvent.VK_RIGHT))
			me.move(5, 0, WINDOW_BOUNDS);

		
		if (!currentlySending && me.isDataChanged()) {
			currentlySending = true;
			myUserRef.setValue(me.getDataObject(), new CompletionListener() {

				@Override
				public void onComplete(DatabaseError arg0, DatabaseReference arg1) {
					currentlySending = false;
				}
				
			});
		}
		
		
	}
	
	
	public void keyPressed() {
		if (!keysDown.contains(super.keyCode))
			keysDown.add(super.keyCode);
		
	}
	
	public void keyReleased() {
		keysDown.remove(new Integer(super.keyCode));
	}
	
	
	
	
	
	/**
	 * 
	 * Handles all changes to the "users" database reference. This part of the database contains information about the players currently in this room.
	 * Because Firebase uses a separate thread than most other processes we're using (both Swing and Processing),
	 * we need to have a strategy for ensuring that code is executed somewhere besides these methods.
	 * 
	 * @author john_shelby
	 *
	 */
	public class UserChangeListener implements ChildEventListener {

		private ConcurrentLinkedQueue<Runnable> tasks;
		
		public UserChangeListener() {  // This threading strategy will work with Processing programs. Just use this code inside your PApplet.
			tasks = new ConcurrentLinkedQueue<Runnable>();
			
			DrawingSurface.this.registerMethod("post", this);
		}
		
		
		public void post() {
			while (!tasks.isEmpty()) {
				Runnable r = tasks.remove();
				r.run();
			}
		}
		
		@Override
		public void onCancelled(DatabaseError arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onChildAdded(DataSnapshot arg0, String arg1) {
			tasks.add(new Runnable() {

				@Override
				public void run() {
					if (me.idMatch(arg0.getKey())) {  // Don't react to our own data
						return;
					}
					
					PlayerData data = arg0.getValue(PlayerData.class);
					Player p = new Player(arg0.getKey(), data, DrawingSurface.this);
					players.add(p);
				}
				
			});
		}

		@Override
		public void onChildChanged(DataSnapshot arg0, String arg1) {
			tasks.add(new Runnable() {

				@Override
				public void run() {
					if (me.idMatch(arg0.getKey()))
						return;
					
					for (int i = 0; i < players.size(); i++) {
						Player p = players.get(i);
						if (p.idMatch(arg0.getKey())) {
							PlayerData data = arg0.getValue(PlayerData.class);
							p.syncWithDataObject(data);
						}
					}
				}
				
			});
			
		}

		@Override
		public void onChildMoved(DataSnapshot arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onChildRemoved(DataSnapshot arg0) {
			tasks.add(new Runnable() {

				@Override
				public void run() {
					if (me.idMatch(arg0.getKey()))
						return;
					
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).idMatch(arg0.getKey())) {
							players.remove(i);
							break;
						}
					}
				}
				
			});
			
		}
		
	}
	
	
	
	
	
	


}
