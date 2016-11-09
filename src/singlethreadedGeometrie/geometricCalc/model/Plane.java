package singlethreadedGeometrie.geometricCalc.model;


/**
 * 
 * @author PhilippKroll
 * this class represents a plane. if this plane should have infinite size, the min/max direction params should be set to Float.POSITIVE_INFINITY otherwhise
 * the plane desrcibes a subplan of the infinite one around the origin. the min values dertermine the length in negative direction and the max ones in
 * positive direction
 */
public class Plane {
	/**
	 * represents the origin of this plane
	 */
	private Vector origin;
	/**
	 * first directional vector
	 */
	private Vector directionalVectorOne;
	/**
	 * second directional vector
	 */
	private Vector directionalVectorTwo;

	/**
	 * represents a plan in a 3-dimensional room
	 * @param origin
	 * @param directionalVectorOne
	 * @param directionalVectorTwo
	 */
	public Plane(Vector origin, Vector directionalVectorOne, Vector directionalVectorTwo){
		this.origin = origin;
		this.directionalVectorOne = directionalVectorOne;
		this.directionalVectorTwo = directionalVectorTwo;
	}

	/**
	 * sets the original vector to the new value. this changes the appearance of this plane!
	 * @param origin the new origin of this plane
	 */
	public void setOrigin(Vector origin) {
		this.origin = origin;
	}

	/**
	 * sets the first directional vector to the new value. this changes the appearance of this plane!
	 * @param directionalVectorOne
	 */
	public void setDirectionalVectorOne(Vector directionalVectorOne) {
		this.directionalVectorOne = directionalVectorOne;
	}

	/**
	 * sets the second directional vector to the new value. this changes the appearance of this plane!
	 * @param directionalVectorTwo
	 */
	public void setDirectionalVectorTwo(Vector directionalVectorTwo) {
		this.directionalVectorTwo = directionalVectorTwo;
	}

	/**
	 * returns the origin of this plane
	 * @return the origin
	 */
	public Vector getOrigin() {
		return origin;
	}

	/**
	 * returns the first directional vector
	 * @return first directional vector
	 */
	public Vector getDirectionalVectorOne() {
		return directionalVectorOne;
	}

	/**
	 * returns the second directional vector
	 * @return the second directional vector
	 */
	public Vector getDirectionalVectorTwo() {
		return directionalVectorTwo;
	}

	public String toString(){
		String s = "Plane:\n";
		s += "origin:\n"+this.origin+"\nDirection 1:"+directionalVectorOne+"\nDirection 2:"+directionalVectorTwo+"\n";
		return s;
	}
}
