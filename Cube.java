/**
 * 
 * @author Arturo Argueta
 * 
 * The algorithms to solve the cube were obtained from this video:
 * https://www.youtube.com/watch?v=609nhVzg-5Q&list=FLhnMH1Jl1eBv6JbBCN3kKZg&index=2
 * 
 * @version 1.0
 */
public class Cube {

	/**
	 * TABLE OF COLORS
	 *  1.- Green
	 *  2.- White
	 *  3.- Orange
	 *  4.- Yellow
	 *  5.- Red
	 *  6.- Blue
	 */

	Side green;
	Side white;
	Side orange;
	Side yellow;
	Side red;
	Side blue;

	Face greenFace;
	Face whiteFace;
	Face orangeFace;
	Face yellowFace;
	Face redFace;
	Face blueFace;

	public Cube(){
		green = new Side(1);
		white = new Side(2);
		orange = new Side(3);
		yellow = new Side(4);
		red = new Side(5);
		blue = new Side (6);

		//The green face will be pointing towards the player all the time in this case
		greenFace = new Face(green, red, orange, white, yellow, blue);
		
		/*
		 * Other Faces Considered:
		whiteFace = new Face(white, red, orange, blue, green, yellow);
		blueFace = new Face(blue, red, orange, yellow, white, green);
		yellowFace = new Face(yellow, red, orange, green, blue, white);
		redFace = new Face(red, blue, green, white, yellow, orange);
		orangeFace = new Face(orange, green, blue, white, yellow, red);
		*/
	}

	/**
	 * This method will print a side of the matrix depending on the number passed
	 * 
	 * @param side the color of the side we want to print (must be bigger than 0 and less than 7)
	 */
	public void printSide(int side){
		System.out.println("Printing side number: "+side);
		if(side==1){
			green.printSide();
		}
		else if(side==2){
			white.printSide();
		}
		else if(side == 3){
			orange.printSide();
		}
		else if(side == 4){
			yellow.printSide();
		}
		else if(side == 5){
			red.printSide();
		}
		else if(side == 6){
			blue.printSide();
		}
		else{
			System.err.println("Not a valid input");
		}	
	}


	
	
	/**
	 * Method to turn the bottom side of the cube clockwise 
	 * @param currentFace side of the cube that is pointing towards the player
	 */
	public void turnBottom(Face currentFace){
		//ROTATE THIS FACE NEGATIVE 90 DEGREES
		currentFace.back.matrix = rotateMinus90degrees(currentFace.back.matrix);
		
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] downVector = downSide(currentFace.down.matrix);
		int [] leftVector = leftSide(currentFace.left.matrix);
		int [] rightVector = rightSide(currentFace.right.matrix);
		int [] upVector = upSide(currentFace.up.matrix);
		
