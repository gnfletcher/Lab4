package Heap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {

  public Node root;
  public Node last;

  public AVLTree() {
    this.root = null;
    this.last = null;
  }

  public AVLTree(Comparable value) {
    this.root = new Node(value);
    this.last = root;
  }

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

  public static boolean rotateLeft(Node node) {
    Node newParent = node.getRightChild();
    Node oldParent = node;
    if (node.getParent().getData().compareTo(node.getData()) <= 0) {
      node.getParent().setRightChild(newParent);
    } else {
      node.getParent().setLeftChild(newParent);
    }
    oldParent.setRightChild(newParent.getLeftChild());
    newParent.setLeftChild(oldParent);
    if (newParent.getLeftChild() != null) {
      newParent.getLeftChild().setParent(newParent);
    }
    if (newParent.getRightChild() != null) {
      newParent.getRightChild().setParent(newParent);
    }
    return true;
  }

  public static boolean rotateRight(Node node) {
    Node newParent = node.getLeftChild();
    Node oldParent = node;
    if (node.getParent().getData().compareTo(node.getData()) <= 0) {
      node.getParent().setRightChild(newParent);
    } else {
      node.getParent().setLeftChild(newParent);
    }
    oldParent.setLeftChild(newParent.getRightChild());
    newParent.setRightChild(oldParent);
    if (newParent.getLeftChild() != null) {
      newParent.getLeftChild().setParent(newParent);
    }
    if (newParent.getRightChild() != null) {
      newParent.getRightChild().setParent(newParent);
    }
    return true;
  }

  public static boolean rotateLeftRight(Node node) {
    rotateRight(node.getRightChild());
    rotateLeft(node);
    return true;
  }

  public static boolean rotateRightLeft(Node node) {
    rotateLeft(node.getLeftChild());
    rotateRight(node);
    return true;
  }

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

  public static AVLTree delete(AVLTree tree, Comparable value) {
    Node node = tree.getNode(value);
    System.out.println(node.getParent().getData());
    System.out.println(node.getParent().getLeftChild().getData());
    System.out.println(node.getParent().getRightChild().getData());
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

  public static ArrayList<Comparable> inorder(AVLTree tree) {
    return tree.inorder(tree.root);
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

  public static int balance(Node node) {
    node.setBalancingFactor(0);
    if (node.hasChildren()) {
      if (node.getLeftChild() != null) {
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

  public void inOrderPrint() {
    ArrayList<Comparable> inorder = inorder(root);
    for (Comparable value : inorder) {
      System.out.print(value + ", ");
    }
    System.out.println("");
  }
}
