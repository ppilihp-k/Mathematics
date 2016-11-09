package singlethreadedGeometrie.geometricCalc;
import exceptions.InfiniteResultsException;
import exceptions.InternalErrorException;
import exceptions.NoResultException;
import singlethreadedGeometrie.geometricCalc.model.Matrix;
import singlethreadedGeometrie.geometricCalc.model.Plane;
import singlethreadedGeometrie.geometricCalc.model.Vector;
import singlethreadedGeometrie.geometricCalc.model.Segment;

/**
 * 
 * @author PhilippKroll
 *
 */
public class GeometricCalculator {

	public GeometricCalculator() {

	}

	public boolean contains(Plane p, Vector v){
		Vector planeNormal = crossprodukt(p.getDirectionalVectorOne(),p.getDirectionalVectorTwo());
		float t = (float)scalarproduct(subtract(v,p.getOrigin()),planeNormal);
		if(t == 0){
			return true;
		}
		return false;
	}

	/**
	 * computes a normalvector for a given plane
	 * @param p the plane
	 * @return a vector, which is orthogonal to the plane
	 */
	public Vector getPlaneNormal(Plane p){
		return this.crossprodukt(p.getDirectionalVectorOne(),p.getDirectionalVectorTwo());
	}

	/**
	 * computes the determinat of a matrix, the computation is based on the
	 * "Laplace Entwicklungssatz"
	 * 
	 * @param m
	 *            - the matrix
	 * @return the determinat
	 */
	public double determinant(Matrix m) {
		if (m.length() < 2) {
			return m.get(0, 0);
		}
		if (m.length() == 2) {
			return (m.get(0, 0) * m.get(1, 1) - m.get(0, 1) * m.get(1, 0));
		}
		double s = 0;
		int n = m.length();
		for (int i = 0; i < n; i++) {
			s += ((Math.pow(-1, i) * m.get(0, i) * determinant(m.discard(0, i))));
		}
		return s;
	}

	/**
	 * multiplies two n x n matricies -> m1 * m2
	 * 
	 * @param m1
	 *            - the first matrix
	 * @param m2
	 *            - the second matrix
	 * @return a new matrix, which represents the result from m1 * m2
	 * @throws IllegalArgumentException,
	 *             if the dimensions differ
	 */
	public Matrix multiply(Matrix m1, Matrix m2) {
		int nm1 = m1.length();
		int nm2 = m2.length();
		if (nm1 != nm2)
			throw new IllegalArgumentException();
		Matrix m3 = new Matrix(nm1);
		double c = 0;
		for (int row = 0; row < nm1; row++) {
			for (int col = 0; col < nm1; col++) {
				c = 0;
				for (int i = 0; i < nm1; i++) {
					c = m3.get(row, col);
					c += (m1.get(row, i) * m2.get(i, col));
					m3.set(row, col, c);
				}
			}
		}
		return m3;
	}

	/**
	 * multiplies a n x n matrix with a n dimensional vecotr
	 * 
	 * @param m
	 *            - the matrix
	 * @param v
	 *            - the vector
	 * @return a new vector, wich represents the result from m * v
	 * @throws IllegalArgumentException
	 *             if the dimension from m and n are different
	 */
	public Vector multiply(Matrix m, Vector v) {
		int nm = m.length();
		int nv = v.length();
		if (nm != nv)
			throw new IllegalArgumentException();
		Vector newVector = new Vector(nm);
		for (int i = 0; i < nm; i++) {
			double c = 0;
			for (int j = 0; j < nm; j++) {
				c += (m.get(i, j) * v.get(j));
			}
			newVector.set(i, c);
		}
		return newVector;
	}

