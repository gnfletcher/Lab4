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
      heap.makeHeap(5);
      heap.insert(8);
      heap.insert(14);
      heap.insert(15);
      heap.insert(16);
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
      
  }

}
