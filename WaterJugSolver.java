import java.util.*;

public class WaterJugSolver {

    static class State {
        int a, b;

        public State(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return a == state.a && b == state.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

        @Override
        public String toString() {
            return "(" + a + ", " + b + ")";
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

    public static void waterJug(int capacityA, int capacityB, int target) {
        if (target > Math.max(capacityA, capacityB)) {
            System.out.println("Target cannot be measured.");
            return;
        }

        State start = new State(0, 0);

        Queue<Node> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        List<State> initialPath = new ArrayList<>();
        initialPath.add(start);
        
        queue.offer(new Node(start, initialPath));
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            State currState = current.state;
            List<State> path = current.path;
            
            int a = currState.a;
            int b = currState.b;

            if (a == target || b == target) {
                System.out.println("\nSolution Found!");
                System.out.println("Step 0: " + path.get(0));

                for (int i = 1; i < path.size(); i++) {
                    System.out.println("Step " + i + ": " + path.get(i));
                }

                System.out.println("\nTotal steps: " + (path.size() - 1));
                System.out.println("Final state: " + path.get(path.size() - 1));
                return;
            }

            List<State> nextStates = new ArrayList<>();
            nextStates.add(new State(capacityA, b));
            nextStates.add(new State(a, capacityB));
            nextStates.add(new State(0, b));
            nextStates.add(new State(a, 0));

            int pourAB = Math.min(a, capacityB - b);
            nextStates.add(new State(a - pourAB, b + pourAB));

            int pourBA = Math.min(b, capacityA - a);
            nextStates.add(new State(a + pourBA, b - pourBA));

            for (State state : nextStates) {
                if (!visited.contains(state)) {
                    visited.add(state);
                    List<State> newPath = new ArrayList<>(path);
                    newPath.add(state);
                    queue.offer(new Node(state, newPath));
                }
            }
        }

        System.out.println("No solution exists.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter capacity of Jug A: ");
        int capacityA = scanner.nextInt();

        System.out.print("Enter capacity of Jug B: ");
        int capacityB = scanner.nextInt();

        System.out.print("Enter target amount: ");
        int target = scanner.nextInt();

        waterJug(capacityA, capacityB, target);

        scanner.close();
    }
}