package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element )
	{
		// TODO: Implement this method
		
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> current = head;
		while(current.next.data != null){
			current = current.next;
		}
		newNode.next = current.next;
		newNode.prev = newNode.next.prev;
		current.next = newNode;
		newNode.next.prev = newNode;
		//	System.out.println(newNode.data+" "+newNode.next.data+" "+newNode.prev.data);
		
		size ++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		
		if(index < 0 || head.next.next == null)
			throw new IndexOutOfBoundsException();
		LLNode<E> current = head.next;
		for(int i = 1; i <= index; i++){
			if(current.next.data == null){
				throw new IndexOutOfBoundsException();
			}else{
				current = current.next;
			}
		}
		return current.data;
		//return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(index < 0)
			throw new IndexOutOfBoundsException();
		if (element == null)
			throw new NullPointerException();
		LLNode<E> current = head;
		for(int i = 1; i <= index; i++){
			if(current.next.data != null){
				current = current.next;
			}else{
				throw new IndexOutOfBoundsException();
			}
		}
		LLNode<E> newNode = new LLNode<E>(element);
		newNode.next = current.next;
		newNode.prev = newNode.next.prev;
		current.next = newNode;
		newNode.next.prev = newNode;		
		size ++;	
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;

	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index < 0)
			throw new IndexOutOfBoundsException();
		LLNode<E> current = head.next;
		LLNode<E> previous = head;
		
		for(int i = 1; i <= index; i++){
			if(current.next.data != null){
				previous = current;
				current = current.next;
			}else{
				throw new IndexOutOfBoundsException();
			}
		}
		previous.next = current.next;
		size --;
		
		return current.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(element == null)
			throw new NullPointerException();
		if(index < 0)
			throw new IndexOutOfBoundsException();
		LLNode<E> current = head.next;
		for(int i = 1; i <= index; i++){
			if(current.next.data != null){
				current = current.next;
			}else{
				throw new IndexOutOfBoundsException();
			}
		}
		E toBeReturned = current.data;
		current.data = element;
		return toBeReturned;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
