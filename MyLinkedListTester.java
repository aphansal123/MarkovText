/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
/* The following test methods:
 		testRemove(), testAddEnd(), testAddAtIndex(), and testSet()
   were implemented by Ameya Phansalkar
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> list3;
	MyLinkedList<Integer> list2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	// this is run before each test to initialize variables and objects
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		list2 = new MyLinkedList<Integer>();
		list3 = new MyLinkedList<Integer>();
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet() {
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		// test longer list contents
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove() {
		// list1 -> 65 -> 21 -> 42 -> null
		int a = list1.remove(0);				// removing node at head of list with more than one element
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		// result: list1 -> 21 -> 42 -> null

		list1.add(0, 8);
		// result: list1 -> 8 -> 21 -> 42 -> null
		list1.add(5);
		// result: list1 -> 8 -> 21 -> 42 -> 5 -> null
		// TODO: Add more tests here
		try {									// removing node at index less than 0
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {									// removing node at index greater than or equal to size of list
			list1.remove(4);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		int b = list1.remove(3);               // removing node at tail of list with more than one element, result: list1 -> 8 -> 21 -> 42 -> null
		assertEquals("Remove: check b is correct ", 5, b);
		assertEquals("Remove: check element at index size - 1 is correct ", (Integer) 42, list1.get(2));
		assertEquals("Remove: check size is correct ", 3, list1.size());
		
		try {									// removing node at index greater than or equal to size of list
			list1.remove(3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		int c = list1.remove(1);                // removing node somewhere in the middle, result: list1 -> 8 -> 42 -> null
		assertEquals("Remove: check b is correct ", 21, c);
		assertEquals("Remove: check element at index 0 is correct ", (Integer) 8, list1.get(0));
		assertEquals("Remove: check element at index 1 is correct ", (Integer) 42, list1.get(1));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {									// removing node at index greater than or equal to size of list
			list1.remove(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		list3.add(15);                         // removing a node from a list with one element
		int d = list3.remove(0);
		assertEquals("Remove: check b is correct ", 21, c);
		assertEquals("Remove: check element at index 0 is correct ", (Integer) 8, list1.get(0));
		
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd() {
        // TODO: implement this test
		try {									// removing node at index greater than or equal to size of list
			list1.add(null);
			fail("Check out of bounds");
		}
		catch (NullPointerException e) {}
		list1.add(68);
		assertEquals("Add at end: check size is correct ", list1.size, 4);
		list2.add(15);                                                                    // adding an element to an empty list
		assertEquals("Add at end: check head is correct ", list2.head.data, list2.tail.data);
	}

	
	/** Test the size of the list */
	@Test
	public void testSize() {
		// TODO: implement this test
		assertEquals("Current size of list is 3 ", list1.size, 3);
	}

	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex() {
        // TODO: implement this test
		try { // indexing less than zero
			list1.add(-1, 3);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {}
		
		try { // indexing >= size of list
			list1.add(4, 22);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try { // indexing >= size of list
			list1.add(2, null);
			fail("Check out of bounds");
		}
		catch (NullPointerException e) {}
		
		list1.add(68);
		list1.add(0, 8); // adding the first (0th) index
		assertEquals("element at index 0 is 8", list1.get(0), (Integer) 8);
		assertEquals("element at index 1 is 65", list1.get(1), (Integer) 65);
		assertEquals("size is now 5", list1.size, 5);
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet() {
	    // TODO: implement this test
		list1.add(68);
	    // list1 -> 65 -> 21 -> 42 -> 68 -> null
		try { // indexing less than zero
			list1.set(-1, 3);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {}
		
		try { // indexing greater than or equal to size of list
			list1.set(4, 3);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {}
		
		list1.set(0, 33); // setting new element at the head of the list, list1 should now be 33 -> 21 -> 42 -> 68 -> null
		assertEquals("element at index 0 is now 33", list1.get(0), (Integer) 33); // issue
		
		list1.set(3, 15); // setting new element at the tail of the list, list1 should now be 33 -> 21 -> 42 -> 15 -> null
		assertEquals("element at index 3 is now 15", list1.get(3), (Integer) 15);
		
		list1.set(2, 18); // setting new element somewhere in the middle, list1 should now be 33 -> 21 -> 18 -> 15 -> null
		assertEquals("element at index 2 is now 18", list1.get(2), (Integer) 18);
	}
	
	
	// TODO: Optionally add more test methods.
	
}
