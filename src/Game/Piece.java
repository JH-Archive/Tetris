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
		System.out.println(Integer.toString(index));
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
			color = new Color(18, 13, 193);
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
	
	public Piece(String orientation) {
		this.orientation = orientation;
		String type = orientation.substring(0, 1);
		
		if (type.equals("I")) {
			color = Color.CYAN;
		}
		else if(type.equals("O")) {
			color = Color.YELLOW;
		}
		else if(type.equals("L")) {
			color = Color.ORANGE;
		}
		else if(type.equals("J")) {
			color = new Color(18, 13, 193);
		}
		else if(type.equals("T")) {
			color = Color.MAGENTA;
		}
		else if(type.equals("S")) {
			color = Color.GREEN;
		}
		else if(type.equals("Z")) {
			color = Color.RED;
		}
		else {
			orientation = "I1";
			color = Color.CYAN;
		}
		
		orientations = createOrientationMap();
		thisPiece = orientations.get(orientation);
	}
	
	public Piece rotatedVersion() {
		String rotatedOrientation = rotateString();
		return new Piece(rotatedOrientation);
	}
	
	private String rotateString() {
		
		// I
		if (orientation == "I1") {
			return "I2";
		}
		else if (orientation == "I2") {
			return "I1";
		}
		
		// O
		else if (orientation == "O") {
			return "O";
		}
		
		// T
		else if (orientation == "T1") {
			return "T2";
		}
		else if (orientation == "T2") {
			return "T3";
		}
		else if (orientation == "T3") {
			return "T4";
		}
		else if (orientation == "T4") {
			return "T1";
		}
		
		// J
		else if (orientation == "J1") {
			return "J2";
		}
		else if (orientation == "J2") {
			return "J3";
		}
		else if (orientation == "J3") {
			return "J4";
		}
		else if (orientation == "J4") {
			return "J1";
		}
		
		// L
		else if (orientation == "L1") {
			return "L2";
		}
		else if (orientation == "L2") {
			return "L3";
		}
		else if (orientation == "L3") {
			return "L4";
		}
		else if (orientation == "L4") {
			return "L1";
		}
		
		// S
		else if (orientation == "S1") {
			return "S2";
		}
		else if (orientation == "S2") {
			return "S1";
		}
		
		// Z
		else if (orientation == "Z1") {
			return "Z2";
		}
		else if (orientation == "Z2") {
			return "Z1";
		}
		
		// default
		else {
			return "I1";
		}
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
		Location i1[] = new Location[3];		
		i1[0] = new Location(0, 1);			
		i1[1] = new Location(0, 2);			
		i1[2] = new Location(0, -1);			
		map.put("I1", i1);
		
		// I2
		Location i2[] = new Location[3];
		i2[0] = new Location(1, 0);
		i2[1] = new Location(2, 0);
		i2[2] = new Location(-1, 0);
		map.put("I2", i2);
		
		// O
		Location o[] = new Location[3];
		o[0] = new Location(1, 0);
		o[1] = new Location(0, 1);
		o[2] = new Location(1, 1);
		map.put("O", o);
		
		// T1
		Location t1[] = new Location[3];
		t1[0] = new Location(0, 1);
		t1[1] = new Location(1, 0);
		t1[2] = new Location(0, -1);
		map.put("T1", t1);
		
		// T2
		Location t2[] = new Location[3];
		t2[0] = new Location(-1, 0);
		t2[1] = new Location(0, 1);
		t2[2] = new Location(1, 0);
		map.put("T2", t2);
		
		// T3
		Location t3[] = new Location[3];
		t3[0] = new Location(0, 1);
		t3[1] = new Location(0, -1);
		t3[2] = new Location(-1, 0);
		map.put("T3", t3);
		
		// T4
		Location t4[] = new Location[3];
		t4[0] = new Location(-1, 0);
		t4[1] = new Location(1, 0);
		t4[2] = new Location(0, -1);
		map.put("T4", t4);
		
		// J1
		Location j1[] = new Location[3];
		j1[0] = new Location(1, 0);
		j1[1] = new Location(0, -1);
		j1[2] = new Location(0, -2);
		map.put("J1", j1);
		
		// J2
		Location j2[] = new Location[3];
		j2[0] = new Location(1, 0);
		j2[1] = new Location(2, 0);
		j2[2] = new Location(0, 1);
		map.put("J2", j2);
		
		// J3
		Location j3[] = new Location[3];
		j3[0] = new Location(-1, 0);
		j3[1] = new Location(0, 1);
		j3[2] = new Location(0, 2);
		map.put("J3", j3);
		
		// J4
		Location j4[] = new Location[3];
		j4[0] = new Location(-1, 0);
		j4[1] = new Location(-2, 0);
		j4[2] = new Location(0, -1);
		map.put("J4", j4);
		
		// L1
		Location l1[] = new Location[3];
		l1[0] = new Location(0, 2);
		l1[1] = new Location(0, 1);
		l1[2] = new Location(1, 0);
		map.put("L1", l1);
		
		// L2
		Location l2[] = new Location[3];
		l2[0] = new Location(-1, 0);
		l2[1] = new Location(-2, 0);
		l2[2] = new Location(0, 1);
		map.put("L2", l2);
		
		// L3
		Location l3[] = new Location[3];
		l3[0] = new Location(-1, 0);
		l3[1] = new Location(0, -1);
		l3[2] = new Location(0, -2);
		map.put("L3", l3);
		
		// L4
		Location l4[] = new Location[3];
		l4[0] = new Location(0, -1);
		l4[1] = new Location(1, 0);
		l4[2] = new Location(2, 0);
		map.put("L4", l4);
		
		// S1
		Location s1[] = new Location[3];
		s1[0] = new Location(1, 0);
		s1[1] = new Location(0, 1);
		s1[2] = new Location(1, -1);
		map.put("S1", s1);
		
		// S2
		Location s2[] = new Location[3];
		s2[0] = new Location(0, 1);
		s2[1] = new Location(-1, 0);
		s2[2] = new Location(1, 1);
		map.put("S2", s2);
		
		// Z1
		Location z1[] = new Location[3];
		z1[0] = new Location(1, 0);
		z1[1] = new Location(0, -1);
		z1[2] = new Location(1, 1);
		map.put("Z1", z1);
		
		// Z2
		Location z2[] = new Location[3];
		z2[0] = new Location(0, 1);
		z2[1] = new Location(1, 0);
		z2[2] = new Location(-1, 1);
		map.put("Z2", z2);
		
		return map;
	}
	
}
