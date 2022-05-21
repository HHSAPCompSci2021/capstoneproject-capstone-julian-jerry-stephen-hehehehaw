package FireBaseStuff;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;

import Instructions.MainMenu;
import Instructions.World;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;

import com.google.firebase.database.FirebaseDatabase;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;


/** 
 * This class represents a Room Chooser
 * @author john_shelby
 */
public class RoomChooser extends JPanel
{
	private JFrame theWindow;
	
	private JList<String> roomList;
	private DefaultListModel<String> model;

	private JButton connectButton;
	private JButton newRoomButton;
	
	
	private DatabaseReference postsRef;
	
	/**
	* Returns the data base references
	* @return DatabaseReference 
	*/
	public DatabaseReference getDBR() {
		return postsRef;
	}
	
	/** 
	 * Creates a new instance of a RoomChooser object
	 */
	public RoomChooser() {
		
		model = new DefaultListModel<String>();
		
		ActionHandler actionEventHandler = new ActionHandler();
		
		setLayout(new BorderLayout());
		
		JPanel cnPanel = new JPanel();
		cnPanel.setLayout(new BorderLayout());
		roomList = new JList<String>();
		roomList.setModel(model);
		roomList.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		cnPanel.add(roomList,BorderLayout.CENTER);
		JLabel ah = new JLabel("Available Rooms");
		ah.setHorizontalAlignment(JLabel.CENTER);
		cnPanel.add(ah,BorderLayout.NORTH);
		cnPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		add(cnPanel);
		
		
		JPanel ePanel = new JPanel();
		ePanel.setLayout(new GridLayout(1,5,15,15));
		newRoomButton = new JButton("<html><center>Create<br>A Room</center></html>");
		newRoomButton.addActionListener(actionEventHandler);
		connectButton = new JButton("<html><center>Join<br>Room</center></html>");
		connectButton.addActionListener(actionEventHandler);

		
		ePanel.add(newRoomButton);
		ePanel.add(connectButton);
		
		cnPanel.add(ePanel,BorderLayout.SOUTH);
		


		// DATABASE SETUP
		FileInputStream refreshToken;
		try {

			refreshToken = new FileInputStream("key.json");

			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(refreshToken))
					.setDatabaseUrl("https://server1heehawbrawls-default-rtdb.firebaseio.com/")
					.build();

			FirebaseApp.initializeApp(options);
			DatabaseReference database = FirebaseDatabase.getInstance().getReference();
			postsRef = database.child("arcade");

			postsRef.addChildEventListener(new DatabaseChangeListener());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/** 
	 * Displays the window
	 */
	public void show() {
		
		theWindow = new JFrame();
		theWindow.add(this);
		theWindow.setBounds(0, 0, 800, 600);
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theWindow.setVisible(true);
		
	}
	
	/** 
	 * Selects the room
	 * @param name The name of the room
	 */
	public void selectRoom(String name) {


		postsRef.orderByChild("name").equalTo(name).limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snap) {
				
//				if (getNumPlayers(snap) >= 2)
//					return;
				
				if (!snap.hasChildren())
					return;
				
				theWindow.setVisible(false);
				
				MainMenu main = new MainMenu ( snap.getChildren().iterator().next().getRef());
				World drawing = new World(main, snap.getChildren().iterator().next().getRef());
				PApplet.runSketch(new String[]{""}, main);
				PSurfaceAWT surf = (PSurfaceAWT) main.getSurface();
				PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
				JFrame window = (JFrame)canvas.getFrame();

				window.setSize(drawing.screenWidth, drawing.screenHeight);
				window.setMinimumSize(new Dimension(drawing.screenWidth, drawing.screenHeight));
				window.setMaximumSize(new Dimension(drawing.screenWidth + 1, drawing.screenHeight + 1));
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.setResizable(true);
				
				window.setLocationRelativeTo(null);
				window.setVisible(true);
				
				canvas.requestFocus();
				
				theWindow.dispose();
			}

			@Override
			public void onCancelled(DatabaseError arg0) {
			}
		});
		
		
		
	}
	
	
//	public int getNumPlayers(DataSnapshot dataSnapshot) {
//		System.out.println((int)dataSnapshot.getChildren().iterator().next().getChildrenCount());
//		
//		return (int)dataSnapshot.getChildren().iterator().next().getChildrenCount();
//	}

	

	/**
	 * 
	 * Handles all changes to the database reference. Because Firebase uses a separate thread than most other processes we're using (both Swing and Processing),
	 * we need to have a strategy for ensuring that code is executed somewhere besides these methods.
	 * 
	 * @author john_shelby
	 *
	 */
	public class DatabaseChangeListener implements ChildEventListener {


		@Override
		public void onCancelled(DatabaseError arg0) {
			// TODO Auto-generated method stub

		}


		@Override
		public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
			
			SwingUtilities.invokeLater(new Runnable() {  // This threading strategy will work with Swing programs. Just put whatever code you want inside of one of these "runnable" wrappers.

				public void run() {
					
					String name = dataSnapshot.child("name").getValue(String.class);
					model.add(0,name);
					//System.out.println ("room name: " + name + " player count: " + getNumPlayers(dataSnapshot));
					
				}
				
			});
			
			
		}
		
		
		


		@Override
		public void onChildChanged(DataSnapshot dataSnapshot, String arg1) {
			// TODO Auto-generated method stub

		}


		@Override
		public void onChildMoved(DataSnapshot dataSnapshot, String arg1) {
			// TODO Auto-generated method stub

		}


		@Override
		public void onChildRemoved(DataSnapshot dataSnapshot) {
			
			SwingUtilities.invokeLater(new Runnable() {
			    public void run() {
			    	String name = dataSnapshot.child("name").getValue(String.class);
			    	model.removeElement(name);
			    }
			});

		}

	}


	
	

	private class ActionHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == newRoomButton) {
				
				String roomName = JOptionPane.showInputDialog("Please choose a name for your room:");
				if (roomName == null || roomName.isEmpty()) {
					JOptionPane.showMessageDialog(RoomChooser.this, "Room creation fail - The room needs a name.");
					return;
				}
				
				if (model.contains(roomName)) {
					JOptionPane.showMessageDialog(RoomChooser.this, "Room creation fail - Room name already exists.");
					return;
				}
				
				postsRef.push().child("name").setValue(roomName, new CompletionListener() {  // Because rooms only have a name, we don't bother making a whole class for them.

					@Override
					public void onComplete(DatabaseError arg0, DatabaseReference arg1) { // This makes it so we enter the room once it's added to the database.
						selectRoom(roomName);
					}
					
				});
				
			} else if (source == connectButton) {
				String sel = roomList.getSelectedValue();
				
				if (sel != null)
					selectRoom(sel);
				
			}

		}
	}

	
	
}
