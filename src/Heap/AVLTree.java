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

  public boolean insert(Comparable value) {
    Node insert = searchForOpen(root, value);
    Node newNode = new Node(value);
    if (insert.getData().compareTo(value) <= 0) {
      insert.setRightChild(newNode);
      newNode.setParent(insert);
    } else {
      insert.setLeftChild(newNode);
      newNode.setParent(insert);
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

  public boolean delete(Comparable value) {
    Node node = getNode(value);
    if (!node.hasChildren()) {
      if (node.getParent().getData().compareTo(value) <= 0) {
        node.getParent().setRightChild(null);
      } else {
        node.getParent().setLeftChild(null);
      }
      balance();
      return true;
    }
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

  public static ArrayList<Comparable> inorder(AVLTree tree, Node node) {
    return tree.inorderTraversal(node);
  }

  public ArrayList<Comparable> inorderTraversal(Node node) {
    ArrayList<Comparable> list = new ArrayList<>();
    if (node.hasChildren()) {
      if (node.getLeftChild() != null) {
        list.addAll(inorderTraversal(node.getLeftChild()));
      }
      list.add(node.getData());
      if (node.getRightChild() != null) {
        list.addAll(inorderTraversal(node.getRightChild()));
      }
    } else {
      list.add(node.getData());
    }
    return list;
  }


  public boolean balance() {
    int balance = balanceTree(root);
    return (balance == 0 || balance == -1 || balance == 1);
  }

  public int balanceTree(Node node) {
    node.setBalancingFactor(0);
    if (node.hasChildren()) {
      if (node.getLeftChild() != null) {
        node.setBalancingFactor(node.getBalancingFactor()
            + (Math.abs(balanceTree(node.getLeftChild())) * -1) - 1);
        if (node.getBalancingFactor() < -1) {
          if(node.getLeftChild().getBalancingFactor() == -1){
            
          } else {
            
          }
        }
      }
      if (node.getRightChild() != null) {
        node.setBalancingFactor(
            node.getBalancingFactor() + (Math.abs(setBalancingFactor(node.getRightChild()))) + 1);
        if (node.getBalancingFactor() > 1) {

        }
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
      if (node.getLeftChild() != null)
        queue.add(node.getLeftChild());
      if (node.getRightChild() != null)
        queue.add(node.getRightChild());
    }
    System.out.println("");
  }

  public void inOrderPrint() {
    ArrayList<Comparable> inorder = inorderTraversal(root);
    for (Comparable value : inorder) {
      System.out.print(value + ", ");
    }
    System.out.println("");
  }

}
