package geometricCalculus.model;

import java.util.Observable;


/**
 * 
 * @author PhilippKroll
 *
 */
public class Matrix extends Observable{
	/**
	 * hashvalue for this instance
	 */
	private int hash;
	/**
	 * represents the dimension of this matrix
	 */
	private int n;
	/**
	 * represents the content of this matrix
	 */
	private double[][] content;

	/**
	 * class geometricCalculus.model.Matrix represents a n x n matrix, the matrix can not be smaller
	 * then 2 in dimension
	 * 
	 * @param n
	 *            - the dimension of this matrix
	 */
	public Matrix(int n) {
		if (n < 1) {
			throw new IllegalArgumentException();
		}
		this.n = n;
		this.content = new double[n][n];
		this.hash = hashCode();
	}

	/**
	 * returns the dimension of this matrix
	 * 
	 * @return - the dimension
	 */
	public int length() {
		return this.n;
	}

	/**
	 * returns the content at the specified position
	 * 
	 * @param i
	 *            - the row, from where to get the content
	 * @param j
	 *            - the column from where to get the content
	 * @return - the content from row i and column j
	 * @throws IllegalArgumentException
	 *             if i and j are not in range of 0 <= (i / j) < matrix.length
	 */
	public double get(int i, int j) {
		if (i >= 0 && i < n && j >= 0 && j < n)
			return content[i][j];
		throw new IllegalArgumentException();
	}

	public int getHash() {
		return hash;
	}

	/**
	 * sets the content at the specified position
	 * 
	 * @param i
	 *            - the row where to set the content
	 * @param j
	 *            - the column where to set the content
	 * @param c
	 *            - the new value
	 * @throws IllegalArgumentException
	 *             if i and j are not in range of 0 <= (i / j) < matrix.length
	 */
	public void set(int i, int j, double c) {
		if (i >= 0 && i < n && j >= 0 && j < n) {
			content[i][j] = c;
		} else {
			throw new IllegalArgumentException();
		}
		if(super.countObservers() > 0)
			super.notifyAll();
	}

	/**
	 * creates a new matrix, which is a submatrix of this instance
	 * 
	 * @param i
	 *            - the startrow
	 * @param j
	 *            - thew startcolumn
	 * @param length
	 *            - the length of the new submatrix
	 * @return the submatrix with i+length x j+length
	 * @throws IllegalArgumentException,
	 *             if i | j < 0 or i | j >= matrix.length() or i | j + length >=
	 *             matrix.length or length <= 0
	 */
	public Matrix getSubmatrix(int i, int j, int length) {
		if (length <= 0 || i < 0 || i >= n || j < 0 || j >= n || i + length > n || j + length > n)
			throw new IllegalArgumentException();
		Matrix m = new Matrix(length);
		for (int k = 0; k < length; k++) {
			for (int k2 = 0; k2 < length; k2++) {
				double c = content[i+k][j+k2];
				m.set(k, k2, c);
			}
		}
		return m;
	}

	/**
	 * discards the row i and the column j and returns a new matrix without row
	 * i und column j
	 * 
	 * @param i
	 *            - the discarded row
	 * @param j
	 *            - the discarded column
	 * @return a new matrix without row i and column j
	 */
	public Matrix discard(int i, int j) {
		/* method has no good performance... */
		if (i < 0 && i >= n && j < 0 && j >= n)
			throw new IllegalArgumentException();
		Matrix m = new Matrix(n - 1);
		int l = content.length - 1;
		double[][] temp = new double[n - 1][n];
		int offset = 0;
		for (int k = 0; k < n - 1; k++) {
			if (k == i) {
				offset++;
			}
			temp[k] = content[k + offset];
		}
		offset = 0;
		double[][] tmp = new double[n - 1][n - 1];
		for (int k = 0; k < n - 1; k++) {
			offset = 0;
			for (int k2 = 0; k2 < n - 1; k2++) {
				if (k2 == j) {
					offset++;
				}
				tmp[k][k2] = temp[k][k2 + offset];
			}
		}
		for (int k = 0; k < l; k++) {
			for (int k2 = 0; k2 < l; k2++) {
				m.set(k, k2, tmp[k][k2]);
			}
		}
		return m;
	}

	/**
	 * the method sets the desired matrix column to the given vector, if the vector and the matrix differ in their dimension,
	 * a IllegalArgumentException is thrown, otherwise the old column content is replaced by the values of the vector
	 * @param column the column, which should be replaced
	 * @param v the vector, which replaces the column
	 */
	public void setColumn(int column,Vector v){
		if(column < 0 || column >= this.length() || length() != v.length()){
			throw new IllegalArgumentException();
		}
		for (int row = 0;row < this.content.length;row++){
			this.content[row][column] = v.get(column);
		}
	}

	/**
	 * clones this matrix
	 * @return a new matrix, which contains the same values as the original one
	 */
	public Matrix clone(){
		Matrix m = new Matrix(n);
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content.length; j++) {
				m.set(i, j, content[i][j]);
			}
		}
		return m;
	}
	
	/**
	 * simple to string method
	 */
	public String toString() {
		String s = n + " x " + n + " geometricCalculus.model.Matrix:\n";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s += content[i][j] + "\t\t";
			}
			s += "\n\n";
		}
		return s;
	}
}
