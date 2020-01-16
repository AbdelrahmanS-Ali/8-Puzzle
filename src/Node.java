import java.util.ArrayList;

public class Node {
	private String state;
	private ArrayList<Node> children;
	private Node parent;
	private int estimatedCostToGoal;
	private int totalCost;
	private int depth;
	private int actualCost;

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int actualCost, int estimatedCost) {
		this.totalCost = actualCost + estimatedCost;
	}

	public int getActualCost() {
		return actualCost;
	}

	public void setActualCost(int actualCost) {
		this.actualCost = actualCost;
	}

	public int getEstimatedCostToGoal() {
		return estimatedCostToGoal;
	}

	public void setEstimatedCostToGoal(int estimatedCost) {
		this.estimatedCostToGoal = estimatedCost;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node(String state) {
		this.state = state;
		children = new ArrayList<Node>();
	}

	public Node() {
		children = new ArrayList<Node>();
	}

	public String getState() {
		return state;
	}

	public void addChild(Node child) {
		children.add(child);
	}
}