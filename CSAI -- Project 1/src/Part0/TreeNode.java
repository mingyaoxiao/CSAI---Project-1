import java.util.*;
public class TreeNode {
	Cell state;
	MoveDirection action;
	TreeNode parent;
	int cost;
	List<Cell> children;
	
	public static generateInitialNode(Cell initialCell) {
		return new TreeNode(initialCell, null, null, 0);
	}
	public TreeNode(Cell state, MoveDirection action, TreeNode parent, int additionalCost) {
		this.state = state;
		this.action = action;
		this.parent = parent;
		this.cost = parent.cost + additionalCost;
	}
	public void appendChild(TreeNode childNode) {
		this.children.add(childNode);
	}
}
