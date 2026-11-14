# Missionaries and Cannibals Solver (Java)

A Java program that solves the classic **Missionaries and Cannibals** puzzle using **Breadth-First Search (BFS)**. It finds the **shortest sequence of boat crossings** that moves everyone safely across the river.

## Problem Statement

Three missionaries and three cannibals are on the left bank of a river with a boat that carries **at most 2 people**. The boat needs at least one person to cross.

**Rule:** On either bank, if missionaries are present, cannibals must **not outnumber** them. Otherwise the missionaries get eaten.

**Goal:** Move all 6 people to the right bank safely.

## Approach

- **State**: `(m, c, boat)`
  - `m` = missionaries on the left bank
  - `c` = cannibals on the left bank
  - `boat` = `0` if the boat is on the left, `1` if on the right
- **Start state**: `(3, 3, 0)`  **Goal state**: `(0, 0, 1)`
- **Moves**: the boat carries `(missionaries, cannibals)` as one of `(1,0)`, `(2,0)`, `(0,1)`, `(0,2)`, `(1,1)`.
  - Boat on left: people are subtracted from the left bank.
  - Boat on right: people are added back to the left bank.
- **Validity checks** on every new state:
  1. Counts stay between 0 and 3.
  2. Left bank is safe: `m == 0` or `c <= m`.
  3. Right bank is safe: `(3-m) == 0` or `(3-c) <= (3-m)`.
- **Search**: BFS with a queue, so the first solution found is the one with the **fewest crossings**.
- **Visited set**: a `HashSet<State>` avoids revisiting states and infinite loops.
- **Path tracking**: each `Node` stores its state and the path of states taken to reach it.

## Project Structure

```
MissionariesAndCannibals.java
 ├── State  -> holds (m, c, boat); overrides equals() and hashCode()
 ├── Node   -> a State plus the path leading to it
 ├── missionariesCannibals() -> BFS solver
 └── main() -> runs the solver
```

## How to Run

**Requirements:** JDK 8 or higher

```bash
javac MissionariesAndCannibals.java
java MissionariesAndCannibals
```

No input is needed; the problem is fixed at 3 missionaries and 3 cannibals.

## Sample Output

```
Solution Found!
Step 0: Missionaries left = 3, Cannibals left = 3, Boat = Left
Step 1: Missionaries left = 3, Cannibals left = 1, Boat = Right
Step 2: Missionaries left = 3, Cannibals left = 2, Boat = Left
Step 3: Missionaries left = 3, Cannibals left = 0, Boat = Right
Step 4: Missionaries left = 3, Cannibals left = 1, Boat = Left
Step 5: Missionaries left = 1, Cannibals left = 1, Boat = Right
Step 6: Missionaries left = 2, Cannibals left = 2, Boat = Left
Step 7: Missionaries left = 0, Cannibals left = 2, Boat = Right
Step 8: Missionaries left = 0, Cannibals left = 3, Boat = Left
Step 9: Missionaries left = 0, Cannibals left = 1, Boat = Right
Step 10: Missionaries left = 1, Cannibals left = 1, Boat = Left
Step 11: Missionaries left = 0, Cannibals left = 0, Boat = Right

Total crossings: 11
```

The minimum is **11 crossings**.

## Complexity

- **Time:** O(M × C × 2) states, which is a tiny constant here (at most 32 states)
- **Space:** O(M × C × 2) for the visited set and queue, plus path copies stored per node

## Limitations

- The numbers of missionaries (3), cannibals (3) and boat capacity (2) are hard-coded.
- Each queue node stores a full copy of its path, which is simple but memory-heavy for larger versions.

## Possible Improvements

- Make the counts and boat capacity configurable
- Use parent pointers instead of copying paths
- Show both banks and the boat's direction in the output
- Generate moves automatically from the boat capacity

## Concepts Used

BFS, queues, HashSet, state-space search, constraint checking, `equals()`/`hashCode()` overriding, static nested classes.
