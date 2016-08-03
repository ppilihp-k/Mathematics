
/**
 * 
 * @author PhilippKroll
 *
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

	public void setOrigin(Vector origin) {
		this.origin = origin;
	}

	public void setDirectionalVectorOne(Vector directionalVectorOne) {
		this.directionalVectorOne = directionalVectorOne;
	}

	public void setDirectionalVectorTwo(Vector directionalVectorTwo) {
		this.directionalVectorTwo = directionalVectorTwo;
	}

	public Vector getOrigin() {
		return origin;
	}

	public Vector getDirectionalVectorOne() {
		return directionalVectorOne;
	}

	public Vector getDirectionalVectorTwo() {
		return directionalVectorTwo;
	}
}
