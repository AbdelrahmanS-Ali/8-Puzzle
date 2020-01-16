import java.util.*;

public class SearchTree {
	private Node root;
	private String goalSate;
	private int depth = 0;

	public SearchTree(Node root, String goalSate) {
		this.root = root;
		this.goalSate = goalSate;
	}

	public void BFS() {
		Queue<Node> frontier = new LinkedList<Node>();
		Node initialState = new Node(root.getState());
		frontier.add(initialState);

		Set<String> explored = new HashSet<String>();

		Node state = new Node();

		while (!frontier.isEmpty()) {
			state = frontier.poll();
			System.out.println(state.getState());

			explored.add(state.getState());
			if (state.getDepth() > depth) {
				depth = state.getDepth();
			}

			if (state.getState().equals(goalSate))
				break;

			List<String> stateNeighbors = NodeUtil.getSuccessors(state.getState(), "BFS");
			for (String n : stateNeighbors) {
				if (explored.contains(n))
					continue;
				Node neighbor = new Node(n);
				neighbor.setDepth(state.getDepth() + 1);
				state.addChild(neighbor);
				neighbor.setParent(state);
				frontier.add(neighbor);
			}

		}
		NodeUtil.printSolution("BFS", state, explored, root, depth);
	}

	public void DFS() {
		Stack<Node> frontier = new Stack<Node>();
		Node initialState = new Node(root.getState());
		initialState.setDepth(0);
		frontier.add(initialState);

		Set<String> explored = new HashSet<String>();

		Node state = new Node();

		while (!frontier.isEmpty()) {
			state = frontier.pop();
			System.out.println(state.getState());
			if (state.getDepth() > depth) {
				depth = state.getDepth();
			}
			explored.add(state.getState());

			if (state.getState().equals(goalSate))
				break;

			List<String> stateNeighbors = NodeUtil.getSuccessors(state.getState(), "DFS");
			for (String n : stateNeighbors) {
				if (explored.contains(n))
					continue;
				Node neighbor = new Node(n);
				neighbor.setDepth(state.getDepth() + 1);
				state.addChild(neighbor);
				neighbor.setParent(state);
				frontier.push(neighbor);
			}

		}
		NodeUtil.printSolution("DFS", state, explored, root, depth);
	}

	public void aStar(int heuristic) {

		NodePriorityComparator nodePriorityComparator = new NodePriorityComparator();
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(10, nodePriorityComparator);
		Node initialState = new Node(root.getState());

		frontier.add(initialState);

		Set<String> explored = new HashSet<String>();

		Node state = new Node();
		state.setTotalCost(0, 0);

		while (!frontier.isEmpty()) {
			state = frontier.poll();
			System.out.println(state.getState());
			if (state.getDepth() > depth) {
				depth = state.getDepth();
			}

			explored.add(state.getState());

			if (state.getState().equals(goalSate))
				break;

			List<String> stateNeighbors = NodeUtil.getSuccessors(state.getState(), "A");
			for (String n : stateNeighbors) {
				if (explored.contains(n))
					continue;
				Node neighbor = new Node(n);
				neighbor.setDepth(state.getDepth() + 1);
				state.addChild(neighbor);
				neighbor.setParent(state);
				if (heuristic == 1)
					neighbor.setTotalCost(state.getActualCost() + 1, manhattan(neighbor.getState(), goalSate));
				else if (heuristic == 2)
					neighbor.setTotalCost(state.getActualCost() + 1, euclidean(neighbor.getState(), goalSate));
				frontier.add(neighbor);
			}

		}
		NodeUtil.printSolution("A", state, explored, root, depth);
	}

	private int manhattan(String currentState, String goalSate) {
		int manhattan = 0;
		for (int i = 0; i < currentState.length(); i += 1)
			for (int j = 0; j < goalSate.length(); j += 1)
				if (currentState.charAt(i) == goalSate.charAt(j))
					manhattan = manhattan + (Math.abs(i % 3 - j % 3) + Math.abs(i / 3 - j / 3));
		return manhattan;
	}

	private int euclidean(String currentState, String goalSate) {
		int euclidean = 0;
		for (int i = 0; i < currentState.length(); i += 1)
			for (int j = 0; j < goalSate.length(); j += 1)
				if (currentState.charAt(i) == goalSate.charAt(j))
					euclidean = euclidean
							+ (int) Math.sqrt((i % 3 - j % 3) * (i % 3 - j % 3) + (i / 3 - j / 3) * (i / 3 - j / 3));
		return euclidean;
	}

}