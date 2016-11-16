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
      heap.print();
      heap.insert(8);
      heap.print();
      heap.insert(14);
      heap.printRootKids();
      heap.print();
      heap.insert(15);
      heap.print();
      heap.insert(16);
      heap.print();
      heap.insert(20);
      heap.print();
      heap.insert(32);
      heap.print();
      heap.insert(36);
      heap.print();
      heap.insert(37);
      heap.print();
      heap.insert(38);
      heap.print();
      heap.insert(39);
      heap.print();
      heap.insert(40);
      heap.print();
      heap.printNextKids();
      heap.insert(42);
      heap.print();
      heap.insert(100);
      heap.print();
      heap.insert(125);
      heap.print();
      heap.insert(130);
      heap.print();
      heap.insert(150);
      heap.print();
  }

}
