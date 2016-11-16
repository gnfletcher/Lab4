/**
 * 
 */
package Heap;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author FletcherG
 *
 */
public class TrashHeap implements MyHeap {

  public Node root;
  public int height;
  public int numberOfNodes;
  public Node last;

  public TrashHeap() {
    this.root = null;
    this.height = -1;
    this.numberOfNodes = 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see Heap.MyHeap#makeHeap(java.lang.Comparable)
   */
  @Override
  public Node makeHeap(Comparable value) {
    Node newNode = new Node(value);
    this.root = newNode;
    this.last = newNode;
    numberOfNodes++;
    return newNode;
  }

  /*
   * (non-Javadoc)
   * 
   * @see Heap.MyHeap#isEmpty()
   */
  @Override
  public boolean isEmpty() {
    if (root == null) {
      return true;
    }
    return false;
  }

  public Node nextOpen() {
    String path = Integer.toBinaryString(numberOfNodes);
    Node current = root;
    System.out.println(numberOfNodes);
    System.out.println(path);
    path = path.substring(1);
    for (int i = path.length() - 1; i >= 0; i--) {
      System.out.println(path.charAt(i));
      if (Integer.valueOf(path.charAt(i)).equals(0)) {
        if (current.getLeftChild() != null) {
          current = current.getLeftChild();
        }
      } else {
        if (current.getRightChild() != null) {
          current = current.getRightChild();
        }
      }
    }
    return current;
  }

  public void printRootKids() {
    System.out.println(root.getData() + ", " + root.getLeftChild().getData() + ", "
        + root.getRightChild().getData());
  }

  public void printNextKids() {
    Node left = root.getLeftChild();
    Node right = root.getRightChild();
    Node twoRight = right.getRightChild();
    Node threeRight = twoRight.getRightChild();
    System.out.println(root.getData() + ", " + root.getLeftChild().getData() + ", "
        + root.getRightChild().getData() + ", " + right.getLeftChild().getData() + ", "
        + right.getRightChild().getData() + ", " + twoRight.getLeftChild().getData() + ", "
        + twoRight.getRightChild().getData());
  }

  public void newLast() {
    String path = Integer.toBinaryString(numberOfNodes - 1);
    path = path.substring(1, path.length());
    Node current = root;
    for (int i = path.length() - 1; i >= 0; i--) {
      if (path.charAt(i) == 1) {
        current = current.getRightChild();
      } else {
        current = current.getLeftChild();
      }
    }
    last = current;
  }

  /*
   * (non-Javadoc)
   * 
   * @see Heap.MyHeap#insert(java.lang.Comparable)
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
      if (parent.getLeftChild() != null) {
        parent.setRightChild(child);
        numberOfNodes++;
        return siftUp(child);
      } else {
        parent.setLeftChild(child);
        numberOfNodes++;
        return siftUp(child);
      }
    }
  }

  public Node swapUp(Node node) {
    Node current = node;
    Comparable parentValue = current.getParent().getData();
    current.getParent().setData(current.getData());
    current.setData(parentValue);
    return current.getParent();
  }

  public Node swapDown(Node node) {
    Node current = node;
    Comparable parentValue = current.getParent().getData();
    current.getParent().setData(current.getData());
    current.setData(parentValue);
    return current;
  }

  public boolean siftUp(Node node) {
    Node current = node;
    while (current.equals(root)) {
      while (current.getParent().getData().compareTo(current.getData()) < 0) {
        swapUp(current);
      }
    }
    return true;
  }

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

  public boolean hasChildren(Node node) {
    if (node.getLeftChild() != null) {
      return true;
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see Heap.MyHeap#deleteMin()
   */
  @Override
  public boolean deleteMin() {
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

  /*
   * (non-Javadoc)
   * 
   * @see Heap.MyHeap#decreaseKey(Heap.Node, java.lang.Comparable)
   */
  @Override
  public boolean decreaseKey(Node key, Comparable updateValue) {
    if (key.getData().compareTo(updateValue) > 0) {
      key.setData(updateValue);
      return siftUp(key);
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see Heap.MyHeap#delete(Heap.Node)
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

  /*
   * (non-Javadoc)
   * 
   * @see Heap.MyHeap#union(Heap.MyHeap)
   */
  @Override
  public boolean union(MyHeap heap) {
    // TODO Auto-generated method stub
    while (!heap.isEmpty()) {
      insert(heap.findMin());
      heap.deleteMin();
    }
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see Heap.MyHeap#findMin()
   */
  @Override
  public Comparable findMin() {
    // TODO Auto-generated method stub
    if (root != null) {
      return root.getData();
    }
    return null;
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

  public void inOrderPrint(Node node) {
    if (node != null) {
      System.out.print(node.getData() + ", ");
      inOrderPrint(node.getLeftChild());
      inOrderPrint(node.getRightChild());
    }
  }
}
