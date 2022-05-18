package FireBase;
import java.awt.Color;
import java.awt.Rectangle;

import processing.core.PApplet;
import processing.core.PShape;

public class Player {

	private String uniqueID;
	
	private String username;
	private double x, y;
	
	private PShape shape;
	
	private boolean dataUpdated;  // Allows us to limit database writes by only sending data when something has actually been modified
	private PlayerData data;

	//public Player(String uniqueID, PApplet surface) {
	//	this(uniqueID, null, 0, 0, surface);
	//}
	
	public Player(String uniqueID, String username, double x, double y, PApplet surface) {
		this.uniqueID = uniqueID;
		this.username = username;
		this.x = x;
		this.y = y;
		Color color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		int rand = (int)(Math.random()*3);
		
		data = new PlayerData();
		data.username = username;
		data.shapeType = rand;
		data.r = color.getRed();
		data.g = color.getGreen();
		data.b = color.getBlue();
		setupShape(rand, color, surface);

		dataUpdated = false;
	}
	
	public Player(String uniqueID, PlayerData data, PApplet surface) {
		this.uniqueID = uniqueID;
		this.data = data;
		this.username = data.username;
		setupShape(data.shapeType, new Color(data.r, data.g, data.b), surface);
		this.syncWithDataObject(data);
	}
	
	private void setupShape(int type, Color c, PApplet surface) {
		
		if (type == 0)
			shape = surface.createShape(PApplet.ELLIPSE, 0,0,50,50);
		else if (type == 1)
			shape = surface.createShape(PApplet.TRIANGLE, -25,25, 0,-25, 25,25);
		else if (type == 2)
			shape = surface.createShape(PApplet.RECT, -25,-25, 50,50);
			
		shape.setFill(c.getRGB());
		shape.setStroke(0);
		shape.setStrokeWeight(5);
	}
	
	public boolean idMatch(String uid) {
		return this.uniqueID.equals(uid);
	}
	
	public boolean isDataChanged() {
		return dataUpdated;
	}
	
	public String getUsername() {
		return username;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void move(double x, double y, Rectangle boundary) {
		this.x += x;
		if (!boundary.contains(this.x, this.y)) 
			this.x -= x;
		
		this.y += y;
		if (!boundary.contains(this.x, this.y)) 
			this.y -= y;
		
		dataUpdated = true;
	}
	
	
	public void draw(PApplet surface) {
		surface.shape(shape, (float)x, (float)y);
		surface.fill(0);
		surface.textSize(14);
		surface.textAlign(PApplet.CENTER, PApplet.BOTTOM);
		surface.text(username, (float)x, (float)y-30);
	}
	
	
	public PlayerData getDataObject() {
		dataUpdated = false;
		
		data.x = x;
		data.y = y;
		return data;
	}
	
	public void syncWithDataObject(PlayerData data) {
		dataUpdated = false;
		
		this.x = data.x;
		this.y = data.y;
	}
	
	
	
}
