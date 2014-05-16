/*
 * @Author Arturo Argueta
 * @Class Side.java
 * This class is used to represent a side of a conventional Rubiks cube
 * @version 1.0
 * 
 */
public class Side {
	int colorSide; //this number represents the color that represents a side (color in the middle of a side)
	int [][] matrix; // this matrix represents the other colors that are in that side of the Rubiks cube
	
	/**
	 * Constructor to create a basic side with just one color on one side
	 * @param theColor color in the middle of this side
	 */
	public Side(int theColor){
		this.colorSide = theColor;
		matrix = new int [3][3];
		this.initializedSide(theColor);
	}
	
	
	/**
	 * 
	 * @param theColor color to represent the side of the matrix
	 * @param up side up 
	 * @param down side down
	 * @param left side left side
	 * @param right the side on the right
	 *//*
	public Side(int theColor, Side up,Side down,Side left,Side right,Side back){
		this.colorSide = theColor;
		matrix = new int [3][3];
		this.initializedSide(theColor);
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.back = back;
	}
	*/
	
	/**
	 * Method used to initialize all the spots in the matrix to a specific integer or color
	 * @param color integer to represent the color we want to set on that side of the matrix
	 */
	public void initializedSide (int color){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				matrix[i][j] = color;
			}
		}
	}
	
	/**
	 * Method used to print all the spots in the side of the Cube
	 */
	public void printSide(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
}
