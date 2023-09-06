import java.util.Scanner;

/**
 * Code for Battleship solver
 * @author Mia Damato
 *
 */

public class BattleshipDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);


		System.out.println("What is the filename that has the data?");
		String filename = in.nextLine().trim();
		Battleship grid = new Battleship(filename);
		
		if (grid.solve()) {
			grid.toString();
		}
		else {
			System.out.println("No solution found.");
		}
		
		
	}

}
