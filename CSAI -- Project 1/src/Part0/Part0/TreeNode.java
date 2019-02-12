package Part0;
import java.util.*;
public class TreeNode {
	Cell state;
	MoveDirection action;
	TreeNode parent;
	int cost;
	List<TreeNode> children;
	ListIterator<TreeNode> childrenIterator;
	private boolean firstTimeFetch = true;
	
	public static TreeNode generateInitialNode(Cell initialCell) {
		return new TreeNode(initialCell, null, null, 0);
	}
	public TreeNode(Cell state, MoveDirection action, TreeNode parent, int additionalCost) {
		this.state = state;
		this.action = action;
		this.parent = parent;
		this.cost = parent.cost + additionalCost;
	}
	public void appendChild(TreeNode childNode) {
		childrenIterator.add(childNode);
	}
	
	public TreeNode nextChild() {
		if(firstTimeFetch) {
			childrenIterator = children.listIterator();
			this.firstTimeFetch = false;
		}
		if(childrenIterator.hasNext())
		return childrenIterator.next();
		
		return null;
	}
	
	public TreeNode prevChild() {
		if(firstTimeFetch) {
			childrenIterator = children.listIterator();
			this.firstTimeFetch = false;
		}
		if(childrenIterator.hasPrevious())
		return childrenIterator.previous();
		
		return null;
	}
}
