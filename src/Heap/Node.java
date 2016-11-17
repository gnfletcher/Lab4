package Heap;

/**
 * A node class that contains the Node's data, left child, right child, parent,
 * and balancing factor
 * 
 * @author Greg Fletcher
 * @author Sean O'Donnell
 */
public class Node {
	private Node left_child;
	private Node right_child;
	private Node parent;
	private Comparable data;
	private int balancingFactor;

	/**
	 * Default constructor that creates an empty node
	 * 
	 */
	public Node() {
		left_child = null;
		right_child = null;
		data = null;
		parent = null;
		balancingFactor = 0;
	}

	/**
	 * Constructor to create a Node containg the given data
	 * 
	 * @param Comparable data to be entered into the node
	 */
	public Node(Comparable data) {
		this.data = data;
		left_child = null;
		right_child = null;
		parent = null;
		balancingFactor = 0;
	}

	/**
	 * Constructor to create a node with the given data, left child, right child, and parent
	 * 
	 * @param Comparable data to be entered into the node
	 * @param Node left child of the node
	 * @param Node right child of the node
	 * @param Node parent of the node
	 */
	public Node(Comparable data, Node left_child, Node right_child, Node parent) {
		this.data = data;
		this.left_child = left_child;
		this.right_child = right_child;
		this.parent = parent;
		balancingFactor = 0;
	}

	/**
	 * Retrieves the left child of the Node
	 * 
	 * @return Node left child
	 */
	public Node getLeftChild() {
		return left_child;
	}

	/**
	 * Retrieves the right child of the Node
	 * 
	 * @return Node right child
	 */
	public Node getRightChild() {
		return right_child;
	}

	/**
	 * Retrieves the data contained in the node
	 * 
	 * @return Comparable data in the node
	 */
	public Comparable getData() {
		return data;
	}
	/**
	 * Retrieves the parent of the Node
	 * 
	 * @return Node parent
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * Set's the left child of the node to the given node
	 * 
	 * @param Node to become left child
	 */
	public void setLeftChild(Node left_child) {
		this.left_child = left_child;
	}

	/**
	 * Set's the right child of the node to the given node
	 * 
	 * @param Node to become right child
	 */
	public void setRightChild(Node right_child) {
		this.right_child = right_child;
	}

	/**
	 * Set's the data of the node to the given data
	 * 
	 * @param Comprabale data to be added to the node
	 */
	public void setData(Comparable data) {
		this.data = data;
	}

	/**
	 * Set's the parent of the node to the given node
	 * 
	 * @param Node to become parent
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * Check's to see if the node has children by checking to see if its left child is null
	 * 
	 * @return boolean whether or not the node has children
	 */
	public boolean hasChildren() {
		if (left_child != null || right_child != null) {
			return true;
		}
		return false;
	}

	/**
	 * Set's the balancing factor of the node to the value
	 * 
	 * @param Node to become left child
	 */
	public void setBalancingFactor(int factor) {
		this.balancingFactor = factor;
	}

	/**
	 * retrieves the balancing factor of the node
	 * 
	 * @return int balancing factor of the node
	 */
	public int getBalancingFactor() {
		return this.balancingFactor;
	}
}