	/**
	 * scales all elements of the matrix with the value s
	 * 
	 * @param m
	 *            - the matrix
	 * @param s
	 *            - the scalar
	 */
	public void scale(Matrix m, double s) {
		int n = m.length();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				double d = m.get(i, j) * s;
				m.set(i, j, d);
			}
		}
	}

	/**
	 * scales all elements of the vector with the value s
	 * 
	 * @param v
	 *            - the vector
	 * @param s
	 *            - the scalar
	 */
	public void scale(Vector v, double s) {
		int n = v.length();
		for (int i = 0; i < n; i++) {
			double d = v.get(i) * s;
			v.set(i, d);
		}
	}

	/**
	 * computes the length of a vector
	 * 
	 * @param v
	 *            - the vector
	 * @return the length of v
	 */
	public double length(Vector v) {
		double r = 0;
		int n = v.length();
		for (int i = 0; i < n; i++) {
			r += Math.pow(v.get(i), 2);
		}
		return Math.sqrt(r);
	}

	/**
	 * adds up two vectors
	 * 
	 * @param v1
	 *            - the first vector
	 * @param v2
	 *            - the second vector
	 * @return a new vector, witch represents the result from v1 + v2
	 */
	public Vector add(Vector v1, Vector v2) {
		int n1 = v1.length();
		int n2 = v2.length();
		if (n1 != n2) {
			throw new IllegalArgumentException();
		}
		Vector v3 = new Vector(n1);
		for (int i = 0; i < n1; i++) {
			v3.set(i, v1.get(i) + v2.get(i));
		}
		return v3;
	}

	/**
	 * makes the vector v a unitlength vector
	 * @param v
	 */
	public void toUnitLength(Vector v){
		double length = 1/length(v);
		for (int i = 0;i < v.length(); i++){
			v.set(i,v.get(i)*length);
		}
	}

	/**
	 * subtracts two vectors
	 * 
	 * @param v1
	 *            - the first vector
	 * @param v2
	 *            - the second vector
	 * @return a new vector, witch represents the result from v1 - v2
	 */
	public Vector subtract(Vector v1, Vector v2) {
		int n1 = v1.length();
		int n2 = v2.length();
		if (n1 != n2) {
			throw new IllegalArgumentException(v1.toString()+",\n"+v2.toString());
		}
		Vector v3 = new Vector(n1);
		for (int i = 0; i < n1; i++) {
			v3.set(i, v1.get(i) - v2.get(i));
		}
		return v3;
	}

	/**
	 * tests, whether the point, represented by the vector p, lies on the segment s
	 * @param s - the segment
	 * @param p - the point
	 * @return true, if p is located on the segment s, false otherwise
	 * @throws IllegalArgumentException, if the dimensions from s's vectors differ with the dimension of p
	 */
	public boolean isLocatedOn(Vector p, Segment s){
		int ls = s.getOrigin().length();
		int lp = p.length();
		if(ls != lp && ls != 3){
			throw new IllegalArgumentException();
		}
		Vector o = s.getOrigin();
		Vector d = s.getDirectionalVector();
		double[] r = new double[3];
		int i = 0;
		while(i < ls){
			double k1 = o.get(i);
			double k2 = d.get(i);
			double k3 = p.get(i);
			if(k1 < 0){
				if(k2 != 0){
					r[i] = ((k3 + k1) / k2);
				} else {
					r[i] = (k3 + k1);
				}				
			} else {
				if(k2 != 0){
					r[i] = ((k3 - k1) / k2);
				} else {
					r[i] = (k3 - k1);
				}
			}
			i++;
		}
		for (int j = 0; j < 2; j++) {
			if(r[j] != r[j+1] && (o.get(j) != 0 || d.get(j) != 0 || p.get(j) != 0)){
				if(r[j] < 0 || r[j] > 1){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * tests, if the two given segments intersect
	 * @param s1 - the first segment
	 * @param s2 - the second segment
	 * @return if a point exists, where the two segments intersect, it is returned, if there is no such point a NoResultException is thrown
	 * @throws NoResultException - if the two segments don't intersect
	 * @throws InfiniteResultsException - if the two segments are the same
	 * @throws InternalErrorException - if there occured an internal error
	 */
	public Vector intersect(Segment s1, Segment s2) throws NoResultException, InfiniteResultsException, InternalErrorException {
		int n1 = s1.getOrigin().length();
		int n2 = s2.getOrigin().length();
		if (n1 != n2 || n1 > 3 || n2 > 3) {
			throw new IllegalArgumentException();
		}
		if(n1 == 2){
			Vector r = subtract(s1.getOrigin(), s2.getOrigin());
			Matrix m = new Matrix(2);
			Vector d1 = s1.getDirectionalVector();
			Vector d2 = s2.getDirectionalVector();
			m.set(0, 0, d1.get(0));
			m.set(1, 0, d1.get(1));
			m.set(1, 0, -d2.get(0));
			m.set(1, 1, -d2.get(1));
			return solve(m, r);
		}
		if(n1 == 3){
			/*the idea is to create two equasion systems, one for the xy plane and the other one for the xz plane. the what result will returned, depends on the
			 * results of the two dimensional intersection of the two planes*/
			Vector o = subtract(s1.getOrigin(), s2.getOrigin());
			Vector d1 = s1.getDirectionalVector();
			Vector d2 = s2.getDirectionalVector();
			Matrix mA = new Matrix(2);
			Matrix mG = new Matrix(2);
			Vector rA = o.subVector(0, 2);
			Vector rG = o.subVector(1, 2);
			mA.set(0, 0, d1.get(0));
			mA.set(0, 1, -d2.get(0));
			mA.set(1, 0, d1.get(1));
			mA.set(1, 1, -d2.get(1));
			mG.set(0, 0, d1.get(0));
			mG.set(1, 0, d1.get(0));
			mG.set(0, 0, -d2.get(2));
			mG.set(1, 1, -d2.get(2));
			int flag = 0;
			/*test intersection in the xy plane*/
			try {
				Vector r1 = solve(mA, rA);
			} catch (NoResultException e1) {
				flag = flag | 1;
			} catch (InfiniteResultsException e1) {
				flag = flag | 2;
			}
			/*test intersection in the xz plane*/
			try {
				Vector r2 = solve(mG, rG);
			} catch (NoResultException e) {
				flag = flag | 4;
			} catch (InfiniteResultsException e) {
				flag = flag | 8;
			}
			switch (flag) {
			case 0:
				/*if there is a intersection in the xy plane and in the xz plane, the two segments intersect in the returned result
				 * if even one of the results from xy plane or xz plane returns an exception, there is no intersection between segment one and segment two*/
				Vector v = new Vector(3);
				v.set(0, s1.getOrigin().get(0) + rA.get(0)*s1.getDirectionalVector().get(0));
				v.set(1,  s1.getOrigin().get(1) + rA.get(1)*s1.getDirectionalVector().get(1));
				v.set(2,  s1.getOrigin().get(2) + rG.get(1)*s1.getDirectionalVector().get(2));
				if(isLocatedOn(v, s1) && isLocatedOn(v, s2)){
					return v;
				} else {
					throw new NoResultException();
				}
			case 9:
				throw new InternalErrorException();
			case 6:
				throw new InternalErrorException();
			case 10:
				throw new InfiniteResultsException();
			default:
				throw new NoResultException();
			}
		}
		throw new InternalErrorException();
	}

	/**
	 * this method calculates the intersection point between a linesegment and a plane<br>
	 * if there is an intersection, the vertex, where s and p intersect is returned,<br>
	 * else if there is no intersection, a NoResultException is thrown,<br>
	 * else if the linesegment is located inside the plane, InfiniteResultsException is thrown
	 * @param s the linesegment
	 * @param p the plane
	 * @return a instance of a vector, representing the intersectionpoint, if there is one,
	 * @throws NoResultException if there is no intersection between s and p
	 * @throws InfiniteResultsException if the linesegment is located inside the plane
	 * @throws InternalErrorException if the dimensions of the segment and plane differ
	 */
	public Vector intersect(Segment s, Plane p) throws NoResultException,InfiniteResultsException,InternalErrorException {
		if (s.getOrigin().length() != p.getOrigin().length()){
			InternalErrorException i = new InternalErrorException();
			i.getMessage().concat(" the linesegment has other dimensions then the plane!");
			throw i;
		}
		Vector a = s.getOrigin();
		Vector b = s.getDirectionalVector();
		Vector e = p.getOrigin();
		Vector n;
		try{
			n = crossprodukt(p.getDirectionalVectorOne(),p.getDirectionalVectorTwo());
		} catch (IllegalArgumentException i){
			InternalErrorException ie = new InternalErrorException();
			ie.getCause().initCause(i);
			throw ie;
		}

		toUnitLength(n);

		float leftSide = (float)(n.get(0)*b.get(0) + n.get(1)*b.get(1) + n.get(2)*b.get(2));
		float rightSide = (float)((-1)*n.get(0)*a.get(0) + n.get(0)*e.get(0) - n.get(1)*a.get(1) + n.get(1)*e.get(1) - n.get(2)*a.get(2) + n.get(2)*e.get(2));

		if(leftSide == rightSide && leftSide == 0){
			/*s is located completely inside the plane p*/
			throw new InfiniteResultsException();
		} else if(rightSide == 0 && leftSide != 0){
			/*no result, the segment s don't touches the plane p*/
			throw new NoResultException();
		} else {
			/*the segment s intersects on a+(rightSide/leftSide)*b with the plane p*/
			Vector v = new Vector(3);
			float t = rightSide/leftSide;
			v.set(0,a.get(0)+t*b.get(0));
			v.set(1,a.get(1)+t*b.get(1));
			v.set(2,a.get(2)+t*b.get(2));
			return v;
		}
	}

	/**
	 * tests, if the given vector is a unitvector with length = 1
	 * @param v - the vector to test
	 * @return true, if v is a unitvector, false otherwise
	 */
	public boolean isUnitVector(Vector v) {
		return length(v) == 1 ? true : false;
	}

	/**
	 * two vectors are equal, if the have the same length, direction and orientation
	 * @param v1 - the first vector
	 * @param v2 - the second vector
	 * @return true, if the two given vectors are the same, false otherwise
	 * @throws IllegalArgumentException, if the two vectors differ in dimension
	 */
	public boolean equals(Vector v1, Vector v2) {
		int n1 = v1.length();
		int n2 = v2.length();
		if(n1 != n2){
			throw new IllegalArgumentException();
		}
		if(length(v1) != length(v2)){
			return false;
		}
		if(angle(v1, v2) == 0){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks if the two given vectors are parallel
	 * @param v1 - fist vector
	 * @param v2 - second vector
	 * @return - true, if the vectors are parallel, false otherwise
	 * @throws InternalErrorException
	 * IllegalArgumentException, if the vectors differ in their dimension
	 */
	public boolean parallel(Vector v1, Vector v2) throws InternalErrorException {
		int n1 = v1.length();
		int n2 = v2.length();
		if(n1 != n2 || n1 < 0 || n1 > 3 || n2 < 0 || n2 > 3){
			throw new IllegalArgumentException();
		}
		/*check if the vectors are linear dependend -> if the first vector is a multiple of the second ,and vice verser, there is just to checl the orientation*/
		if(!linearDependend(v1,v2)){
			return false;
		}
		return true;
	}

	/**
	 * tests, if the given vectors are linear dependend
	 * @param v - the vectors whitch should be tested
	 * @return true, if the vectors are linear dependend, false otherwise
	 * @throws InternalErrorException, if the function is not implemented yet 
	 * @throws IllegalArgumentException, if the vectors differ in their dimensions
	 */
	public boolean linearDependend(Vector... v) throws InternalErrorException {
		for (int i = 0; i < v.length-1; i++) {
			if(v[i].length() != v[i+1].length()){
				throw new IllegalArgumentException();
			}
		}
		int n = v[0].length();
		if(n + 1 == v.length + 1){
			return true;
		}
		switch (v.length) {
		case 1:
			return true;
		case 2:
			/*in R^2, if there are 2 different vectors, they are linear independend, if the determinant, of a eqs, witch contains the vectors, equals zero*/
			if(v[0].length() == 2){
				Matrix m = new Matrix(2);
				for (int i = 0; i < n; i++) {
					m.set(i, 0, v[0].get(i));
					m.set(i, 1, v[1].get(i));
				}
				if(determinant(m) == 0){
					return true;
				} else {
					return false;
				}
			} else if(v[0].length() == 3){
				/*if the dimension of two vectors is 3, the vectors are linear dependend, if the first vector multiplied with a scalar equals the second one and vice verser*/
				try {
					Vector r = solve(v[0],v[1]);
					for (int i = 0; i < r.length()-1; i++) {
						if(r.get(i) != r.get(i+1)){
							return false;
						}
					}
					return true;
				} catch (NoResultException e) {
					return false;
				}
			}
		case 3:
			if(v[0].length() == 3){
				/*in R^3, if there are 3 different vectors, they are linear independend, if the determinant, of a eqs, witch contains the vectors, equals zero*/
				Matrix m = new Matrix(3);
				for (int i = 0; i < n; i++) {
					m.set(i, 0, v[0].get(i));
					m.set(i, 1, v[1].get(i));
					m.set(i, 2, v[2].get(i));
				}
				if(determinant(m) == 0){
					return true;
				} else {
					return false;
				}
			} else {
				throw new InternalErrorException();
			}
		default:
			return false;
		}
	}

	/**
	 * translates the vector toTranslate with the values from t like: i(toTranslate) = i(toTranslate) * i(t)
	 * @param toTranslate the vector, that is translated
	 * @param t the vector, containing the translationvalues
	 */
	public void translate(Vector toTranslate, Vector t){
		for (int i = 0; i < toTranslate.length(); i++){
			toTranslate.set(i,toTranslate.get(i) * t.get(i));
		}
	}

	/**
	 * returns the angle between this to vector
	 * @param v1 - the first vector
	 * @param v2 - the second vector
	 * @return the angle in between the two vectors
	 * @throws IllegalArgumentException, if the dimensions of v1 and v2 differ
	 */
	public double angle(Vector v1, Vector v2){
		int n1 = v1.length();
		int n2 = v2.length();
		if(n1 != n2 || n1 > 3 || n2 > 3){
			throw new IllegalArgumentException();
		}
		double scp = scalarproduct(v1, v2);
		double lp = length(v1) * length(v2);
		if(scp != 0){
			return Math.acos(scp/lp);
		}
		return 0;
	}
	
	/**
	 * returns the scalarproduct of the two given vectors
	 * @param v1 - the first vector
	 * @param v2 - the second vector
	 * @return the scalarprodukt of the two given vectors
	 * @throws IllegalArgumentException, if the two vectors differ in dimension
	 */
	public double scalarproduct(Vector v1, Vector v2){
		int n1 = v1.length();
		int n2 = v2.length();
		if(n1 != n2 || n1 > 3 || n2 > 3){
			throw new IllegalArgumentException();
		}
		double s = 0;
		for (int i = 0; i < n1; i++) {
			s += (v1.get(i) * v2.get(i));
		}
		return s;
	}
	
	/**
	 * computes the crossprodukt from v1 x v2 only in R^3
	 * @param v1 - the first vector
	 * @param v2 - the second vector
	 * @return the vector, whitch is orthogonal to v1 and v2
	 */
	public Vector crossprodukt(Vector v1,Vector v2){
		int n1 = v1.length();
		int n2 = v2.length();
		if(n1 != n2  || n1 != 3 || n2 != 3){
			throw new IllegalArgumentException();
		}
		Vector cp = new Vector(n1);
		cp.set(0, (v1.get(1) * v2.get(2)) - (v1.get(2) * v2.get(1)));
		cp.set(1, (v1.get(2) * v2.get(0)) - (v1.get(0) * v2.get(2)));
		cp.set(2, (v1.get(0) * v2.get(1)) - (v1.get(1) * v2.get(0)));		
		return cp;
	}
	
	/**
	 * returns r devided by x
	 * @param x
	 * @param r
	 * @return 
	 * @throws NoResultException, if x equals zero
	 */
	public double solve(double x, double r) throws NoResultException{
		if(x != 0)
			return r / x;
		throw new NoResultException();
	}
	
	/**
	 * solves the 1 x (n + 1) equasionsystem, represented by v and r, where r is the resultvector and v contains the variable values
	 * @param v - the left hand side of the eqs
	 * @param r - the right hand side of the eqs
	 * @return the result of each row of the eqs
	 * @throws NoResultException, if the eqs contains an untrue element like 0 = 1
	 * @throws IllegalArgumentException, if the two vectors differ in their dimensions
	 */
	public Vector solve(Vector v, Vector r) throws NoResultException{
		int nv = v.length();
		int nr = r.length();
		if(nv != nr){
			throw new IllegalArgumentException();
		}
		Vector res = new Vector(nv);
		for (int i = 0; i < nv; i++) {
			double t = v.get(i);
			if(t != 0){
				res.set(i, r.get(i) / t);
			} else {
				if(r.get(i) != 0){
					throw new NoResultException();
				}
			}
		}
		return res;
	}
	
	/**
	 * the methos solves the system of equasion, given as a coefficientmatrix and resultvector
	 * @param m - the matrix, whitch represents the coefficientmatrix
	 * @param v - the resultvector, the extension of the coeficientmatrix
	 * @return an vector, whitch contains the results 
	 * @throws NoResultException, if the lgs has no result
	 * @throws InfiniteResultsException, if the lgs has infinite results
	 * @throws IllegalArgumentException, if the given matrix and vector differ in dimension
	 */
	public Vector solve(Matrix m, Vector v) throws NoResultException, InfiniteResultsException {
		int nm = m.length();
		int nv = v.length();
		if (nm != nv) {
			throw new IllegalArgumentException();
		}

		/*
		 * if the determinante equals zero, there is an infinite amount of
		 * solutions or no result to this lgs, in this case we can stop here
		 */
		if (determinant(m) == 0) {
			int r = rank(m);
			/*given the fact, that this solves only systems of equasion, where m is quadratic there is only
			 * the two following ways to interprete the determinantes value*/
			if (r < nm) {
				throw new NoResultException();
			} else {
				throw new InfiniteResultsException();
			}
		}

		/*
		 * if the equasionsystem has only one element left,the methode has to
		 * stop the recursion here
		 */
		if (nm == 1) {
			/*
			 * if there is a true statement, there is an infinite amount of
			 * solutions again. not sure, if this is needed -> checked the
			 * determinante earlier
			 */
			if (m.get(0, 0) == 0 && m.get(0, 0) != v.get(0)) {
				throw new NoResultException();
			}
			/*
			 * if there is a wrong statement, there is no result for this
			 * equasionsystem
			 */
			if (m.get(0, 0) == v.get(0) && v.get(0) == 0) {
				throw new InfiniteResultsException();
			}
			/* otherwise return the solution */
			Vector result = new Vector(1);
			result.set(0, v.get(0) / m.get(0, 0));
			return result;
		}

		/*
		 * count zeros, if the first row has one, swap it until the content of
		 * the first element is not equal to zero
		 */
		for (int i = 1; i < nm; i++) {
			if (m.get(0, 0) == 0) {
				swapRows(0, i, m);
				swapRows(0, i, v);
			}
		}

		/* try to subtract rows so, that in the first column are only zeros */
		for (int i = 1; i < nm; i++) {
			double c1 = m.get(0, 0);
			double c2 = m.get(i, 0);
			if (c2 != 0 && c1 != 0) {
				for (int j = 0; j < nm; j++) {
					m.set(i, j, m.get(i, j) * c1);
					m.set(0, j, m.get(0, j) * c2);
				}
				v.set(i, v.get(i) * c1);
				v.set(0, v.get(0) * c2);
				subtractRows(0, i, m);
				subtractRows(0, i, v);
				for (int j = 0; j < nm; j++) {
					if (c2 != 0) {
						m.set(0, j, m.get(0, j) / c2);
					}
				}
				if (c2 != 0) {
					v.set(0, v.get(0) / c2);
				}
			}
		}
		/* create the new and smaller instance of this problem */
		Matrix mk = m.getSubmatrix(1, 1, nm - 1);
		Vector vk = v.subVector(1, nm - 1);
		Vector littleResult = null;
		try {
			littleResult = solve(mk, vk);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* compute the current problem, with help of the returened values */
		Vector result = new Vector(nm);
		int i = 0;
		while (i < littleResult.length()) {
			result.set(i + 1, littleResult.get(i));
			i++;
		}
		/* compute the first value of the actual row */
		i = 1;
		while (i < nm) {
			double c = m.get(0, i);
			m.set(0, i, c * result.get(i));
			if(m.get(0, i) < 0){
				v.set(0, v.get(0) - m.get(0, i));
			} else {
				v.set(0, v.get(0) - m.get(0, i));
			}
			i++;
		}
		/* set the last computed value in the resultvector */
		if (m.get(0, 0) != 0) {
			result.set(0, v.get(0) / m.get(0, 0));
		}
		return result;
	}

	/**
	 * swaps the two rows i1 and i2 in the given vector v
	 * @param i1 - first row
	 * @param i2 - second row
	 * @param v - the given vector
	 */
	private void swapRows(int i1, int i2, Vector v) {
		int n = v.length();
		if (i1 < 0 || i1 >= n || i2 < 0 || i2 >= n) {
			throw new IllegalArgumentException();
		}
		double c = v.get(i1);
		v.set(i1, v.get(i2));
		v.set(i2, c);
	}

	/**
	 * the method subtracts two rows of a given vector. subtracts i2 - i1 and
	 * stores the result in row i2
	 * 
	 * @param i1
	 *            - first row
	 * @param i2
	 *            - second row
	 * @param v
	 *            - the given vector
	 * @throws IllegalArgumentException,
	 *             if the vector v doesn't contains i1 or i2
	 */
	private void subtractRows(int i1, int i2, Vector v) {
		int n = v.length();
		if (i1 < 0 || i1 >= n || i2 < 0 || i2 >= n) {
			throw new IllegalArgumentException();
		}
		double c = v.get(i2);
		v.set(i2, c - v.get(i1));
	}

	/**
	 * the method subtracts the row i1 from the row i2 in the given matrix m,
	 * after this mathod has finished, row i1 stays as before and row i2
	 * contains the solution from i2 - i1
	 * 
	 * @param i1
	 *            - the row witch will be subtracted
	 * @param i2
	 *            - the row witch will be subtracted from
	 * @param m
	 *            - the matrix
	 * @throws IllegalArgumentException,
	 *             if the vector v doesn't contains i1 or i2
	 */
	private void subtractRows(int i1, int i2, Matrix m) {
		int n = m.length();
		if (i1 < 0 || i1 >= n || i2 < 0 || i2 >= n) {
			throw new IllegalArgumentException();
		}
		for (int k = 0; k < n; k++) {
			double c = m.get(i2, k);
			m.set(i2, k, c - m.get(i1, k));
		}
	}

	/**
	 * the method swaps the row i1 and i2 in the given matrix. keep in mind,
	 * that the matrix itselve is manipulated, no new matrix with switched rows
	 * is returned.
	 * 
	 * @param i1
	 *            - one row to switch
	 * @param i2
	 *            - the other row to switch
	 * @param m
	 *            - the matrix, where to switch rows
	 * @throws IllegalArgumentException,
	 *             if the matrix doesn't contains the rows i1 or i2
	 */
	private void swapRows(int i1, int i2, Matrix m) {
		int n = m.length();
		if (i1 < 0 || i1 >= n || i2 < 0 || i2 >= n) {
			throw new IllegalArgumentException();
		}
		for (int k = 0; k < n; k++) {
			double c = m.get(i1, k);
			m.set(i1, k, m.get(i2, k));
			m.set(i2, k, c);
		}
	}

	/**
	 * this method should compute the rank of the given matrix
	 * 
	 * @param m
	 *            - the matrix
	 * @return the rank of the matrix
	 */
	public int rank(Matrix m) {
		if (m.length() == 1) {
			if (m.get(0, 0) != 0) {
				return 1;
			} else {
				return 0;
			}
		}
		int n = m.length();
		for (int i = 1; i < n; i++) {
			if (m.get(0, 0) == 0) {
				swapRows(0, i, m);
			} else {
				break;
			}
		}
		for (int i = 1; i < n; i++) {
			double c1 = m.get(0, 0);
			double c2 = m.get(i, 0);
			if (c1 < 0 && c2 < 0 || c1 < 0 && c2 > 0) {
				c1 *= (-1);
			}
			for (int j = 0; j < n; j++) {
				if (c1 != 0 && c2 != 0) {
					m.set(0, j, m.get(0, j) * c2);
					m.set(i, j, m.get(i, j) * c1);
				}
			}
			if (m.get(i, 0) != 0) {
				subtractRows(0, i, m);
			}
			for (int j = 0; j < n; j++) {
				if (c2 != 0) {
					m.set(0, j, m.get(0, j) / c2);
				} else {
					break;
				}
			}
		}
		Matrix mk = m.getSubmatrix(1, 1, n - 1);
		for (int i = 0; i < n; i++) {
			if (m.get(0, i) != 0) {
				return rank(mk) + 1;
			}
		}
		return rank(mk);
	}
}


