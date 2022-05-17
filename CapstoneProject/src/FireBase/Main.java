package FireBase;

public class Main {

	public static void main(String args[]) {

		// Firebase uses a special logger to control what gets printed to the command line.
		// Just copy/paste these 2 lines to the beginning of your main()
		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
	    root.setLevel(ch.qos.logback.classic.Level.ERROR);  // This only shows us firebase errors. Change "ERROR" to "DEBUG" to see lots of database info.
	    
		RoomChooser rc = new RoomChooser();
		rc.show();
		
		
	}
	
	
	
	

}

