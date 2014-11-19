import java.util.*;

public class Sequence<E>implements Collection<E>{
	protected E capacity[];
	protected int numberOfElementsStored;
	Object object;
	
	public Sequence()
	{
		capacity = (E[]) new Object [10];
	}
	
	public Sequence( int n ) throws IllegalArgumentException
	{		
		if ( n <= 0 ) 
		{
			throw new IllegalArgumentException( n + " Must be larger than 0 ");			
		}
		
		capacity = (E[]) new Object [n];
	}
	
	public int size()
	{
		return numberOfElementsStored;
		
	}
	
	public void append ( E element )
	{
		if ( numberOfElementsStored == capacity.length ) 
	    {
	         E[] temp = (E[]) new Object [2 * numberOfElementsStored]; // allow room for growth
	         System.arraycopy (capacity, 0, temp, 0, numberOfElementsStored);      
	         capacity = temp;
	    } // if ArrayCollection occupies all of myStorage
		capacity [numberOfElementsStored++] = element;
	}

	public E get( int k )
	{
		if ( k < 0 || k >= numberOfElementsStored ) 
		{
			throw new IndexOutOfBoundsException( k + " Must be larger than 0 ");			
		}
		
		return (E)capacity[k];
			
	}
	
	public void set ( int k, E newElement )
	{
		if ( k < 0 || k >= numberOfElementsStored ) 
		{
			throw new IndexOutOfBoundsException( k + " Must be larger than 0 ");			
		}	
		capacity[k] = newElement;
	}
	
	
	public boolean add(E arg0) 
	{
		return false;
	}

	public boolean addAll(Collection<? extends E> arg0) 
	{
		return false;
	}

	public void clear() 
	{
		
	}

	public boolean contains(Object arg0) 
	{
		return false;
	}

	public boolean containsAll(Collection<?> arg0) 
	{
		return false;
	}

	public boolean isEmpty() 
	{
		return false;
	}

	public Iterator<E> iterator() 
	{
		return null;
	}

	public boolean remove(Object arg0) 
	{
		return false;
	}

	public boolean removeAll(Collection<?> arg0) 
	{
		return false;
	}

	public boolean retainAll(Collection<?> arg0) 
	{
		return false;
	}

	public Object[] toArray() 
	{
		return null;
	}

	public <T> T[] toArray(T[] arg0) 
	{
		return null;
	}
}
