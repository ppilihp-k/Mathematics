package utils;

/**
 * represents a simple tupel
 * @author PhilippKroll
 *
 * @param <T> type of the first element
 * @param <E> type of the second element
 */
public class Tupel<T,E> {

	public T first;
	public E second;

	public Tupel(){};

	public Tupel(T f,E s){
		first = f;
		second = s;
	}

}
