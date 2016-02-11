package solver;

import java.util.Scanner;

public class MatrixSolver {

	private static Scanner keyb = new Scanner(System.in);

	/**
	 * Handles the creation and manipulation of a matrix Using a simple user
	 * interface
	 * 
	 * @param args
	 *            Command line arguments, currently not used
	 */
	public static void main(String[] args) {
		// Get matrix dimensions from user
		System.out.print("Enter height of matrix: ");
		int rows = Integer.parseInt(keyb.nextLine());
		System.out.print("Enter width of matrix: ");
		int columns = Integer.parseInt(keyb.nextLine());

		// Get all elements of the matrix from the user
		Matrix userMatrix = buildMatrix(columns, rows);

		// Show the menu
		System.out.println("\n~~~~~~MENU~~~~~~");
		System.out.println("1.Solve manually");
		System.out.println("2.Solve via AI");
		System.out.println("~~~~~~~~~~~~~~~~");

		// Get an option from the user
		System.out.print("Enter solve method: ");
		int choice = Integer.parseInt(keyb.nextLine());

		// Make a decision based on the input
		switch (choice) {
		case 1:
			userGuidedSolve(userMatrix);
			break;
		case 2:
			SolverAI ai = new SolverAI(userMatrix);
			ai.exampleSolve();
			break;
		default:
			System.out.println("Invalid choice!");
			System.out.println("~~~MATRIX~~~\n" + userMatrix);
			break;
		}

		// Program ends here, close scanner
		keyb.close();
	}

	/**
	 * Get the matrix from the user
	 * 
	 * @param columns
	 *            The number of columns of the matrix
	 * @param rows
	 *            The number of rows of the matrix
	 * @return A new matrix from the user's input
	 */
	private static Matrix buildMatrix(int columns, int rows) {
		// Allocate space for a 2d array
		int[][] userMatrix = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			// Get a row at a time from the user, trim is and split at spaces
			System.out.printf("Enter %d elements for row %d: ", columns, i + 1);
			String[] elements = keyb.nextLine().trim().split(" ");

			// Iterate through entered row and add each element to the array
			for (int j = 0; j < columns; j++) {
				userMatrix[i][j] = Integer.parseInt(elements[j]);
			}
		}

		// Return a matrix created using the 2d array
		return new Matrix(userMatrix);
	}

	/**
	 * Lets the user manually solve a matrix using basic row operations
	 * 
	 * @param matrix
	 *            The matrix to solve
	 */
	private static void userGuidedSolve(Matrix matrix) {
		// Some variables to store options
		int firstRow, secondRow, firstMultiplier, secondMultiplier;

		// Print starting matrix
		System.out.print("\n~~~MATRIX~~~\n" + matrix);

		// Loop variables
		int choice = 0, count = 0;

		// Loop until user enters a negative
		while (choice > -1) {
			// Print the matrix each iteration
			if (count == 0) {
				System.out.print("\n~~~START~~~\n" + matrix);
			} else {
				System.out.printf("\n~~~AFTER STEP %d~~~\n%s", count, matrix);
			}
			count++;

			// Print the menu
			System.out.println("~~~~~~~~~~~~~~MANUAL~~~~~~~~~~~~~~");
			System.out.println("1.Add rows");
			System.out.println("2.Swap rows");
			System.out.println("3.Divide row by constant");
			System.out.println("4.Multiply row by constant");
			System.out.println("5.Multiply by constant and add rows");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			// Get input from the user
			System.out.print("Enter option (-1 to quit): ");
			choice = Integer.parseInt(keyb.nextLine());

			// Print the matrix so the user can choose rows for operations
			System.out.print("~~~MATRIX~~~\n" + matrix);

			// Run different operations for each operation
			switch (choice) {
			case 1:
				// Add rows
				System.out.print("Enter row to add to: ");
				firstRow = Integer.parseInt(keyb.nextLine());

				System.out.print("Enter row to add to " + firstRow + ": ");
				secondRow = Integer.parseInt(keyb.nextLine());

				matrix.addRows(firstRow - 1, secondRow - 1);
				break;
			case 2:
				// Swap rows
				System.out.print("Enter row one: ");
				firstRow = Integer.parseInt(keyb.nextLine());

				System.out.print("Enter row to swap with: ");
				secondRow = Integer.parseInt(keyb.nextLine());

				matrix.swapRows(firstRow - 1, secondRow - 1);
				break;
			case 3:
				// Divide row by constant
				System.out.print("Enter row to divide: ");
				firstRow = Integer.parseInt(keyb.nextLine());

				System.out.print("Enter constant to divide by: ");
				firstMultiplier = Integer.parseInt(keyb.nextLine());

				matrix.divideRow(firstRow - 1, firstMultiplier);
				break;
			case 4:
				// Multiply row by constant
				System.out.print("Enter row to multiply: ");
				firstRow = Integer.parseInt(keyb.nextLine());

				System.out.print("Enter constant to multiply by: ");
				firstMultiplier = Integer.parseInt(keyb.nextLine());

				matrix.multiplyRow(firstRow - 1, firstMultiplier);
				break;
			case 5:
				// Multiply and add
				System.out.print("Enter row to multiply and add to: ");
				firstRow = Integer.parseInt(keyb.nextLine());

				System.out.print("Enter constant to multiply " + firstRow
						+ " by: ");
				firstMultiplier = Integer.parseInt(keyb.nextLine());

				System.out.print("Enter row to multiply and add to " + firstRow
						+ ": ");
				secondRow = Integer.parseInt(keyb.nextLine());

				System.out.print("Enter constant to multiply " + secondRow
						+ " by: ");
				secondMultiplier = Integer.parseInt(keyb.nextLine());

				matrix.multiplyAndAddRows(firstRow - 1, firstMultiplier,
						secondRow - 1, secondMultiplier);
				break;
			default:
				break;
			}
		}
	}
}