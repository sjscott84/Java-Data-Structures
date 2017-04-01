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
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		longerList.add(3, 24);
		shortList.add(1, "C");
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(2));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		//for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			//assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		//}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH + 1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		try {
			list1.remove(13);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		
		String b = shortList.remove(0);
		assertEquals("Remove: check a is correct ", "A", b);
		assertEquals("Remove: check element 0 is correct ", "C", shortList.get(0));
		assertEquals("Remove: check size is correct ", 2, shortList.size());
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		try {
			longerList.add(2, null);
			fail("Check element");
		}
		catch (NullPointerException e) {
		}
		
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "C", shortList.get(1));
		assertEquals("Remove: check size is correct ", 3, shortList.size());
		
		assertEquals("Check first", (Integer)0, longerList.get(0));
		assertEquals("Check second", (Integer)1, longerList.get(1));
		assertEquals("Remove: check size is correct ", 11, longerList.size());
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Remove: check size is correct ", 3, shortList.size());
		assertEquals("Remove: check size is correct ", 11, longerList.size());
		assertEquals("Remove: check size is correct ", 0, emptyList.size());
		assertEquals("Remove: check size is correct ", 3, list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		try {
			longerList.add(2, null);
			fail("Check element");
		}
		catch (NullPointerException e) {
		}
		
		try {
			longerList.add(13, 15);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			longerList.add(-1, 15);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		assertEquals("Check longList", (Integer)24, longerList.get(3));
		
		assertEquals("Check shortList", "C", shortList.get(1));
		
		assertEquals("Remove: check size is correct ", 3, shortList.size());
		
		assertEquals("Remove: check size is correct ", 11, longerList.size());
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try{
			longerList.set(3, null);
			fail("Check element");
		}
		catch(NullPointerException e){		
		}
		
		try {
			longerList.set(13, 15);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			longerList.set(-1, 15);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		int a = longerList.set(3, 14);
		assertEquals("Set: check a is correct ", 24, a);
		assertEquals("Set: check element 3 is correct ", (Integer)14, longerList.get(3));
		
		String b = shortList.set(1, "D");
		assertEquals("Set: check b is corret ", "C", b);
		assertEquals("Set: check element 1 is correct ", "D", shortList.get(1));
		
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
