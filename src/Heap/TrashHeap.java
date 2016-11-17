/**
 * 
 */
package Heap;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements the MyHeap interface to create a heap
 * 
 * @author Greg Fletcher
 * @author Sean O'Donnell
 */
public class TrashHeap implements MyHeap {

	public Node root;
	public int numberOfNodes;
	public Node last;
	
	/**
	 * Default constructor that creates an empty heap
	 */
	public TrashHeap() {
		this.root = null;
		this.numberOfNodes = 0;
	}

	/**
	 * Creates a heap with the root being the given value
	 * updates the root and the last node to the new node
	 * increments the number of nodes by 1
	 * 
	 * @param Comprabale value to become the root
	 * @return Node the root node
	 */
	@Override
	public Node makeHeap(Comparable value) {
		Node newNode = new Node(value);
		this.root = newNode;
		this.last = newNode;
		numberOfNodes++;
		return newNode;
	}

	/**
	 * Checks to see if the tree is empty by checking the number of nodes
	 * 
	 * 
	 * @return boolean whether or not tree is empty
	 */
	@Override
	public boolean isEmpty() {
		return numberOfNodes == 0;
	}

	/**
	 * Returns the parent of the next open space in the heap
	 * 
	 * @return Node parent of next open spot
	 */
	public Node nextOpen() {
		String path = Integer.toBinaryString(numberOfNodes + 1);
		Node current = root;
		for (int i = 1; i < path.length() - 1; i++) {
			if (Character.compare(path.charAt(i), '0') == 0) {
				current = current.getLeftChild();
			} else {
				current = current.getRightChild();
			}
		}
		return current;
	}

	/**
	 * Updates the last value in the heap after a deletion has occurred
	 * 
	 */
	public void newLast() {
		String path = Integer.toBinaryString(numberOfNodes);
		Node current = root;
		for (int i = 1; i < path.length(); i++) {
			if (Character.compare(path.charAt(i), '0') == 0) {
				current = current.getLeftChild();
			} else {
				current = current.getRightChild();
			}
		}
		last = current;
	}

	/**
	 * Inserts a new node into the heap with the given value
	 * 
	 * @param Comparable value to be inserted into heap
	 * @return boolean if operation was successful.
	 */
	@Override
	public boolean insert(Comparable value) {
		if (root == null) {
			Node newNode = new Node(value);
			root = newNode;
			last = newNode;
			numberOfNodes++;
			return true;
		} else {
			Node parent = nextOpen();
			Node child = new Node(value);
			last = child;
			if (parent.getLeftChild() == null) {
				parent.setLeftChild(child);
				child.setParent(parent);
				numberOfNodes++;
				return siftUp(child);
			} else {
				parent.setRightChild(child);
				child.setParent(parent);
				numberOfNodes++;
				return siftUp(child);
			}
		}
	}

	/**
	 * Swaps the given node with its parent
	 * 
	 * @param Node to be swapped
	 * @return Node that was swapped
	 */
	public Node swapUp(Node node) {
		Node current = node;
		Comparable parentValue = current.getParent().getData();
		current.getParent().setData(current.getData());
		current.setData(parentValue);
		return current.getParent();
	}

	/**
	 * Swaps the given node with its child
	 * 
	 * @param Node to be swapped
	 * @return Node that was swapped
	 */
	public Node swapDown(Node node) {
		Node current = node;
		Comparable parentValue = current.getParent().getData();
		current.getParent().setData(current.getData());
		current.setData(parentValue);
		return current;
	}

	/**
	 * Checks to see if the given node's value is greater than its parent's values.
	 * If it is not, it is swapped with its parent and the process is repeated
	 * If it is, do nothing
	 * 
	 * @param Node from which to sift up
	 * @return boolean if the operation was successful
	 */
	public boolean siftUp(Node node) {
		Node current = node;
		while (!current.equals(root)) {
			while (current.getParent().getData().compareTo(current.getData()) > 0) {
				swapUp(current);
			}
			current = current.getParent();
		}
		return true;
	}

