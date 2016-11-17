/**
 * 
 */
package Heap;

/**
 * Driver for TrashHeap and AVLTree classes
 * 
 * @author Greg Fletcher
 * @author Sean O'Donnell
 *
 */
public class driver {

  public static void main(String[] args) {
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
      
      AVLTree.insert(tree, 12);
      AVLTree.insert(tree, 50);
      AVLTree.insert(tree, 10);
      AVLTree.insert(tree, 15);
      AVLTree.insert(tree, 47);
      AVLTree.insert(tree, 52);
      //tree.print();
      //System.out.println(AVLTree.inorder(tree));
      AVLTree.insert(tree, 54);
      AVLTree.insert(tree, 59);
      //tree.print();
      //System.out.println(AVLTree.inorder(tree));
      AVLTree.insert(tree, 8);
      AVLTree.insert(tree, 9);
      tree.print();
      //tree.print();
      //System.out.println(AVLTree.inorder(tree));
      //System.out.println(AVLTree.search(tree, 50));
      //System.out.println(AVLTree.search(tree, 15));
      //System.out.println(AVLTree.search(tree, 100));
      AVLTree.delete(tree, 45);
      AVLTree.delete(tree, 10);
      tree.print();
      //System.out.println(tree.count(11, 20));
      System.out.println(AVLTree.inorder(tree));
  }

}
