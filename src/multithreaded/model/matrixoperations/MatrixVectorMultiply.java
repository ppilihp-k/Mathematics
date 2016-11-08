package multithreaded.model.matrixoperations;

import geometricCalculus.model.Matrix;
import geometricCalculus.model.Vector;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class MatrixVectorMultiply {
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
}
