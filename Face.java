/**
 * 
 * @author Arturo Argueta
 * @version 1.0
 *
 */
public class Face {
	/**
	 * TABLE OF COLORS
	 *  1.- Green
	 *  2.- White
	 *  3.- Orange
	 *  4.- Yellow
	 *  5.- Red
	 *  6.- Blue
	 */

	// CURRENT AND NEIGHBORS OF A SPECIFIC SIDE
	Side currentSide;
	Side up;
	Side down;
	Side left;
	Side right;
	Side back;
		
	//CONTRUCTOR
	public Face(Side currentSide, Side up,Side down,Side left,Side right,Side back){
		this.currentSide = currentSide;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.back = back;
	}
	
	
	
}
