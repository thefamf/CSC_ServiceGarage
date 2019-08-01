package edu.ncsu.csc216.garage.model.util;

//import java.util.NoSuchElementException;

/**
 * Interface to describe behaviors of a generic type of iterator over a collection.
 * @author mlee25 Michael Lee
 *
 * @param <E> the type of elements returned by this iterator
 */
public interface SimpleIterator<E> {

	/** Abstract method to check if the iteration has more elements.
	 *  @return true if the iteration has more elements
	 */
	public boolean hasNext();

	/** Abstract method to get next element.
	 * @return the next element in the iteration
	 */
	public E next();
}
