package geometricCalculus.model;

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
	 * if the plane describes a subplane, the direction values determinte the size of the subplane
	 */
	private float minDirectionOne;
	/**
	 * if the plane describes a subplane, the direction values determinte the size of the subplane
	 */
	private float maxDirectionOne;
	/**
	 * if the plane describes a subplane, the direction values determinte the size of the subplane
	 */
	private float minDirectionTwo;
	/**
	 * if the plane describes a subplane, the direction values determinte the size of the subplane
	 */
	private float maxDirectionTwo;

	/**
	 * represents a plan in a 3-dimensional room
	 * @param origin
	 * @param directionalVectorOne
	 * @param directionalVectorTwo
	 */
	public Plane(Vector origin, Vector directionalVectorOne, Vector directionalVectorTwo,float minDirectionOne,float maxDirectionOne,float minDirectionTwo,float maxDirectionTwo){
		this.origin = origin;
		this.directionalVectorOne = directionalVectorOne;
		this.directionalVectorTwo = directionalVectorTwo;
		this.minDirectionOne = minDirectionOne;
		this.maxDirectionOne = maxDirectionOne;
		this.minDirectionTwo = minDirectionTwo;
		this.maxDirectionTwo = maxDirectionTwo;
	}

	/**
	 * returns the minimum length in direction one
	 * @return the minimum length
	 */
	public float getMinDirectionOne() {
		return minDirectionOne;
	}

	/**
	 * sets the new minimum length in first direction.
	 * @param minDirectionOne
	 */
	public void setMinDirectionOne(float minDirectionOne) {
		this.minDirectionOne = minDirectionOne;
	}

	/**
	 * returns the maximum size in direction one
	 * @return the actual max length in directionOne
	 */
	public float getMaxDirectionOne() {
		return maxDirectionOne;
	}

	/**
	 * sets a new max length in direction one
	 * @param maxDirectionOne
	 */
	public void setMaxDirectionOne(float maxDirectionOne) {
		this.maxDirectionOne = maxDirectionOne;
	}
	/**
	 * returns the minimum length in direction two
	 * @return the minimum length
	 */
	public float getMinDirectionTwo() {
		return minDirectionTwo;
	}
	/**
	 * sets the new minimum length in second direction.
	 * @param minDirectionTwo
	 */
	public void setMinDirectionTwo(float minDirectionTwo) {
		this.minDirectionTwo = minDirectionTwo;
	}
	/**
	 * returns the maximum size in direction two
	 * @return the actual max length in direction two
	 */
	public float getMaxDirectionTwo() {
		return maxDirectionTwo;
	}
	/**
	 * sets a new max length in direction two
	 * @param maxDirectionTwo
	 */
	public void setMaxDirectionTwo(float maxDirectionTwo) {
		this.maxDirectionTwo = maxDirectionTwo;
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
}
