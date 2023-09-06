import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Battleship {
	private int[][] grid;
	int gridSize;
	int[] colAmounts;
	int[] rowAmounts;
	int[] currentShips = new int[3];
	int fourSegCount;
	int threeSegCount;
	int twoSegCount;
	int oneSegCount;
	int totalCount;
	
	/**
	 * Code for Battleship solver, to read in a grid and print solution, based off
	 *  of all constraints
	 * @author Mia Damato
	 *
	 */
	
	/**
	 * Constructor to read in file and create grid
	 * @param filename file that contains the board and requirements
	 */
	public Battleship(String filename) {
		File file = new File(filename);
		
		Scanner input;
		
		try {
			input = new Scanner(file);
			while (input.hasNextLine()) {
				this.gridSize = input.nextInt();
				this.fourSegCount = input.nextInt();
				this.threeSegCount = input.nextInt();
				this.twoSegCount = input.nextInt();
				this.oneSegCount = input.nextInt();
				this.totalCount = fourSegCount + threeSegCount + twoSegCount + oneSegCount;
				
				this.grid = new int[gridSize][gridSize];
				input.nextLine();
				//read in column labels
				String colLabelsString = input.nextLine();
				String[] colLabelsStringArray = colLabelsString.split(" ");
				
				this.colAmounts = new int[gridSize];
				for (int x = 0; x < gridSize; x++) {
					colAmounts[x] = Integer.parseInt(colLabelsStringArray[x]);
				}
				
				this.rowAmounts = new int[gridSize];
				
				//read in row labels
				for (int row = 0; row < gridSize ; row++) {
					String thisLine = input.nextLine();
					rowAmounts[row] = thisLine.charAt(0);
					char[] lineArray = thisLine.toCharArray();
					//read in grid values
					for (int col = 1; col <= gridSize; col++) {
						//if grid space is empty
						if (lineArray[col] == ' ') {
							grid[row][col-1] = 0;
						}
						//if grid space is water
						else if (lineArray[col] == '~') {
							grid[row][col-1] = 1;
						}
						//if grid space is ship
						else if (lineArray[col] == 'B') {
							//add to current row/column count ?
							grid[row][col-1] = 2;
						}
					}
					
				}
					
			}
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Calls other solve function
	 * @return boolean 
	 */
	public boolean solve() {
		return solve(0,0);
	}
	
	/**
	 * For every empty cell, checks if each possible value can be placed, and places it if it can. 
	 * Runs until all cells are filled (non-zero; 1(water) or 2(boat))
	 * 
	 * @param row beginning at 0, represents the row value of the current cell
	 * @param col beginning at 0, represents the column value of the current cell
	 * @return boolean true if grid is solved, false if not yet solved
	 */
	public boolean solve(int row, int col) {
		//while grid is not full
		if (row == gridSize) {  //checks if all rows are filled
			return true;
		}
		if (col == gridSize) {  //checks if all co
			return solve(row+1,0);
		}
		//find empty cell
		if (this.grid[row][col] != 0) { //spot is already filled
			return solve(row, col+1);
		}
		//for each possible value (1(water), 2(boat))
		for (int c = 1; c < 3; c++) {
			if(canPlace(c, row, col)) { //checks constraints
				this.grid[row][col] = c;
				if(solve(row, col+1)) {
					return true;
				} 
				this.grid[row][col] = 0;
			}
			
		}
		return false;
	}
	
	/**
	 * Tests value in the given cells based off of 4 constraints: Total row amount of ship segments, total column amount
	 * of ship segments, surrounding diagonal cells, and total ships needed for the board to be solved
	 * @param val value (1 or 2) being tested
	 * @param row Represents row value of current cell
	 * @param col Represents column value of current cell
	 * @return boolean true if value can be placed in the given cell, false if not
	 */
	private boolean canPlace(int val, int row, int col) {
		//constraints:
		//if row amount is not met
		//if column amount is not met
		//if any surrounding cells are ships
		//if ship sizes are not met
		//blob count !! (start at the largest)
		
		
		return checkRow(val,row) && checkCol(val, col) 
				&& checkSurrounding(val, row, col) && checkShips(); 
	}
	
	/**
	 * Checks if the row total has been met/can be met for the given cell's row
	 * @param val value being checked (0 or 1)
	 * @param row row of the cell
	 * @return boolean true if row conditions allow for value to be placed, false if not
	 */
	private boolean checkRow(int val, int row) {
		
		
		//calculate row total
		int currRowTotal = 0;
		//for each cell in row
		for (int v = 0; v < gridSize; v ++) {
			//if a cell has a ship part, add to currRowTotal
			if(this.grid[row][v] == 2) {
				currRowTotal ++ ;
			}
		}
		//if boat segment is being checked	
		if (val == 2) {
			//checks if row total is met or 0
			if (rowAmounts[row] == 0 | currRowTotal == rowAmounts[row]) {
				return false;
			}
		}
		//if water is being checked
		else if (val == 1) {
			//check if enough cells will be left in row for required boats
			int emptyRowCells = 0;
			for (int i = 0; i < gridSize; i++) {
				if (grid[row][i] == 0) {
					emptyRowCells++;
				}
			}
			
			if ((rowAmounts[row] - currRowTotal) > emptyRowCells) {
				return false;
			}
			
		}
		return true;
			
	}
	/**
	 * Checks if the column total has been met/can be met for the given cell's column
	 * @param val value being checked (0 or 1)
	 * @param col column of the cell
	 * @return boolean true if column conditions allow for value to be placed, false if not
	 */
	private boolean checkCol(int val, int col) {
		//calculate column total
		int currColTotal = 0;
		//for each cell in column
		for (int v = 0; v < gridSize; v ++) {
			//if a cell has a ship part, add to currColTotal
			if(this.grid[v][col] == 2) {
				currColTotal ++ ;
			}
		}
		//if boat
		if (val == 2) {		
			//checks if column total is met or 0
			if (colAmounts[col] == 0 | currColTotal == colAmounts[col]) {
				return false;
			}
		}	
		else if (val == 1) {
			//check if enough cells will be left in row for required boats
			int emptyColCells = 0;
			for (int i = 0; i < gridSize; i++) {
				if (grid[i][col] == 0) {
					emptyColCells++;
				}
			}
			
			if ((colAmounts[col] - currColTotal) > emptyColCells) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if surrounding (diagonal) cells inhibit ship segment from being placed in cell
	 * @param val value being checked for given cell
	 * @param row row value for given cell
	 * @param col column value for given cell
	 * @return boolean true if can be placed based off of diagonal cells (if ship value), false if not (or if water value)
	 */
	private boolean checkSurrounding(int val, int row, int col) {
		//if value is ship and NOT a cell on the edges:
		if (val == 2) {
			if (row > 0 && col > 0 && row < gridSize && col < gridSize) {
				//checks if diagonal cells have a ship part
				if (this.grid[row+1][col+1] == 2 | this.grid[row+1][col-1] == 2 | this.grid[row-1][col-1] == 2 | this.grid[row-1][col-1] == 2) {
					return false;
				}
			}
			//top left corner
			else if (row == gridSize && col == 0) {
				if (this.grid[row-1][col+1] == 2) {
					return false;
				}
			}
			//
			//else if (row ) {
				
			//}
		}
		return true;
	}
	
	/**
	 * 
	 * @return boolean 
	 */
	private boolean checkShips() {
		//boolean for if ship sizes allow for ship segment to be placed
		
		this.findShips();
		
		
		
		//use a variable for num ships available; 1-segments will be double
		
		int numCurrent = currentShips[3] + currentShips[2]+ currentShips[1] + currentShips[0];
		int numAvailable = totalCount - numCurrent;  //difference between number of ships needed and currently placed
		
		//if total count is not met (at least one ship is still needed)
		if (numAvailable > 0) {
			//check four-segment boats (has to equal total count)
			if (currentShips[3] < fourSegCount) {
				return true;
			}
			//check three-segment boats (can be higher than total count)
			else if (((threeSegCount - currentShips[2]) >= numAvailable ) | (currentShips[2] < threeSegCount)) {
				return true;
			}
			//check two-segment boats (can be higher than total count)
			else if (((twoSegCount - currentShips[1]) >= numAvailable )| (currentShips[1] < twoSegCount)) {
				return true;
			}
			//check one-segment boats (can be higher than total count)
			else if (((oneSegCount - currentShips[0]) >= numAvailable ) | (currentShips[0] < oneSegCount)) {
				return true;
			}
			
		}
		return false;
		
	}
	
	/**
	 * 
	 */
	public void findShips() {
	    for (int row = 0; row < this.grid.length; row++) {
	        for (int col = 0; col < this.grid.length; col++) {
	            if (this.grid[row][col] == 2) {
	                //store ship values
	            	if (this.countShip(row, col) == 1) {
	            		this.currentShips[0] ++;
	            	}
	            	else if (this.countShip(row,col) == 2) {
	            		this.currentShips[1] ++;
	            	}
	            	else if (this.countShip(row, col) == 3) {
	            		this.currentShips[2] ++;
	            	}
	            	else if (this.countShip(row, col) == 4) {
	            		this.currentShips[3] ++;
	            	}
	            	
	       
	            }
	        }
	    }

	    for (int row = 0; row < this.grid.length; row++) {
	        for (int col = 0; col < this.grid.length; col++) {
	            if (this.grid[row][col] == 1) {
	                this.grid[row][col] = 2;
	            }
	        }
	    }
	  }

	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private int countShip(int row, int col) {
		if (row < 0 || row >= this.grid.length ||
	            col < 0 || col >= this.grid.length || 
	            this.grid[row][col] != 2) {
	        return 0;
	    }
	    else {
	        this.grid[row][col] = 0;    
	        return 1 + this.countShip(row-1, col-1)
	                 + this.countShip(row-1,   col)
	                 + this.countShip(row-1, col+1)
	                 + this.countShip(  row, col-1)
	                 + this.countShip(  row, col+1)
	                 + this.countShip(row+1, col-1)
	                 + this.countShip(row+1,   col)
	                 + this.countShip(row+1, col+1);
	    }
	}

	/**
	 * Presents the given battleship grid as a String
	 * @return gridString the formatted grid string/image
	 */
	public String toString() {
		String output = "";
	
		
		
		output = colAmounts + "\n-";
		
		for (int x = 1; x < gridSize; x++) {
			output = output + "-";
		}
			
		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {
				output = output + rowAmounts[row] + '|' + grid[row][col];
				
			}
			output = output + "|\n";
		}
	
			
		for (int x = 1; x < gridSize; x++) {
			output = output + '-';
		}
		
		return output;

	}
	
	//constraints are different; but mostly similar to sudoku (boat or water instead of numbers)
	//water will have constraints; can place water if when u place water there will still be enough spots to place boats
	
}
