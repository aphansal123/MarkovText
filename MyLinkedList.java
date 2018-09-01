package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */

/* add(E element), get(int index), add(int index, E element), remove(int index), and 
   set(int index, E element) methods written by Ameya */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	
	public boolean add(E element) {
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}
		LLNode<E> n = new LLNode<E>(element);
		if (size == 0) {
			head = n;
			tail = n;
		} else {
			tail.next = n;
			n.prev = tail;
			tail = n;
		}
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) {
		// TODO: Implement this method.
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		int start = 0;
		LLNode<E> curr = head;
		while (start != index) {
			curr = curr.next;
			start++;
		}
		return curr.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		// TODO: Implement this method
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> n = new LLNode<E>(element);
		if (size == 0) { // inserting the first element
			head = n;
			tail = n;
		} else if (index == 0) { // replacing the head node of the list
			n.next = head;
			head.prev = n;
			head = n;
		} else if (index == size) { // adding at the end of the list
			tail.next = n;
			n.prev = tail;
			tail = n;
		} else { // inserting in the middle
			LLNode<E> curr = head;
			int start = 0;
			while (start != index - 1) { // stop 1 before the index to insert at
				curr = curr.next;
				start++;
			}
			n.next = curr.next;
			curr.next.prev = n;
			curr.next = n;
			n.prev = curr;
		}
		size++;
	}


	/** Return the size of the list */
	public int size() {
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		// TODO: Implement this method
		if (index < 0 || index >= size) { 			// index is outside bounds of list
			throw new IndexOutOfBoundsException();
		}
		if (size == 1) {  							// removing the node from a list with one element
			LLNode<E> result = new LLNode<E>(head.data);
			head = new LLNode<E>(null);
			tail = new LLNode<E>(null);
			head.next = tail;
			tail.prev = head;
			size--;
			return result.data;
		} else if (index == 0) {                   // removing the node at the head of the list
			LLNode<E> toRemove = head;
			head = head.next;
			head.prev.next = null;
			head.prev = null;
			size--;
			return toRemove.data;
		} else if (index == size - 1) {            // removing the node at the tail of the list
			LLNode<E> result = new LLNode<E>(tail.data);
			tail = tail.prev;
			tail.next = null;
			size--;
			return result.data;
		} else {                                  // removing a node in the middle of the list
			LLNode<E> curr = head;
			int start = 0;
			while (start != index - 1) {         // stop 1 before the index to insert at
				curr = curr.next;
				start++;
			}
			LLNode<E> toRemove = curr.next;
			curr.next = curr.next.next;
			toRemove.prev = null;
			toRemove.next.prev = curr;
			toRemove.next = null;
			size--;
			return toRemove.data;
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) {
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size) {       // setting new element at an index that is out of bounds
			throw new IndexOutOfBoundsException();
		}
		E result;
		if (index == 0) {				    // setting new element at the head of the list
			result = head.data;
			head.data = element;
		} else if (index == size - 1) {    // setting new element at the tail of the list
			result = tail.data;
			tail.data = element;
		} else {                           // setting new element somewhere in the middle
			LLNode<E> curr = head;
			int start = 0;
			while (start != index) { // stop 1 before the index to insert at
				curr = curr.next;
				start++;
			}
		    result = curr.data;
			curr.data = element;
		}
		return result;
	}   
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
