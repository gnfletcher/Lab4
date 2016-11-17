package Heap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of an AVL Tree that contains all necessary operations
 * 
 * @author Greg Fletcher
 * @author Sean O'Donnell
 */
public class AVLTree {

  public Node root;
  public Node last;

	/**
	 * Default constructor that creates an empty tree
	 */
  public AVLTree() {
    this.root = null;
    this.last = null;
  }

	/**
	 * Constructor to create a tree with the given value at the root
	 * Creates a new node and sets that node to the root
	 * 
	 * @param Comparable value to become the root
	 */
  public AVLTree(Comparable value) {
    this.root = new Node(value);
    this.last = root;
  }

  /**
   * Searches tree for given element.
   * 
   * @param tree to be searched
   * @param Comparable value to be searched for
   * @return boolean if operation was successful.
   */
  public static boolean search(AVLTree tree, Comparable value) {
    Node current = tree.root;
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

  /**
   * Returns the node containing the target value
   * If no node has this value, null is returned
   * 
   * @param Comparable value to be searched for
   * @return Node containg target value
   */
  public Node getNode(Comparable value) {
    Node current = root;
    while (current.getData().compareTo(value) != 0 && current.hasChildren()) {
      if (current.getData().compareTo(value) < 0) {
        current = current.getRightChild();
      } else {
        current = current.getLeftChild();
      }
    }
    if (current.getData().compareTo(value) == 0) {
      return current;
    }
    return null;
  }

  /**
   * Performs a left rotation around the given node
   * 
   * @param Node around which rotation is done
   * @return boolean if operation was successful.
   */
  public static boolean rotateLeft(Node node) {
    Node newParent = node.getRightChild();
    newParent.setParent(node.getParent());
    Node oldParent = node;
    if (node.getParent().getLeftChild().equals(node)) {
      node.getParent().setLeftChild(newParent);
    } else {
      node.getParent().setRightChild(newParent);
    }
    oldParent.setRightChild(newParent.getLeftChild());
    oldParent.setParent(newParent);
    newParent.setLeftChild(oldParent);
    if (newParent.getLeftChild() != null) {
      newParent.getLeftChild().setParent(newParent);
    }
    if (newParent.getRightChild() != null) {
      newParent.getRightChild().setParent(newParent);
    }
    return true;
  }

  /**
   * Performs a right rotation around the given node
   * 
   * @param Node around which rotation is done
   * @return boolean if operation was successful.
   */
  public static boolean rotateRight(Node node) {
    Node newParent = node.getLeftChild();
    newParent.setParent(node.getParent());
    Node oldParent = node;
    if (node.getParent().getLeftChild().equals(node)) {
      node.getParent().setLeftChild(newParent);
    } else {
      node.getParent().setRightChild(newParent);
    }
    oldParent.setLeftChild(newParent.getRightChild());
    oldParent.setParent(newParent);
    newParent.setRightChild(oldParent);
    if (newParent.getLeftChild() != null) {
      newParent.getLeftChild().setParent(newParent);
    }
    if (newParent.getRightChild() != null) {
      newParent.getRightChild().setParent(newParent);
    }
    return true;
  }

  /**
   * Performs a double left rotation around the given node
   * 
   * @param node around which rotation is done
   * @return boolean if operation was successful.
   */
  public static boolean rotateLeftRight(Node node) {
    rotateRight(node.getRightChild());
    rotateLeft(node);
    return true;
  }

  /**
   * Performs a double right rotation around the given node
   * 
   * @param node around which rotation is done
   * @return boolean if operation was successful.
   */
  public static boolean rotateRightLeft(Node node) {
    rotateLeft(node.getLeftChild());
    rotateRight(node);
    return true;
  }

  /**
   * Inserts a node containing the given value into the given tree
   * 
   * @param AVLtree to be added to
   * @param Comparable value to be added to the tree
   * @return AVLtree new tree with inserted value
   */
  public static AVLTree insert(AVLTree tree, Comparable value) {
    Node insert = searchForOpen(tree.root, value);
    Node newNode = new Node(value);
    if (insert.getData().compareTo(value) <= 0) {
      insert.setRightChild(newNode);
      newNode.setParent(insert);
    } else {
      insert.setLeftChild(newNode);
      newNode.setParent(insert);
    }
    balance(tree.root);
    return tree;
  }

  /**
   * Searches the given tree to find a place to insert the given value
   * 
   * @param AVLtree to be searched
   * @param Comparable value to find a spot for
   * @return Node that the value will be insrted after
   */
  public static Node searchForOpen(Node node, Comparable value) {
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

  /**
   * Deletes a given element from the tree and rebalances
   * 
   * @param Tree to be deleted from
   * @param Comparable value to be deleted
   * @return AVLtree new tree with node deleted
   */
  public static AVLTree delete(AVLTree tree, Comparable value) {
    Node node = tree.getNode(value);
    if (!node.hasChildren()) {
      if (node.getParent().getLeftChild().equals(node)) {
        node.getParent().setLeftChild(null);
      } else {
        node.getParent().setRightChild(null);
      }
      balance(tree.root);
      return tree;
    } else {
      if (node.getLeftChild() != null) {
        Node replacement = node.getLeftChild();
        while (replacement.getRightChild() != null) {
          replacement = node.getRightChild();
        }
        replacement.getParent().setRightChild(null);
        replacement.setLeftChild(node.getLeftChild());
        replacement.setRightChild(node.getRightChild());
        if (node.getParent().getLeftChild().equals(node)) {
          node.getParent().setLeftChild(replacement);
        } else {
          node.getParent().setRightChild(replacement);
        }
      } else {
        Node replacement = node.getRightChild();
        if (node.getParent().getLeftChild().equals(node)) {
          node.getParent().setLeftChild(replacement);
        } else {
          node.getParent().setRightChild(replacement);
        }
      }
      balance(tree.root);
      return tree;
    }
  }

  /**
   * Returns an ArrayList of the inorder representation of the given tree
   * 
   * @param AVLTree to be represented
   * @return ArrayList<Comparable> of the nodes
   */
  public static ArrayList<Comparable> inorder(AVLTree tree) {
    return tree.inorder(tree.root);
  }

  /**
   * Returns an ArrayList of the inorder representation of the tree
   * 
   * @param Node to start at
   * @return ArrayList<Comparable> of the nodes
   */
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

  /**
   * Recursively balances the tree so that each node's balancing factor does not exceed 1 or -1
   * 
   * @param node to be balanced
   * @return int resultant balancing factor
   */
  public static int balance(Node node) {
    node.setBalancingFactor(0);
    if (node.hasChildren()) {
      if (node.getLeftChild() != null) {
        System.out.println(node.getData());
        node.setBalancingFactor(
            node.getBalancingFactor() + (Math.abs(balance(node.getLeftChild())) * -1) - 1);
      }
      if (node.getRightChild() != null) {
        node.setBalancingFactor(
            node.getBalancingFactor() + (Math.abs(balance(node.getRightChild()))) + 1);
      }
      if (node.getBalancingFactor() == -2) {
        if (node.getLeftChild().getBalancingFactor() == -1) {
          rotateRight(node);
        } else {
          rotateRightLeft(node);
        }
        node.setBalancingFactor(node.getBalancingFactor() + 1);
      } else if (node.getBalancingFactor() == 2) {
        if (node.getRightChild().getBalancingFactor() == 1) {
          rotateLeft(node);
        } else {
          rotateLeftRight(node);
        }
        node.setBalancingFactor(node.getBalancingFactor() - 1);
      }
    }
    return node.getBalancingFactor();
  }

  /**
   * Counts the number of values between the two given values
   * 
   * @param Comparable starting value
   * @param ending value
   * @return int number of values
   */
  public int count(Comparable xZero, Comparable xOne) {
    return countFromRoot(xZero, xOne, root);
  }

  /**
   * Counts the number of values between the given values from the root
   * 
   * @param Comparable starting value
   * @param ending value
   * @param Node starting node
   * @return int number of values
   */
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

  /**
   * Prints the nodes in the tree
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
      if (node.getLeftChild() != null) {
        queue.add(node.getLeftChild());
      }
      if (node.getRightChild() != null) {
        queue.add(node.getRightChild());
      }
    }
    System.out.println("");
  }

  /**
   * Prints the inorder representation of the tree
   *
   */
  public void inOrderPrint() {
    ArrayList<Comparable> inorder = inorder(root);
    for (Comparable value : inorder) {
      System.out.print(value + ", ");
    }
    System.out.println("");
  }
}
