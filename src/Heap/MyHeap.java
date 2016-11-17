package Heap;

/**
 * Interface for Heap data structure
 * 
 * @author Greg Fletcher
 * @author Sean O'Donnell
 */
public interface MyHeap {
	/**
	 * Creates a heap with the root being the given value
	 * 
	 * @param Comparable value to become root
	 * @return Node the root node
	 */
    public Node makeHeap(Comparable value);
    
	/**
	 * Checks to see if the heap is empty
	 * 
	 * @return boolean whether or not heap is empty
	 */
    public boolean isEmpty();
    
	/**
	 * Inserts a new node with the given value into the heap
	 * 
	 * @param Comparable value to be inserted
	 * @return boolean whether or not the value was added
	 */
    public boolean insert(Comparable value);
    
	/**
	 * Deletes the minimum value in the heap
	 * 
	 * @return boolean whether or not the minimum was deleted
	 */
    public boolean deleteMin();
    
	/**
	 * Checks to see if the given value is less than the given node's value. If so, update the node's value.
	 * 
	 * @param Node whose value is to be compared
	 * @param Comparable value to be compared to
	 * @return boolean whether or not the node's value was updated
	 */
    public boolean decreaseKey(Node key, Comparable updateValue);
    
	/**
	 * Deletes the given node from the heap
	 * 
	 * @param Node to be deleted
	 * @return boolean whether or not the node was deleted
	 */
    public boolean delete(Node del);
    
	/**
	 * Merges two heaps together
	 * 
	 * @param MyHeap heap to be unioned with
	 * @return boolean whether union was successful or not
	 */
    public boolean union(MyHeap heap);
    
	/**
	 * Finds the minimum value in the heap
	 * 
	 * @return Comparable minimum value in the heap
	 */
    public Comparable findMin();
}