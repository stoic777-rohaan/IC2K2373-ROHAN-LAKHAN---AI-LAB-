import java.util.Scanner;

public class SudokuSolver {

    // Print the 9x9 grid nicely
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < row.length; i++) {
                sb.append(row[i]);
                if (i < row.length - 1) {
                    sb.append(" ");
                }
            }
            System.out.println(sb.toString());
        }
    }

    // Check if a number can be placed at grid[row][col]
    public static boolean isValid(int[][] grid, int row, int col, int num) {
        // Check row
        for (int j = 0; j < 9; j++) {
            if (j != col && grid[row][j] == num) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (i != row && grid[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if ((i != row || j != col) && grid[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Solve Sudoku using Backtracking
    public static boolean solveSudoku(int[][] grid) {
        // Find empty cell (0 means empty)
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(grid, row, col, num)) {
                            grid[row][col] = num;

                            if (solveSudoku(grid)) {
                                return true;
                            }

                            // Backtracking
                            grid[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Sudoku (0 for empty cells):");

        int[][] grid = new int[9][9];

        // Take input row by row
        for (int i = 0; i < 9; i++) {
            while (true) {
                System.out.print("Row " + (i + 1) + ": ");
                String line = scanner.nextLine().trim();
                String[] parts = line.split("\\s+");

                if (parts.length == 9) {
                    boolean validRow = true;
                    int[] tempRow = new int[9];
                    try {
                        for (int j = 0; j < 9; j++) {
                            int val = Integer.parseInt(parts[j]);
                            if (val < 0 || val > 9) {
                                validRow = false;
                                break;
                            }
                            tempRow[j] = val;
                        }
                    } catch (NumberFormatException e) {
                        validRow = false;
                    }

                    if (validRow) {
                        grid[i] = tempRow;
                        break;
                    }
                }
                System.out.println("Enter exactly 9 numbers between 0 and 9.");
            }
        }

        // Validate original Sudoku
        boolean valid = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = grid[i][j];
                if (num != 0) {
                    if (!isValid(grid, i, j, num)) {
                        valid = false;
                        break;
                    }
                }
            }
            if (!valid) {
                break;
            }
        }

        if (!valid) {
            System.out.println("Invalid Sudoku! Duplicate numbers found.");
        } else {
            System.out.println("\nOriginal Sudoku:");
            printGrid(grid);

            if (solveSudoku(grid)) {
                System.out.println("\nSolved Sudoku:");
                printGrid(grid);
            } else {
                System.out.println("\nNo solution exists!");
            }
        }

        scanner.close();
    }
}