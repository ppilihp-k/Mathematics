package singlethreadedGeometrie.geometricCalc.model;

public class Segment {

	private Vector origin;
	private Vector directionalVector;
	
	public Segment(Vector origin, Vector directionalVector){
		this.origin = origin;
		this.directionalVector = directionalVector;
	}

	public void setOrigin(Vector origin) {
		this.origin = origin;
	}

	public void setDirectionalVector(Vector directionalVector) {
		this.directionalVector = directionalVector;
	}

	public Vector getOrigin() {
		return origin;
	}

	public Vector getDirectionalVector() {
		return directionalVector;
	}	
	
	public String toString(){
		String s = "segment:\n";
		int n = origin.length();
		for (int i = 0; i < n; i++) {
			s += (origin.get(i) + " + " +" x*"+directionalVector.get(i)+"\n");
		}
		return s;
	}
}
