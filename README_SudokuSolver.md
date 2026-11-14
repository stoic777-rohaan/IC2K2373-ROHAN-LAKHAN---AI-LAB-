# Sudoku Solver (Java)

A Java program that solves a 9x9 Sudoku puzzle using **Backtracking**. The user enters the puzzle row by row (with `0` for empty cells), the program validates it, and then prints the solved grid.

## Problem Statement

Fill a 9x9 grid so that every **row**, every **column**, and every **3x3 sub-grid** contains the digits 1 to 9 exactly once. Some cells are pre-filled; the rest must be completed.

## Approach

- **Representation**: a `int[9][9]` grid where `0` means an empty cell.
- **Algorithm**: recursive **backtracking** (a form of Depth-First Search):
  1. Find the first empty cell.
  2. Try digits 1 to 9 in that cell.
  3. If a digit is valid, place it and recurse to solve the rest.
  4. If the recursion fails, **undo** the placement (set back to `0`) and try the next digit.
  5. If no digit works, return `false` so the previous cell tries a different value.
  6. If no empty cell remains, the puzzle is solved.
- **Validity check (`isValid`)**: a digit can be placed at `(row, col)` only if it does not already appear in:
  - the same row
  - the same column
  - the same 3x3 sub-grid (top-left corner found using `(row / 3) * 3` and `(col / 3) * 3`)
- **Input validation**: each row must have exactly 9 integers between 0 and 9; otherwise the program asks again.
- **Initial puzzle check**: before solving, every pre-filled number is checked with `isValid` to catch duplicates. If any are found, the program prints `Invalid Sudoku! Duplicate numbers found.`

## Project Structure

```
SudokuSolver.java
 ├── printGrid(grid)                 -> prints the 9x9 grid
 ├── isValid(grid, row, col, num)    -> checks row, column and 3x3 box
 ├── solveSudoku(grid)               -> recursive backtracking solver
 └── main()                          -> input, validation, solving, output
```

## How to Run

**Requirements:** JDK 8 or higher

```bash
javac SudokuSolver.java
java SudokuSolver
```

Enter each row as 9 space-separated numbers, using `0` for empty cells.

## Sample Run

**Input**

```
Row 1: 5 3 0 0 7 0 0 0 0
Row 2: 6 0 0 1 9 5 0 0 0
Row 3: 0 9 8 0 0 0 0 6 0
Row 4: 8 0 0 0 6 0 0 0 3
Row 5: 4 0 0 8 0 3 0 0 1
Row 6: 7 0 0 0 2 0 0 0 6
Row 7: 0 6 0 0 0 0 2 8 0
Row 8: 0 0 0 4 1 9 0 0 5
Row 9: 0 0 0 0 8 0 0 7 9
```

**Output**

```
Solved Sudoku:
5 3 4 6 7 8 9 1 2
6 7 2 1 9 5 3 4 8
1 9 8 3 4 2 5 6 7
8 5 9 7 6 1 4 2 3
4 2 6 8 5 3 7 9 1
7 1 3 9 2 4 8 5 6
9 6 1 5 3 7 2 8 4
2 8 7 4 1 9 6 3 5
3 4 5 2 8 6 1 7 9
```

## Possible Outcomes

| Situation | Output |
|-----------|--------|
| Puzzle has a solution | `Solved Sudoku:` followed by the grid |
| Pre-filled numbers conflict | `Invalid Sudoku! Duplicate numbers found.` |
| Valid start but unsolvable | `No solution exists!` |
| Bad row input | `Enter exactly 9 numbers between 0 and 9.` (re-prompts) |

## Complexity

- **Time:** worst case O(9^m), where `m` is the number of empty cells (in practice much faster because invalid digits are pruned early)
- **Space:** O(m) for the recursion stack, plus O(1) for the fixed 9x9 grid

## Limitations

- Always scans from the top-left to find the next empty cell, which is simple but not the fastest strategy.
- Does not check whether the solution is unique; it returns the first one found.
- Console input only.

## Possible Improvements

- Choose the empty cell with the fewest candidates (MRV heuristic) to speed up solving
- Use boolean arrays for rows, columns and boxes to make validity checks O(1)
- Read puzzles from a file
- Count the number of solutions to check uniqueness

## Concepts Used

Backtracking, recursion, DFS-style search, constraint checking, 2D arrays, input validation with `Scanner`.
