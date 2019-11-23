package Game;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Piece {
	// type of Piece
	private String orientation;
	
	// color of piece
	public Color color;
	
	// locations of all other blocks in piece
	private Map<String, Location[]> orientations;
	Location[] thisPiece;
	
	// used in random generation of a new Piece
	public Piece() {
		String starters[] = {"I1", "O", "L1", "J1", "T1", "S1", "Z1"};
		int index = (int)(Math.random() * 7);
		orientation = starters[index];
		
		if (orientation == "I1") {
			color = Color.CYAN;
		}
		else if(orientation == "O") {
			color = Color.YELLOW;
		}
		else if(orientation == "L1") {
			color = Color.ORANGE;
		}
		else if(orientation == "J1") {
			color = Color.getHSBColor(248, 100, 50);
		}
		else if(orientation == "T1") {
			color = Color.MAGENTA;
		}
		else if(orientation == "S1") {
			color = Color.GREEN;
		}
		else if(orientation == "Z1") {
			color = Color.RED;
		}
		else {
			orientation = "I1";
			color = Color.CYAN;
		}
		
		orientations = createOrientationMap();
		thisPiece = orientations.get(orientation);
	}
	
	public Location[] getPiece() {
		return thisPiece;
	}
	
	public String getOrientation() {
		return orientation;
	}
	
	private static Map<String, Location[]> createOrientationMap() {
		Map<String, Location[]> map = new HashMap<String, Location[]>();
		
		// I1
		Location loc[] = new Location[3];		
		loc[0] = new Location(0, 1);			
		loc[1] = new Location(0, 2);			
		loc[2] = new Location(0, -1);			
		map.put("I1", loc);
		
		// I2
		loc[0] = new Location(1, 0);
		loc[1] = new Location(2, 0);
		loc[2] = new Location(-1, 0);
		map.put("I2", loc);
		
		// O
		loc[0] = new Location(1, 0);
		loc[1] = new Location(0, 1);
		loc[2] = new Location(1, 1);
		map.put("O", loc);
		
		// T1
		loc[0] = new Location(0, 1);
		loc[1] = new Location(1, 0);
		loc[2] = new Location(-1, 0);
		map.put("T1", loc);
		
		// T2
		loc[0] = new Location(0, -1);
		loc[1] = new Location(0, 1);
		loc[2] = new Location(1, 0);
		map.put("T2", loc);
		
		// T3
		loc[0] = new Location(1, 0);
		loc[1] = new Location(0, -1);
		loc[2] = new Location(-1, 0);
		map.put("T3", loc);
		
		// T4
		loc[0] = new Location(-1, 0);
		loc[1] = new Location(0, 1);
		loc[2] = new Location(0, -1);
		map.put("T4", loc);
		
		// J1
		loc[0] = new Location(-1, 0);
		loc[1] = new Location(1, 0);
		loc[2] = new Location(1, 1);
		map.put("J1", loc);
		
		// J2
		loc[0] = new Location(0, -1);
		loc[1] = new Location(0, 1);
		loc[2] = new Location(1, -1);
		map.put("J2", loc);
		
		// J3
		loc[0] = new Location(-1, 0);
		loc[1] = new Location(1, 0);
		loc[2] = new Location(-1, -1);
		map.put("J3", loc);
		
		// J4
		loc[0] = new Location(-1, 1);
		loc[1] = new Location(0, -1);
		loc[2] = new Location(0, 1);
		map.put("J4", loc);
		
		// L1
		loc[0] = new Location(-1, 0);
		loc[1] = new Location(1, 0);
		loc[2] = new Location(-1, 1);
		map.put("L1", loc);
		
		// L2
		loc[0] = new Location(0, -1);
		loc[1] = new Location(0, 1);
		loc[2] = new Location(-1, -1);
		map.put("L2", loc);
		
		// L3
		loc[0] = new Location(-1, 0);
		loc[1] = new Location(1, 0);
		loc[2] = new Location(1, -1);
		map.put("L3", loc);
		
		// L4
		loc[0] = new Location(1, 1);
		loc[1] = new Location(0, -1);
		loc[2] = new Location(0, 1);
		map.put("L4", loc);
		
		// S1
		loc[0] = new Location(1, 0);
		loc[1] = new Location(0, 1);
		loc[2] = new Location(-1, 1);
		map.put("S1", loc);
		
		// S2
		loc[0] = new Location(0, -1);
		loc[1] = new Location(1, 0);
		loc[2] = new Location(1, 1);
		map.put("S2", loc);
		
		// Z1
		loc[0] = new Location(-1, 0);
		loc[1] = new Location(0, 1);
		loc[2] = new Location(1, 1);
		map.put("Z1", loc);
		
		// Z2
		loc[0] = new Location(0, -1);
		loc[1] = new Location(-1, 0);
		loc[2] = new Location(-1, 1);
		map.put("Z2", loc);
		
		return map;
	}
	
}
