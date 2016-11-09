package singlethreadedGeometrie.geometricCalc.model;

import java.util.Observable;

/**
 * 
 * @author PhilippKroll
 *
 */
public class Vector extends Observable{

	/**
	 * represents the dimension of this vector
	 */
	private int n;
	/**
	 * represents the content of this vector
	 */
	private double[] content;
	
	/**
	 * creates a n-dimensional vector
	 * @param n - the dimension
	 */
	public Vector(int n){
		this.n = n;
		this.content = new double[n];
	}
	
	/**
	 * returns the dimension of this vector
	 * @return the dimension of this vector
	 */
	public int length(){
		return n;
	}

	public void subtractRows(int i1,int i2){
		int n = length();
		if (i1 < 0 || i1 >= n || i2 < 0 || i2 >= n) {
			throw new IllegalArgumentException();
		}
		double c = get(i2);
		set(i2, c - get(i1));
	}

	public void swapRows(int i1,int i2){
		int n = length();
		if (i1 < 0 || i1 >= n || i2 < 0 || i2 >= n) {
			throw new IllegalArgumentException();
		}
		double c = get(i1);
		set(i1, get(i2));
		set(i2, c);
	}

	/**
	 * returns the content from the specified position in this vector
	 * @param i - the row from where the content is returned
	 * @return the content from row i
	 * @throws IllegalArgumentException if not: 0 <= i < vector.length
	 */
	public double get(int i){
		if(i >= 0 && i < n)
			return content[i];
		throw new IllegalArgumentException();
	}
	
	/**
	 * sets the content on the specified position to c
	 * @param i - the row, where to set the content
	 * @param c - the value
	 * @throws IllegalArgumentException if not: 0 <= i < vector.length
	 */
	public void set(int i, double c){
		if(i < 0 && i >= n)
			throw new IllegalArgumentException();
		content[i] = c;
		if(super.countObservers() > 0)
			super.notifyAll();
	}

	/**
	 * computes the length of this vector
	 * @return the length of this vector
	 */
	public double vectorLength(){
		double l = 0;
		for (double d: this.content){
			l += Math.pow(d,2);
		}
		return Math.sqrt(l);
	}
	
	/**
	 * creates a new subvector
	 * @param i - the row where to start the subvector
	 * @param length - the length of the returned subvector
	 * @return a subvector of the vector, where subvector contains dimension[i] to dimension[i+length]
	 * @throws IllegalArgumentException, if i < 0 or i >= dimension[v] or length < 1 or length >= dimention[v]
	 */
	public Vector subVector(int i,int length){
		if(i < 0 || i >= n || length < 1 || length >= n){
			throw new IllegalArgumentException();
		}
		Vector v = new Vector(length);
		for (int k = 0; k < length; k++) {
			v.set(k, content[i+k]);
		}
		return v;
	}
	
	/**
	 * clones this Vector
	 * @return a new Vector, which contains the same values as this original one
	 */
	public Vector clone(){
		Vector v = new Vector(n);
		for (int i = 0; i < content.length; i++) {
			v.set(i, content[i]);
		}
		return v;
	}
	
	/**
	 * simple to string method
	 */
	public String toString(){
		String s = "Vector:\n";
		for (int i = 0; i < n; i++) {
			s += content[i] + "\n";
		}
		return s;
	}
}
