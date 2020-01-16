
public class solve {
	final static private String GOAL_STATE = "012345678";

	public static void main(String[] args) {
		String initailState = "103245678";
		long startTime = System.currentTimeMillis();

		SearchTree search = new SearchTree(new Node(initailState), GOAL_STATE);
//		 search.aStar(1);
		search.aStar(2);
//		 search.BFS();
//		 search.DFS();

		long finishTime = System.currentTimeMillis();
		long totalTime = finishTime - startTime;
		System.out.println("Running time  :" + totalTime + " msec");

	}
}