	/**
	 * Checks to see if the given node's value is less than its children's values.
	 * If it is not, it is swapped with its lesser child and the process is repeated
	 * If it is, do nothing
	 * 
	 * @param Node from which to sift down
	 * @return boolean if the operation was successful
	 */
	public boolean siftDown(Node node) {
		Node current = node;
		while (hasChildren(current)) {
			if (current.getRightChild() != null) {
				if (current.getLeftChild().getData().compareTo(current.getData()) < 0
						|| current.getRightChild().getData().compareTo(current.getData()) < 0) {
					if (current.getLeftChild().getData().compareTo(current.getRightChild().getData()) < 0) {
						current = swapDown(current.getLeftChild());
					} else {
						current = swapDown(current.getRightChild());
					}
				} else {
					return true;
				}
			} else {
				if (current.getLeftChild().getData().compareTo(current.getData()) < 0) {
					swapDown(current.getLeftChild());
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if a node has any children by checking to see if its left child is null
	 * 
	 * @param Node to be checked for children
	 * @return boolean whether or not the node has children
	 */
	public boolean hasChildren(Node node) {
		if (node.getLeftChild() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Deletes the minimum value in the heap by swapping the root with the last
	 * node, deleting the last node, then sifting down from the root
	 * 
	 * @return boolean whether or not the minimum was deleted
	 */
	@Override
	public boolean deleteMin() {
		if (root == last) {
			root = null;
			numberOfNodes--;
			return true;
		}
		root.setData(last.getData());
		if (numberOfNodes % 2 == 0) {
			last.getParent().setLeftChild(null);
		} else {
			last.getParent().setRightChild(null);
		}
		numberOfNodes--;
		newLast();
		siftDown(root);
		return true;
	}

	/**
	 * Compares the given value to the value in the given Node, if the given
	 * value is less than the given node's value, the node's value becomes the
	 * given value
	 * 
	 * @param node
	 *            whose value is to be compared to the given value
	 * @param Comparable
	 *            value to be compared to the given node's value
	 * @return boolean whether or not given node's value was updated
	 */
	@Override
	public boolean decreaseKey(Node key, Comparable updateValue) {
		if (key.getData().compareTo(updateValue) > 0) {
			key.setData(updateValue);
			return siftUp(key);
		}
		return false;
	}

	/**
	 * Deletes a node by swapping it with the last node then deleting the last
	 * node
	 * 
	 * @param Node
	 *            to be deleted
	 * @return boolean if Node was deleted
	 */
	@Override
	public boolean delete(Node del) {
		del.setData(last.getData());
		if (numberOfNodes % 2 == 0) {
			last.getParent().setLeftChild(null);
		} else {
			last.getParent().setRightChild(null);
		}
		numberOfNodes--;
		newLast();
		siftUp(del);
		siftDown(del);
		return true;
	}

	/**
	 * Takes a given heap and merges its elements with this heap
	 * 
	 * @param Heap
	 *            to be merged with this heap
	 * @return boolean if the union was successful.
	 */
	public boolean union(MyHeap heap) {
		while (!heap.isEmpty()) {
			insert(heap.findMin());
			heap.deleteMin();
		}
		return true;
	}

	/**
	 * Finds and returns the minimum value in the heap
	 * 
	 * @return Comparable minimum value in heap
	 */
	@Override
	public Comparable findMin() {
		// TODO Auto-generated method stub
		if (root != null) {
			return root.getData();
		}
		return null;
	}

	/**
	 * Prints all of the nodes in the heap
	 * 
	 */
	public void print() {
		Queue<Node> queue = new LinkedList<Node>();
		if (root == null)
			return;
		queue.clear();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.remove();
			System.out.print(node.getData() + " ");
			if (node.getLeftChild() != null)
				queue.add(node.getLeftChild());
			if (node.getRightChild() != null)
				queue.add(node.getRightChild());
		}
		System.out.println("");
	}

}
