import java.util.*;

public class MissionariesAndCannibals {

    static class State {
        int m, c, boat;

        public State(int m, int c, int boat) {
            this.m = m; rohan
                ??????????????????????????????????
            this.c = c;
            this.boat = boat;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return m == state.m && c == state.c && boat == state.boat;
        }

        @Override
        public int hashCode() {
            return Objects.hash(m, c, boat);
        }
    }

    static class Node {
        State state;
        List<State> path;

        public Node(State state, List<State> path) {
            this.state = state;
            this.path = path;
        }
    }

    public static void missionariesCannibals() {
        State start = new State(3, 3, 0);
        State goal = new State(0, 0, 1);

        int[][] moves = {
            {1, 0},
            {2, 0},
            {0, 1},
            {0, 2},
            {1, 1}
        };

        Queue<Node> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        List<State> initialPath = new ArrayList<>();
        initialPath.add(start);

        queue.offer(new Node(start, initialPath));
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            State state = current.state;
            List<State> path = current.path;

            if (state.equals(goal)) {
                System.out.println("\nSolution Found!");

                for (int step = 0; step < path.size(); step++) {
                    State s = path.get(step);
                    String side = (s.boat == 0) ? "Left" : "Right";
                    System.out.println("Step " + step + ": Missionaries left = " + s.m + ", Cannibals left = " + s.c + ", Boat = " + side);
                }

                System.out.println("\nTotal crossings: " + (path.size() - 1));
                return;
            }

            int m = state.m;
            int c = state.c;
            int boat = state.boat;

            for (int[] move : moves) {
                int moveM = move[0];
                int moveC = move[1];

                State newState;
                if (boat == 0) {
                    newState = new State(m - moveM, c - moveC, 1);
                } else {
                    newState = new State(m + moveM, c + moveC, 0);
                }

                int newM = newState.m;
                int newC = newState.c;

                if (!(newM >= 0 && newM <= 3 && newC >= 0 && newC <= 3)) {
                    continue;
                }

                int rightM = 3 - newM;
                int rightC = 3 - newC;

                if (newM > 0 && newC > newM) {
                    continue;
                }

                if (rightM > 0 && rightC > rightM) {
                    continue;
                }

                if (!visited.contains(newState)) {
                    visited.add(newState);
                    List<State> newPath = new ArrayList<>(path);
                    newPath.add(newState);
                    queue.offer(new Node(newState, newPath));
                }
            }
        }

        System.out.println("No solution exists.");
    }

    public static void main(String[] args) {
        missionariesCannibals();
    }
}
