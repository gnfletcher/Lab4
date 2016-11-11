/**
 * 
 */
package Heap;


/**
 * @author FletcherG
 *
 */
public class TrashHeap implements MyHeap {

  public Node root;
  public int height;
  public int numberOfNodes;
  public Node nextParent;

  public TrashHeap() {
    this.root = null;
    this.height = -1;
    this.nextParent = null;
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

  public boolean levelFull() {
    return numberOfNodes == Math.pow(2, height);
  }

  public Node nextOpen() {
    String path = Integer.toBinaryString(numberOfNodes + 1);
    path = path.substring(1, path.length() - 1);
    Node current = root;
    for (int i = path.length() - 1; i >= 0; i--) {
      if (path.charAt(i) == 1) {
        current = current.getRightChild();
      } else {
        current = current.getLeftChild();
      }
    }
    return current;
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
      numberOfNodes++;
      return true;
    } else {
      Node parent = nextOpen();
      Node child = new Node(value);
      if (parent.getLeftChild() != null) {
        parent.setRightChild(child);
        numberOfNodes++;
        return true;
      } else {
        nextOpen().setLeftChild(child);
        numberOfNodes++;
        return true;
      }
    }
  }
  
  public boolean siftUp(Node node){
    Node current = node;
    while(current != root && current.getParent().getData().compareTo(current.getData()) < 0){
      Comparable parentValue = current.getParent().getData();
      current.getParent().setData(current.getData());
      current.setData(parentValue);
      current = current.getParent();
    }
    return true;
  }
  
  public boolean siftDown(Node node){
    Node current = node;
    while(current.hasChildren(current) && current.)
    return false;
    
  }
  
  public boolean hasChildren(Node node){
    if(node.getLeftChild() != null){
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
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see Heap.MyHeap#decreaseKey(Heap.Node, java.lang.Comparable)
   */
  @Override
  public boolean decreaseKey(Node key, Comparable updateValue) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see Heap.MyHeap#delete(Heap.Node)
   */
  @Override
  public boolean delete(Node del) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see Heap.MyHeap#union(Heap.MyHeap)
   */
  @Override
  public boolean union(MyHeap heap) {
    // TODO Auto-generated method stub
    return false;
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



}