		// CHANGE THE COLORS OF THE MATRIXS
		currentFace.down.matrix = changeDownSide(currentFace.down.matrix, leftVector);
		currentFace.right.matrix = changeRightSideInverse(currentFace.right.matrix, downVector);
		currentFace.up.matrix = changeUpSide(currentFace.up.matrix, rightVector);
		currentFace.left.matrix = changeLeftSideInverse(currentFace.left.matrix, upVector);
	}
	
	
	/**
	 * Method to turn the bottom side of the cube counterclockwise 
	 * @param currentFace side of the cube that is pointing towards the player
	 */
	public void turnBottomPrime(Face currentFace){
		//ROTATE THIS FACE 90 DEGREES
		currentFace.back.matrix = rotate90degrees(currentFace.back.matrix);
		
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] downVector = downSide(currentFace.down.matrix);
		int [] leftVector = leftSide(currentFace.left.matrix);
		int [] rightVector = rightSide(currentFace.right.matrix);
		int [] upVector = upSide(currentFace.up.matrix);
		
		// CHANGE THE COLORS OF THE MATRIXS
		currentFace.down.matrix = changeDownSideInverse(currentFace.down.matrix, rightVector);
		currentFace.right.matrix = changeRightSide(currentFace.right.matrix, upVector);
		currentFace.up.matrix = changeUpSideInverse(currentFace.up.matrix, leftVector);
		currentFace.left.matrix = changeLeftSide(currentFace.left.matrix, downVector);
	}
	
	/**
	 * Method to rotate the down side clockwise of a cube face. This method will rotate the bottom row and middle row
	 * @param currentFace The current face of the cube
	 */
	public void turnDownDouble(Face currentFace){
		//ROTATE THIS FACE NEGATIVE 90 DEGREES
		currentFace.down.matrix = rotateMinus90degrees(currentFace.down.matrix);
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] currentVector = downSide(currentFace.currentSide.matrix);
		int [] leftVector = downSide(currentFace.left.matrix);
		int [] rightVector = downSide(currentFace.right.matrix);
		int [] backVector = downSide(currentFace.back.matrix);
		
		int [] currentMiddleVector = middleSide(currentFace.currentSide.matrix);
		int [] leftMiddleVector = middleSide(currentFace.left.matrix);
		int [] rightMiddleVector = middleSide(currentFace.right.matrix);
		int [] backMiddleVector = middleSide(currentFace.back.matrix);
		
		// CHANGE THE COLORS OF THE MATRIXS
		currentFace.currentSide.matrix = changeDownSide(currentFace.currentSide.matrix, leftVector);
		currentFace.right.matrix = changeDownSide(currentFace.right.matrix, currentVector);
		currentFace.back.matrix = changeDownSide(currentFace.back.matrix, rightVector);
		currentFace.left.matrix = changeDownSide(currentFace.left.matrix, backVector);
		
		currentFace.currentSide.matrix = changeMiddleSide(currentFace.currentSide.matrix, leftMiddleVector);
		currentFace.right.matrix = changeMiddleSide(currentFace.right.matrix, currentMiddleVector);
		currentFace.back.matrix = changeMiddleSide(currentFace.back.matrix, rightMiddleVector);
		currentFace.left.matrix = changeMiddleSide(currentFace.left.matrix, backMiddleVector);
	}
	
	/**
	 * Method to rotate the down side counterclockwise of a cube face. This method will rotate the bottom row and middle row
	 * @param currentFace The current face of the cube
	 */
	public void turnDownDoublePrime(Face currentFace){
		//ROTATE THE FACE 90 DEGREES
		currentFace.down.matrix = rotate90degrees(currentFace.down.matrix);
		
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] currentVector = downSide(currentFace.currentSide.matrix);
		int [] leftVector = downSide(currentFace.left.matrix);
		int [] rightVector = downSide(currentFace.right.matrix);
		int [] backVector = downSide(currentFace.back.matrix);
		
		int [] currentMiddleVector = middleSide(currentFace.currentSide.matrix);
		int [] leftMiddleVector = middleSide(currentFace.left.matrix);
		int [] rightMiddleVector = middleSide(currentFace.right.matrix);
		int [] backMiddleVector = middleSide(currentFace.back.matrix);
		
		// CHANGE THE COLORS OF THE MATRIXS
		currentFace.currentSide.matrix = changeDownSide(currentFace.currentSide.matrix, rightVector);
		currentFace.right.matrix = changeDownSide(currentFace.right.matrix, backVector);
		currentFace.back.matrix = changeDownSide(currentFace.back.matrix, leftVector);
		currentFace.left.matrix = changeDownSide(currentFace.left.matrix, currentVector);
		
		currentFace.currentSide.matrix = changeMiddleSide(currentFace.currentSide.matrix, rightMiddleVector);
		currentFace.right.matrix = changeMiddleSide(currentFace.right.matrix, backMiddleVector);
		currentFace.back.matrix = changeMiddleSide(currentFace.back.matrix, leftMiddleVector);
		currentFace.left.matrix = changeMiddleSide(currentFace.left.matrix, currentMiddleVector);
	}
	
	/**
	 * Method to turn the down side of the cube clockwise 
	 * @param currentFace side of the cube that is pointing towards the player
	 */
	public void turnDown(Face currentFace){
		//ROTATE THIS FACE NEGATIVE 90 DEGREES
		currentFace.down.matrix = rotateMinus90degrees(currentFace.down.matrix);
		
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] currentVector = downSide(currentFace.currentSide.matrix);
		int [] leftVector = downSide(currentFace.left.matrix);
		int [] rightVector = downSide(currentFace.right.matrix);
		int [] backVector = downSide(currentFace.back.matrix);
		
		// CHANGE THE COLORS OF THE MATRIXS
		currentFace.currentSide.matrix = changeDownSide(currentFace.currentSide.matrix, leftVector);
		currentFace.right.matrix = changeDownSide(currentFace.right.matrix, currentVector);
		currentFace.back.matrix = changeDownSide(currentFace.back.matrix, rightVector);
		currentFace.left.matrix = changeDownSide(currentFace.left.matrix, backVector);
	}
	
	/**
	 * Method to turn the down side of the cube clockwise 
	 * @param currentFace side of the cube that is pointing towards the player
	 */
	public void turnDownPrime(Face currentFace){
		//ROTATE THE FACE 90 DEGREES
		currentFace.down.matrix = rotate90degrees(currentFace.down.matrix);
		
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] currentVector = downSide(currentFace.currentSide.matrix);
		int [] leftVector = downSide(currentFace.left.matrix);
		int [] rightVector = downSide(currentFace.right.matrix);
		int [] backVector = downSide(currentFace.back.matrix);
		
		// CHANGE THE COLORS OF THE MATRIXS
		currentFace.currentSide.matrix = changeDownSide(currentFace.currentSide.matrix, rightVector);
		currentFace.right.matrix = changeDownSide(currentFace.right.matrix, backVector);
		currentFace.back.matrix = changeDownSide(currentFace.back.matrix, leftVector);
		currentFace.left.matrix = changeDownSide(currentFace.left.matrix, currentVector);
	}
	
	/**
	 * Method to turn the front side of the cube clockwise 
	 * @param currentFace side of the cube that is pointing towards the player
	 */
	public void turnFront(Face currentFace){
		//ROTATE THIS FACE NEGATIVE 90 DEGREES
		currentFace.currentSide.matrix = rotateMinus90degrees(currentFace.currentSide.matrix);
		
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] upVector = downSide(currentFace.up.matrix);
		int [] downVector = upSide(currentFace.down.matrix);
		int [] leftVector = rightSide(currentFace.left.matrix);
		int [] rightVector = leftSide(currentFace.right.matrix);
		
		// CHANGE THE COLORS OF THE MATRIXS
		currentFace.left.matrix = changeRightSide(currentFace.left.matrix, downVector);
		currentFace.right.matrix = changeLeftSide(currentFace.right.matrix, upVector);
		currentFace.up.matrix = changeDownSideInverse(currentFace.up.matrix, leftVector);
		currentFace.down.matrix = changeUpSideInverse(currentFace.down.matrix, rightVector);
	}
	
	/**
	 * Method to turn the front side of the cube counterclockwise 
	 * @param currentFace side of the cube that is pointing towards the player
	 */
	public void turnFrontPrime(Face currentFace){
		//ROTATE THIS FACE 90 DEGREES
		currentFace.currentSide.matrix = rotate90degrees(currentFace.currentSide.matrix);
		
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] upVector = downSide(currentFace.up.matrix);
		int [] downVector = upSide(currentFace.down.matrix);
		int [] leftVector = rightSide(currentFace.left.matrix);
		int [] rightVector = leftSide(currentFace.right.matrix);
		
		// CHANGE THE COLORS OF THE MATRIXS
		currentFace.left.matrix = changeRightSideInverse(currentFace.left.matrix, upVector);
		currentFace.right.matrix = changeLeftSideInverse(currentFace.right.matrix, downVector);
		currentFace.up.matrix = changeDownSide(currentFace.up.matrix, rightVector);
		currentFace.down.matrix = changeUpSide(currentFace.down.matrix, leftVector);
	}

	/**
	 * Method to turn the up side of the cube clockwise 
	 * @param currentFace side of the cube that is pointing towards the player
	 */
	public void turnUp(Face currentFace){
		//ROTATE THIS FACE NEGATIVE 90 DEGREES
		currentFace.up.matrix = rotateMinus90degrees(currentFace.up.matrix);
		
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] currentVector = upSide(currentFace.currentSide.matrix);
		int [] backVector = upSide(currentFace.back.matrix);
		int [] leftVector = upSide(currentFace.left.matrix);
		int [] rightVector = upSide(currentFace.right.matrix);

		// CHANGE THE COLORS OF THE MATRIXS
		currentFace.currentSide.matrix = changeUpSide(currentFace.currentSide.matrix, rightVector);
		currentFace.left.matrix = changeUpSide(currentFace.left.matrix, currentVector);
		currentFace.back.matrix = changeUpSide(currentFace.back.matrix, leftVector);
		currentFace.right.matrix = changeUpSide(currentFace.right.matrix, backVector);
	}


	/**
	 * Method to turn the up side of the cube counterclockwise 
	 * @param currentFace side of the cube that is pointing towards the player
	 */
	public void turnUpPrime(Face currentFace){
		//ROTATE THIS FACE 90 DEGREES
		currentFace.up.matrix = rotate90degrees(currentFace.up.matrix);
		
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] currentVector = upSide(currentFace.currentSide.matrix);
		int [] backVector = upSide(currentFace.back.matrix);
		int [] leftVector = upSide(currentFace.left.matrix);
		int [] rightVector = upSide(currentFace.right.matrix);
		
		// CHANGE THE COLORS OF THE MATRIX
		currentFace.currentSide.matrix = changeUpSide(currentFace.currentSide.matrix, leftVector);
		currentFace.left.matrix = changeUpSide(currentFace.left.matrix, backVector);
		currentFace.back.matrix = changeUpSide(currentFace.back.matrix, rightVector);
		currentFace.right.matrix = changeUpSide(currentFace.right.matrix, currentVector);
	}
	

	/**
	 * Method to turn the left side of the cube counterclockwise 
	 * @param currentFace side of the cube that is pointing towards the player
	 */
	public void turnLeft(Face currentFace){
		//ROTATE THIS FACE NEGATIVE 90 DEGREES
		currentFace.left.matrix = rotateMinus90degrees(currentFace.left.matrix);
		
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] upVector = leftSide(currentFace.up.matrix);
		int [] downVector = leftSide(currentFace.down.matrix);
		int [] currentVector = leftSide(currentFace.currentSide.matrix);
		int [] backVector = rightSide(currentFace.back.matrix);

		// CHANGE THE COLORS OF THE MATRIX
		currentFace.currentSide.matrix = changeLeftSide(currentFace.currentSide.matrix, upVector);
		currentFace.up.matrix = changeLeftSideInverse(currentFace.up.matrix, backVector);
		currentFace.back.matrix = changeRightSideInverse(currentFace.back.matrix, downVector);
		currentFace.down.matrix = changeLeftSide(currentFace.down.matrix, currentVector);
	}

	/**
	 * Method to turn the left side of the cube clockwise 
	 * @param currentFace side of the cube that is pointing towards the player
	 */
	public void turnLeftPrime(Face currentFace){
		//ROTATE THIS FACE 90 DEGREES
		currentFace.left.matrix = rotate90degrees(currentFace.left.matrix);
		
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] upVector = leftSide(currentFace.up.matrix);
		int [] downVector = leftSide(currentFace.down.matrix);
		int [] currentVector = leftSide(currentFace.currentSide.matrix);
		int [] backVector = rightSide(currentFace.back.matrix);

		// CHANGE THE COLORS OF THE MATRIX
		currentFace.currentSide.matrix = changeLeftSide(currentFace.currentSide.matrix, downVector);
		currentFace.up.matrix = changeLeftSide(currentFace.up.matrix, currentVector);
		currentFace.back.matrix = changeRightSideInverse(currentFace.back.matrix, upVector);
		currentFace.down.matrix = changeLeftSideInverse(currentFace.down.matrix, backVector);
	}
	

	/**
	 * Method to make a right turn of the Rubiks cube.
	 * @param currentFace the front facing side of the Rubiks cube
	 */
	public void turnRight(Face currentFace){
		//ROTATE THIS FACE NEGATIVE 90 DEGREES
		currentFace.right.matrix = rotateMinus90degrees(currentFace.right.matrix);
		
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] upVector = rightSide(currentFace.up.matrix);
		int [] downVector = rightSide(currentFace.down.matrix);
		int [] currentVector = rightSide(currentFace.currentSide.matrix);
		int [] backVector = leftSide(currentFace.back.matrix);

		// CHANGE THE COLORS OF THE MATRIX
		currentFace.currentSide.matrix = changeRightSide(currentFace.currentSide.matrix, downVector);
		currentFace.up.matrix = changeRightSide(currentFace.up.matrix, currentVector);
		currentFace.back.matrix = changeLeftSideInverse(currentFace.back.matrix, upVector);
		currentFace.down.matrix = changeRightSideInverse(currentFace.down.matrix, backVector);

	}
	
	
	/**
	 * Method to make a turn of the right side but this is the inverted version of a regular right turn.
	 * @param currentFace the side of the cube that is pointing towards the player
	 */
	public void turnRightPrime(Face currentFace){
		//ROTATE THIS FACE 90 DEGREES
		currentFace.right.matrix =rotate90degrees(currentFace.right.matrix);
		
		//VECTORS THAT WILL BE CHANGED ON THE DIFFERENT SIDES OF THE CUBE
		int [] upVector = rightSide(currentFace.up.matrix);
		int [] downVector = rightSide(currentFace.down.matrix);
		int [] currentVector = rightSide(currentFace.currentSide.matrix);
		int [] backVector = leftSide(currentFace.back.matrix);

		// CHANGE THE COLORS OF THE MATRIX
		currentFace.currentSide.matrix = changeRightSide(currentFace.currentSide.matrix, upVector);
		currentFace.up.matrix = changeRightSideInverse(currentFace.up.matrix, backVector); 
		currentFace.back.matrix = changeLeftSideInverse(currentFace.back.matrix, downVector); //FIX
		currentFace.down.matrix = changeRightSide(currentFace.down.matrix, currentVector);

	}


	/**
	 * Method to rotate a 2D matrix -90 degrees.
	 * Like rotating a square 90 degrees
	 * @param matrix The matrix we want to rotate
	 * @return the original matrix rotated -90 degrees
	 */
	public int [][] rotateMinus90degrees(int [][] matrix){
		int [][] rotatedMatrix = new int[3][3];
		//rotate top row of the old matrix
		rotatedMatrix[0][2]= matrix[0][0];
		rotatedMatrix[1][2]= matrix[0][1];
		rotatedMatrix[2][2]= matrix[0][2];
		// rotate middle row of the old matrix
		rotatedMatrix[0][1]= matrix[1][0];
		rotatedMatrix[1][1]= matrix[1][1];
		rotatedMatrix[2][1]= matrix[1][2];
		// rotate bottom row of the old matrix
		rotatedMatrix[0][0]= matrix[2][0];
		rotatedMatrix[1][0]= matrix[2][1];
		rotatedMatrix[2][0]= matrix[2][2];		
		return rotatedMatrix;
	}

	/**
	 * Method to rotate a matrix 90 degrees in order to keep track of the numbers.
	 * @param matrix
	 * @return a matrix that has all the numbers rotated 90 degrees
	 */
	public int [][] rotate90degrees(int [][] matrix){
		int [][] rotatedMatrix = new int[3][3];
		//rotate top row of the old matrix
		rotatedMatrix[2][0]= matrix[0][0];
		rotatedMatrix[1][0]= matrix[0][1];
		rotatedMatrix[0][0]= matrix[0][2];
		// rotate middle row of the old matrix
		rotatedMatrix[2][1]= matrix[1][0];
		rotatedMatrix[1][1]= matrix[1][1];
		rotatedMatrix[0][1]= matrix[1][2];
		// rotate bottom row of the old matrix
		rotatedMatrix[2][2]= matrix[2][0];
		rotatedMatrix[1][2]= matrix[2][1];
		rotatedMatrix[0][2]= matrix[2][2];		
		return rotatedMatrix;
	}

	/**
	 * This method returns the right side of a 2D matrix in a 1D vector
	 * @param matrix the matrix we want to get the left side from
	 * @return a vector representing the left side of a matrix
	 */
	public int[] rightSide(int [][] matrix){
		int[] rightSide = new int[3];
		rightSide[0]=matrix[0][2];
		rightSide[1]=matrix[1][2];
		rightSide[2]=matrix[2][2];
		return rightSide;
	}


	/**
	 * This method returns the left side of a 2D matrix in a 1D vector
	 * @param matrix the matrix we want to get the left side from
	 * @return a vector representing the left side of a matrix
	 */
	public int[] leftSide(int [][] matrix){
		int[] leftSide = new int[3];
		leftSide[0]=matrix[0][0];
		leftSide[1]=matrix[1][0];
		leftSide[2]=matrix[2][0];
		return leftSide;
	}

	/**
	 * This method returns the left side of a 2D matrix in a 1D vector
	 * @param matrix the matrix we want to get the left side from
	 * @return a vector representing the left side of a matrix
	 */
	public int[] upSide(int [][] matrix){
		int[] leftSide = new int[3];
		leftSide[0]=matrix[0][0];
		leftSide[1]=matrix[0][1];
		leftSide[2]=matrix[0][2];
		return leftSide;
	}
	
	/**
	 * This method returns the down side of a 2D matrix in a 1D vector
	 * @param matrix the matrix we want to get the down side from
	 * @return a vector representing the down side of a matrix
	 */
	public int[] downSide(int [][] matrix){
		int[] leftSide = new int[3];
		leftSide[0]=matrix[2][0];
		leftSide[1]=matrix[2][1];
		leftSide[2]=matrix[2][2];
		return leftSide;
	}
	
	/**
	 * Method to get the middle row of a cube's face
	 * @param matrix the current face of the cube
	 * @return a vector representing the middle row of the cube
	 */
	public int[] middleSide(int [][] matrix){
		int[] middleSide = new int[3];
		middleSide[0]=matrix[1][0];
		middleSide[1]=matrix[1][1];
		middleSide[2]=matrix[1][2];
		return middleSide;
	}


	/**
	 * Method to change the down side of a matrix with a 2D vector
	 * @param matrix the matrix we want to change
	 * @param vector the parameters to change to a certain vector in the matrix
	 * @return
	 */
	public int[][] changeDownSideInverse(int[][] matrix,int[]vector){
		int [][] theMatrix= new int[3][3];
		theMatrix=matrix;
		theMatrix[2][0]=vector[2];
		theMatrix[2][1]=vector[1];
		theMatrix[2][2]=vector[0];
		return theMatrix;
	}
	
	
	/**
	 * Method to change the down side of a matrix with a 2D vector
	 * @param matrix the matrix we want to change
	 * @param vector the parameters to change to a certain vector in the matrix
	 * @return
	 */
	public int[][] changeDownSide(int[][] matrix,int[]vector){
		int [][] theMatrix= new int[3][3];
		theMatrix=matrix;
		theMatrix[2][0]=vector[0];
		theMatrix[2][1]=vector[1];
		theMatrix[2][2]=vector[2];
		return theMatrix;
	}
	
	/**
	 * Method to alter the middle row of a cube's face
	 * @param matrix a face of the cube
	 * @param vector the middle row we want to change
	 * @return the altered face of the cube
	 */
	public int[][] changeMiddleSide(int[][] matrix,int[]vector){
		int [][] theMatrix= new int[3][3];
		theMatrix=matrix;
		theMatrix[1][0]=vector[0];
		theMatrix[1][1]=vector[1];
		theMatrix[1][2]=vector[2];
		return theMatrix;
	}
	
	
	/**
	 * Method to change the right side of a matrix with a 2D vector
	 * @param matrix the matrix we want to change
	 * @param vector the parameters to change to a certain vector in the matrix
	 * @return
	 */
	public int[][] changeRightSide(int[][] matrix,int[]vector){
		int [][] theMatrix= new int[3][3];
		theMatrix=matrix;
		theMatrix[0][2]=vector[0];
		theMatrix[1][2]=vector[1];
		theMatrix[2][2]=vector[2];
		return theMatrix;
	}
	
	
	
	/**
	 * Method to change the right side of a matrix with a 2D vector
	 * @param matrix the matrix we want to change
	 * @param vector the parameters to change to a certain vector in the matrix
	 * @return
	 */
	public int[][] changeRightSideInverse(int[][] matrix,int[]vector){
		int [][] theMatrix= new int[3][3];
		theMatrix=matrix;
		theMatrix[0][2]=vector[2];
		theMatrix[1][2]=vector[1];
		theMatrix[2][2]=vector[0];
		return theMatrix;
	}


	/**
	 * Method to change the left side of a matrix with a 2D vector
	 * @param matrix the matrix we want to change
	 * @param vector the parameters to change to a certain vector in the matrix
	 * @return
	 */
	public int[][] changeLeftSide(int[][] matrix,int[]vector){
		int [][] theMatrix= new int[3][3];
		theMatrix=matrix;
		theMatrix[0][0]=vector[0];
		theMatrix[1][0]=vector[1];
		theMatrix[2][0]=vector[2];
		return theMatrix;
	}
	
	/**
	 * Method to change the left side of a matrix with a 2D vector
	 * @param matrix the matrix we want to change
	 * @param vector the parameters to change to a certain vector in the matrix
	 * @return
	 */
	public int[][] changeLeftSideInverse(int[][] matrix,int[]vector){
		int [][] theMatrix= new int[3][3];
		theMatrix=matrix;
		theMatrix[0][0]=vector[2];
		theMatrix[1][0]=vector[1];
		theMatrix[2][0]=vector[0];
		return theMatrix;
	}

	/**
	 * Method to change the upper side of a matrix with a 1D vector
	 * @param matrix the matrix we want to change
	 * @param vector the parameters to change to a certain vector in the matrix
	 * @return
	 */
	public int[][] changeUpSide(int[][] matrix,int[]vector){
		int [][] theMatrix= new int[3][3];
		theMatrix=matrix;
		theMatrix[0][0]=vector[0];
		theMatrix[0][1]=vector[1];
		theMatrix[0][2]=vector[2];
		return theMatrix;
	}
	
	/**
	 * Method to change the upper side of a matrix with a 1D vector
	 * @param matrix the matrix we want to change
	 * @param vector the parameters to change to a certain vector in the matrix
	 * @return
	 */
	public int[][] changeUpSideInverse(int[][] matrix,int[]vector){
		int [][] theMatrix= new int[3][3];
		theMatrix=matrix;
		theMatrix[0][0]=vector[2];
		theMatrix[0][1]=vector[1];
		theMatrix[0][2]=vector[0];
		return theMatrix;
	}

	
	/**
	 * Method to check if the Rubiks cube has been solved
	 * @return true if the cube is solved and false otherwise
	 */
	public boolean isSolved(){
		int green = 1;
		int white = 2;
		int orange = 3;
		int yellow = 4;
		int red = 5;
		int blue = 6;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				//IF THE COLORS DONT MATCH THE SIDE WE RETURN FALSE
				if(this.green.matrix[i][j] != green || this.white.matrix[i][j]!=white || this.orange.matrix[i][j]!=orange || this.yellow.matrix[i][j]!=yellow || this.red.matrix[i][j]!=red || this.blue.matrix[i][j]!=blue){
					return false;
				}
			}
		}
		return true;
	}

	
	/**
	 * Method to check if the green side is solved
	 * @return true if the green side is completely green and false otherwise
	 */
	public boolean isBottomGreen(){
		int green = this.green.colorSide;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				// IF THERE'S A PIECE THAT IS NOT GREEN WE RETURN FALSE
				if(this.green.matrix[i][j] != green )
					return false;
			}
		}
		return true;
	}
	
	/**
	 * In this method we check if the green side has been solved, and if the corner pieces match the neighbor pieces
	 * @return true if the green side has only green tiles and the neighbors match. False otherwise
	 */
	public boolean isBottomGreenStep(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(this.greenFace.currentSide.matrix[i][j] != 1 )
					return false;
			}
		}
		
		//HERE WE CHECK THE NEIGHBORS
		if(this.greenFace.up.matrix[2][0] != 5 || this.greenFace.up.matrix[2][1] != 5 || this.greenFace.up.matrix[2][2] != 5)
			return false;
		if(this.greenFace.down.matrix[0][0] != 3 || this.greenFace.down.matrix[0][1] != 3 || this.greenFace.down.matrix[0][2] != 3)
			return false;
		if(this.greenFace.right.matrix[0][0] != 4 || this.greenFace.right.matrix[1][0] != 4 || this.greenFace.right.matrix[2][0] != 4)
			return false;
		if(this.greenFace.left.matrix[0][2] != 2 || this.greenFace.left.matrix[1][2] != 2 || this.greenFace.left.matrix[2][2] != 2)
			return false;
		
		return true;
	}

	/**
	 * Method to check if the coordinates: [0][1],[1][0],[2][1],[1][2] of the green matrix are green
	 * @return true if the green cross is there and false otherwise
	 */
	public boolean isBottomGreenCross() {
		boolean isCross=false;
		if(this.greenFace.currentSide.matrix[0][1] == 1 && this.greenFace.currentSide.matrix[1][0] ==1 &&  this.greenFace.currentSide.matrix[2][1] == 1 && this.greenFace.currentSide.matrix[1][2] == 1){
			if(this.greenFace.left.matrix[1][2] == 2 && this.greenFace.right.matrix[1][0]== 4 && this.greenFace.up.matrix[2][1]== 5 && this.greenFace.down.matrix[0][1]== 3){
				isCross = true;
			}
		}
		return isCross;
	}

	/**
	 * This method is to verify that the second layer of the cube has been finished
	 * @return true if the second layer is complete and false if the second layer needs fixes
	 */
	public boolean isSecondLayerDone() {
		if(!this.isBottomGreen()){
			return false;
		}
		for(int i=0;i<3;i++){
			if(this.greenFace.up.matrix[2][i] != 5 || this.greenFace.left.matrix[i][2] != 2 || this.greenFace.right.matrix[i][0] != 4|| this.greenFace.down.matrix[0][i] != 3){
				return false;
			}
			if(this.greenFace.up.matrix[1][i] != 5 || this.greenFace.left.matrix[i][1] != 2 || this.greenFace.right.matrix[i][1] != 4|| this.greenFace.down.matrix[1][i] != 3){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Method to check if the blue cross is done
	 * @return true if there's a cross, and false otherwise
	 */
	public boolean isBlueCross() {
		if(this.greenFace.back.matrix[0][1] == 6 && this.greenFace.back.matrix[1][0] == 6 && this.greenFace.back.matrix[1][1] == 6 && this.greenFace.back.matrix[1][2] == 6 && this.greenFace.back.matrix[2][1] == 6){
			return true;
		}
		return false;
	}
	
	
	/**
	 * Method to check if the blue bottom is done
	 * @return true if the entire bottom is blue, and false otherwise
	 */
	public boolean isBlueBottom() {
		int blue = this.blue.colorSide;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(this.blue.matrix[i][j] != blue )
					return false;
			}
		}
		return true;
	}
}
