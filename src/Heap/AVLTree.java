package Heap;

import java.util.ArrayList;

public class AVLTree {

	public Node root;
	public int numberOfNodes;
	public Node last;
	public int balance;

	public AVLTree() {
		this.root = null;
		this.numberOfNodes = 0;
		this.last = null;
		this.balance = 0;

	}

	public AVLTree(Node node) {
		this.root = node;
		this.numberOfNodes = 1;
		this.last = node;
		this.balance = 0;
	}

	public boolean search(Comparable value) {
		Node current = root;
		while (current.getData().compareTo(value) != 0 && current.hasChildren()) {
			if (current.getData().compareTo(value) < 0) {
				current = current.getRightChild();
			} else {
				current = current.getLeftChild();
			}
		}
		if (current.getData().compareTo(value) == 0) {
			return true;
		}
		return false;
	}

	public boolean balance() {
		
		return true;
	}

	public boolean rotateLeft(Node node) {
		Node newParent = node.getRightChild();
		Node oldParent = node;
		if (node.getParent().getData().compareTo(node.getData()) <= 0) {
			node.getParent().setRightChild(newParent);
		} else {
			node.getParent().setLeftChild(newParent);
		}
		oldParent.setRightChild(newParent.getLeftChild());
		newParent.setLeftChild(oldParent);
		return true;
	}
	
	public boolean rotateRight(Node node) {
		Node newParent = node.getLeftChild();
		Node oldParent = node;
		if (node.getParent().getData().compareTo(node.getData()) <= 0) {
			node.getParent().setRightChild(newParent);
		} else {
			node.getParent().setLeftChild(newParent);
		}
		oldParent.setLeftChild(newParent.getRightChild());
		newParent.setRightChild(oldParent);
		return true;
	}

	public boolean rotateLeftRight(Node node) {
		rotateRight(node.getRightChild());
		rotateLeft(node);
		return true;
	}
	
	public boolean rotateRightLeft(Node node) {
		rotateLeft(node.getLeftChild());
		rotateRight(node);
		return true;
	}
	public boolean insert(Node node) {
		Node insert = searchForOpen(root, node.getData());
		if (insert.getData().compareTo(node.getData()) <= 0) {
			insert.setRightChild(node);
		} else {
			insert.setLeftChild(node);
		}
		balance();
		return true;
	}

	public Node searchForOpen(Node node, Comparable value) {
		if (node.getData().compareTo(value) <= 0) {
			if (node.getRightChild() != null) {
				return searchForOpen(node.getRightChild(), value);
			}
		} else if (node.getData().compareTo(value) > 0) {
			if (node.getLeftChild() != null) {
				return searchForOpen(node.getLeftChild(), value);
			}
		}
		return node;
	}

	public boolean delete(Node node) {
		Node replacement = node.getLeftChild();
		while (replacement.getRightChild() != null) {
			replacement = node.getRightChild();
		}
		replacement.setLeftChild(node.getLeftChild());
		replacement.setRightChild(node.getRightChild());
		if (node.getParent().getData().compareTo(node.getData()) <= 0) {
			node.getParent().setRightChild(replacement);
		} else {
			node.getParent().setLeftChild(replacement);
		}
		balance();
		return true;
	}

	public ArrayList<Comparable> inorder(Node node) {
		ArrayList<Comparable> list = new ArrayList<>();
		if (node.hasChildren()) {
			if (node.getLeftChild() != null) {
				list.addAll(inorder(node.getLeftChild()));
			}
			list.add(node.getData());
			if (node.getRightChild() != null) {
				list.addAll(inorder(node.getRightChild()));
			}
		} else {
			list.add(node.getData());
		}
		return list;
	}

	public int count(Comparable xZero, Comparable xOne) {
		return countFromRoot(xZero, xOne, root);
	}

	public int countFromRoot(Comparable xZero, Comparable xOne, Node node) {
		int i = 0;
		if (node.hasChildren()) {
			if (node.getLeftChild() != null) {
				if (node.getLeftChild().getData().compareTo(xZero) >= 0
						&& node.getLeftChild().getData().compareTo(xOne) <= 0) {
					i = i + countFromRoot(xZero, xOne, node.getLeftChild());
				}
			}
			if (node.getRightChild() != null) {
				if (node.getRightChild().getData().compareTo(xZero) >= 0
						&& node.getRightChild().getData().compareTo(xOne) <= 0) {
					i = i + countFromRoot(xZero, xOne, node.getRightChild());
				}
			}
			if (node.getData().compareTo(xZero) >= 0 && node.getData().compareTo(xOne) <= 0) {
				i++;
			}
		} else {
			if (node.getData().compareTo(xZero) >= 0 && node.getData().compareTo(xOne) <= 0) {
				i++;
			}
		}

		return i;
	}
}
