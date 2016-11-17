/**
 * 
 */
package Heap;

/**
 * @author FletcherG
 *
 */
public class driver {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
      TrashHeap heap = new TrashHeap();
      /*
      heap.makeHeap(5);
      heap.insert (50);
      heap.insert(14);
      heap.insert(15);
      heap.insert(8);
      heap.insert(20);
      heap.insert(40);
      heap.insert(42);
      heap.insert(100);
      heap.insert(125);
      heap.insert(32);
      heap.insert(36);
      heap.insert(150);
      heap.insert(37);
      heap.insert(38);
      heap.insert(39);
      heap.insert(130);
      heap.print();
      System.out.println(heap.findMin());
      heap.deleteMin();
      heap.print();
      heap.deleteMin();
      heap.print();
      heap.delete(heap.nextOpen());
      heap.print();
      System.out.println(heap.findMin());
      heap.decreaseKey(heap.nextOpen(), 32);
      heap.print();
      heap.decreaseKey(heap.nextOpen(), 19);
      heap.print();
      TrashHeap heapTwo = new TrashHeap();
      heapTwo.makeHeap(5);
      heapTwo.insert (6);
      heapTwo.insert(11);
      heapTwo.insert(21);
      heapTwo.insert(50);
      heapTwo.insert(48);
      heapTwo.print();
      heap.union(heapTwo);
      heap.print();
      */
      
      AVLTree tree = new AVLTree(45);
      tree.insert(12);
      tree.insert(50);
      tree.insert(10);
      tree.insert(15);
      tree.insert(47);
      tree.insert(52);
      tree.print();
      tree.inOrderPrint();
      System.out.println(tree.search(50));
      System.out.println(tree.search(15));
      System.out.println(tree.search(100));
      tree.delete(10);
      tree.print();
      tree.inOrderPrint();
      System.out.println(tree.count(11, 20));
  }

}
